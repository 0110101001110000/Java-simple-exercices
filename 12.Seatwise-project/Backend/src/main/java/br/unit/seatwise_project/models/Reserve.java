
package br.unit.seatwise_project.models;


// Init -------------------------------------------------------------------- //


public class Reserve {


    // Attributes

    private Long id;
    private Long clientId;
    private Long chairId;


    // Constructors

    public Reserve(Long id, Long clientId, Long chairId) {
        this.id       = id;
        this.clientId = clientId;
        this.chairId  = chairId;
    }


    // Getter methods

    public Long getId() {
        return id;
    }

    public Long getClientId() {
        return clientId;
    }

    public Long getChairId() {
        return chairId;
    }


    // Setter methods

    public void setId(Long id) {
        this.id = id;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public void setChairId(Long chairId) {
        this.chairId = chairId;
    }
}
