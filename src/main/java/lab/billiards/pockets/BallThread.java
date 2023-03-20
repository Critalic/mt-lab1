package lab.billiards.pockets;

import java.util.Optional;

public class BallThread extends Thread {
    private Ball b;
    private BallCanvas ballCanvas;

    public BallThread(Ball ball, BallCanvas ballCanvas) {
        b = ball;
        this.ballCanvas = ballCanvas;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i < 10000; i++) {
                b.move();
                System.out.println("Thread name" + Thread.currentThread().getName());
                Optional<Pocket> pocketCollided = ballCanvas.getPockets().stream()
                        .filter(pocket -> b.collidesWith(pocket)).findFirst();
                if (pocketCollided.isPresent()) {
                    ballCanvas.remove(b);
                    pocketCollided.get().incrementScore();
                    break;
                }
                Thread.sleep(5);
            }
        } catch (InterruptedException ex) {
            System.out.println("Exception in thread " + Thread.currentThread().getName());
        }
    }
}
