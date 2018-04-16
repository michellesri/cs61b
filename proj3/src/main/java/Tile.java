public class Tile {

    private double lrlon;
    private double ullon;


    private double lrlat;
    private double ullat;

    private double widthOfTile;
    private double heightOfTile;


    private int x;
    private int y;

    public Tile(double lrlon, double lrlat, double ullon, double ullat,
                int x, int y, double widthOfTile, double heightOfTile) {
        this.ullon = ullon;
        this.ullat = ullat;
        this.lrlon = lrlon;
        this.lrlat = lrlat;
        this.x = x;
        this.y = y;

        this.widthOfTile = widthOfTile;
        this.heightOfTile = heightOfTile;
    }

    public double getLrlon() {
        return lrlon;
    }

    public double getUllon() {
        return ullon;
    }

    public double getLrlat() {
        return lrlat;
    }

    public double getUllat() {
        return ullat;
    }

    public double getWidthOfTile() {
        return widthOfTile;
    }

    public double getHeightOfTile() {
        return heightOfTile;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
