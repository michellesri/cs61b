import java.util.Arrays;

public class NBody {

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        double radiusUniverse = readRadius(filename);
        Planet[] planets = readPlanets(filename);


        StdDraw.enableDoubleBuffering();

        int time = 0;

        while (time < T) {
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];

            for (int i = 0; i < planets.length; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }

            for (int i = 0; i < planets.length; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.setScale(-radiusUniverse, radiusUniverse);
            StdDraw.clear();
            StdDraw.picture(0, 0, "images/starfield.jpg");

            for(int i = 0; i < planets.length; i++) {
                planets[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);

            time += dt;

        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radiusUniverse);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }

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