
package br.unit.seatwise_project.view.ui;

import javax.swing.border.AbstractBorder;
import java.awt.*;


// Init -------------------------------------------------------------------- //


public class RoundedBorder extends AbstractBorder {


    // Attributes

    private int   radius;
    private int   weight;
    private Color color;


    // Constructors

    public RoundedBorder() {
        this.radius = 12;
        this.weight = -2;
        this.color  = Color.GRAY;
    }


    // Getter methods

    public int getRadius() {
        return radius;
    }

    public int getWeight() {
        return weight;
    }

    public Color getColor() {
        return color;
    }


    // Setter methods

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setColor(Color color) {
        this.color = color;
    }


    // Main methods

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.setColor(this.getColor());
        g.drawRoundRect(x, y, width + this.getWeight(), height + this.getWeight(), this.getRadius(), this.getRadius());
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(this.getRadius(), this.getRadius(), this.getRadius(), this.getRadius());
    }
}
