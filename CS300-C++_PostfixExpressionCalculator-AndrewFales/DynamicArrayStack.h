#ifndef DYNAMIC_ARRAY_STACK
#define DYNAMIC_ARRAY_STACK
#include "Stack.h"
#include "StackOverflowException.h"
#include "StackUnderflowException.h"


/*

    DynamicArrayStack class derived from the Stack 'interface'.
    Utilizes a dynamic array to implement stack behavior.
    Resizes itself if client needs additional stack space.
*/

template<class T>
class DynamicArrayStack : public Stack<T>{
    private:
        T* array;
        int top;
        int capacity;
    public:
        DynamicArrayStack(int);
        void push(const T&);
        void pop();
        T peek() const;
        bool isEmpty() const;
        bool isFull() const;
        void resize();
        ~DynamicArrayStack();
};

//Destructor
template<class T>
DynamicArrayStack<T>::~DynamicArrayStack(){
    delete[] array;
}

//Constuctor
template<class T>
DynamicArrayStack<T>::DynamicArrayStack(int initialCapacity){
    capacity = initialCapacity;
    array = new T[capacity];
    top = -1;
}

//Method to double the size of the array if client needs more stack space
//Creating a new array and new capacity, then copies the current array into the new array.
//Sets capacity to new capacity, and frees up the memory the old array was using.
template<class T>
void DynamicArrayStack<T>::resize(){
    int newCapacity = capacity * 2;
    T* newArray = new T[newCapacity];
    memcpy(newArray, array, capacity * sizeof(int));
    capacity = newCapacity;
    delete [] array;
    array = newArray;
}

//Method to push elements onto the top of the stack, resizes the array if the stack is full.
template<class T>
void DynamicArrayStack<T>::push(const T& data){
    if(isFull()){
        resize();
    }
    array[++top] = data;
}

//Method to remove the top of the stack, throws an underflow exception if stack is empty.
template<class T>
void DynamicArrayStack<T>::pop(){
    if(!isEmpty())
        top--;
    else
        throw StackUnderflowException();
}

//Method to return the value at the top of the stack.
template<class T>
T DynamicArrayStack<T>::peek() const{
    return array[top];
}

//Method to check if stack is empty.
template<class T>
bool DynamicArrayStack<T>::isEmpty() const{
    return top == -1;
}

//Method to check if stack is at capacity.
template<class T>
bool DynamicArrayStack<T>::isFull() const{
    return (top >= capacity - 1);
}

#endif