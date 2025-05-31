
package br.unit.seatwise_project.controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;


// Init -------------------------------------------------------------------- //


public class MainController {


    // Private classes

    public static class SizeTracker extends ComponentAdapter {


        // Attributes

        JPanel logoSectionPanel;
        JPanel contentSectionPanel;
        JLabel logo;
        Image  logoImage;


        // Constructors

        public SizeTracker(JPanel logoSectionPanel, JPanel contentSectionPanel, JLabel logo, Image logoImage) {
            this.logoSectionPanel = logoSectionPanel;
            this.contentSectionPanel = contentSectionPanel;
            this.logo = logo;
            this.logoImage = logoImage;
        }


        // Main methods

        @Override
        public void componentResized(ComponentEvent e) {
            super.componentResized(e);

            int width = ((JComponent) e.getSource()).getWidth();
            int height = ((JComponent) e.getSource()).getHeight();

            this.logoSectionPanel.setPreferredSize(new Dimension(width, height / 3));
            this.contentSectionPanel.setPreferredSize(new Dimension(width, height / 3 * 2));

            // Resize logo
            int newImageSize = (logoSectionPanel.getWidth() >= logoSectionPanel.getHeight()) ? ((int) (logoSectionPanel.getHeight() * .8)) : ((int) (logoSectionPanel.getWidth() * .8));
            if (newImageSize < 120) {
                Image newImage = logoImage.getScaledInstance(
                        newImageSize,
                        newImageSize,
                        Image.SCALE_DEFAULT
                );
                this.logo.setIcon(new ImageIcon(newImage));
            }
        }
    }
}
