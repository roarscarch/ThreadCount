package org.example;

public class TwoThreadsExample {

  
    private static volatile boolean shouldStop = false;

    public static void main(String[] args) {
       
        Thread thread1 = new Thread(new CountingTask());

       
        Thread thread2 = new Thread(new PrintingTask());

      
        thread1.start();
        thread2.start();

       
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Main thread: All threads completed");
    }

  
    static class CountingTask implements Runnable {
        @Override
        public void run() {
            for (int i = 1; i <= 10 && !shouldStop; i++) {
                System.out.println("Thread 1: Counting " + i);
            }
            shouldStop = true;
        }
    }

  
    static class PrintingTask implements Runnable {
        @Override
        public void run() {
            char c = 'a';
            while (!shouldStop && c <= 'c') {
                System.out.println("Thread 2: Printing " + c);
                c++;
            }
            shouldStop = true;
        }
    }
}
