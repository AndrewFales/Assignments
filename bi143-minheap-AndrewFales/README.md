# Data Structures & Algorithms - BinaryHeap and Closest Points
## Introduction
![BinaryHeap](https://sarabostani-public.s3.amazonaws.com/ds-and-algo/img/binary_heap.gif)

In this exercise we are going to implement a simple Binary Heap which acts as a priority queue. This ADT will be used to solve the problem of finding a list of closest points on the Cartesian coordinates to a point `P`.

## Tasks
By following the definition of a min heap and its properties, implement each method in the `MinHeap.java` class. You may implement the heap either with a binary tree or an array.

In this case of the MinHeap implementation, we will offer the heap an object of type `T` and a priority value. The priority value determines the order of the object in the heap. Note that you can have 2 objects with the same priority as well.

**HINT** : Each item in the array or the node of the binary tree (if you choose a binary tree for implementation) should carry both the object and its priority. Consider creating an inner class and maybe name it `Data`. You may also use a `Tuple`.

In actuality and in Java's implementation of `PriorityQueue`, the priority of each object is determined by their precedence. For example if the type is `String`, alphabetic order determines the priority. Other objects need to define how they "compare" to each other by implementing the `Comparable` interface and define the comparison logic so that they can get ordered properly.

## K-Closest Points Problem
Given a set of points in the Cartesian plane, e.g. [(2,3), (4,11), (0,9), ...], and a single point, p, and a number k, find the k closest points to the point p.

Complete the `find` method in the `KClosest.java` file. 

Note that the distance between each point and `p` is the Pythagorean distance. Write a helper private function that calculates the distance. You can test your logic in the `Program.java` file.

