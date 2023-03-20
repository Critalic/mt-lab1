package lab.dashes;

public class OutputController {
    private static int syncCount=0;

    public synchronized void printNonBlocking(String ch) {
        if((syncCount+1)%20==0) {
            System.out.println(ch);
        } else {
            System.out.print(ch);
        }
        syncCount++;
    }

    public synchronized void printBlocking(String ch) {
        if((syncCount+1)%20==0) {
            System.out.println(ch);
        } else {
            System.out.print(ch);
        }
        syncCount++;
        this.notifyAll();
        try {
            this.wait(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        OutputController outputController = new OutputController();
        DashDealer horizontal = new DashDealer("-", ch -> outputController.printNonBlocking(ch));
        DashDealer vertical = new DashDealer("|", ch -> outputController.printNonBlocking(ch));

        DashDealer horizontal1 = new DashDealer( "-", ch -> outputController.printBlocking(ch));
        DashDealer vertical1 = new DashDealer("|", ch -> outputController.printBlocking(ch));

        Thread t1 = new Thread(horizontal1);
        Thread t2 = new Thread(vertical1);

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
