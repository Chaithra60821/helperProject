package java_threads;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 *
 * Java Latch makes a thread to wait until it reaches it terminal state
 */
public class LatcheEx {
   public static long timetasks(int threads) throws InterruptedException {
     CountDownLatch startGate = new CountDownLatch(1);
     CountDownLatch endGate = new CountDownLatch(threads);

     //Starting gate allows the master thread to release all the worker threads at once, and the
     //ending gate allows the master thread to wait for the last thread to finish

     for(int i=0; i<threads ; i++){
       Thread t = new Thread(){
         @Override
         public void run() {
           try {
             //waiting on startGate thread countdown to come to zero
             startGate.await();
             printThreadName();
           } catch (InterruptedException e) {
             e.printStackTrace();
           } finally {
             endGate.countDown();
           }
         }
       };
       //System.out.println("Currentthread " +  Thread.currentThread());
       t.start();
     }
     long start = System.nanoTime();
     startGate.countDown();
     //waiting for the endgate to countdown to come to zero.
     endGate.await();
     long end =System.nanoTime();
     return start- end;
   }

    public static void printThreadName() {
      System.out.println(Thread.currentThread().getName()
          + ", executing run() method!" + " " + LocalDateTime.now());

    }

   public static void main(String[] args) throws InterruptedException {
     LatcheEx latch = new LatcheEx();
     long l = timetasks(5);
     System.out.println("TIME Taken : " +  l);
   }
}
