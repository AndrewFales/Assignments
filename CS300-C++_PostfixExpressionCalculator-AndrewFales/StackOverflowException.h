#ifndef STACK_OVERFLOW
#define STACK_OVERFLOW

#include <iostream>

using namespace std;

class StackOverflowException: public exception{
    public:
        const char* what() const throw ()
        {
            return "Stack overflow: cannot push onto a full stack";
        }

};

#endif