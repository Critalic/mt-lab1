package lab.billiards.pockets;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Pocket {
    private double x;
    private double y;
    private double size;

    private int score;

    public Pocket(double x, double y, double size) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.score = 0;
    }

    public synchronized void incrementScore() {
        this.score++;
    }

    public int getScore() {
        return score;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.darkGray);
        g2.fill(new Ellipse2D.Double(x, y, size, size));
    }
}
