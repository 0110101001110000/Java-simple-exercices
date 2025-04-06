
package br.unit.stack_project.models;

import jakarta.validation.constraints.NotNull;


// Init -------------------------------------------------------------------- //


/**
 * @author 0110101001110000
 */
public class PageDTO {


    // Attributes

    private UrlDTO url;
    @NotNull(message = "O html da página não pode ser nulo")
    private String htmlPage;


    // Constructors

    public PageDTO(UrlDTO url, String htmlPage) {
        this.setUrl(url);
        this.setHtmlPage(htmlPage);
    }


    // Getter methods

    public UrlDTO getUrl() {
        return this.url;
    }

    private String getHtmlPage() {
        return this.htmlPage;
    }


    // Setter methods

    private void setUrl(UrlDTO url) {
        this.url = url;
    }

    private void setHtmlPage(String htmlPage) {
        this.htmlPage = htmlPage;
    }
}
