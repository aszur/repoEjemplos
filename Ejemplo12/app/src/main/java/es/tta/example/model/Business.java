package es.tta.example.model;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by asier on 23/12/16.
 */

public class Business {

    public Status getStatus(String dni)throws IOException, JSONException {

    }

    public Test getTest(int id) throws IOException, JSONException{

    }
    public Exercise getExercise(int id) throws IOException, JSONException{

    }
    public void uploadSolution(int userId, int exerciseId, InputStream is, String filename) throws IOException{

    }
    public void uploadChoice(int userId, int choiceId) throws JSONException, IOException{

    }
}
