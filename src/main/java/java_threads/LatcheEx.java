package java_threads;

import java.util.concurrent.CountDownLatch;

/**
 *
 * Java Latch makes a thread to wait until it reaches it terminal state
 */
public class LatcheEx {
   public static long timetasks(int threads, Runnable task) throws InterruptedException {
     CountDownLatch startGate = new CountDownLatch(1);
     CountDownLatch endGate = new CountDownLatch(threads);

     for(int i=0; i<threads ; i++){
       Thread t = new Thread(){
         @Override
         public void run() {
           try {
             //waiting on startGate thread countdown to come to zero
             startGate.await();
             task.run();
           } catch (InterruptedException e) {
             e.printStackTrace();
           } finally {
             endGate.countDown();
           }
         }
       };
       t.start();
     }
     long start = System.nanoTime();
     startGate.countDown();
     //waiting for the endgate to countdown to come to zero.
     endGate.await();
     long end =System.nanoTime();
     return start- end;
   }

  private class RunnableImpl implements Runnable {
    public void run() {
      System.out.println(Thread.currentThread().getName()
          + ", executing run() method!");

    }
  }

   public static void main(String[] args) throws InterruptedException {
     LatcheEx latch = new LatcheEx();
     long l = timetasks(5, latch.new RunnableImpl());
     System.out.println("TIME Taken : " +  l);
   }
}
