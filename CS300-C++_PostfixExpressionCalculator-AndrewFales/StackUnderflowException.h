#ifndef STACK_UNDERFLOW
#define STACK_UNDERFLOW

#include <iostream>

using namespace std;

class StackUnderflowException: public exception{
    public:
        const char* what() const throw()
        {
            return "Stack underflow: cannot pop from an empty stack";
        }
};

#endif