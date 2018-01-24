public class NBody {
    public static double readRadius(String fileName) {
        In in = new In(fileName);

        int numPlanets = in.readInt();
        double radiusUniverse = in.readDouble();

        return radiusUniverse;


    }
}