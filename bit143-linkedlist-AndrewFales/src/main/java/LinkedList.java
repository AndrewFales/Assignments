public class LinkedList<T> {
    // Definition of the Node class that LinkedList internally uses
    class Node {
        T item;
        Node next;

        public Node() {
        }

        public Node(T item) {
            this.item = item;
        }
    }

    private Node head;
    private Node tail;
    // to keep track of the size of the array
    private int size = 0;

    public void push(T item) {
        // TODO: complete the push method
        // You need to consider 2 cases. When list is empty and when it's not
        // Don't forget to increment size!
        if(head == null){
            Node l = new Node(item);
            head = l;
            tail = head;
        }
        else{
            Node toAdd = new Node(item);
            tail.next = toAdd;
            tail = toAdd;
        }
        size++;
    }

    public T get(int i) throws ListIndexOutOfBoundException {
        rangeCheck(i); // this throws an exception but we won't catch it here
        // TODO: complete the rest of the method
        // instead of null, you should return the item stored in the node
        Node cur = head;
        for(int x = 0; cur != null && x < i; x++){
            cur = cur.next;  
        }
        if(cur != null){
            return cur.item;
        }
        else{
            return null;
        }
    }

    public int find(T item) {
        // TODO: complete the find method
        Node cur = head;
        int returnVal = -1;
        for(int i = 0; i < size; i++){
            if( cur.item.equals(item)){
                returnVal = i;
                return returnVal;
            }
            cur = cur.next;
        }
        return returnVal;
    }

    public void insert(int i, T item) throws ListIndexOutOfBoundException {
        // TODO: Complete the insert method
        /* there are 3 cases: 
        1. list is empty (head is null)
        2. inserting at the beginning of the list (i = 0)
        3. inserting anywhere else

        the new node will be inserted at the index and push the
        current node to right. e.g.
        [ac1][ac2][ac3]
        insert(1, ac1.5)
        [ac1][ac1.5][ac2][ac3]
        insert(3, ac2.5)
        [ac1][ac1.5][ac2][ac2.5][ac3]
        */
        Node toAdd = new Node(item);
        if(head == null){     
            head = new Node(item);
            tail = head;
            size++;
            return;
        }
        if(i == 0){
            this.push(item);
            return;
        }
        rangeCheck(i);
        Node temp = head;
        for(int x = 0; x < i -1; x++){
            temp = temp.next;
        }
        toAdd.next = temp.next;
        temp.next = toAdd;
        size++;
    }

    public int size() {
        return size;
    }

    public T delete(int i) throws ListIndexOutOfBoundException, EmptyListException {
        // TODO: complete the delete method
        // use this to store the item and return it in the end
        T deletedItem = this.get(i);
        
        /*
        similar to insert, consider 3 cases
        empty list, insert at the beginning, and anywhere else
        don't forget to decrement size

        💡 HINT: you can use cursor.next = cursor.next.next
        */
        Node cur = head;
        if(head == null){
            throw new EmptyListException();
        }
        if(i == 0){
            deletedItem = head.item;
            head = head.next;
            --size;
            return deletedItem;
        }
        for(int x = 0; x < i -1; x++){
            cur = cur.next;
        }
        cur.next = cur.next.next;
        --size;
        return deletedItem;

    }

    // Utility methods
    public void rangeCheck(int i) throws ListIndexOutOfBoundException {
        if (i < 0 || i >= size)
            throw new ListIndexOutOfBoundException();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node cur = head;
        sb.append("[");
        while (cur != null) {
            if (cur.next == null) {
                sb.append(cur.item.toString());
            } else {
                sb.append(cur.item.toString());
                sb.append(", ");
            }
            cur = cur.next;
        }

        sb.append("]");
        return sb.toString();
    }
}

class ListIndexOutOfBoundException extends Exception {
}

class EmptyListException extends Exception {
}
