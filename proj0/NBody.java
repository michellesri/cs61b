import java.util.Arrays;

public class NBody {

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        double radiusUniverse = readRadius(filename);
        Planet[] planets = readPlanets(filename);

        StdDraw.setScale(-radiusUniverse, radiusUniverse);
        StdDraw.clear();
        StdDraw.picture(0, 0, "images/starfield.jpg");
        for(int i = 0; i < planets.length; i++) {
            planets[i].draw();
        }

        StdDraw.show();
//        System.out.println(Arrays.toString(planets));


    }
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