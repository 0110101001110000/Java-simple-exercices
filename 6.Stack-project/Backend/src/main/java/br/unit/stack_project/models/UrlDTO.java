
package br.unit.stack_project.models;

import org.hibernate.validator.constraints.URL;


// Init -------------------------------------------------------------------- //


public class UrlDTO {


    // Attributes

    @URL(message = "Url inválida")
    private String url;


    // Constructors

    public UrlDTO(String url) {
        this.setUrl(url);
    }


    // Getter methods

    private String getUrl() {
        return url;
    }


    // Setter methods

    private void setUrl(String url) {
        this.url = url;
    }
}
