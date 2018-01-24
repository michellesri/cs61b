public class Planet {
    private static final double G = 6.67e-11;


    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    public Planet(Planet p) {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
    }

    public double calcDistance(Planet p1) {
        double dx = this.xxPos - p1.xxPos;
        double dy = this.yyPos - p1.yyPos;
        return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }

    public double calcForceExertedBy(Planet p1) {
        double r = calcDistance(p1);
        return G * this.mass * p1.mass / Math.pow(r, 2);
    }

    public double calcForceExertedByX(Planet p1) {
        double force = this.calcForceExertedBy(p1);
        double r = this.calcDistance(p1);
        double dx = p1.xxPos - this.xxPos;

        return force * dx / r;
    }

    public double calcForceExertedByY(Planet p1) {
        double force = this.calcForceExertedBy(p1);
        double r = this.calcDistance(p1);
        double dy = p1.yyPos - this.yyPos;

        return force * dy / r;
    }

    public double calcNetForceExertedByX(Planet[] planets) {
        double sumXComponents = 0;
        for (int i = 0; i < planets.length; i++) {
            if (this.equals(planets[i])) {
                continue;
            }
            sumXComponents += calcForceExertedByX(planets[i]);
        }
        return sumXComponents;
    }

    public double calcNetForceExertedByY(Planet[] planets) {
        double sumYComponents = 0;
        for (int i = 0; i < planets.length; i++) {
            if (this.equals(planets[i])) {
                continue;
            }
            sumYComponents += calcForceExertedByY(planets[i]);
        }
        return sumYComponents;
    }

    public void update(double dt, double fX, double fY) {
        double aNetX = fX / this.mass;
        double aNetY = fY / this.mass;

        this.xxVel = this.xxVel + dt * aNetX;
        this.yyVel = this.yyVel + dt * aNetY;

        this.xxPos = this.xxPos + dt * this.xxVel;
        this.yyPos = this.yyPos + dt * this.yyVel;
    }
}