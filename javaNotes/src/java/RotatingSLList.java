public class RotatingSLList<Item> extends SLList<Item> {

    // rotates list to the right
    public void rotateRight() {
        Item x = removeLast();
        addFirst(x);
    }

    public static void main(String[] args) {
        RotatingSLList<Integer> rs1 = new RotatingSLList<>();

        // creates SList: [10, 11, 12, 13]
        rs1.addLast(10);
        rs1.addLast(11);
        rs1.addLast(12);
        rs1.addLast(13);

        // should be: [13, 10, 11, 12]
        rs1.rotateRight();
        rs1.print();
    }
}