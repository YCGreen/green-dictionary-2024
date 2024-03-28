package green.dictionary;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class EnglishDictionary {
    private final HashMap<String, List<String[]>> definitions = new HashMap<>();

    public EnglishDictionary()  {
        try (CSVReader csvReader = new CSVReader(new FileReader(
                "englishDictionary.csv"))) {
            String[] nextDef;
            while ((nextDef = csvReader.readNext()) != null) {
                String word = nextDef[0];
                String[] defs = Arrays.copyOfRange(nextDef, 2, nextDef.length);
                List<String[]> defsAsList = definitions.getOrDefault(word, new ArrayList<>());
                defsAsList.add(defs);
                definitions.put(word, defsAsList);
            }
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }

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
