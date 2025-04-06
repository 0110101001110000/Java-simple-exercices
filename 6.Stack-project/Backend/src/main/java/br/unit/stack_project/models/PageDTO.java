
package br.unit.stack_project.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;


// Init -------------------------------------------------------------------- //


/**
 * @author 0110101001110000
 */
public class PageDTO {


    // Attributes

    @NotNull(message = "Url não pode ser nulo")
    @URL(message = "Url inválida")
    @NotEmpty(message = "Url não pode estar vazia")
    private String url;

    @NotNull(message = "O html da página não pode ser nulo")
    private String htmlPage;


    // Constructors

    public PageDTO(String url, String htmlPage) {
        this.setUrl(url);
        this.setHtmlPage(htmlPage);
    }


    // Getter methods

    public String getUrl() {
        return this.url;
    }

    public String getHtmlPage() {
        return this.htmlPage;
    }


    // Setter methods

    private void setUrl(String url) {
        this.url = url;
    }

    private void setHtmlPage(String htmlPage) {
        this.htmlPage = htmlPage;
    }
}
