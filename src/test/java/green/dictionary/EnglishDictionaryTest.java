package green.dictionary;

import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EnglishDictionaryTest {

    @Test
    public void getDefinitionNonexistent() {
        // given
        EnglishDictionary dictionary = new EnglishDictionary();

        // when
        List<String> definitions = dictionary.getDefinition("blahblahblah");

        // then
        assertNull(definitions);
    }

    @Test
    public void getDefinitionOne() {
        // given
        EnglishDictionary dictionary = new EnglishDictionary();

        // when
        List<String> definitions = dictionary.getDefinition("Aard-vark");

        // then
        assertEquals("An edentate mammal, of the genus Orycteropus, "
                + "somewhat resembling a pig, common in some parts of Southern Africa. "
                + "It burrows in the ground, and feeds entirely on ants, "
                + "which it catches with its long, slimy tongue.", definitions.get(0));
    }

    @Test
    public void getDefinitionMultiple() {
        // given
        EnglishDictionary dictionary = new EnglishDictionary();

        // when
        List<String> definitions = dictionary.getDefinition("Aband");

        // then
        assertEquals("To abandon.", definitions.get(0));
        assertEquals("To banish; to expel.", definitions.get(1));
    }

    @Test
    public void getDefinitionCase() {
        // given
        EnglishDictionary dictionary = new EnglishDictionary();

        // when
        List<String> definitions = dictionary.getDefinition("aBaNd");

        // then
        assertEquals("To abandon.", definitions.get(0));
        assertEquals("To banish; to expel.", definitions.get(1));
    }

}