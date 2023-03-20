package lab.billiards.priority;

import java.awt.*;

public enum Colors {

    RED(Color.BLUE),
    BLUE(Color.RED);

    private Color color;

    Colors(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
