package lab.billiards.pockets;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BallCanvas extends JPanel {
    private final ArrayList<Ball> balls = new ArrayList<>();
    private ArrayList<Pocket> pockets = new ArrayList<>();
    private JLabel scoreLabel;

    public void setScoreLabel(JLabel scoreLabel) {
        this.scoreLabel = scoreLabel;
    }

    public void add(Ball b) {
        this.balls.add(b);
    }

    public void remove(Ball b) {
        this.balls.remove(b);
    }

    public void appendPockets(int pocketSize) {
        pockets = new ArrayList<>();
        double halfSize = ((double) pocketSize)/2;
        for (double i = 0; i < 6; i++) {
            if (i % 2 == 0) {
                pockets.add(new Pocket((i / 4 * this.getWidth()) - halfSize, -halfSize, pocketSize));
            } else {
                pockets.add(new Pocket(((i - 1) / 4 * this.getWidth())-halfSize, this.getHeight()-halfSize, pocketSize));
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        int score = 0;
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        for(Pocket pocket : pockets) {
            pocket.draw(g2);
            score+=pocket.getScore();
        }
        for (Ball b : balls) {
            b.draw(g2);
        }

        scoreLabel.setText("Current score - " + score);
    }

    public List<Pocket> getPockets() {
        return pockets;
    }
}
