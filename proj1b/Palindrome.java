public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        ArrayDeque<Character> palindromeDeque = new ArrayDeque();

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            palindromeDeque.addLast(c);
        }
        return palindromeDeque;
    }

    public boolean isPalindrome(String word) {

        if (word.length() == 1) {
            System.out.println("bahakdfjd");
            return true;
        }
        Deque<Character> palindromeDeque = wordToDeque(word);
        int counter = palindromeDeque.size();
        String builtString = "";

        while (counter > 0) {
            builtString += palindromeDeque.removeLast();
            counter--;
        }
        System.out.println(builtString + " " + word);
        System.out.println(builtString.getClass() + " " + word.getClass());

        return builtString.equals(word);
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word.length() == 1) {
            return true;
        }
        Deque<Character> tempWord = wordToDeque(word);

        while (tempWord.size() > 1) {
            if (!cc.equalChars(tempWord.removeFirst(), tempWord.removeLast())) {
                return false;
            }
        }
        return true;
    }
}
