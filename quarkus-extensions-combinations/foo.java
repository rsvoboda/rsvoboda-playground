import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;

public class foo {

    static String[] extensions = new String[] {
        //    "agroal",
        //    "artemis-jms",
        //    "config-yaml",
        //    "core",
           "elytron-security",
        //    "gizmo",
           "hibernate-orm",
           "hibernate-orm-panache",
           "hibernate-validator",
           "jackson",
           "jaxb",
           "jdbc-mariadb",
           "jdbc-mssql",
           "jdbc-mysql",
           "jdbc-postgresql",
           "jsonb",
           "jsonp",
           "kafka-client",
           "keycloak-authorization",
           "kubernetes",
           "narayana-jta",
           "oidc",
           "quartz",
        //    "reactive-pg-client",
           "rest-client",
           "resteasy",
           "resteasy-jackson",
           "resteasy-jsonb",
           "resteasy-jaxb",
           "scheduler",
           "smallrye-context-propagation",
           "smallrye-fault-tolerance",
           "smallrye-health",
           "smallrye-jwt",
           "smallrye-metrics",
           "smallrye-openapi",
           "smallrye-reactive-messaging",
           "smallrye-reactive-messaging-amqp",
           "smallrye-reactive-messaging-kafka",
           "smallrye-reactive-streams-operators",
           "spring-data-jpa",
           "spring-di",
           "spring-web",
           "spring-security",
           "undertow",
           "undertow-websockets",
            "vertx"
    };

    static String applicationPropertiesContent = "# Configuration file\n" +
        "# key = value\n" +
        "\n" +
        "# MP JWT\n" +
        "mp.jwt.verify.publickey=asd\n" +
        "mp.jwt.verify.publickey.location=/foo/bar\n" +
        "\n" +
        "# Flyway minimal config properties\n" +
        "quarkus.flyway.migrate-at-start=false\n" +
        "\n" +
        "# Flyway needs to configure a datasource\n" +
        "quarkus.datasource.url: jdbc:postgresql://localhost:5432/mydatabase\n" +
        "quarkus.datasource.driver: org.postgresql.Driver\n" +
        "quarkus.datasource.username: sarah\n" +
        "quarkus.datasource.password: connor\n" +
        "\n" +
        "# Kafka\n" +
        "quarkus.kafka-streams.application-id=002-quarkus-all-extensions\n" +
        "quarkus.kafka-streams.topics=kafka-topic\n" +
        "\n" +
        "quarkus.artemis.url=foo\n" +
        "\n" +
        "quarkus.oauth2.client-id=client_id\n" +
        "quarkus.oauth2.client-secret=secret\n" +
        "quarkus.oauth2.introspection-url=http://oauth-server/introspect";

    static Map<String, Integer> generateProjectStatuses = new TreeMap<>();
    static Map<String, Integer> compileProjectStatuses = new TreeMap<>();
    static Map<String, Integer> testProjectStatuses = new TreeMap<>();

    public static void main(String[] args) {
        int arrayLength = extensions.length;
        // for (int i = 1; i <= arrayLength; i++) {
        for (int i = 2; i <= 3; i++) {
                combinations(arrayLength, i);
            System.out.println("  ===  ===  ===  ===  ===  ===  ===");
        }

        for (Map.Entry<String, Integer> entry : generateProjectStatuses.entrySet()) {
            System.out.println(entry.getKey() + "\t\t : " + entry.getValue().toString() +
                    "\t\t : " + compileProjectStatuses.get(entry.getKey()) +
                    "\t\t : " + testProjectStatuses.get(entry.getKey())
                    );
        }
    }

    public static void combinations(int n, int r) {
        int[] a = new int[r];
        for (int i = 0; i < r; i++) {   // initialize the first combination
            a[i] = i;
        }
        int i = r - 1;                  // Index to keep track of maximum unsaturated element in array
                                        // a[0] can only be n-r+1 exactly once - termination condition
        while (a[0] < n - r + 1) {
                                        // If outer elements are saturated
                                        // keep decrementing i till you find unsaturated element
            while (i > 0 && a[i] == n - r + i) {
                i--;
            }
            processCombination(a);

            a[i]++;
            while (i < r - 1) {         // Reset each outer element to prev element + 1
                a[i + 1] = a[i] + 1;
                i++;
            }
        }
    }

    private static void processCombination(int[] combination) {
        StringBuilder artifactId = new StringBuilder("fooBar");
        StringBuilder extensionsList = new StringBuilder();
        for (int i = 0; i < combination.length ; i++) {
            extensionsList.append(extensions[combination[i]]);
            extensionsList.append(",");

            artifactId.append("_");
            artifactId.append(combination[i]);
        }

        String generateProjectCommand = "mvn io.quarkus:quarkus-maven-plugin:999-SNAPSHOT:create " +
                "-DprojectGroupId=io.quarkus.qe -DprojectArtifactId=" + artifactId.toString() +
                " -DprojectVersion=1.0.0-SNAPSHOT -DplatformArtifactId=quarkus-bom -DclassName=\"io.quarkus.qe.MyResource\"" +
                " -Dextensions=\"" + extensionsList.toString() + "\"";

        String compileProjectCommand = "mvn package -DskipTests -DskipITs -f " + artifactId.toString() + "/pom.xml";
        String testProjectCommand = "mvn verify -f " + artifactId.toString() + "/pom.xml";
        String cleanProjectCommand = "mvn clean -f " + artifactId.toString() + "/pom.xml";

        executeCommandForArtifact(artifactId, generateProjectCommand, generateProjectStatuses);
        writeApplicationProperties(Paths.get(artifactId.toString() + "/src/main/resources/application.properties"));
        executeCommandForArtifact(artifactId, compileProjectCommand, compileProjectStatuses);
        executeCommandForArtifact(artifactId, testProjectCommand, testProjectStatuses);
        executeCommand(cleanProjectCommand);
    }

    private static void writeApplicationProperties(Path path) {
        try {
            Files.write(path, applicationPropertiesContent.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void executeCommandForArtifact(StringBuilder artifactId, String command, Map<String, Integer> commandStatuses) {
        System.out.println(command);
        try {
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command("bash", "-c", command);
            Process mvnGenerateProcess = processBuilder.start();

//            BufferedReader reader = new BufferedReader(new InputStreamReader(mvnGenerateProcess.getInputStream()));
//            String line;
//            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
//            }

            int exitCode = mvnGenerateProcess.waitFor();
            commandStatuses.put(artifactId.toString(), exitCode);
            System.out.println("\tExited with error code: " + exitCode);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    private static void executeCommand(String command) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("bash", "-c", command);
            Process process = processBuilder.start();
            int exitCode = process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
