package org.experiments.rsvoboda.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class SerializeArray {

    public static void main(String[] args) throws IOException {

        List<User> users = new ArrayList<>();
        users.add(new User("Rostislav", "Svoboda"));
        users.add(new User("Pavel", "Svoboda"));
        users.add(new User("Lukas", "Svoboda"));


        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//        mapper.setSerializationInclusion(Include.NON_NULL);
//        mapper.setSerializationInclusion(Include.NON_EMPTY);

        String serializedJSON = mapper.writeValueAsString(users);

        TypeReference ref = new TypeReference<List<User>>(){};
        List<User> deserializedUsers = mapper.readValue(new StringReader(serializedJSON), ref);
        System.out.println(deserializedUsers);


    }
}
