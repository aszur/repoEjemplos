package es.tta.example.model;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by asier on 13/01/17.
 */

public class AT_GetTest extends AsyncTask<String, Integer, Test> {
    @Override
    protected Test doInBackground(String... tst) {
        String surl = "http://u017633.ehu.eus:28080/ServidorTta/rest/tta/getTest?id="+tst[0];
        Test test = new Test();
        List<Choice> opciones = new ArrayList<Choice>();
        try {
            JSONObject jsonObject = RestClient.getJson(surl);
            test.setEnunciado(jsonObject.getString("wording"));
            JSONArray ja = jsonObject.getJSONArray("choices");
            int size = ja.length();
            for (int i = 0; i < size; i++) {
                Choice opcion = new Choice();
                JSONObject jo = (JSONObject) ja.getJSONObject(i);
                opcion.setId(jo.getInt("id"));
                opcion.setAdvice(jo.getString("advice"));
                opcion.setAnswer(jo.getString("answer"));
                opcion.setCorrect(jo.getString("correct"));
                opcion.setRes((Resource)jo.get("resourceType"));
                opciones.add(opcion);
            }
            test.setRespuesta1(opciones.get(0));
            test.setRespuesta2(opciones.get(1));
            test.setRespuesta3(opciones.get(2));
            test.setRespuesta4(opciones.get(3));
            test.setRespuesta5(opciones.get(4));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return test;
    }
}


