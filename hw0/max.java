public class MaxOfArray {
   public static int max(int[] m) {
    int max = m[0];
     for (int count = 1; count < m.length; count++) {
         if (m[count] > max) {
          max = m[count];
        }
    }
    return max;
   }
   public static void main(String[] args) {
      int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};
      System.out.print(max(numbers));
   }
}