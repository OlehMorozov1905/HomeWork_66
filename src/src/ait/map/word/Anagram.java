package src.ait.map.word;

import java.util.HashMap;
import java.util.Map;

public class Anagram {
    public static boolean isAnagram(String word, String part) {

        if (word == null || part == null || word.isEmpty() || part.isEmpty()) {
            return false;
        }

        word = word.toLowerCase();
        part = part.toLowerCase();

        if (word.length() < part.length()) {
            return false;
        }

        Map<Character, Integer> wordFrequency = calcCharFrequency(word);
        Map<Character, Integer> partFrequency = calcCharFrequency(part);

        for (Map.Entry<Character, Integer> entry : partFrequency.entrySet()) {
            char character = entry.getKey();
            int frequency = entry.getValue();

            if (!wordFrequency.containsKey(character) || wordFrequency.get(character) < frequency) {
                return false;
            }
        }

        return true;
    }

    private static Map<Character, Integer> calcCharFrequency(String str) {
        Map<Character, Integer> charFrequency = new HashMap<>();

        for (char c : str.toCharArray()) {
            charFrequency.merge(c, 1, (oldValue, newValue) -> oldValue + newValue);
        }
        return charFrequency;
    }
}
