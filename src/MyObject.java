/*
Author: Arjun Sharma (251240847)
Object class that stores width, height, type, id, location object, and a BST of location objects
 */
public class MyObject implements MyObjectADT{

    private int width; // width of the object
    private int height; // height of the object
    private String type; // type of the object
    private int id; // id of the object
    private Location locus; // location object storing the x and y coordinates
    private BinarySearchTree pelTree; // binary search tree storing the location objects of the object

    // constructor, stores the width, height, type, id, and location object
    public MyObject (int id, int width, int height, String type, Location pos) {
        this.id = id;
        this.width = width;
        this.height = height;
        this.type = type;
        this.locus = pos;
        this.pelTree = new BinarySearchTree();
    }

    // getting the width of the object
    public int getWidth() {
        return width;
    }

    // getting the height of the object
    public int getHeight() {
        return height;
    }

    // getting the type of the object
    public String getType() {
        return type;
    }

    // getting the id of the object
    public int getId() {
        return id;
    }

    // getting the location object of the object
    public Location getLocus() {
        return locus;
    }

    // changing the location object of the object to the specified location object
    public void setLocus(Location value) {locus = value;}

    // changing the type of the object to the specified type
    public void setType(String t) {
        type = t;
    }

    // adding a location object to the binary search tree
    public void addPel(Pel pix) throws DuplicatedKeyException { pelTree.put(pelTree.getRoot(), pix); }

    // checking if the object intersects with the entered object
    // returns true if they intersect, false if they don't
    public boolean intersects(MyObject fig) {
        int otherX = fig.locus.getx(), otherY = fig.locus.gety(); // getting the coordinates for the other object
        int thisX = locus.getx(), thisY = locus.gety(); // getting the coordinates for this object


        try {
            Pel thisPel = pelTree.smallest(pelTree.getRoot()); // getting the smallest pel object for this class

            // searching through the binary search tree for the pel object
            while (thisPel != null) {
                int treeX = thisPel.getLocation().getx(); // x coordinate of the pel object in the tree
                int treeY = thisPel.getLocation().gety(); // y coordinate of the pel object in the tree

                // location object used for comparing the coordinates of the pel object in the tree with the other object
                Location location = new Location(treeX + thisX - otherX, treeY + thisY - otherY);

                // looking through the tree of the other object to see if the pel object in the tree of this object
                // intersects with the other object
                if (fig.pelTree.get(fig.pelTree.getRoot(), location) != null)  { return true; }

                // getting the next value in the BST
                thisPel = pelTree.successor(pelTree.getRoot(), thisPel.getLocation());
            }
            // catching empty tree exception
        } catch (EmptyTreeException e) { System.out.println("error"); }

        // if this BST does not contain any pel objects that intersect with the other object, return false
        return false;
    }
}
