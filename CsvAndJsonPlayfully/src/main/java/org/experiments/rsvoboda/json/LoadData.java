package org.experiments.rsvoboda.json;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class LoadData {

    public static void main(String[] args) throws IOException {

        InputStream is = LoadData.class.getResourceAsStream("/org/experiments/rsvoboda/json/person.json");

        ObjectMapper mapper = new ObjectMapper();
        DateFormat fmt = new SimpleDateFormat("dd-MMM-yyyy");
        mapper.setDateFormat(fmt);

        // to avoid error on localDateOfBirth
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // aka  @JsonIgnoreProperties(ignoreUnknown=true) on POJO classes

        User user = mapper.readValue(is, User.class);
        System.out.println(user);

    }
}
