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

public class AT_GetUserData extends AsyncTask<String, Integer, User> {
    @Override
    protected User doInBackground(String... usuario) {
        boolean estado = false;
        User user1 = new User();
        String usr = usuario[0];
        String surl = "http://u017633.ehu.eus:28080/ServidorTta/rest/tta/getStatus?dni="+usr;

        try {
            JSONObject jo = new JSONObject(RestClient.getString(surl));
            user1.setId(jo.getInt("id"));
            user1.setLessonNumber(jo.getInt("lessonNumber"));
            user1.setLessonTitle(jo.getString("lessonTitle"));
            user1.setNextExercise(jo.getInt("nextExercise"));
            user1.setNextText(jo.getInt("nextText"));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
       


        return user1;
    }
}


