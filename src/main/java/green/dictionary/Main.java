package green.dictionary;

import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EnglishDictionary dic = new EnglishDictionary();

        List<String[]> text = dic.getDefinition("Abandon");

        for (String[] definition : text) {
            System.out.println(String.join(", ", definition));
        }



    }
}
