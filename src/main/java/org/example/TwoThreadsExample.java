package org.example;

public class TwoThreadsExample {

    // Shared flag to signal the other thread to stop
    private static volatile boolean shouldStop = false;

    public static void main(String[] args) {
        // Create and start the first thread
        Thread thread1 = new Thread(new CountingTask());

        // Create and start the second thread
        Thread thread2 = new Thread(new PrintingTask());

        // Start both threads
        thread1.start();
        thread2.start();

        // Wait for the main thread to complete
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Main thread: All threads completed");
    }

    // Task for the first thread
    static class CountingTask implements Runnable {
        @Override
        public void run() {
            for (int i = 1; i <= 10 && !shouldStop; i++) {
                System.out.println("Thread 1: Counting " + i);
            }
            shouldStop = true; // Signal the second thread to stop
        }
    }

    // Task for the second thread
    static class PrintingTask implements Runnable {
        @Override
        public void run() {
            char c = 'a';
            while (!shouldStop && c <= 'c') {
                System.out.println("Thread 2: Printing " + c);
                c++;
            }
            shouldStop = true; // Signal the first thread to stop
        }
    }
}
