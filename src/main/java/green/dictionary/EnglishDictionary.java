package green.dictionary;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class EnglishDictionary {
    /*
    Reads the englishDictionary file once (in the constructor)
     */

    private static List<String[]> text;
    private static FileReader fileReader;
    private static CSVReader csvReader;
    private static HashMap<String, List<String[]>> definitions = new HashMap<>();


    public EnglishDictionary() throws IOException, CsvException {
      /* fileReader = new FileReader("/Users/yaelgreen/IdeaProjects/green-dictionary-2024/src/main/resources/englishDictionary.csv");
        csvReader = new CSVReader(fileReader);
        text = csvReader.readAll();

        for(int i = 0; i < text.size(); i++) {
            definitions.put(i, text.get(i));
        } */
        try (CSVReader csvReader = new CSVReader(new FileReader("/Users/yaelgreen/IdeaProjects/green-dictionary-2024/src/main/resources/englishDictionary.csv"))) {
         /*   String[] prevDef = new String[]{"A"};
            String[] nextDef;
            List<String[]> defsPerWord = null;

            while ((nextDef = csvReader.readNext()) != null) {
                if(nextDef[0].equals(prevDef[0])) {
                    defsPerWord.add(nextDef);
                }
                definitions.put(nextDef[0], defsPerWord);
                defsPerWord = null;
                prevDef = nextDef;
            } */
            String[] nextDef;
            while ((nextDef = csvReader.readNext()) != null) {
                String word = nextDef[0];
                List<String[]> wordDefs = definitions.getOrDefault(word, new ArrayList<>());
                wordDefs.add(nextDef);
                definitions.put(word, wordDefs);
            }
            csvReader.close();
        }

    }

    public List<String[]> getDefinition(String word) {
        return definitions.get(word);
    }








}
