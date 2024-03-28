package green.dictionary;

import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EnglishDictionary dic;
        try {
            dic = new EnglishDictionary();
        //    List<String[]> text = dic.getFullText();

            List<String[]> text = dic.getDefinition("Abandon");

                for (String[] definition : text) {
                    System.out.println(String.join(", ", definition));
                }
         /*   for (String[] row : text) {
                for (String cell : row) {
                    System.out.print(cell + "\t");
                }
                System.out.println();
            } */
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }


    }
}
