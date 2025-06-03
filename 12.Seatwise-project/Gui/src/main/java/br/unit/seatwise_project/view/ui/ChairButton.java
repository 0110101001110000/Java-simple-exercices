
package br.unit.seatwise_project.view.ui;

import br.unit.seatwise_project.model.Chair;
import br.unit.seatwise_project.model.Client;
import br.unit.seatwise_project.model.Reserve;

import javax.swing.*;
import java.awt.*;


// Init -------------------------------------------------------------------- //


public class ChairButton extends RoundedButton {


    // Attributes

    private Client  client;
    private Chair   chair;
    private Reserve reserve;


    // Constructors

    public ChairButton(Client client, Chair chair) {
        this(client, chair, null);
    }

    public ChairButton(Client client, Chair chair, Reserve reserve) {
        super();

        this.client  = client;
        this.chair   = chair;
        this.reserve = reserve;

        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(32, 32));

        this.fetch();
    }


    // Getter methods

    public Client getClient() {
        return client;
    }

    public Chair getChair() {
        return chair;
    }

    public Reserve getReserve() {
        return reserve;
    }


    // Setter methods

    public void setClient(Client client) {
        this.client = client;
    }

    public void setChair(Chair chair) {
        this.chair = chair;
    }

    public void setReserve(Reserve reserve) {
        this.reserve = reserve;
    }


    // Main methods

    public void fetch() {
        Color  color;
        String message;
        String imagePath;

        if (this.reserve == null) {
            color     = this.getBackground();
            imagePath = "src/main/resources/images/transparent-icon-free-32x32.png";
            message   = "Livre";
        }
        else if (!this.reserve.getClientId().equals(this.client.getId())) {
            color     = this.getBackground();
            message   = "Ocupada";
            imagePath = "src/main/resources/images/transparent-icon-block-32x32.png";
        }
        else {
            color     = new Color(147, 198, 161);
            message   = "Ocupada por Você";
            imagePath = "src/main/resources/images/transparent-icon-block-32x32.png";
        }

        this.setBorderWeight(-2);
        this.setBorderColor(color);
        this.setIcon(new ImageIcon(imagePath));
        this.setToolTipText(String.format("Id: %d | Status: %s", this.chair.getId(), message));
    }
}
