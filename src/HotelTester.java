/**
 * @author Yoshimasa Ohhata
 * @author Yuto Tonosaki
 *
 * Tester class for the Hotel reservation system.
 * It creates multiple PeriodicCustomer threads, runs them for a set duration,
 * interrupts them, and then prints the final reservation status.
 */
public class HotelTester {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();

        // Threadの作成
        Thread customer1 = new Thread(new PeriodicCustomer("Yoshi", hotel));
        Thread customer2 = new Thread(new PeriodicCustomer("Yuto", hotel));
        Thread customer3 = new Thread(new PeriodicCustomer("Min", hotel));

        // 各Threadを開始
        customer1.start();
        customer2.start();
        customer3.start();

        try {
            // スレッドがそれぞれ少なくとも5回のアクションを実行できるように時間を設定
            // 各アクションの待機時間は1〜3秒（平均2秒）であるため、
            // 20秒（20000ミリ秒）待機することで、確実に5回以上のアクションが実行されます。
            Thread.sleep(20000);

            // それぞれのThreadの実行を終了させる
            customer1.interrupt();
            customer2.interrupt();
            customer3.interrupt();

            // 各Threadが終了するのを待つ
            customer1.join();
            customer2.join();
            customer3.join();
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }

        System.out.println(hotel.reservationInformation());
    }
}
