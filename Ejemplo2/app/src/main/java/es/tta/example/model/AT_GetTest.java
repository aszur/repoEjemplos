package es.tta.example.model;

import android.os.AsyncTask;

import com.google.gson.Gson;

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
        System.out.println("Ejecutando GetTest");
        String surl = "http://u017633.ehu.eus:28080/ServidorTta/rest/tta/getTest?id="+tst[0];
        Test test = new Test();
        List<Choice> opciones = new ArrayList<Choice>();
        try {
            //RestClient.setHttpBasicAuth("12345678A","tta");
            //JSONObject jsonObject = RestClient.getJson(surl);
            JSONObject jsonObject = new JSONObject("{\"wording\":\"Â¿CuÃ¡l de las siguientes " +
                    "opciones NO se indica en el fichero de manifiesto de la app?\",\"choices\"" +
                    ":[{\"id\":1,\"advise\":\"http://developer.android.com/guide/topics/manifest/" +
                    "manifest-intro.html\",\"answer\":\"VersiÃ³n de la aplicaciÃ³n\",\"correct\":fa" +
                    "lse,\"resourceType\":{\"id\":5,\"description\":\"html\",\"mime\":\"text/html\"}},{" +
                    "\"id\":2,\"advise\":\"<html><body>The manifest describes the <b>components of the" +
                    " application</b>: the activities, services, broadcast receivers, and content provider" +
                    "s that ...</body></html>\",\"answer\":\"Listado de componentes de la aplicaciÃ³n\",\"c" +
                    "orrect\":false,\"resourceType\":{\"id\":5,\"description\":\"html\",\"mime\":\"text/html\"}" +
                    "},{\"id\":3,\"advise\":null,\"answer\":\"Opciones del menÃº de ajustes\",\"correct\":tru" +
                    "e,\"resourceType\":null},{\"id\":4,\"advise\":\"http://u017633.ehu.eus:28080/static/Serv" +
                    "idorTta/AndroidManifest.mp4\",\"answer\":\"Nivel mÃ\u00ADnimo de la API Android requerid" +
                    "a\",\"correct\":false,\"resourceType\":{\"id\":4,\"description\":\"mp4\",\"mime\":\"video/mp4" +
                    "\"}},{\"id\":5,\"advise\":\"http://u017633.ehu.eus:28080/static/ServidorTta/AndroidManifest.mp4" +
                    "\",\"answer\":\"Nombre del paquete java de la aplicaciÃ³n\",\"correct\":false,\"resourceTy" +
                    "pe\":{\"id\":3,\"description\":\"mp3\",\"mime\":\"audio/mpeg\"}}]}");
            test.setEnunciado(jsonObject.getString("wording"));
            System.out.println("Enunciado: "+ test.getEnunciado());
            JSONArray ja = jsonObject.getJSONArray("choices");
            int size = ja.length();
            for (int i = 0; i < size; i++) {
                Choice opcion = new Choice();
                JSONObject jo = (JSONObject) ja.getJSONObject(i);
                opcion.setId(jo.getInt("id"));
                opcion.setAdvice(jo.getString("advise"));
                opcion.setAnswer(jo.getString("answer"));
                opcion.setCorrect(jo.getString("correct"));
                //opcion.setRes(jo.get("resourceType"));
                Gson gson = new Gson();
                Resource res = new Resource();
                res = gson.fromJson(jo.getString("resourceType"),Resource.class);
                opcion.setRes(res);
                //System.out.println("Resource N"+(i+1) +":"+res.getMime());
                opciones.add(opcion);
            }
            test.setRespuesta1(opciones.get(0));
            test.setRespuesta2(opciones.get(1));
            test.setRespuesta3(opciones.get(2));
            test.setRespuesta4(opciones.get(3));
            test.setRespuesta5(opciones.get(4));
        } /*catch (IOException e) {
            e.printStackTrace();
        } */catch (JSONException e) {
            e.printStackTrace();
        }
        return test;
    }
}


