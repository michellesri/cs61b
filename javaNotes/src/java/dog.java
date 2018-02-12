public class Dog implements Comparable<Dog>{
    private String name;
    private int size;

    public Dog(String n, int s) {
        name = n;
        size = s;
    }

    public void bark() {
        System.out.println(name + " says: bark");
    }

    public int compareTo(Dog uddaDog) {
        // returns -1 if this dog is less than the dog pointed at by o...
        return this.size - uddaDog.size;
    }


}
