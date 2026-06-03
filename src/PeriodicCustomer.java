/**
 * @author Yoshimasa Ohhata
 * @author Yuto Tonosaki
 *
 * Represents a regular customer interacting with a Hotel object.
 * This class simulates the behavior of a customer who randomly makes or cancels reservations at regular intervals.
 */

import java.util.Random;

public class PeriodicCustomer implements Runnable {
    private String user;
    private Hotel hotel;
    private Random random;

    /**
     * @param user name of the customer
     * @param hotel hotel object that the customer interacts with
     */
    public PeriodicCustomer(String user, Hotel hotel) {
        this.user = user;
        this.hotel = hotel;
        this.random = new Random();
    }

    /**
     * The Thread alternates between attempting to make a reservation or canceling an existing reservation at random intervals
     */
    public void run() {
        try {
            while (true) {
                //ランダムな待機時間を設定することで実際に予約される状況を想定
                Thread.sleep(random.nextInt(2000) + 1000);

                if (random.nextBoolean()) {
                    int firstDay = random.nextInt(31) + 1;
                    int lastDay = firstDay + random.nextInt(32 - firstDay);
                    boolean stay = hotel.requestReservation(this.user, firstDay, lastDay);

                    //debug用
//                    System.out.println("Thread: " + this.user + ", Trying to reserve from " + firstDay + " to " + lastDay);

                    if (stay) {
                        System.out.println("Reservation made: " + this.user + " from " + firstDay + " through " + lastDay);
                    } else {
                        System.out.println("Reservation unsuccessful: " + this.user + " from " + firstDay + " through " + lastDay);
                    }
                } else {
                    boolean cancel = hotel.cancelReservation(this.user);

//                    System.out.println("Thread: " + this.user + " Trying to cansel");

                    if (cancel) {
                        System.out.println("Reservations successfully canceled for " + this.user);
                    } else {
                        System.out.println("Reservations not canceled for " + this.user + ", no current reservation.");
                    }
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Periodic Test Customer " + this.user + " Shutting Down");
        }
    }
}
