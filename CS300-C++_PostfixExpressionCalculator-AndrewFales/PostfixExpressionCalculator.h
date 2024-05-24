#ifndef POSTFIX_EXPRESSION_CALCULATOR
#define POSTFIX_EXPRESSION_CALCULATOR

#include "LinkedStack.h"
#include "DynamicArrayStack.h"
#include "Stack.h"
#include <string>
#include<iostream>
using namespace std;
/*
    Class to evaluate ReversePolish/Postfix Notation using a stack.

    Andrew Fales
    CS300
    10/19/2023
*/
class PostfixExpressionCalculator{
    private:
        Stack<double>& stack;
    public:
        PostfixExpressionCalculator(Stack<double>&);
        bool isOperator(char);
        bool isOperand(char);
        double evaluate(string);
        double getResult(double, double, char);
        double getNum(char);
};

//Constructor
PostfixExpressionCalculator::PostfixExpressionCalculator(Stack<double>& newStack):stack(newStack){

}

//Method to see if a given character is an operator, return true if it is.
bool PostfixExpressionCalculator::isOperator(char c){

    switch(c){
        case '-':
        case '+':
        case '*':
        case '/':
            return true;
        default:
            return false;
    }
}

//Method to see if a given character is an operand, return true if it is.
bool PostfixExpressionCalculator::isOperand(char c){
    switch (c)
    {
        case '0':
        case '1':
        case '2':
        case '3':
        case '4':
        case '5':
        case '6':
        case '7':
        case '8':
        case '9':
            return true;
        default:
            return false;
    }
}

//Method to convert a char to double, used to push a number onto the stack.
double PostfixExpressionCalculator::getNum(char ch){
   double num;
   num = ch;
   return (num-'0');
}


//Method to evaluate a given PostFix expression.
//Takes a string, creates an iterator to traverse the string by each character.
//Evaluates each character to see if its a number(operand) to store in the stack, pushes onto stack if it is.
//If the iterator is on a character that is an operator, 
//it will then get the numbers stored in the stack then do a calculation based off operator to get a resultant number and push the result onto the stack.
//returns the resultant of entire expression

double PostfixExpressionCalculator::evaluate(string expr){
    double value1, value2, resultantNum;
    string::iterator iterator;
    for(iterator=expr.begin(); iterator!=expr.end(); iterator++){ //traversing the given Postfix Expression
        if(isOperand(*(iterator))){ //Checking if the char is a number 
            stack.push(getNum(*(iterator)));
        }
        else if(isOperator(*(iterator))){ //Checking if the char is an operator
            value1 = stack.peek();
            stack.pop();
            value2 = stack.peek();
            stack.pop();
            resultantNum = getResult(value1, value2, *(iterator));
            stack.push(resultantNum);
        }
    }
    return stack.peek(); //returns resultant number (top of stack)
}

//Method to get the result of two values and an operator. 
double PostfixExpressionCalculator::getResult(double v1, double v2, char c){
    switch(c){
        case '-':
            return v2-v1;
        case '+':
            return v2+v1;
        case '*':
            return v2*v1;
        case '/':
            return v2/v1;
        default:
            return 0;
    }
}

#endif