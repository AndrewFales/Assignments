
public class MinHeap<T> {
    private Data[] heap;
    public T object;
    private int size = 1;
    private static final int DEFAULT_CAPACITY = 10;

    public class Data implements Comparable<Data>{
        public T object;
        private double prio;
        public Data(){

        }
        public Data(T obj, double priority){
            this.object = obj;
            this.prio = priority;
        }
        public double getPrio(){
            return prio;
        }
        public T getObj(){
            return object;
        }
        @Override
        public int compareTo(MinHeap<T>.Data tData){
            if(prio > tData.prio)
                return 1;
            if(prio == tData.prio)
                return 0;
            return -1;
        }
    }

    public MinHeap(){
        heap = new MinHeap.Data[DEFAULT_CAPACITY];
        this.size = 0;
    }

    private int parentIndex(int i){
        return i / 2;
    }

    private int leftChild(int i){
        return (2 * i);
    }

    private int rightChild(int i){
        return (2 * i) + 1;
    }

    private boolean hasParent(int i){
        if(i > 1){
            return true;
        }
        return false;
    }

    private boolean hasLeftChild(int i){
        if(leftChild(i) <= size){
            return true;
        }
        return false;
    }

    private boolean hasRightChild(int i){
        if(rightChild(i) <= size){
            return true;
        }
        return false;
    }

    private Data getParent(int i){
        return  heap[parentIndex(i)];
    }
    //Swaps two given nodes
    private void swap(int i, int x){
        Data temp;
        temp = heap[i];
        heap[i] = heap[x];
        heap[x] = temp;
    }


    // Offer accepts an object of type T and a priority value associated with it
    public void offer(T obj, double priority) {
        Data newData = new Data(obj, priority);
        size++;
        heap[size] = newData;
        bubbleUp();
    }
    //Percolates through the heap swaping nodes as needed to ensure a minheap property is met.
    public void bubbleUp(){
        int idx = size;
        while(hasParent(idx) && (getParent(idx).compareTo(heap[idx]) > 0)){
            swap(idx, parentIndex(idx));
            idx = parentIndex(idx);
        }
    }
    //Ensures that minheap property is met when the head node is removed from the heap.
    public void bubbleDown(){
        int idx = 1;
        while(hasLeftChild(idx)){
            int leftC = leftChild(idx);
            if(hasRightChild(idx) && heap[leftChild(idx)].compareTo(heap[rightChild(idx)]) > 0){
                leftC = rightChild(idx);
            }
            if(heap[idx].compareTo((heap[leftC])) > 0){
                swap(idx, leftC);
            }
            else break;
            idx = leftC;
        }
    }

    // Reads the value stored on the top of the heap but doesn't remove it
    public T peek() {
        if(size == 0){
            return null;
        }
        return heap[1].getObj();
    }
    // Removes an object from the top of the heap and returns it
    public T poll() {
        T returnObj = heap[1].getObj();
        swap(1, size);
        heap[size] = null;
        size--;
        bubbleDown();
        return returnObj;
    }

    // returns true if the object is in the heap, false otherwise
    // What is the time complexity of this operation?
    public boolean contains(T obj) {
        for(int i = 1; i <= size; i++){
            if(obj.equals(heap[i].getObj()))
                return true;
        }
        return false;
    }

    public int size() {
        return size;
    }
    //used for testing purposes
    public void print(){
        for(int i = 0; i < size; i++){
            System.out.println(heap[i].getObj());
        }
    }
}
