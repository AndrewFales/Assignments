#ifndef LINKED_STACK
#define LINKED_STACK
#include "Stack.h"
#include "StackUnderflowException.h"

/*

    LinkedStack class derived from the Stack 'interface'.
    Utilizes a linked list data structure to implement stack behavior.
    
*/

//Defining node structure
template<class T>
struct Node{

    T data;
    Node<T>* next;
    
};

template<class T>
class LinkedStack : public Stack<T>{

    private:

        Node<T>* head;

    public:
        LinkedStack();
        void push(const T&);
        bool isEmpty() const;
        bool isFull() const;
        void pop();
        T peek() const;
        ~LinkedStack();

};

//Destructor, iterates through the linked list and deletes/deallocates from memory each node.
template <class T>
LinkedStack<T>::~LinkedStack()
{
    while(head != nullptr)
    {
        Node<T>* temp = head;
        head = head->next;
        delete temp;
        temp = nullptr;
    }
}

//Constructor
template<class T>
LinkedStack<T>::LinkedStack(){
    head = nullptr;
}

//Method to pop an element off the stack, creates a temp node to store head node. 
//Moves the head of the linkedlist to the next node, then frees up the space the previous head node occupied.
template<class T>
void LinkedStack<T>::pop(){

    if(isEmpty()){ //To handle a case where client wants to remove an element that does not exist.
        throw StackUnderflowException();
    }

    Node<T>* temp = new Node<T>;

    temp = head;
    head = head->next;
    free(temp);
}

//Method to push a new element onto the stack. 
//Creates a new node and stores a given value into it, makes the new node the head of the linkedlist
//and pushes the old head node to the next position in the linked list.
template<class T>
void LinkedStack<T>::push(const T& item){

    Node<T>* newNode = new Node<T>;

    newNode->data = item;
    newNode->next = head;
    head = newNode;
}

//Method to check is stack is empty
template<class T>
bool LinkedStack<T>::isEmpty() const{
    return head == nullptr;
}

//LinkedList can never be 'full', but needs to satisfy the Stack interface.
template<class T>
bool LinkedStack<T>::isFull() const{
    return 0;
}

//Returns the value of the node at the top of the stack
template<class T>
T LinkedStack<T>::peek() const{
    return head->data;
}

#endif