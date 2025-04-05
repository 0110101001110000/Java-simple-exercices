
package br.unit.stack_project.models;


// Init -------------------------------------------------------------------- //


public class UrlDTO {


    // Attributes

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
