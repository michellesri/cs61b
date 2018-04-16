import java.awt.image.Raster;
import java.util.HashMap;
import java.util.Map;

/**
 * This class provides all code necessary to take a query box and produce
 * a query result. The getMapRaster method must return a Map containing all
 * seven of the required fields, otherwise the front end code will probably
 * not draw the output correctly.
 */
public class Rasterer {

    public Rasterer() {

    }

    /**
     * Takes a user query and finds the grid of images that best matches the query. These
     * images will be combined into one big image (rastered) by the front end. <br>
     *
     *     The grid of images must obey the following properties, where image in the
     *     grid is referred to as a "tile".
     *     <ul>
     *         <li>The tiles collected must cover the most longitudinal distance per pixel
     *         (LonDPP) possible, while still covering less than or equal to the amount of
     *         longitudinal distance per pixel in the query box for the user viewport size. </li>
     *         <li>Contains all tiles that intersect the query bounding box that fulfill the
     *         above condition.</li>
     *         <li>The tiles must be arranged in-order to reconstruct the full image.</li>
     *     </ul>
     *
     * @param params Map of the HTTP GET request's query parameters - the query box and
     *               the user viewport width and height.
     *
     * @return A map of results for the front end as specified: <br>
     * "render_grid"   : String[][], the files to display. <br>
     * "raster_ul_lon" : Number, the bounding upper left longitude of the rastered image. <br>
     * "raster_ul_lat" : Number, the bounding upper left latitude of the rastered image. <br>
     * "raster_lr_lon" : Number, the bounding lower right longitude of the rastered image. <br>
     * "raster_lr_lat" : Number, the bounding lower right latitude of the rastered image. <br>
     * "depth"         : Number, the depth of the nodes of the rastered image <br>
     * "query_success" : Boolean, whether the query was able to successfully complete; don't
     *                    forget to set this to true on success! <br>
     */
    public Map<String, Object> getMapRaster(Map<String, Double> params) {
        double ullonQuery = params.get("ullon");
        double ullatQuery = params.get("ullat");

        double lrlonQuery = params.get("lrlon");
        double lrlatQuery = params.get("lrlat");

        double widthQuery = params.get("w");
        double heightQuery = params.get("h");

        double lonDPP = (lrlonQuery - ullonQuery) / widthQuery;

        int depth = findDepth(lonDPP);

        // System.out.println(params);
        Map<String, Object> results = new HashMap<>();
        System.out.println("Since you haven't implemented getMapRaster, nothing is displayed in "
                           + "your browser.");

        String[][] render_grid = new String[6][1];
        double raster_ul_lon = 0.0f;

        Map<String, Object> result = new HashMap<>();
        result.put("render_grid", render_grid);
        result.put("raster_ul_lon", raster_ul_lon);
        result.put("query_success", true);

        return result;
    }

    private Tile[][] createGrid(int depth) {

        double gridWidth = Math.pow(2, depth);
        double rootULLon = MapServer.ROOT_ULLON;
        double rootLRLon = MapServer.ROOT_LRLON;

        double rootULLat = MapServer.ROOT_ULLAT;
        double rootLRLat = MapServer.ROOT_LRLAT;

        double widthOfTile = (rootLRLon - rootULLon) / gridWidth; // width of tile in longitude
        double heightOfTile = (rootLRLat - rootULLat) / gridWidth; // height of tile in latitude


        Tile[][] tiles = new Tile[(int) gridWidth][(int) gridWidth];

        for (int i = 0; i < gridWidth; i++) {
            double currentULLon = rootULLon;
            double currentULLat = rootULLat;

            double currentLRLon = currentULLon + widthOfTile;
            double currentLRLat = currentULLat + heightOfTile;
            int x;
            int y;

            for (int j = 0; j < gridWidth; j++) {
                currentULLon += widthOfTile;
                currentULLat += heightOfTile;

                currentLRLon += widthOfTile;
                currentLRLat += heightOfTile;
                x = i;
                y = j;

                tiles[i][j] = new Tile(currentLRLon, currentLRLat, currentULLon,
                        currentULLat, x, y, widthOfTile, heightOfTile);
            }
        }
        return tiles;
    }

    private boolean isTileBoundingBox(Tile tile, double ullonquery, double ullatquery,
                                      double lrlatquery, double lrlonquery,
                                      double widthOfTile, double heightOfTile) {

        double lrlon = tile.getLrlon();
        double lrlat = tile.getLrlat();
        double ullon = tile.getUllon();
        double ullat = tile.getUllat();
        return ((lrlon <= lrlonquery || lrlonquery <= (lrlon + widthOfTile))
                && (ullon <= ullonquery || ullonquery <= (ullon + heightOfTile)));
    }

// write a main function to test this code?

    private int findDepth(double lonDPP) {
        int currentDepth = 0;

        while (currentDepth < 7) {
            if ((MapServer.ROOT_LRLON - MapServer.ROOT_ULLON)
                    / (MapServer.TILE_SIZE * Math.pow(2, currentDepth)) > lonDPP) {
                return currentDepth;
            }
            currentDepth++;
        }
        return currentDepth;
    }

    public static void main(String[] args) {
        Tile tile = new Tile(2, 2, 0, 0, 0,0, 2, 2);

        Rasterer rasterer = new Rasterer();
//        rasterer.isTileBoundingBox(tile, 0, 1, 2, 10, 2, 2);
        System.out.println(rasterer.isTileBoundingBox(tile, 20, 20, 40,
                40, 2, 2));

    }


}
