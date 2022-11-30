# HTTPS & Java 

## Trust the certificate
Once you have server in production it will be configured to run on https to prevent simple network tapping - e.g. for username / password. If the server has certificate signed by public CA you will only need to change `http://` to `https://`. If the server has self-signed certificate or certificate signed by internal CA you will need to change `http://` to `https://` and instrument java to trust that server. To do so you need to get the certificate and import it to JDK truststore. Another option is to get the certificate, import it to separate truststore and instruct java to use that truststore. Second option is better for use-cases when users can't patch JDK installation.

```bash
echo -n | openssl s_client -connect server:443 2>/dev/null | sed -ne '/BEGIN CERTIFICATE/,/END CERTIFICATE/p' > server.crt
echo "yes" | keytool -import -keystore truststore.jks -alias server -file server.crt -storepass fooBar

## add -Djavax.net.ssl.trustStore=/path/to/truststore.jks -Djavax.net.ssl.trustStorePassword=fooBar to executed java / mvn command or to process "java opts"
```

## Change password of jks keystore
```bash
# get the alias name
keytool -list -v -keystore server-keystore.jks -storepass password

# change the passwords
keytool -storepasswd -new my-new-password -keystore server-keystore.jks -storepass password
keytool -keypasswd -alias server -keypass password -new my-new-password -keystore server-keystore.jks -storepass my-new-password

```