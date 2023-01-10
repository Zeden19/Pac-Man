/*
Author: Arjun Sharma (251240847)
BinarySearchTree class that stores the Pel objects in a binary search tree
 */

public class BinarySearchTree implements BinarySearchTreeADT {
    private BNode root; // root of the binary search tree

    // constructor, sets the root to null, therefore creating an empty tree
    public BinarySearchTree() {
        root = new BNode();
    }

    // finding the node in the BST that contains the pel object with the same location as the given location
    // returns the pel object if found, otherwise returns null if not found in the tree
    public Pel get(BNode r, Location key) {
        if (r.isLeaf()) return null; // returning null if key was not found in the BST
        if (r.getData().getLocation().compareTo(key) == 0) return r.getData(); // if we found the key in the BST

        // if the key is less than the current node's key, search the left subtree, otherwise search the right subtree
        else if (r.getData().getLocation().compareTo(key) > 0) return get(r.leftChild(), key);
        else return get(r.rightChild(), key);
    }

    // inserting a pel object into the BST
    // throws a DuplicatedKeyException if the key is already in the tree
    public void put(BNode r, Pel newData) throws DuplicatedKeyException {

        // getting the location of where to add the new node
        BNode node = getNode(r, newData.getLocation());

        // if the node is not a leaf, throw a DuplicatedKeyException
        if (!node.isLeaf()) throw new DuplicatedKeyException("Could not add node");
        else {
            // set the nodes' children and parent to make it an internal node with the new data
            node.setContent(newData);
            node.setLeftChild(new BNode());
            node.setRightChild(new BNode());
            node.leftChild().setParent(node);
            node.rightChild().setParent(node);
        }
    }

    // removes the specified node from the binary search tree
    // throws a InexistentKeyException if the key is not in the tree
    public void remove(BNode r, Location key) throws InexistentKeyException{

        BNode node = getNode(r, key); // getting the node to remove

        if (node.isLeaf()) throw new InexistentKeyException("Key not Found"); // if the node was not found, throw exception

        BNode parent = node.parent(); // parent of the node to remove

        if (node.leftChild().isLeaf() || node.rightChild().isLeaf()) { // if either child is a leaf

            // if the left child is a leaf
            if (node.leftChild().isLeaf()) {
                BNode right = node.rightChild(); // the right child

                if (parent != null) {
                    // if the node to remove is the left child of its parent, set the parent's left child to the right child
                    if (parent.leftChild() == node) parent.setLeftChild(right);
                    else parent.setRightChild(right); // otherwise, set the parent's right child to the right child

                } else {
                    root = right; // if the node to remove is the root, set the root to the right child
                }

            // above processes, except reversed: if the right child is a leaf
            } else {
                BNode left = node.leftChild();
                if (parent != null) {
                    if (parent.leftChild() == node) parent.setLeftChild(left);
                    else parent.setRightChild(left);

                } else {
                    root = left;
                }
            }

        // if both children are internal nodes
        } else {
            try {
                BNode smallest = smallestNode(node.rightChild()); // getting the smallest node in the right subtree
                node.setContent(smallest.getData()); // setting the node to remove's data to the smallest node's data
                remove(smallest, smallest.getData().getLocation()); // removing the smallest node
            } catch (EmptyTreeException e) {
                e.printStackTrace();
            }
        }
    }

    // getting the successor of the node with the given location
    public Pel successor(BNode r, Location key) {

        try {
            if (r.isLeaf()) return null; // if the root was a leaf, return null, since empty tree

            else {
                BNode node = getNode(r, key); // getting the node with the given location

                // if the node has a right child, return the smallest node in the right subtree
                if (!node.isLeaf() && !node.rightChild().isLeaf()) return smallest(node.rightChild());

                    // if the node does not have a right child, return the smallest node in the right subtree of the node's parent
                else {
                    node = node.parent(); // setting the node to its parent

                    // finding the potential successor
                    while (node != null && node.getData().getLocation().compareTo(key) < 0) {
                        node = node.parent();
                    }

                    // if no successor was found return null, otherwise return the successor
                    if (node == null) return null;
                    else return node.getData();
                }
            }

            // catching empty tree exception when we use smallest.
        } catch (EmptyTreeException e) {
            e.printStackTrace();
        }
        return null;
    }

    // getting the predecessor of the node with the given location
    public Pel predecessor(BNode r, Location key){

        try {
            if (r.isLeaf()) return null; // if the root was a leaf, return null, since empty tree

            else {
                BNode node = getNode(r, key); // getting the node with the given location

                // if the node has a left child, return the largest node in the left subtree
                if (!node.leftChild().isLeaf() && !node.isLeaf()) return largest(node.leftChild());

                // if the node does have a left child, return the largest node in the left subtree of the node's parent
                else {
                    node = node.parent(); // setting the node to its parent

                    // finding the potential predecessor
                    while (node != null && node.getData().getLocation().compareTo(key) > 0) {
                        node = node.parent();
                        }

                    // if no predecessor was found return null, otherwise return the predecessor
                    if (node == null) return null;
                    else return node.getData();
                }
            }

            // catching empty tree exception when we use largest.
        } catch (EmptyTreeException e) {
            e.printStackTrace();
        }
        return null;
    }

    // getting the smallest node in the tree
    // throws an EmptyTreeException if the tree is empty
    public Pel smallest(BNode r) throws EmptyTreeException {
        if (r.isLeaf()) throw new EmptyTreeException("Empty tree"); // if empty tree throw EmptyTreeException

        // going thorough the tree until we reach the leftmost node
        while (!r.isLeaf()) {
            r = r.leftChild();
        }

        // returning the data of the leftmost node, which is the smallest node
        return r.parent().getData();
    }

    // getting the largest node in the tree
    // throws an EmptyTreeException if the tree is empty
    public Pel largest(BNode r) throws EmptyTreeException {
        if (r.isLeaf()) throw new EmptyTreeException("Empty tree"); // if empty tree throw EmptyTreeException

        // going thorough the tree until we reach the rightmost node
        while (!r.isLeaf()) {
            r = r.rightChild();
        }

        // returning the data of the rightmost node, which is the largest node
        return r.parent().getData();
    }

    // getting the root of the tree
    public BNode getRoot() {
        return root;
    }

    // getting the node with the given location, same as get except we return a node instead of data
    private BNode getNode(BNode r, Location key) {
        if (r.isLeaf()) return r;
        if (r.getData().getLocation().compareTo(key) == 0) return r;
        else if (r.getData().getLocation().compareTo(key) > 0) return getNode(r.leftChild(), key);
        else return getNode(r.rightChild(), key);
    }

    // getting the smallest node, same as smallest except we return a node instead of data
    private BNode smallestNode(BNode r) throws EmptyTreeException {
            if (r.isLeaf()) throw new EmptyTreeException("Empty tree");
            while (!r.isLeaf()) {
                r = r.leftChild();
            }
            return r.parent();
        }
}

