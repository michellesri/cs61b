public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        ArrayDeque palindromeDeque = new ArrayDeque();

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            palindromeDeque.addLast(c);
        }
        return palindromeDeque;
    }
}