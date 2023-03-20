package lab.counter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Counter {
    private int counter;
    private Lock lock = new ReentrantLock();

    public void incrementNon() {
        counter++;
    }

    public void decrementNon() {
        counter--;
    }

    public synchronized void incrementMethod() {
        counter++;
    }

    public synchronized void decrementMethod() {
        counter--;
    }

//    public synchronized void incrementObject() {
//        counter++;
//        this.notifyAll();
//        try {
//            this.wait(20);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public synchronized void decrementObject() {
//        counter--;
//        this.notifyAll();
//        try {
//            this.wait(20);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }

    public void incrementObject() {
        lock.lock();
        counter++;
        lock.unlock();
    }

    public void decrementObject() {
        lock.lock();
        counter--;
        lock.unlock();
    }

    public void incrementBlock() {
        synchronized (Counter.class) {
            counter++;
        }
    }

    public void decrementBlock() {
        synchronized (Counter.class) {
            counter--;
        }
    }

    public static void main(String[] args) {
        final Counter counter = new Counter();

        Thread decrN = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                counter.decrementNon();
            }
        });
        Thread incrN = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                counter.incrementNon();
            }
        });

        Thread decrM = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                counter.decrementMethod();
            }
        });
        Thread incrM = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                counter.incrementMethod();
            }
        });

        Thread decrB = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                counter.decrementBlock();
            }
        });
        Thread incrB = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                counter.incrementBlock();
            }
        });

        Thread decrO = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                counter.decrementObject();
            }
        });
        Thread incrO = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                counter.incrementObject();
            }
        });

        //none
        decrN.start();
        incrN.start();

        try {
            incrN.join();
            decrN.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("none - " + counter.counter);

        counter.counter=0;

        //block
        decrB.start();
        incrB.start();

        try {
            incrB.join();
            decrB.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("block - " + counter.counter);

        counter.counter=0;

        //object
        decrO.start();
        incrO.start();

        try {
            incrO.join();
            decrO.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("object - " +  counter.counter);

        counter.counter=0;

        // method
        decrM.start();
        incrM.start();

        try {
            incrM.join();
            decrM.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("method - " + counter.counter);
    }
}
