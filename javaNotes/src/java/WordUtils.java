public class WordUtils {
    // returns the length of the longest word
    public static String longest(ListInterface <String> list) {
        int maxDex = 0;
        for (int i = 0; i < list.size(); i++) {
            String longestString = list.get(maxDex);
            String thisString = list.get(i);
            if (thisString.length() > longestString.length()) {
                maxDex = i;
            }
        }

        return list.get(maxDex);
    }

}
