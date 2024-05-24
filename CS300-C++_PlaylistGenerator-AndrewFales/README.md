First and foremost, this is by far the hardest and most complex project I have completed thus far.
It is by no means perfect, but it is functional and I am proud of what I have accomplished.

This program provides utility for a user to obtain relevant information about their own music listening habits,
their friends listening habits, find common friends between two users and recommendations for musical artists based off listening habits and friends.

Internally the program handles and organizes data using hashmaps, linked lists, unordered sets, and pairs.
Additionally, data is sorted using bubble sort.

For the hashmap implementation, I built my own custom template hashmap that provides typical hashmap behavior and functionality.
There is no true hash function for each key, however it would be relatively straightforward to add one (the assignment document did not call for one to be used).
As such, keys are distributed through the table using the modulo operator. I built it originally using C++'s hash method and that would work perfectly fine if hashing was needed.
To deal with collisions, the hashmap uses separate chaining with a vector made up of linked lists.

To handle user and artist data, there are two classes respectively. The user class utilizes an unordered set to store information on
both the friends of the user and the artist the user listens to. There are methods within the class to retrieve a users set data for 
both the artists and friends. 
The artist class is able to hold both the artists ID and their name.

The recommender header is where all of the functions of the main program are. The class uses two hashmaps, one for artists and another for users.
Artist hashmap stores all the information on artistID's and the name, and the user hashmap stores information on each user.
Data is loaded into each hashmap from 3 files, one for artists, one for the artists a user listens to, and another for users friends.
Loading is done by reading each line of the input file, and then storing the data within each Artist or User in their respective hashmaps.

The most complex method is the core recommend function, it takes in a given user and however many recommendations we want to display.
It grabs data from the users friends and the users artists, counts the intersections(listen count) between all of the users friends artists and their own.
It stores that data in a seperate hashmap that is made of int and pairs (for the name of the artist and the listen count), then converts that to a vector.
The vector is then sorted in descending order using bubble sort and displayed to the user, providing the artists listened to by all of their friends the most.

Bubble sort is not ideal for large data sets, and if I was to scale this program I would change it to a different sorting algorithm for efficiency.
For the data given for this project however, bubble sort seems reasonable.

I learned a lot about handling data in a way I was not used to, using nested datastructures to achieve functionality is something really cool.
I also learned A LOT about for each loops, iterating through data and how to properly access an element that I wanted to read or replace.
Finding intersections was cool and im glad I know how to do it now.

This was a tough project, but I am satisfied with my result and I learned a lot.
