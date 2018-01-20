public class TriangleDemo {
    public static void main(String[] args) {
      drawTriangle(10);
    }
    public static String drawTriangle(int N) {
      String triangle = "";
      int counter = 0;
      while (counter < N) {
        triangle += "*";
        counter += 1;
        System.out.println(triangle);
      }
      return triangle;
    }
}