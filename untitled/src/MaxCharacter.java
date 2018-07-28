import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MaxCharacter {


    // @param an string input
    // returns the character with the most occurrences from the input string
    // if there is a tie, the char that appears earliest in the string will be returned
    public char maxChar(String string) {

        Map<Character, Integer> charCount = new HashMap<Character, Integer>();

        //add all the chars and their charCount to hashmap gtt'
        for (char ch : string.toCharArray()) {
            if (charCount.containsKey(ch)) {
                int charValue = charCount.get(ch);
                charValue++; // add one to current count
                charCount.put(ch, charValue);
            } else {
                charCount.put(ch, 1);
            }
        }

        //loop through hashmap and values to find the char with most occurrences
        int maxCount = 0;
        char maxChar = Character.MIN_VALUE;
        for (Map.Entry<Character, Integer> entry : charCount.entrySet()) {
            char key = entry.getKey();
            Integer value = entry.getValue();

            if (value > maxCount) {
                maxCount = value;
                maxChar = key;
            }
        }
        return maxChar;
    }

    public static void main(String[] args) {
        String string1 = "abcaabc";
        String string2 = "abb";
        String string3 = "ccbb";
        String string4 = "";

        MaxCharacter maxCharInstance = new MaxCharacter();

        System.out.println(maxCharInstance.maxChar(string1)); //a
        System.out.println(maxCharInstance.maxChar(string2)); //b
        System.out.println(maxCharInstance.maxChar(string3)); //c
        System.out.println(maxCharInstance.maxChar(string4)); //empty char


    }
}
