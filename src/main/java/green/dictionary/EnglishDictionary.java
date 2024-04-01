package green.dictionary;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EnglishDictionary {
    private final HashMap<String, List<String>> definitions = new HashMap<>();

    public EnglishDictionary() {
        InputStream in = EnglishDictionary.class.getResourceAsStream(
                "/englishDictionary.csv");

        CSVReader csvReader = new CSVReader(new InputStreamReader(in));

        try {
            String[] nextDef;
            while ((nextDef = csvReader.readNext()) != null) {
                String word = nextDef[0];
                List<String> defsAsList = definitions.getOrDefault(word, new ArrayList<>());
                defsAsList.add(extractDefFromLine(nextDef));
                definitions.put(word, defsAsList);
            }
        } catch (CsvValidationException | IOException e) {
            throw new RuntimeException(e);
        }

    }

    private String extractDefFromLine(String[] line) {
        if (line.length < 3) { //default definition is 3rd element as per this csv
            return "Abnormal format of definition, unable to extract";
        }
        return line[2];
    }

    public List<String> getDefinition(String word) {
        return definitions.get(word);
    }

    public String defToString(List<String> defs) {
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < defs.size(); i++) {
            str.append(defs.get(i)).append("\n");
        }
        return str.toString();
    }
}