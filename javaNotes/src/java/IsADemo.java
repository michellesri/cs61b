public class IsADemo {
    public static void main(String[] args) {
        SLList<String> someList = new SLList<>();
        ListInterface<String> LP = someList;
        someList.addLast("elk");
        someList.addLast("dwell");
        someList.addLast("on");
        someList.addLast("existential");
        someList.addLast("crises");
        peek(someList);
        peek(LP);
    }

    public static void peek(ListInterface<String> list) {
        System.out.println(list.getLast());
    }

    public static void peek(SLList<String> list) {
        System.out.println(list.getFirst());
    }
}
