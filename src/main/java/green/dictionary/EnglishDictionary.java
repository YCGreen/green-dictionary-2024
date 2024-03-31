package green.dictionary;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class EnglishDictionary {
    private final HashMap<String, List<String[]>> definitions = new HashMap<>();

    public EnglishDictionary() {
        InputStream in = EnglishDictionary.class.getResourceAsStream(
                "/englishDictionary.csv");

        CSVReader csvReader = new CSVReader(new InputStreamReader(in));

        try {
            String[] nextDef;
            while ((nextDef = csvReader.readNext()) != null) {
                String word = nextDef[0];
                List<String[]> defsAsList = definitions.getOrDefault(word, new ArrayList<>());
                defsAsList.add(extractDefFromLine(nextDef));
                definitions.put(word, defsAsList);
            }
        } catch (CsvValidationException | IOException e) {
            throw new RuntimeException(e);
        }

    }

    private String[] extractDefFromLine(String[] line) {
        if (line.length < 3) { //default definition is 3rd element as per this csv
            return new String[] {"Abnormal format of definition, unable to extract"};
        }
        return Arrays.copyOfRange(line, 2, line.length);
    }

    public List<String[]> getDefinition(String word) {
        return definitions.get(word);
    }

    public String toString(List<String[]> text) {
        if (text == null) {
            return null;
        }
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < text.size(); i++) {
            str.append(String.join(", ", text.get(i)));
        }
        return str.toString();
    }

}