public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        ArrayDeque palindromeDeque = new ArrayDeque();

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            palindromeDeque.addLast(c);
        }
        return palindromeDeque;
    }

    public boolean isPalindrome(String word) {
        Deque palindromeDeque = wordToDeque(word);
        int counter = palindromeDeque.size();
        String builtString = "";

        while (counter > 0) {
            builtString += palindromeDeque.removeFirst();
        }
        return builtString == word;
    }
}