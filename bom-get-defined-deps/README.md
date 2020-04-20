# List defined dependencies in BOM

## Dependency
```xml
    <dependencies>
        <dependency>
            <groupId>org.apache.maven</groupId>
            <artifactId>maven-model</artifactId>
            <version>3.6.3</version>
        </dependency>
    </dependencies>
```

## Code
```java
package cz.rsvoboda;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;

public class Main {
    public static void main(String... args) throws IOException, XmlPullParserException {
        MavenXpp3Reader reader = new MavenXpp3Reader();
        Model model = reader.read(new FileReader(
                "/Users/rsvoboda/Downloads/maven-repository/com/redhat/quarkus/quarkus-product-bom/" +
                        "1.3.2.Final-redhat-00001/quarkus-product-bom-1.3.2.Final-redhat-00001.pom"));

        model.getDependencyManagement().getDependencies()
                .stream()
                .filter(dependency -> dependency.getGroupId().equals("io.quarkus"))
                .sorted(Comparator.comparing(Dependency::getArtifactId))
                .forEach(dependency -> {
                    System.out.println(dependency.getGroupId() + ":" +
                            dependency.getArtifactId() + ":" +
                            dependency.getVersion()
                    );
                });
    }
}
```