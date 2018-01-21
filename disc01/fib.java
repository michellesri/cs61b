public class fib {
  public static void main(String[] args) {
    System.out.println(fibonacci(3));
  }
  
  public static int fibonacci(int n) {
    int counter = 1;
    int prev = 0;
    int curr = 1;
    int total = prev + curr;
    while (counter < n) {
      prev = curr;
      curr = total;
      total = prev + curr;
      counter++;
    }
    return total;
  }
}
