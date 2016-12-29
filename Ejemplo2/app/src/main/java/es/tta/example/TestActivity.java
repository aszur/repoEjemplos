package es.tta.example;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.docencia.ejemplo1.R;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.IOException;

import es.tta.example.model.AudioPlayer;
import es.tta.example.model.Test;
import es.tta.example.presentation.Data;

public class TestActivity extends AppCompatActivity{
    protected Data data;
    protected RadioGroup opciones;
    protected int seleccionada;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Test test = new Test(getString(R.string.pregunta),getString(R.string.respuesta0_1),getString(R.string.respuesta0_2),
                getString(R.string.respuesta0_3),getString(R.string.respuesta0_4),getString(R.string.respuesta0_1),"");

        String[] answer = {test.getRespuesta1(),
                test.getRespuesta2(),
                test.getRespuesta3(),
                test.getRespuesta4(),
                test.getRespuesta5()};
        opciones = (RadioGroup) findViewById(R.id.opcionesTest);
        for (String opcion : answer) {
            RadioButton radio = new RadioButton(this);
            radio.setText(opcion);
            radio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    findViewById(R.id.botonEnviarRespuestaTest).setVisibility(View.VISIBLE);
                }
            });
            opciones.addView(radio);
        }

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /*
        protected void compruebaTest(View v){
            RadioGroup opciones = (RadioGroup) findViewById(R.id.opcionesTest);
            String opcionId = opciones.getCheckedRadioButtonId();
            RadioButton opcion = (RadioButton) findViewById(opcionId);
            if(opcionId == 3){
                Toast.makeText(this, R.string.acierto, Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, R.string.fallo, Toast.LENGTH_SHORT).show();
            }
        }
        */
    protected void compruebaTest(View v) {
        Log.d("Comprueba Test", "Entramos en comprueba test");
        int choices = opciones.getChildCount();
        for (int i = 0; i < choices; i++) {
            opciones.getChildAt(i).setEnabled(false);
        }
        Button bt = (Button) findViewById(R.id.botonEnviarRespuestaTest);
        bt.setVisibility(View.GONE);
        //ponemos a verde la correcta
        opciones.getChildAt(2).setBackgroundColor(Color.GREEN );// La correcta es la 3
        //obtengo el id de la seleccionada
        seleccionada = opciones.getCheckedRadioButtonId(); //Este es el id del boton, no su  indice
        opciones.getChildAt(2).setBackgroundColor(Color.GREEN); //La posicion 3 es la correcta, se pone en verde
        if (seleccionada == 3) {
            Toast.makeText(this, R.string.acierto, Toast.LENGTH_SHORT).show();
        } else {
            findViewById(seleccionada).setBackgroundColor(Color.RED);
            findViewById(R.id.botonAyuda).setVisibility(View.VISIBLE);
            Toast.makeText(this, R.string.fallo, Toast.LENGTH_SHORT).show();
        }
    }

    protected void ayudame(View v) {
        Log.d("Consejo", getString(R.string.ayuda));
        int selectecIndex = opciones.indexOfChild(findViewById(seleccionada));
        if(selectecIndex == 0){
            LinearLayout layout = (LinearLayout) findViewById(R.id.activity_test);
            WebView web;
            if ((web= (WebView)findViewById(R.id.webViewId)) ==null) {
                web = new WebView(this);
                web.setId(R.id.webViewId);
            }
            web.loadData(getString(R.string.ayuda), "text/html", null);
            web.setBackgroundColor(Color.TRANSPARENT);
            web.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);
            layout.addView(web);
        }else if(selectecIndex == 1){
            String consejo = getString(R.string.ayuda2);
            if(consejo.substring(0,10).contains("://")){
                Uri uri = Uri.parse(consejo);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }else{
                LinearLayout layout = (LinearLayout) findViewById(R.id.activity_test);
                WebView web;
                if ((web= (WebView)findViewById(R.id.webViewId)) ==null) {
                    web = new WebView(this);
                    web.setId(R.id.webViewId);
                }
                web.loadData(getString(R.string.ayuda), "text/html", null);
                web.setBackgroundColor(Color.TRANSPARENT);
                web.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);
                layout.addView(web);
            }

        }else if(selectecIndex == 3){
            LinearLayout layout = (LinearLayout) findViewById(R.id.activity_test);
            VideoView videoView = new VideoView(this);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            videoView.setLayoutParams(params);
            videoView.setVideoURI(Uri.parse("http://u017633.ehu.eus:28080/static/ServidorTta/AndroidManifest.mp4"));
            MediaController controller = new MediaController(this) {
                @Override
                public void hide(){

                }
                @Override
                public boolean dispatchKeyEvent(KeyEvent event){
                    if(event.getKeyCode() == KeyEvent.KEYCODE_BACK){
                        finish();
                    }
                    return super.dispatchKeyEvent(event);
                }
            };
            controller.setAnchorView(videoView);
            videoView.setMediaController(controller);
            layout.addView(videoView);
            videoView.start();
        }else if(selectecIndex == 4){
            LinearLayout layout = (LinearLayout) findViewById(R.id.activity_test);
            AudioPlayer audioPlay = new AudioPlayer(findViewById(R.id.activity_test));
            try {
                audioPlay.setAudioUri(Uri.parse("http://u017633.ehu.eus:28080/static/ServidorTta/AndroidManifest.mp4"));
            } catch (IOException e) {
                e.printStackTrace();
            }


        }


    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Test Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}