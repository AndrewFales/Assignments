import java.util.Arrays;

class DataItem {
    long key;
    String item;

    public DataItem(long key, String item) {
        this.key = key;
        this.item = item;
    }

    @Override
    public String toString() {
        return String.format("{%s:%s}", key, item);
    }
}
//testing sync
public class HashMap {
    private int size = 0;
    private static final int INITIAL_SIZE = 10;
    private static final int DELETED_KEY = 0;
    private DataItem[] items;
    
    public HashMap() {
        items = new DataItem[INITIAL_SIZE];
    }

    public int size() {
        return size;
    }

    /*
        the hashing method that converts a string to a long value
        this method performs a simple conversion by multiplying each
        character's value to 27 to the power of the position of the
        character in the index.
        Example : "str" -> 's'*27^0 + 't'*27^1 + 'r'*27^2 
    */
    public long hashFunction(String key) {
        // TODO: implement the hash function as described
        long returnVal = 0;
        long x;
        for(int i = 0; i < key.length(); i++){
            char character = key.charAt(i);
            int ascii = (int) character;
            x = (long) Math.pow(27, (double) i);
            returnVal = ascii*x + returnVal;
            }
        return returnVal;
    }

    /*
        This method adds the item to the array by converting the key to 
        the hashed long. In case of collision, the method will perform a 
        linear probe to find an empty spot for insertion
    */
    public void put(String key, String value) throws TableIsFullException {
        // TODO: throw the TableIsFullException if size is greater than or equal to table.length - 1
        if(size >= items.length -1){
            throw new TableIsFullException();
        }
        // TODO: do a linear probe and insert the value into the data item into the table
        long temp = hashFunction(key) % INITIAL_SIZE;
        long i = temp;
        DataItem newVal = new DataItem(hashFunction(key), value);
        do{
            if(items[(int) i] == null){
                items[(int) i] = newVal;
                size++;
                return;     
            }
            if(items[(int) i].equals(key)){
                newVal.item = value;
                return;
            }
            
        }
        while (i!= temp);
    }

    // This method will perform a linear probe and return the v
    
//alue stored in the DataItem at index
    public String get(String key) {
        //TODO : complete the method
        long i = hashFunction(key) % INITIAL_SIZE;
        // for( DataItem item : items){
        //     if(item.key == i){
        //         return item.item;
        //     }
        // }
        // while(items[(int) i] != null){
        //     if(items[(int) i].equals(key) ){
        //         return items[(int) i].item;
        //     }
        // }
        if(items[(int) i] == null){
            return null;
        }
        return items[(int) i ].item;
    }

    // Updates the value at key
    public void update(String key, String newValue) {
        //TODO: complete the method
        long temp = hashFunction(key) % INITIAL_SIZE;
        items[(int) temp].item = newValue;
        }

    public String delete(String key) {
        //TODO: delete will not set the item at the calculated index to null
        //  it will rather just set the key to DELETED_KEY
        long temp = hashFunction(key) % INITIAL_SIZE;
        items[(int)temp].key = DELETED_KEY;
        --size;
        return items[(int)temp].item;
    }

    @Override
    public String toString() {
        return Arrays.toString(items);
    }

}

class TableIsFullException extends Exception {}
