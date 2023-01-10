/*
Author: Arjun Sharma (251240847)
Location class that contains x and y coordinates
 */
public class Location {
    private int x; // x coordinate
    private int y; // y coordinate

    // constructor storing the coordinates
    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // getting the x coordinate
    public int getx() {
        return x;
    }

    // getting the y coordinate
    public int gety() {return y;}

    // comparing this objects coordinate with another objects coordinate
    // returns 1 if this object is greater than the other object, 0 if they are the same,
    // and -1 if this object is less than the other object
    public int compareTo(Location p) {
        if (this.gety() > p.gety()) return 1;

        if (this.gety() == p.gety() && this.getx() > p.getx()) return 1;

        if (this.gety() == p.gety() && this.getx() == p.getx()) return 0;

        if (this.gety() < p.gety()) return -1;

        if (this.gety() == p.gety() && this.getx() < p.getx()) return -1;

        else return -1;
    }

}
