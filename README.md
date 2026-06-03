# Hotel Reservation Multithreading Simulation

A multithreaded Java application that simulates concurrent reservation requests and cancellations for a single-room hotel during the month of December.

This project was developed as part of the **CPSC 1181** curriculum at **Langara College**.

---

## Features

- **Thread-Safe Reservation System**: Utilizes Java's `synchronized` keyword in the `Hotel` class to handle race conditions when multiple threads read/write reservation data.
- **Concurrent Clients**: Simulates multiple clients (`PeriodicCustomer` threads) running concurrently, attempting to make and cancel reservations at random intervals.
- **Graceful Shutdown**: Employs thread interruption (`interrupt()`) and termination synchronization (`join()`) to safely shut down client threads and display the final reservation logs.

---

## Class Architecture

### 1. `Hotel.java`
Manages a list of 31 reservation slots (December 1st to 31st).
* `requestReservation(String user, int firstDay, int lastDay)`: Attempts to book a block of days. Fails if the user already has a reservation, if the days are taken, or if the range is invalid.
* `cancelReservation(String user)`: Cancels all days booked under the specified user.
* `reservationInformation()`: Returns a formatted list of all days and who reserved them (or `Available`).

### 2. `PeriodicCustomer.java` (Implements `Runnable`)
Represents a customer who repeatedly performs one of two actions after sleeping for a random duration (between 1.0s and 3.0s):
* Makes a new reservation with a random, valid date range.
* Cancels their existing reservation.
* Catching `InterruptedException` shuts down the loop and prints a shutdown message.

### 3. `HotelTester.java`
The entry point of the simulation.
* Spawns three threads representing customers: **Yoshi**, **Yuto**, and **Min**.
* Starts all threads and lets them run for 20 seconds.
* Interrupts all customer threads and waits for them to terminate using `join()`.
* Prints the final state of the hotel reservation grid.

---

## How to Compile and Run

Make sure you have JDK installed (Java 8 or higher).

1. **Navigate to the project root directory**:
   ```bash
   cd assingment7
   ```

2. **Compile the source files**:
   ```bash
   javac -d out src/*.java
   ```

3. **Run the program**:
   ```bash
   java -cp out HotelTester
   ```

---

## Sample Console Output

```text
Reservations not canceled for Min, no current reservation.
Reservation made: Yuto from 17 through 21
Reservation unsuccessful: Yoshi from 18 through 27
Reservations successfully canceled for Yuto
Reservation made: Yoshi from 2 through 29
Reservation unsuccessful: Yuto from 20 through 22
...
Periodic Test Customer Yoshi Shutting Down
Periodic Test Customer Min Shutting Down
Periodic Test Customer Yuto Shutting Down

Hotel Reservation Info
__________________
1: Available
2: Yoshi
3: Yoshi
...
```

---

## Academic Integrity & Credits

* **Starter Code**: `Hotel.java` was provided by the course instructor (**Chris Schmidt**) as starter code for this assignment and remains unmodified.
* **Student Implementation**: `PeriodicCustomer.java` (the concurrent client logic) and `HotelTester.java` (the multithreaded test suite) were authored by the students (**Yoshimasa Ohhata** & **Yuto Tonosaki**).
