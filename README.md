#Dining Philosophers Problem

###Abstract

<p>The objective of this project was to implement the famous Dining Philosophers problem, originally formulated by Edsger Dijkstra. This concurrent algorithm design illustrates synchronization issues and techniques for resolving them. The problem states that several philosophers are sitting at a round table with a fork on each side of them. Each philosopher periodically stops thinking and decides to eat. In order to eat, they must pick up both forks. The philosopher must pick up the fork or wait for the fork to be available. At no point may two philosophers hold the same fork. When the philosopher finishes eating, he or she must put down the fork. The challenge is thus making sure that the philosophers can continuously alternate between eating and thinking without ever having the philosophers wait indefinitely to pick up a fork. In other words, the philosophers must never enter a deadlock.</p>

###Architecture

<p>This problem was solved with the use of a numbering system as well as Java’s synchronization functionalities. The solution consists of three classes; a Fork class which represents a single fork, a Philosopher class which represents a single philosopher, and a Main class, responsible for initializing the application.
The Fork has a unique identifier as well as a Thread object which holds the object that is currently using the fork instance.</p>

<p>The <b>Fork</b> class also has two functions for handling synchronization. The acquire method is responsible for ensuring that only one philosopher can acquire the fork. This is achieved by setting this method to synchronized and waiting for the fork to be released before setting the new holder to the philosopher making the request. The release method is responsible for ensuring that the correct philosopher is releasing the fork. This method is also synchronized and notifies the next philosopher that the fork is now available.</p>

<p>The <b>Philosopher</b> class also has a unique identifier as well as the fork instances that are on the right and left of him. To prevent deadlocks, the forks are ordered based on their ranking. That is, the lower ranked fork is the first fork that the philosopher will attempt to pick up. This is to prevent the case where all of the philosophers attempt to pick up the forks simultaneously. For example, if all the philosophers attempted to pick up the fork on their right first, then they would all be waiting for the left fork to be available. Since all of the forks would be picked up, the philosophers would be in deadlock. The fork ranking is based on the fork’s unique identifier. It’s important to note that the forks are ordered from smallest rank to largest rank. This means that all of the philosophers will attempt to reach for the fork on their right with the exception of one philosopher whom will be waiting for the left fork to be available. In this way, if all the philosophers attempted to pick up the fork at the same time, one of the philosophers would possess no forks and thus one fork would remain available. The available fork would be picked up by another philosopher who is already holding a fork. Thus, a deadlock could not happen. The Philosopher class contains methods for thinking, picking up the forks, eating, and releasing the forks.</p>

<p>The <b>Main</b> class has a main method which accepts to arguments. The first being the number of philosophers and the second being the time (in milliseconds) the philosophers spend eating. If both arguments are provided, the method proceeds to generate an array of forks and an array of philosophers. Each Philosopher object is initialized with a unique identifier, the time spent eating and the fork instances that are to it’s left and right side. The Philosopher threads are then started and the method continues to run until a predefined runtime is reached. After terminating all of the philosopher threads, the method prints statistics about each of the philosophers such as time spent eating, time spent sleeping, and total number of times the philosopher ate.</p>

###Inputs/Outputs for Testing

To run the application, open a terminal and navigate to the folder containing the Main.java, Fork.java, and Philosopher.java files. If the class files are not present, simply compile the java code. Then run the following command:

    java Main [number of philosophers] [time spent eating (in milliseconds)]

All of the information about the current philosopher activity will be outputted to the terminal. Just before exiting, the application will print a list of statistics pertaining to each philosopher. The statistics include the time spent thinking, the time spent eating, and the number of times the philosopher stopped to eat.

###Conclusion

Overall, the assignment was a success. After verifying the logic and running many simulations, the application never enters a deadlock state. This is evidenced by the output statistics which demonstrate that all of the philosophers have eaten roughly the same number of times. This suggests that none of the philosophers were starved. The application always exits successfully which means that a deadlock state is never reached. The application is also capable of handling any number of philosophers and any amount of time spent eating.
