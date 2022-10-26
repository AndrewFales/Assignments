# Data Structures & Algorithms - SuperArray
![arrayList](https://sarabostani-public.s3.amazonaws.com/ds-and-algo/img/ArrayList.png)
## Introduction

In this assignment we will keep expanding the functionalities of the SuperArray. This data structure will behave as a stack and a list with a dynamic size. The array will also be generic so any type of object can be stored in it.
## Tasks
Read the comments in `SuperArray.java` and complete the tasks that are required. Upon successful completion, you should be able to call all methods successfully and test the results in `Main.java`. You can optionally run tests under test/java and see them all pass.
## More on add at index and push
In both add and push, you need to check for the size of the array. If the array is at capacity, you need to copy the array into a bigger one that's 50% bigger, meaning `newLength = items.length + items.lenght/2`.
The function `add(i, item)` acts more like an "insert" at index. You need to shift everything up by one and update the cell at the index.
 
