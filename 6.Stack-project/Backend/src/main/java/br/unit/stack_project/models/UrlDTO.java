
package br.unit.stack_project.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;


// Init -------------------------------------------------------------------- //


public class UrlDTO {


    // Attributes

    @NotNull(message = "Url não pode ser null")
    @URL(message = "Url inválida")
    @NotEmpty(message = "Url não pode estar vazia")
    private String url;


    // Constructors

    public UrlDTO(String url) {
        this.setUrl(url);
    }


    // Getter methods

    public String getUrl() {
        return url;
    }


    // Setter methods

    private void setUrl(String url) {
        this.url = url;
    }
}
