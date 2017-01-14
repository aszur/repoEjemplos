package es.tta.example.model;

/**
 * Created by asier on 14/01/17.
 */

public class Resource {
    private int id;
    private String description;
    private String mime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }
}
