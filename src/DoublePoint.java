/** A Point with type double values across a 2D-coordinate system. */
public class DoublePoint {
    /** Field for the x-axis as double type. */
    private double x;
    /** Field for the y-axis as double type. */
    private double y;

    /** Constructor with no arguments which creates a point at origin. */
    public DoublePoint(){
        this.x = 0;
        this.y = 0;
    }

    /** Constructor that takes x and y as arguments to create a point at (x,y) in a 2D-Coordinate system.
     * @param x x-Coordinate
     * @param y y-Coordinate
     */
    public DoublePoint(double x, double y){
        this.x = x;
        this.y = y;
    }
    /** Retrieves the value x from the point. */
    public double getX() {
        return x;
    }

    /** Retrieves the value y from the point. */
    public double getY() {
        return y;
    }

    /** Sets the values x and y to new values
     * @param x x-Coordinate
     * @param y y-Coordinate
     */
    public void setLocation(double x, double y){
        this.x = x;
        this.y = y;
    }

    /** Sets new x and y coordinates from another point.
     * @param location A location/position in a 2D-Coordinate system.
     */
    public void setLocation(DoublePoint location){
        this.x = location.getX();
        this.y = location.getY();
    }

    /** Moves the point coordinates with the change dx and dy.
     * @param dx change in x-Coordinate
     * @param dy change in y-Coordinate
     */
    public void translate(double dx, double dy){
        this.x += dx;
        this.y +=dy;
    }

    /** Retrieve the location of the point as a new point. */
    public DoublePoint getLocation(){
        return new DoublePoint(this.x, this.y);
    }

}
