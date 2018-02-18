import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();
    static OffByOne obo = new OffByOne();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        assertTrue(palindrome.isPalindrome("racecar"));
        assertTrue(palindrome.isPalindrome("a"));

        assertFalse(palindrome.isPalindrome("dog"));
        assertFalse(palindrome.isPalindrome("aA"));
        assertFalse(palindrome.isPalindrome("ab"));

    }

    @Test
    public void testOffByOneCheck() {
        assertTrue(obo.equalChars('a', 'b'));
        assertTrue(obo.equalChars('r', 'q'));

        assertFalse(obo.equalChars('a', 'a'));
        assertFalse(obo.equalChars('a', 'e'));
        assertFalse(obo.equalChars('z', 'a'));
    }

    @Test
    public void testOboPalindrome() {
        assertTrue(palindrome.isPalindrome("flake", obo));
        assertTrue(palindrome.isPalindrome("a", obo));

        assertFalse(palindrome.isPalindrome("base", obo));
        assertFalse(palindrome.isPalindrome("baseball", obo));
        assertFalse(palindrome.isPalindrome("aba", obo));


    }
}
