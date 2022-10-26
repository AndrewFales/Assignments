import java.util.LinkedList;
import java.util.Queue;

public class BST {
    private class TreeNode {
        int key;
        TreeNode left;
        TreeNode right;
    
        public TreeNode(int key) {
            this.key = key;
        }
    }

    private TreeNode root;
    private int size = 0;

    public int size() {
        return size;
    }

    // This method returns true if the key exists in the tree
    public boolean hasKey(int key) {
        // we need to call the recursive private method (auxillary method)
        return hasKey(root, key);
    }

    // This is the recursive method that performs a binary search on the tree and returns true
    //  if it finds the key. Otherwise false
    private boolean hasKey(TreeNode node, int key) {
        // TODO: base case 1: if node is null what should you return?
        if(node == null){
            return false;
        }
        // TODO: base case 2: if you found the key (node.key == key) what should you return?
        if(node.key == key){
            return true;
        }
        if(key < node.key){
            return hasKey(node.left, key);
        }
        else if(key > node.key){
            return hasKey(node.right, key);
        }
        else{
            return true;
        }

        // TODO: we recursively need to search the left sub tree and the right subtree.
        //  if the key is in either left or right, we return true
    }

    // This method returns the height of the tree
    public int height() {
        // We need to call the private recursive methods
        return height(root);
    }

    // This method recursively calls itself to calculate the height of the tree
    private int height(TreeNode node) {
        // TODO: base case: if the tree is empty or the tree has 1 node height should be 0. so return 0
        if(node == null){
            return -1;
        }
        // TODO: otherwise, we want to return 1 + maximum of the height of left subtree and the right subtree
        else{
            int leftHeight = height(node.left);
            int rightHeight = height(node.right);
            if(leftHeight > rightHeight)
                return (leftHeight + 1);
            else
                return(rightHeight + 1);
        }
    }

    // This is the public method that the user of the class calls to add a key
    public void add(int key) {
        // We want to maintain unique keys in the BST
        // TODO: check if the key is already in the tree by calling hasItem. If it does, simply return
        if(hasKey(key))
            return;
        // Here you need to call the recursive private add method and give it root and key. 
        //    it will return the root, so store it in root
        root = add(root, key);
        // TODO: don't forget the size!
        size++;
    }

    // Recursive add method
    // We need this method because we don't want the user of BST to maintain a TreeNode or know about it at all
    private TreeNode add(TreeNode root, int key) {
        // TODO: base case: if the tree is empty, we create and return a new node
        if(root == null){
            return new TreeNode(key);
        }
        // TODO: otherwise, we need to search and find the correct spot to add the node
        // TODO: if key is less than the root, we want to add the key to the left subtree and assign the modified left subtree to root.left
        if(key < root.key){
            root.left = add(root.left, key);
        }
        // TODO: else, we want to add the key to the right subtree and assign it to root.right
        else if(key > root.key){
            root.right = add(root.right, key);
        }
        // Note: remember, these are recursive calls so you should call this same method and pass the correct root node and key to it

        // In the end we return the root
        return root;
    }

    // This method converts the tree into a pre-order string
    public String preOrderString() {
        // TOOD: if root is null we should return an empty string
        if(root == null){
            return "";
        }
        // Call the private recursive preOrderString method and return what it returns
        // Note: trim() will trim the extra white spaces from both ends of a string
        return preOrderString(root).trim();
        
    }

    // The recursive method to traverse the tree in a preOrder manner
    private String preOrderString(TreeNode node) {
        // TODO: base case
        if(node == null)
            return "";
        // We need a String variable. In pre-order traversal, we visit the node (add its key to the String) first.
        // The extra white space is needed for a better print out
        String str = " " + node.key;
        // TODO: call the same method on the left subtree and append what it returns to str
        str = str + preOrderString(node.left);
        // TODO: do the same with right subtree
        str = str + preOrderString(node.right);
        return str;
    }

    // In order to string traversal
    public String inOrderString() {
        // TODO: similar to preOrderString
        if(root == null){
            return "";
        }
        return inOrderString(root).trim();
    }

    private String inOrderString(TreeNode root) {
        //TODO: complete the method
        if(root == null){
            return "";
        }
        String lefString, riString;
        if(root.left != null){
            lefString = inOrderString(root.left);
        }
        else{
            lefString = " ";
        }
        if(root.right != null){
            riString = inOrderString(root.right);
        }
        else{
            riString = " ";
        }
        return
            lefString + root.key + riString;
    }

    // Level order traversal to string
    public String levelOrderString() {
        // Since this method is not recursive, we don't need an auxillary method
        // TODO: watch out for empty trees
        if(root == null)
            return "";
        //NOTE: refer to the lecture about level order traversal and complete the algorithm below
        // We can use the built-in queue provided by java libraries
        Queue<TreeNode> q = new LinkedList<>();
        
        //TODO: add the root to the queue by calling "add" on q
        q.add(root);
        // We are going to add keys to this string variable
        String str = "";

        // TODO: as long as q is not empty... (you can call the isEmpty method on the queue)
        while(!q.isEmpty()){
            TreeNode tempNode = q.poll();
            str = str + " " + tempNode.key;
            if(tempNode.left != null){
                q.add(tempNode.left);
            }
            if(tempNode.right != null){
                q.add(tempNode.right);
            }
        }
            // TODO: dequeue a node from q by calling "poll" and store it in a temporary TreeNode

            // TODO: append the tmp node's key to str + an single space 
            
            // TODO: if the left node of tmp is not null, add it to the queue
            
            // TODO: if the right node of tmp is not null, add it to the queue
            

        return str.trim();
    }

    // The delete method
    public void delete(int key) {
        // TODO: call the recursive delete method
        delete(root, key);
        size--;            
    }

    // Recursive delete
    private TreeNode delete(TreeNode node, int key) {
        //TODO: Base Case: If the tree is empty
        if(node == null){
            return node;
        }

        //TDOO: Otherwise, recur down the tree. The mechanism is similar to add
        // if key is less than the node's key, call delete on the left subtree and assign it to node.left
        if(key < node.key){
            node.left = delete(node.left, key);
        }
        // else if key is greater, call delete on right subtree and assign it to node.right
        else if(key > node.key) {
            node.right = delete(node.right, key);
        }

        // ELSE case is when key is same as node's key, then This is the node to be deleted
        else {
            // Node with only one child or no child
            if (node.left == null)
                return node.right;
            else if (node.right == null)
                return node.left;

        //     // node with two children: Get the in-order successor (smallest in the right subtree)
        //     // This is the step where we swap the value of the node with the smallest of the right subtree
        //     // TODO: find the smallest in the right subtree by calling minValue. Store what it returns in node.key
            node.key = minValue(node.right);

        //     // TODO: now we can just call delete on the right subtree to remove the leaf node. call delete and store what 
        //     /// it returns in node.right
            node.right = delete(node.right, node.key);
         }
        return node;
    }

    // Finds the minimum value in a tree (BST lecture)
    private int minValue(TreeNode node) {
        //TODO: traverse down the left subtree with a while loop.
        int minVal = node.key;
        while(node.left != null){
            minVal = node.left.key;
            node = node.left;
        }
        //TODO: return the node's key
        
        return minVal;
    }

    @Override
    public String toString() {
        return treeToString(root);
    }
    private String treeToString(TreeNode root) {
        if (root == null) {
            return "";
        }
        if (root.left == null && root.right == null) {
            return root.key + "";
        }
        if (root.right == null) {
            return root.key + "(" + treeToString(root.left) + ")";
        }

        return root.key + "(" + treeToString(root.left) + ")(" + treeToString(root.right) + ")";
    }

}