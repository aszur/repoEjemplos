package es.tta.example.model;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by asier on 13/01/17.
 */

public class AT_GetExercise extends AsyncTask<String, Integer, Exercise> {
    @Override
    protected Exercise doInBackground(String... ex) {
        String surl = "http://u017633.ehu.eus:28080/ServidorTta/rest/tta/getExercise?id="+ex[0];
        Exercise exercise = new Exercise();
        try {
            JSONObject jsonObject = RestClient.getJson(surl);
            exercise.setId(jsonObject.getInt("id"));
            exercise.setWording(jsonObject.getString("wording"));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return exercise;
    }
}


