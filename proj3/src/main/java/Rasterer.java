import java.awt.image.Raster;
import java.util.ArrayList;
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

        double widthQuery = params.get("w"); // width in pixels
        double heightQuery = params.get("h"); // height in pixels

        double lonDPP = (lrlonQuery - ullonQuery) / widthQuery;

        int depth = findDepth(lonDPP);

        // System.out.println(params);
        Map<String, Object> results = new HashMap<>();
        System.out.println("Since you haven't implemented getMapRaster, nothing is displayed in "
                           + "your browser.");

        String[][] render_grid = new String[6][1];
        double raster_ul_lon = 0.0f;

        results.put("render_grid", render_grid);
        results.put("raster_ul_lon", raster_ul_lon);
        results.put("query_success", true);

        return results;
    }

    private Result getFilenames(double queryUllon, double rootUllon,
                               double queryUllat, double rootUllat,
                               double queryLrlon, double rootLrlon,
                               double queryLrlat, double rootLrlat,
                               int depth) {

        double UllonFromTopLeft = queryUllon - rootUllon;
        double UllatFromTopLeft = queryUllat - rootUllat;

        double LrlonFromTopLeft = queryUllon - rootLrlon;
        double LrlatFromTopLeft = queryUllat - rootLrlat;

        double tileWidth = (rootLrlon - rootUllon) / Math.pow(2, depth);

        int upperLeftTileX = (int) (UllonFromTopLeft / tileWidth);
        int upperLeftTileY = (int) (UllatFromTopLeft / tileWidth);

        int lowerRightTileX = (int) (LrlonFromTopLeft / tileWidth);
        int lowerRightTileY = (int) (LrlatFromTopLeft / tileWidth);

        double raster_lr_lon = lowerRightTileX * tileWidth;
        double raster_lr_lat = lowerRightTileY * tileWidth;

        double raster_ul_lon = upperLeftTileX * tileWidth;
        double raster_ul_lat = upperLeftTileY * tileWidth;

        int xSize = lowerRightTileX - upperLeftTileX + 1;
        int ySize = lowerRightTileY - upperLeftTileY + 1;
        String[][] filenames = new String[xSize][ySize];
        // file name example: d1_x0_y0

        int counterX = 0;
        int counterY = 0;
        for (int i = upperLeftTileX; i <= lowerRightTileX; i++) {
            for (int j = upperLeftTileY; j <= lowerRightTileY; j++) {
                filenames[counterX][counterY] = "d" + depth + "_x" + i + "_y" + j;
                counterY++;
            }
            counterY = 0;
            counterX++;
        }

        Result result = new Result(raster_lr_lon, raster_lr_lat, raster_ul_lon,
                raster_ul_lat, depth, true, filenames);

        return result;

    }

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

}
