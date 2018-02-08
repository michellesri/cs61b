public class IntList {
    public int first;
    public IntList rest;

    public IntList(int f, IntList r) {
        first = f;
        rest = r;
    }

    public static void main(String[] args) {
        IntList L = new IntList(15, null);
        L = new IntList(10, L);
        L = new IntList(5, L);

        System.out.println(L.size());

        System.out.println(L.iterativeSize());

        System.out.println(L.get(2));
    }

    public int size() {
        // return the size of the list using recursion
        if (rest == null) {
            return 1;
        }
        return 1 + this.rest.size();
    }

    public int iterativeSize() {
        // return the size of the list iteratively
        IntList p = this;
        int totalSize = 0;

        while (p != null) {
            totalSize += 1;
            p = p.rest;
        }
        return totalSize;
    }

    public int get(int i) {
        // returns the ith element in the list
        if (i == 0) {
            return first;
        }
        return rest.get(i - 1);
    }

    public static IntList incrList(IntList L, int x) {
        // returns a new IntList similar to L, but with all values incremented by x.
        // TODO not implemented
        return null;
    }

//    public static IntList dincrList(IntList L, int x) {
//        // returns a new IntList similar to L, but with all values decremented by x.
//    }
}