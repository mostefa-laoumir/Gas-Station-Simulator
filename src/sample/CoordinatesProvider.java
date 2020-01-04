package sample;
import javafx.geometry.Point2D;
import java.util.ArrayList;
import java.util.List;

public final class CoordinatesProvider {

    /**
     * @definition this class have the role of providing most of important
     * points in the scene with X and Y coordinate for several animations
     */

    private static final Point2D INITIAL_POINT = new Point2D(1236, 440);
    private static final Point2D INITIAL_POINT_GAZ = new Point2D(1236, 302);
    private static final Point2D FUEL_POINT = new Point2D(366, 440);
    private static final Point2D GAZ_POINT = new Point2D(366, 302);
    private static final Point2D PAY_POINT = new Point2D(1011, 646);
    private static final Point2D OUT_POINT = new Point2D(1350, 646);




    public static Point2D getInitialPoint() {
        return INITIAL_POINT;
    }

    public static Point2D getFuelPoint() {
        return FUEL_POINT;
    }

    public static Point2D getInitialPointGaz() {
        return INITIAL_POINT_GAZ;
    }

    public static Point2D getGazPoint() {
        return GAZ_POINT;
    }

    public static Point2D getOutPoint() {
        return OUT_POINT;
    }

    public static Point2D getPayPoint() {
        return PAY_POINT;
    }

}
