//import java.util.Arrays;

public class NBody {
    public static double readRadius(String fileName) {
        In in = new In(fileName);

        int numPlanets = in.readInt();
        double radiusUniverse = in.readDouble();

        return radiusUniverse;
    }

    public static Planet[] readPlanets(String fileName) {
        In in = new In(fileName);

        int numPlanets = in.readInt();
        double radiusUniverse = in.readDouble();

        Planet[] planetsInFile = new Planet[numPlanets];


        int counter = 0;
        while (counter < numPlanets) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();

            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();

            planetsInFile[counter] = new Planet(xxPos, yyPos, xxVel,
            yyVel, mass, imgFileName);
            counter++;

        }
        return planetsInFile;
    }
}