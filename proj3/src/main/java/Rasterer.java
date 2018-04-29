import java.awt.image.Raster;
import java.util.Arrays;
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

        double queryUllon = params.get("ullon");
        double queryUllat = params.get("ullat");


        double queryLrlon = params.get("lrlon");
        double queryLrlat = params.get("lrlat");

        double widthQuery = params.get("w"); // width in pixels
        double heightQuery = params.get("h"); // height in pixels

//        double queryUllon = -122.22970123786706;
//        double queryUllat = 37.84953486131026;
//        double queryLrlon = -122.2183737371942;
//        double queryLrlat = 37.844436007045545;
//
//        double widthQuery = 471.69289870450666;
//        double heightQuery = 726.1100968054328;

        double lonDPP = (queryLrlon - queryUllon) / widthQuery;

        int depth = findDepth(lonDPP);

        Result queryResults = getQuery(queryUllon, MapServer.ROOT_ULLON,
                queryUllat, MapServer.ROOT_ULLAT,
                queryLrlon, MapServer.ROOT_LRLON,
                queryLrlat, MapServer.ROOT_LRLAT,
                depth);

        // System.out.println(params);
        Map<String, Object> results = new HashMap<>();

//        double raster_ul_lon = 0.0f;

        results.put("render_grid", queryResults.filenames);
        results.put("raster_ul_lon", queryResults.raster_ul_lon);
        results.put("raster_ul_lat", queryResults.raster_lr_lat);
        results.put("raster_lr_lon", queryResults.raster_lr_lon);
        results.put("raster_lr_lat", queryResults.raster_lr_lat);
        results.put("depth", queryResults.depth);
        results.put("query_success", queryResults.query_success);

//        if (queryResults.filenames != null) {
//            for (int i = 0; i < queryResults.filenames.length; i++) {
//                System.out.println("wooo all my results: " + Arrays.toString(queryResults.filenames[i]));
//            }
//
//        }
//        System.out.println(results);
        return results;
    }

    private Result getQuery(double queryUllon, double rootUllon,
                               double queryUllat, double rootUllat,
                               double queryLrlon, double rootLrlon,
                               double queryLrlat, double rootLrlat,
                               int depth) {

        double UllonFromTopLeft = queryUllon - rootUllon;
        double UllatFromTopLeft = queryUllat - rootUllat;

        double LrlonFromTopLeft = queryLrlon - rootUllon;
        double LrlatFromTopLeft = queryLrlat - rootUllat;

        int numTilePerAxis = (int) Math.pow(2, depth);

        double tileWidth = (rootLrlon - rootUllon) / numTilePerAxis;
        double tileHeight = (rootLrlat - rootUllat) / numTilePerAxis;

        int upperLeftTileX = Math.max((int) (UllonFromTopLeft / tileWidth), 0);
        int upperLeftTileY = Math.max((int) (UllatFromTopLeft / tileHeight), 0);

        int lowerRightTileX = Math.min((int) (LrlonFromTopLeft / tileWidth), numTilePerAxis - 1);
        int lowerRightTileY = Math.min((int) (LrlatFromTopLeft / tileHeight), numTilePerAxis - 1);

        double raster_ul_lon = upperLeftTileX * tileWidth + rootUllon;
        double raster_ul_lat = upperLeftTileY * tileHeight + rootUllat;

        double raster_lr_lon = lowerRightTileX * tileWidth + tileWidth + rootUllon;
        double raster_lr_lat = lowerRightTileY * tileHeight + tileHeight + rootUllat;

        int xSize = lowerRightTileX - upperLeftTileX + 1;
        int ySize = lowerRightTileY - upperLeftTileY + 1;

        if (xSize <= 0 || ySize <= 0) {
            Result result = new Result(raster_lr_lon, raster_lr_lat, raster_ul_lon,
                    raster_ul_lat, depth, false, null);
            return result;
        }

        String[][] filenames = new String[ySize][xSize];
        // num files
//        int numFiles = (int) Math.pow(2, depth);
//        String[][] filenames = new String[numFiles][numFiles];
        // file name example: d1_x0_y0

        int counterX = 0;
        int counterY = 0;
        for (int j = upperLeftTileY; j <= lowerRightTileY; j++) {
            for (int i = upperLeftTileX; i <= lowerRightTileX; i++) {

                filenames[counterY][counterX] = "d" + depth + "_x" + i + "_y" + j + ".png";
                counterX++;
            }
            counterX = 0;
            counterY++;
        }

        Result result = new Result(raster_lr_lon, raster_lr_lat, raster_ul_lon,
                raster_ul_lat, depth, true, filenames);

        return result;

    }

    private int findDepth(double lonDPP) {
        int currentDepth = 0;

        while (currentDepth < 7) {
            if ((MapServer.ROOT_LRLON - MapServer.ROOT_ULLON)
                    / (MapServer.TILE_SIZE * Math.pow(2, currentDepth)) <= lonDPP) {
                return currentDepth;
            }
            currentDepth++;
        }
        return currentDepth;
    }

}
