import java.util.Arrays;

public class SuperArray {
    private Account[] accounts;
    private int size = 0;

    public SuperArray(int size) {
        this.accounts = new Account[size];
    }

    // put or update
    public void update(int i, Account account) {
        // TODO: call rangeCheck(i) to ensure the index is in range
        this.rangeCheck(i);
        // TODO: assign the account to the location at i
        this.accounts[i] = account; 
    }

    // get item at index (read)
    public Account get(int i) {
        // TODO: rangeCheck(i);
        this.rangeCheck(i);
        // TODO: return the value stored at i
        return this.accounts[i];
    }
    // find item (search) 
    // this method will return the index of the first occurrence of the item in the array
    public int find(Account account) {
        //TODO: loop through the array up to this.size and as soon as you find the account return the index (i)
        int returnVar = -1;
        if(account.getUserName() != null && account.getPassword() != null){
            for(int i = 0; i < this.size; i++){
                if(accounts[i].getUserName() == account.getUserName() && accounts[i].getPassword() == account.getPassword()){
                    returnVar = i;
                    break;
                }
            }
            return returnVar;
        }
        // TODO: by the time you get out of the loop you haven't found the item, so return -1
        return returnVar;
    }

    // TODO: this method will return the size which determines the size of the array
    public int size() {
        return size;
    }

    // insert (unique or not unique?)
    public void add(Account ac) {
        if (size >= accounts.length){
            throw new ListIsFullException();
        }
        
        // TODO: add ac to the accounts array at the last index which is the same as size
        accounts[size] = ac;
        // TODO: increment the size by one
        size++;
    }

    // delete an item (by index or by object?)
    //-- by index
    
    // In the first overload we are going to delete an item at the provided index
    // TODO: write a method that receives an index and "deletes" the item at that index
    // write a public method that returns nothing (void) called delete which receives an index
    public void delete(int i) {
        Account[] tempArr = null;
        
        // TODO: make sure the index is in range by calling rangeCheck(i);
        this.rangeCheck(i);
        // TODO: the idea is to write a loop that starts at the index passed to the method, all the way up to "size - 1"
        //   then it copies the next cell to the current one. This shifts all values in the array 
        // HINT: arr[i] = arr[i + 1]
        
        if(i == this.size -1){
            for (int x = 0; x <= this.size -1; x++) {
                if(i == x){
                    tempArr = new Account[this.size - 1];
                    for(int index = 0; index < x; index++){
                        tempArr[index] = this.accounts[index];
                    }
                    for(int j = x; j < this.size - 1; j++){
                        tempArr[j] = this.accounts[j+1];
                    }
                    break;
                }
            }
        }
        else{
            for (int x = 0; x < this.size -1; x++) {
                if(i == x){
                    tempArr = new Account[this.size - 1];
                    for(int index = 0; index < x; index++){
                        tempArr[index] = this.accounts[index];
                    }
                    for(int j = x; j < this.size - 1; j++){
                        tempArr[j] = this.accounts[j+1];
                    }
                    break;
                }
            }
        }
        // for (int x = 0; x < this.size -1; x++) {
        //     if(i == x){
        //         tempArr = new Account[this.size - 1];
        //         for(int index = 0; index < x; index++){
        //             tempArr[index] = this.accounts[index];
        //         }
        //         for(int j = x; j < this.size - 1; j++){
        //             tempArr[j] = this.accounts[j+1];
        //         }
        //         break;
        //     }
        // }
        // TODO: After the loop, set the last cell's value to null and decrement size
        this.accounts[this.size -1] = null;
        this.size = this.size -1;
        this.accounts = tempArr;
    }

    //-- by object
    // the idea is to delete the first occurrence of the item in the array
    // you already have the methods that can take care of this. Just use those
    public void delete(Account ac) {
        // TODO: call the find method to find the index then delete the item at that index
        // HINT: you already have all the methods you need. Just call them!!
        this.delete(this.find(ac));
        //this.delete(this.find(ac));

    }

    // this method will check the range of the index and throw an exception if it's not in range
    private void rangeCheck(int i) {
        if (i < 0 || i >= size)
            throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public String toString() {
        return Arrays.toString(accounts);
    }
}

class ListIsFullException extends RuntimeException {}
