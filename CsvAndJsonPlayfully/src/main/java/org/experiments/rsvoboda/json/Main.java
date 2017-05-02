package org.experiments.rsvoboda.json;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws IOException {

        User user = new User("Rostislav", "Svoboda");
        user.setEmails(Arrays.asList("foo@bar.com"));

        LocalDate localDate = LocalDate.of(2012, Month.DECEMBER, 12);
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        user.setDateOfBirth(date);

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        DateFormat fmt = new SimpleDateFormat("dd-MMM-yyyy");
        mapper.setDateFormat(fmt);

        mapper.setSerializationInclusion(Include.NON_NULL);
        mapper.setSerializationInclusion(Include.NON_EMPTY);

        mapper.writeValue(System.out, user);  // String jsonStr = mapper.writeValueAsString(user);

    }
}
