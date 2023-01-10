/*
Author: Arjun Sharma (251240847)
BNode class that is used for the Binary Search Tree, stores a Pel object for the key
 */

public class BNode {
    private Pel data; // the data, which is the key
    private BNode left; // left child
    private BNode right; // right child
    private BNode parent; // parent node

    // constructor, stores the data and sets the children and parents to specified value, used for internal nodes
    public BNode(Pel value, BNode left, BNode right, BNode parent) {
        this.data = value;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }

    // constructor,  sets the children, parent and data to null, used for leaf nodes
    public BNode() {
        data = null;
        left = null;
        right = null;
        parent = null;
    }

    // getting the parent node
    public BNode parent() {return parent;}

    // setting the parent node
    public void setParent(BNode newParent) { parent = newParent;}

    // setting the left child
    public void setLeftChild (BNode p) {left = p;}

    // setting the right child
    public void setRightChild (BNode p) {
        right = p;
    }

    // setting the data
    public void setContent (Pel value) {data = value;}

    // checking if this node is a leaf node
    public boolean isLeaf() {return left == null && right == null;}

    // getting the data, which is the pel object
    public Pel getData () {return data;}

    // getting the left child
    public BNode leftChild() {return left;}

    // getting the right child
    public BNode rightChild() {return right;}

}
