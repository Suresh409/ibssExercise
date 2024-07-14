package words;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class HowManyWordsTest {
    @Test
    public void howManyWords() {
        List<String> uniqueWords =
                Words.getUniqueWordsFromSentence("A cat Sat on a mat; a Monkey sat on the Cat.");

        List<String> expectedResult = List.of("a", "cat", "sat", "on", "the", "mat", "monkey");

        Assert.assertEquals(7, uniqueWords.size());
        for (String word : expectedResult) {
            Assert.assertTrue(String.format("Word '%s' should be included", word), uniqueWords.contains(word));
        }
    }
}
