./gradlew assemble
native-image --no-server \
             --allow-incomplete-classpath -cp build/libs/micronaut-hello-world-0.1-all.jar \
             -H:ReflectionConfigurationFiles=reflect.json \
             -H:EnableURLProtocols=http \
             -H:IncludeResources="logback.xml|application.yml|META-INF/services/*.*" \
             -H:Name=micronaut-hello-world \
             -H:Class=micronaut.hello.world.Application \
             -H:+ReportUnsupportedElementsAtRuntime \
             -H:+AllowVMInspection \
             --rerun-class-initialization-at-runtime='sun.security.jca.JCAUtil$CachedSecureRandomHolder,javax.net.ssl.SSLContext' \
             --delay-class-initialization-to-runtime=io.netty.handler.codec.http.HttpObjectEncoder,io.netty.handler.codec.http.websocketx.WebSocket00FrameEncoder,io.netty.handler.ssl.util.ThreadLocalInsecureRandom,com.sun.jndi.dns.DnsClient
             
             # -H:-UseServiceLoaderFeature \
