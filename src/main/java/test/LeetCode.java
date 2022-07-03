package test;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * @author maple
 * @create 2022-06-12 10:40
 */
public class LeetCode {
    @Test
    public void test() {
        String pattern = "abb";
        String[] words = new String[]{"abc","deq","mee","aqq","dkd","ccc"};
        List<String> res = findAndReplacePattern(words, pattern);
        res.forEach(System.out::println);
    }

    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> res = new ArrayList<>();

        for (String word : words) {
            boolean flag = true; // 表示当前word能够与pattern匹配
            Map<Character, Character> patternToWord = new HashMap<>();
            Map<Character, Character> wordToPattern = new HashMap<>();
            if (word.length() != pattern.length()) {
                continue;
            }
            for (int i = 0; i < pattern.length(); i++) {
                char cInWord = word.charAt(i);
                char cInPattern = pattern.charAt(i);
                if (patternToWord.get(cInPattern) == null && wordToPattern.get(cInWord) == null) {
                    patternToWord.put(cInPattern, cInWord);
                    wordToPattern.put(cInWord, cInPattern);
                } else if (patternToWord.get(cInPattern) != null && patternToWord.get(cInPattern) == cInWord && wordToPattern.get(cInWord) != null && wordToPattern.get(cInWord) == cInPattern) {
                } else {
                    flag = false;
                    break;
                }
            }
            // 如果能匹配
            if (flag) {
                res.add(word);
            }
        }
        return res;
    }
}
