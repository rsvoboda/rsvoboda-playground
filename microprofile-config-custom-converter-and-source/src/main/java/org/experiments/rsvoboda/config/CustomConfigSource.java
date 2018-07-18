package org.experiments.rsvoboda.config;

import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;
import javax.json.Json;
import java.math.BigDecimal;
import java.util.*;
import java.io.StringReader;

import java.io.BufferedReader;
import java.io.FileReader;

import org.eclipse.microprofile.config.spi.ConfigSource;

public class CustomConfigSource implements ConfigSource {

    //  String fileLocation = System.getProperty("user.dir").split("target")[0]
//      + "resources/CustomConfigSource.json";
    String fileLocation = "/Users/rsvoboda/tmp/microprofile-config-example/resources/CustomConfigSource.json";

    @Override
    public int getOrdinal() {
        return Integer.parseInt(getProperties().get("config_ordinal"));
    }

    @Override
    public Set<String> getPropertyNames() {
        return getProperties().keySet();
    }

    @Override
    public String getValue(String key) {
        return getProperties().get(key);
    }

    @Override
    public String getName() {
        return "Custom Config Source: file:" + this.fileLocation;
    }

    public Map<String, String> getProperties() {
        Map<String, String> m = new HashMap<String, String>();
        String jsonData = this.readFile(this.fileLocation);
        JsonParser parser = Json.createParser(new StringReader(jsonData));
        String key = null;
        while (parser.hasNext()) {
            final Event event = parser.next();
            switch (event) {
                case KEY_NAME:
                    key = parser.getString();
                    break;
                case VALUE_STRING:
                    String string = parser.getString();
                    m.put(key, string);
                    break;
                case VALUE_NUMBER:
                    BigDecimal number = parser.getBigDecimal();
                    m.put(key, number.toString());
                    break;
                case VALUE_TRUE:
                    m.put(key, "true");
                    break;
                case VALUE_FALSE:
                    m.put(key, "false");
                    break;
                default:
                    break;
            }
        }
        parser.close();
        return m;
    }

    public String readFile(String fileName) {
        String result = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }
            result = sb.toString();
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
