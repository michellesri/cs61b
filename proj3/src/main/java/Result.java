import java.util.ArrayList;

public class Result {

    double raster_ul_lon;
    double raster_ul_lat;
    double raster_lr_lon;
    double raster_lr_lat;
    int depth;
    boolean query_success;
    String[][] filenames;

    public Result(double raster_lr_lon, double raster_lr_lat,
                  double raster_ul_lon, double raster_ul_lat,
                  int depth, boolean query_success, String[][] filenames) {

        this.raster_lr_lat = raster_lr_lat;
        this.raster_lr_lon = raster_lr_lon;
        this.raster_ul_lat = raster_ul_lat;
        this.raster_ul_lon = raster_ul_lon;
        this.depth = depth;
        this.query_success = query_success;
        this.filenames = filenames;
    }
}
