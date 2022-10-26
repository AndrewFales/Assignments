import java.util.Arrays;

public class SuperArray<T> {
    private Object[] items;
    private int size = 0;
    private static final int INITIAL_SIZE = 3;

    public SuperArray() {

        this.items = new Object[INITIAL_SIZE];
    }

    // put or update
    public void update(int i, T item) {
        rangeCheck(i);
        // TODO
        this.items[i] = item;
    }

    // get item at index (read)
    public T get(int i) {
        rangeCheck(i);
        // every time we need to return an element in the array, we call elements(int i)
        return element(i);
    }
    // find item (search) 
    // this method will return the index of the first occurrence of the item in the array
    public int find(T item) {
        int returnVal = -1;
        //TODO: loop through the array up to this.size and as soon as you find the item return the index (i)
        if(item != null){
            for(int i = 0; i < this.size; i++){
                if(this.items[i].equals(item)){
                    returnVal = i;
                    return returnVal;
                }
            }
        }
        // TODO: by the time you get out of the loop you haven't found the item, so return -1
        return returnVal;
    }

    // This method will return the size which determines the size of the array
    public int size() {
        return size;
    }

    // This method returns the capacity (only for testing purposes)
    public int capacity() {
        return items.length;
    }

    // Add items to the array at a specific index
    public void add(int i, T item) {      
        // TODO: the trick here is to start at the last index, copy each element to the right
        //  and go down and finally store item at index i
        Object[] newItems = this.copyOf(items, items.length);
        for(int x = 0; x < this.size+1; x++){
            if(x < i - 1){
                newItems[x] = items[x];
            }
            else{
                if(x == i -1){
                    newItems[x] = item;
                }
                else{
                    newItems[x] = items[x - 1];
                }
            }
        }
        size++;
        items = newItems;
        
        // Hint: call copyOf to make a copy of the current array if size >= items.length
    }

    public void push(T item) {
        //TODO: implement the push method
        if(size == items.length){
            items = this.copyOf(items, items.length);
        }
        items[size++] = item;
        // Hint: call copyOf to make a copy of the current array if size >= items.length
    }

    public Object pop() {
        // TODO: implement pop
        T item = this.element(--size);
        if(item == null){
            return null;
        }
        // return null if empty
        return item;
    }

    // TODO: this method will copy everything from a to a new array of bigger size and return it
    private Object[] copyOf(Object[] a, int newLength) {
        // TODO: newLenght determines the length of the new array
        newLength = items.length + items.length/2;
        Object[] newItems = new Object[newLength];
        for(int i = 0; i < this.size; i++){
            
            newItems[i] = a[i];
        }
        return newItems;
        
    }

    // delete an item (by index or by object?)
    //-- by index
    
    // In the first overload we are going to delete an item at the provided index
    // TODO: write a method that receives an index and "deletes" the item at that index
    // write a public method that returns nothing (void) called delete which receives an index
    public void delete(int i) {
        Object[] tempArr = null;
        // TODO: make sure the index is in range by calling rangeCheck(i);
        
        // TODO: the idea is to write a loop that starts at the index passed to the method, all the way up to "size - 1"
        //   then it copies the next cell to the current one. This shifts all values in the array 
        // HINT: arr[i] = arr[i + 1]
        rangeCheck(i);
        // the idea is to write a loop that starts at the index passed to the method, all the way up to "size - 1"
        //   then it copies the next cell to the current one. This shifts all values in the array 
        // HINT: arr[i] = arr[i + 1]
        
        // set the last cell's value to null
        
        // TODO: After the loop, set the last cell's value to null and decrement size
        for (int x = 0; x < this.size -1; x++) {
            if(i == x){
                tempArr = new Account[this.size - 1];
                for(int index = 0; index < x; index++){
                    tempArr[index] = this.items[index];
                }
                for(int j = x; j < this.size - 1; j++){
                    tempArr[j] = this.items[j+1];
                }
                break;
            }
        }
        this.items[this.size - 1] = null;
        this.size = this.size -1;
        this.items = tempArr;
    }

    //-- by object
    // the idea is to delete the first occurrence of the item in the array
    // you already have the methods that can take care of this. Just use those
    public void delete(T item) {
        // TODO: call the find method to find the index then delete the item at that index
        // HINT: you already have all the methods you need. Just call them!!
        this.delete(this.find(item));
    }

    // this method will check the range of the index and throw an exception if it's not in range
    private void rangeCheck(int i) {
        if (i < 0 || i >= size)
            throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public String toString() {
        return Arrays.toString(items);
    }

    // This method is used to return an element in the array as T
    @SuppressWarnings("unchecked")
    private T element(int i) {
        return (T) items[i];
    }
}