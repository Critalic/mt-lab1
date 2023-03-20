package lab.billiards.priority;

public class BallThread extends Thread {
    private Ball ball;
    private BallThread other;

    public BallThread(Ball ball) {
        this.ball = ball;
    }

    public BallThread(BallThread other) {
        this.other = other;
    }

    public BallThread() {}

    public void setBall(Ball ball) {
        this.ball = ball;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i < 10000; i++) {
                ball.move();
                System.out.println("Thread name" + Thread.currentThread().getName());
                Thread.sleep(5);
            }
        } catch (InterruptedException ex) {
            System.out.println("Exception in thread " + Thread.currentThread().getName());
        }
    }
}
