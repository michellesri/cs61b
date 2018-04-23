import java.util.Arrays;

/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        // find the longest ascii. pad all the other characters with an imaginary character
            // call sortHelper
        char blankspace = '_';

        String[] asciisCopyPadded = new String[asciis.length];
        String[] asciisCopy = new String[asciis.length];

        int longestLength = 0;
        for (int i = 0; i < asciis.length; i++) {
            int currentLength = asciis[i].length();
            if (currentLength > longestLength) {
                longestLength = currentLength;
            }
            asciisCopyPadded[i] = asciis[i];
            asciisCopy[i] = asciis[i];
        }

        for (int i = 0; i < asciisCopyPadded.length; i++) {
            String currentAscii = asciisCopyPadded[i];
            while (currentAscii.length() < longestLength) {
                currentAscii += blankspace;
            }
        }

        for (int i = asciisCopyPadded.length - 1; i >= 0; i--) {
            sortHelperLSD(asciisCopyPadded, asciisCopy, i);
        }
        return asciisCopy;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciisCopyPadded Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciisCopyPadded, String[] asciisCopy, int index) {
        // Optional LSD helper method for required LSD radix sort
        int[] intValsArray = new int[asciisCopyPadded.length];

        for (int i = 0; i < asciisCopyPadded.length; i++) {
            int charAtCurrentIndex = asciisCopyPadded[i].charAt(index);
            intValsArray[i] = charAtCurrentIndex;
        }

        for (int i = 0; i < intValsArray.length; i++) {
            int min = Integer.MAX_VALUE;
            int minIndex = 0;
            for (int j = i; j < intValsArray.length; j++) {
                if (intValsArray[j] < min) {
                    min = intValsArray[j];
                    minIndex = j;
                }
            }
            swap(i, minIndex, intValsArray, asciisCopyPadded, asciisCopy);
        }

        return;
    }

    private static void swap(int i, int j, int[] intValsArray, String[] asciisCopyPadded, String[] asciisCopy) {
        int temp = intValsArray[i];
        intValsArray[i] = intValsArray[j];
        intValsArray[j] = temp;

        String tempCopyPadded = asciisCopyPadded[i];
        asciisCopyPadded[i] = asciisCopyPadded[j];
        asciisCopyPadded[j] = tempCopyPadded;

        String tempCopy = asciisCopy[i];
        asciisCopy[i] = asciisCopy[j];
        asciisCopy[j] = tempCopy;
    }

//    public static void main(String[] args) {
//        String[] blah = new String[3];
//        blah[0] = "bbb";
//        blah[1] = "aaa";
//        blah[2] = "ccc";
//
////        sortHelperLSD(blah, 2);
//        System.out.println(Arrays.toString(blah));
//    }

    // in the sort function, call it on every index from LSD until index 0

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }
}
