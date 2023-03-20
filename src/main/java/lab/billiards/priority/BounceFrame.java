package lab.billiards.priority;

import javax.swing.*;
import java.awt.*;

public class BounceFrame extends JFrame {
    private final BallCanvas canvas;
    public static final int WIDTH = 1400;
    public static final int HEIGHT = 800;

    public BounceFrame() {
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Bounce program");

        this.canvas = new BallCanvas();


        System.out.println("In Frame Thread name = " + Thread.currentThread()
                .getName());
        Container content = this.getContentPane();
        content.add(this.canvas, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.lightGray);

        JButton buttonStart = new JButton("Start");
        JButton buttonStop = new JButton("Stop");

        buttonStart.addActionListener(e -> {
            setUpJoin();
        });

        buttonStop.addActionListener(e -> System.exit(0));

        buttonPanel.add(buttonStart);
        buttonPanel.add(buttonStop);
        content.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void setUpPriority() {
        int size = 5;
        int distance = 1;
        for (int i = 0; i < HEIGHT / (size + distance); i++) {
            Ball b;
            BallThread ballThread = new BallThread();
            if (i % 50 == 0) {
                b = new Ball(canvas, Colors.BLUE, 0, i * size + distance, size);
                ballThread.setPriority(Thread.MIN_PRIORITY);
            } else {
                b = new Ball(canvas, Colors.RED, 0, i * size + distance, size);
                ballThread.setPriority(Thread.MAX_PRIORITY);
            }
            canvas.add(b);
            ballThread.setBall(b);
            ballThread.start();
            System.out.println("Thread name = "
                    + ballThread.getName());
        }
    }

    private void setUpJoin() {
        BallThreadJoin previous = null;
        for (int i = 0; i < 10; i++) {
            Ball b = new Ball(canvas, Colors.BLUE, 0, i * 22, 20);

            BallThreadJoin ballThread;
            if (previous == null) {
                ballThread = new BallThreadJoin(b, 20);

            } else {
                ballThread = new BallThreadJoin(b, previous, 20*(i+1));
            }

            previous = ballThread;
            ballThread.setPriority(Thread.MIN_PRIORITY);
            canvas.add(b);
            ballThread.start();
            System.out.println("Thread name = "
                    + ballThread.getName());
        }
    }
}
