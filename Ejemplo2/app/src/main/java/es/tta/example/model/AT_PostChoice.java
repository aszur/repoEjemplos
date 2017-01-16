package es.tta.example.model;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by asier on 13/01/17.
 */

public class AT_PostChoice extends AsyncTask<String, Integer, Void> {
    @Override
    protected Void doInBackground(String... opcion) {
        String surl = "http://u017633.ehu.eus:28080/ServidorTta/rest/tta/postChoice";
        try {
            RestClient.setHttpBasicAuth(opcion[2], opcion[3]);
            JSONObject jo = new JSONObject();
            jo.put("userId", opcion[0]);
            jo.put("choiceId", opcion[1]);
            RestClient.postJson(jo, surl);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}


