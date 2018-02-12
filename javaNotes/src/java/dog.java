public class Dog implements OurComparable{
    private String name;
    private int size;

    public Dog(String n, int s) {
        name = n;
        size = s;
    }

    public void bark() {
        System.out.println(name + " says: bark");
    }

    public int compareTo(Object o) {
        // returns -1 if this dog is less than the dog pointed at by o...
        Dog uddaDog = (Dog) o;

        return this.size - uddaDog.size;
    }


}
