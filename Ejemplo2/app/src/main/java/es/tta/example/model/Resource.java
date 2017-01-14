package es.tta.example.model;

/**
 * Created by asier on 14/01/17.
 */

public class Resource {
    private int id;
    private String type;
    private String mime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }
}
