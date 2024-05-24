#include "PostfixExpressionCalculator.h" 
#include "DynamicArrayStack.h" 
#include "LinkedStack.h"
#include "Stack.h"
#include <iostream>
#include <fstream>
#include <sstream>
#include <vector>
#include <string>
using namespace std;

// It takes a stack, a postfix expression, the expected result, and a points counter as parameters.
// It creates an instance of PostfixExpressionCalculator with the given stack.
// It evaluates the postfix expression and compares the result to the expected value.
// If the result matches the expected value, it adds 10 points to the total points earned; 
// otherwise, it prints a test failure message.
// It catches and handles any exceptions that might occur during the evaluation.
void testCalculator(Stack<double>& stack, const string& postfixExpression, double expected, int& points) {
    PostfixExpressionCalculator calculator(stack);
    try {
        double result = calculator.evaluate(postfixExpression);
        cout << "Result: " << result << endl;
        if (result == expected) {
            points += 10; // Add 10 points for a correct output
        }
        else {
            cout << "Test Failed: Expected " << expected << ", but got " << result << endl;
        }
    } catch (const std::exception& e) {
        cerr << "Error: " << e.what() << endl;
    }
}

int main() {
    LinkedStack<double> linkedStack;
    DynamicArrayStack<double> dynamicArrayStack(3);

    int totalPoints = 0; // Total points earned

    //The program reads input from an external file named "postfix_input.txt." 
    //Each line in this file is expected to contain a postfix expression 
    //and the expected result, separated by a comma.
    ifstream inputFile("postfix_input.txt");
    if (!inputFile) {
        cerr << "Error: Unable to open the input file." << endl;
        return 1;
    }

    string line;
    while (getline(inputFile, line)) {
        istringstream iss(line);
        string postfixExpression;
        double expectedResult;
        getline(iss, postfixExpression, ',');
        iss >> expectedResult;
        cout << endl<<postfixExpression<<endl;
        cout << "Testing LinkedStack:" << endl;
        testCalculator(linkedStack, postfixExpression, expectedResult, totalPoints);

        cout << "Testing DynamicArrayStack:" << endl;
        testCalculator(dynamicArrayStack, postfixExpression, expectedResult, totalPoints);

    }

    // At the end of the program, the total points earned are displayed based 
    // on the number of correct test cases (each correct test adds 10 points).
    cout << "Total Points Earned: " << totalPoints << " points" << endl;

    inputFile.close();
    return 0;
}
