package es.tta.example.model;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by asier on 23/12/16.
 */

public class Exercise {
    private int id;
    private String wording;

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getWording(){
        return wording;
    }
    public void setWording(String wording){
        this.wording = wording;
    }


}
