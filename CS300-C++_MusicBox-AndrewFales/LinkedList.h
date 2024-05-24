#ifndef LINKED_LIST
#define LINKED_LIST

#include <iostream>
using namespace std;
/*

Generic Doubly LinkedList class to store songs in a playlist

Andrew Fales

*/

//Defining a node.
template<class T>
struct Node{

    T data;
    struct Node *next;
    struct Node *prev;
};

template<class T>
class LinkedList{
    private:
        Node<T>* head;
        int size;
    public:
        LinkedList();
        ~LinkedList();
        void push_back(const T&);
        void print();
        int getSize();
        T at(int);
        bool contains(T);
        bool remove(T&);
        bool replace(int, const T&);
        int getIndex(T&);
        bool isEmpty();
};

//Constructor
template<class T>
LinkedList<T>::LinkedList(){
    head = nullptr;
    size = 0;
}

//Destructor
template<class T>
LinkedList<T>::~LinkedList(){
    while(head != nullptr){

        Node<T>* temp = head;
        head = head->next;
        delete temp;
        temp = nullptr;
    }
}

//Adds an item to the end of the linked list.
template<class T>
void LinkedList<T>::push_back(const T& item){

    Node<T>* newNode = new Node<T>;
    newNode->data = item;
    newNode->next = nullptr;
    if(head == nullptr){ //If there are no nodes in the current linked list, add node to the front
        newNode->prev = nullptr;
        head = newNode;
        size++;
        return;
    }
    Node<T>* temp = head;
    while(temp->next != nullptr){//Traverse to the last node
        temp = temp->next;
    }

    temp->next = newNode;
    newNode->prev = temp;
    size++;
    return;
}

//Removes a specific item from the linked list
template<class T>
bool LinkedList<T>::remove(T& item){
    Node<T>* temp = head;
    if(contains(item)){ //Checks to see if the item exists in the list
        int idx = getIndex(item);
        if(idx == 0){
            head = temp->next;
        }
        else{//Managing the prev/next pointers for the node before and after the node to delete
            for(int i=0; i < idx; i++){
                temp = temp->next;
            }
            if(temp->next != nullptr){
                temp->next->prev = temp->prev;
            }
            if(temp->prev != nullptr){
                temp->prev->next = temp->next;
            }
        }
        size--;
        free(temp); //frees the space allocated to the node we removed from the linked list
        return true;
    }
    return false;
}

//Replaces an item in the linkedlist at a given index.
template<class T>
bool LinkedList<T>::replace(int idx, const T& item){
    Node<T>* temp = head;
    if(idx == 0){//Update head data with item if index is the front
        temp->data = item;
    }
    else{
        for(int i=0; i < idx; i++){//Traverse to the index we want to replace.
            temp = temp->next;
        }
        temp->data = item;
        return true;
    }
    return false;
}

//Returns the item at a given index
template<class T>
T LinkedList<T>::at(int idx){
    Node<T>* cur = head;
    int count = 0;
    while(cur != nullptr){//Traverse to the index we want.
        if(count == idx)
            return cur->data;
        count++;
        cur = cur->next;
    }
}

//Utility function to print the nodes in the linked list
template<class T>
void LinkedList<T>::print(){
    for(int i = 0; i < size; i++){
        cout<<at(i)<<endl;
    }
}

//Returns the index of a given item
template<class T>
int LinkedList<T>::getIndex(T& item){
    for(int i = 0; i < size; i++){
        if(item == at(i))
            return i;
    }
    return -1;
}

//Checks to see if a given item is in the linked list
template<class T>
bool LinkedList<T>::contains(T item){
    for(int i = 0; i <= size; i++){
        if(item == at(i))
            return true;
    }
    return false;
}

//Utility functions
template<class T>
int LinkedList<T>::getSize(){
    return size;
}

template<class T>
bool LinkedList<T>::isEmpty(){
    return size == 0;
}
#endif