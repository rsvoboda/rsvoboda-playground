package org.experiments.rsvoboda.csv;

import com.opencsv.CSVReader;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

//        String csvFile = "";
//        List<List<String>> rows = new ArrayList<>();
//        try (InputStream in = new FileInputStream(csvFile);) {
//            CSV csv = new CSV(true, ',', in);
//            List<String> colNames = null;
//            if (csv.hasNext())
//                colNames = new ArrayList<String>(csv.next());
//            while (csv.hasNext()) {
//                List<String> fields = new ArrayList<String>(csv.next());
//                rows.add(fields);
//            }
//        }

        String csvStr = "A,B,C,D\n"
                + "1,2,3,4\n"
                + "5,6,7,\"8\nadded new line\"\n"
                + "i,j,k,l\n"
                + "o,p,q,r";

        System.out.println("in-house CSV version: ");
        try (Reader in = new StringReader(csvStr);) {
            CSV csv = new CSV(true, ',', in);

            if (csv.hasNext()) {
                List<String> columnNames = new ArrayList<String>(csv.next());
                System.out.println("Column names: " + columnNames);
                System.out.println();
            }
            while (csv.hasNext()) {
                List<String> fields = csv.next();
                for (int i = 0 ; i < fields.size() ; i++) {
                    System.out.printf("%-3d: %s%n", (i+1), fields.get(i));
                }
                System.out.println();
            }
        }

        System.out.println("opencsv version: ");

        try (Reader in = new StringReader(csvStr);) {
            CSVReader reader = new CSVReader(in,',');  //      new CSVReader(new FileReader("yourfile.csv"));
            String[] line;
            while ((line = reader.readNext()) != null) {
                System.out.println(Arrays.toString(line));
            }
        }



    }
}
