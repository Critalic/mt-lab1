package lab.billiards.priority;


import javax.swing.*;

public class Bounce {
    public static void main(String[] args) {
        BounceFrame bounceFrame = new BounceFrame();
        bounceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        bounceFrame.setVisible(true);
        System.out.println("Thread name = " + Thread.currentThread().getName());
    }
}
