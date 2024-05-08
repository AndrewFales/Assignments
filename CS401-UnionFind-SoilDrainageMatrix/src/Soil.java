import java.io.*;
import java.util.*;

public class Soil {
    private int[] parent; //Array for the index of the parent for each element
    private int[] size; //Array for the size of the tree for each root
    private int n; //Grid size
    private int virtualTop; //Conceptual node representing all top row cells that can drain water
    private int virtualBottom;//Conceptual node representing all bottom row cells that can drain water

    /*
    * Constructor for initializing the soil matrix
    * filePath is the path to the file containing the soil grid matrix
    * IOException in case there is an error reading the file
    */
    public Soil(String filePath) throws IOException {
        List<int[]> matrix = loadMatrixFromFile(filePath);
        n = matrix.size();
        int totalCells = n * n;
        parent = new int[totalCells + 2]; //Includes two virtual nodes to simplify connectivity
        size = new int[totalCells + 2];
        virtualTop = totalCells; //Second to last index for virtual top
        virtualBottom = totalCells + 1; //Last index for virtual bottom

        for (int i = 0; i < totalCells + 2; i++) {
            parent[i] = i; //Initially each node is its parent
            size[i] = 1;
        }
        
        initialize(matrix);
    }

    /*
     * Initializing union-find structure for a given soil matrix
     */
    private void initialize(List<int[]> matrix) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix.get(i)[j] == 1) {
                    int id = i * n + j; //ID for the current cell
                    if (i == 0) {
                        union(id, virtualTop); //Connect top row cells to virtual top
                    }
                    if (i == n - 1) {
                        union(id, virtualBottom); //Connect bottom row cells to virtual bottom
                    }
                    //Union with the cell to the right given it is also 1
                    if (i < n - 1 && matrix.get(i + 1)[j] == 1) {
                        union(id, (i + 1) * n + j);
                    }
                    //Union with the cell underneath given it is also 1
                    if (j < n - 1 && matrix.get(i)[j + 1] == 1) {
                        union(id, i * n + (j + 1));
                    }
                }
            }
        }
    }

    /*
     * Finds if there is a path from the virtual top node to the virtual bottom node
     */
    public boolean doesDrain() {
        return find(virtualTop) == find(virtualBottom);
    }

    /*
     * Finds the root of a given node, and compresses the tree for efficiency purposes
     */
    private int find(int p) {
        int root = p;
        //First traversal, to find the root of the tree
        while (root != parent[root]) {
            root = parent[root];
        }
        //Second traversal to compress the tree
        while (p != root) {
            int next = parent[p]; //Save the next node
            parent[p] = root; //Point current node directly to the root
            p = next; //Move p to the next node up
        }
        return root;
    }

    /*
     * Joins two nodes p and q into a single set if they are not already connected
     */
    private void union(int p, int q) {
        int rootP = find(p); //Find root of node p
        int rootQ = find(q);//Find root of node q
        //Check if they are in the same set
        if (rootP != rootQ) {
            //If rootP's tree is smaller, attach it under rootQ's tree
            if (size[rootP] < size[rootQ]) {
                parent[rootP] = rootQ;
                size[rootQ] += size[rootP];
            } 
            //If rootQ's tree is smaller or equal, attach it under rootP's tree
            else { 
                parent[rootQ] = rootP;
                size[rootP] += size[rootQ];
            }
        }
    }

    /*
     * Loads a soil matrix from a given file path
     */
    private List<int[]> loadMatrixFromFile(String filePath) throws IOException {
        List<int[]> rows = new ArrayList<>(); //Empty list to store the rows of the matrix
        BufferedReader reader = new BufferedReader(new FileReader(filePath)); //Open file
        String line; //Store the line

        //Loop through each line of the file until the end is reached.
        while ((line = reader.readLine()) != null) {
            String[] values = line.trim().split("\\s+"); //Split line into an array
            int[] row = Arrays.stream(values).mapToInt(Integer::parseInt).toArray(); //Conversion from string to int
            rows.add(row); //Add the array to the list
        }
        reader.close();
        return rows;
    }
}
