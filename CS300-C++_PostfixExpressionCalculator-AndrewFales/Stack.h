#ifndef STACK_H
#define STACK_H

template <class T>
class Stack
{
    public:
        virtual void push(const T& ) = 0;
        virtual void pop() = 0;
        virtual T peek() const  = 0;
        virtual bool isEmpty() const = 0;
        virtual bool isFull() const = 0;
};


#endif 