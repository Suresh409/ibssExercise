package words;

import java.util.List;
import java.util.Arrays;

public class Words {
    public static List<String> getUniqueWordsFromSentence(String sentence) {
        return Arrays.stream(sentence.split("\\s+"))
            .map(word -> word.replaceAll("[^a-zA-Z]", "").toLowerCase())
            .distinct()
            .toList();
        return null;
    }
}

