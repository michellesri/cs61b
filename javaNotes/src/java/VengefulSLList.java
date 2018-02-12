public class VengefulSLList<Item> extends SLList<Item> {
    SLList<Item> deletedItems;

    public VengefulSLList() {
        deletedItems = new SLList<Item>();
    }

    @Override
    public Item removeLast() {
        Item x = super.removeLast();
        deletedItems.addLast(x);
        return x;
    }

    public void printLostItems() {
        // prints deleted items
        deletedItems.print();
    }

    public static void main(String[] args) {
        VengefulSLList<Integer> vs1 = new VengefulSLList<Integer>();
        vs1.addLast(1);
        vs1.addLast(5);
        vs1.addLast(10);
        vs1.addLast(13); // [1, 5, 10, 13]
        vs1.removeLast(); // 13 gets deleted
        vs1.removeLast(); // 10 gets deleted
        System.out.print("The fallen are: ");
        vs1.printLostItems(); // should print out 10 and 13
    }
}