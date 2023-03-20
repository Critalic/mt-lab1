package lab.billiards.pockets;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Objects;
import java.util.Random;

public class Ball {
    private Component canvas;
    private static final int XSIZE = 20;
    private static final int YSIZE = 20;
    private int x = 0;
    private int y = 0;
    private int dx = 2;
    private int dy = 2;

    public Ball(Component c) {
        this.canvas = c;
        if (Math.random() < 0.5) {
            x = new Random().nextInt(this.canvas.getWidth());
            y = 0;
        } else {
            x = 0;
            y = new Random().nextInt(this.canvas.getHeight());
        }
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.darkGray);
        g2.fill(new Ellipse2D.Double(x, y, XSIZE, YSIZE));
    }

    public void move() {
        x += dx;
        y += dy;
        if (x < 0) {
            x = 0;
            dx = -dx;
        }
        if (x + XSIZE >= this.canvas.getWidth()) {
            x = this.canvas.getWidth() - XSIZE;
            dx = -dx;
        }
        if (y < 0) {
            y = 0;
            dy = -dy;
        }
        if (y + YSIZE >= this.canvas.getHeight()) {
            y = this.canvas.getHeight() - YSIZE;
            dy = -dy;
        }
        this.canvas.repaint();
    }

    public boolean collidesWith(Pocket pocket) {
        double centerX = x+ ((double) XSIZE)/2;
        double centerY = y+ ((double) YSIZE)/2;

        return ((pocket.getX() < centerX && centerX < pocket.getX()+ pocket.getSize()) &&
                (pocket.getY() < centerY && centerY < pocket.getY() + pocket.getSize()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ball ball = (Ball) o;
        return x == ball.x && y == ball.y && dx == ball.dx && dy == ball.dy && Objects.equals(canvas, ball.canvas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(canvas, x, y, dx, dy);
    }
}
