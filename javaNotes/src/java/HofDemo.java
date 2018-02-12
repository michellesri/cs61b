public class HofDemo {
    // demonstrates higher order functions in java
    public static int do_twice(IntUnaryFunction f, int x) {
        return f.apply(f.apply(x));
    }

    public static void main(String[] args) {
        IntUnaryFunction tenx = new TenX();
        System.out.println(do_twice(tenx, 2));
    }
}