
package br.unit.seatwise_project.view.ui;

import javax.swing.*;
import java.awt.*;


// Init -------------------------------------------------------------------- //


public class RoundedButton extends JButton {


    // Attributes

    private int   borderRadius;
    private int   borderWeight;
    private Color borderColor;
    private Color background;
    private Color onPressBackground;


    // Constructors

    public RoundedButton() {
        super();

        this.borderRadius      = 15;
        this.borderWeight      = -1;
        this.borderColor       = super.getBackground();
        this.background        = super.getBackground();
        this.onPressBackground = new Color(240, 244, 249);

        setContentAreaFilled(false);
    }


    // Getter methods

    public int getBorderRadius() {
        return borderRadius;
    }

    public int getBorderWeight() {
        return borderWeight;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    @Override
    public Color getBackground() {
        return background;
    }

    public Color getOnPressBackground() {
        return onPressBackground;
    }


    // Setter methods

    public void setBorderRadius(int borderRadius) {
        this.borderRadius = borderRadius;
    }

    public void setBorderWeight(int borderWeight) {
        this.borderWeight = borderWeight;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    @Override
    public void setBackground(Color background) {
        this.background = background;
    }

    public void setOnPressBackground(Color onPressBackground) {
        this.onPressBackground = onPressBackground;
    }


    // Main methods

    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(this.getOnPressBackground());
        } else {
            g.setColor(this.getBackground());
        }
        g.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), this.getBorderRadius(), this.getBorderRadius());
        super.paintComponent(g);
    }

    protected void paintBorder(Graphics g) {
        g.setColor(this.getBorderColor());
        g.drawRoundRect(
                0,
                0,
                this.getWidth() + this.getBorderWeight(),
                this.getHeight() + this.getBorderWeight(),
                this.getBorderRadius(),
                this.getBorderRadius()
        );
    }
}
