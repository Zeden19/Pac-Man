/*
Author; Arjun Sharma (251240847)
Pel class that contains the location of a object and the color of the object
 */

public class Pel {
    private Location location; // Location object storing the x and y coordinates
    private int color; // color of the object

    // constructor, stores the object's location and color
    public Pel(Location p, int color) {
        this.location = p;
        this.color = color;
    }

    // getting the location of the object
    public Location getLocation() {
        return location;
    }

    // getting color of object
    public int getColor() {
        return color;
    }
}
