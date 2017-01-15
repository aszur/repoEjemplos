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
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.docencia.ejemplo1.R;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import es.tta.example.model.AT_GetTest;
import es.tta.example.model.AT_PostChoice;
import es.tta.example.model.AudioPlayer;
import es.tta.example.model.Choice;
import es.tta.example.model.Test;
import es.tta.example.presentation.Data;

public class TestActivity extends AppCompatActivity{
    protected Data data;
    protected RadioGroup opciones;
    protected int seleccionada;
    public String usuario;
    public String pwd;
    Test test = null;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Bundle extras = getIntent().getExtras();
        usuario = extras.getString("usuario"); //Recogemos credenciales
        pwd = extras.getString("pwd");
        System.out.print(usuario+":"+pwd);
        String [] strings = {"1",usuario,pwd};
        AT_GetTest gT = new AT_GetTest();

        try {
            test = gT.execute(strings).get();
            TextView pregunta = (TextView)findViewById(R.id.preguntaTest);
            pregunta.setText(test.getEnunciado());
            String[] answer = {test.getRespuesta1().getAnswer(),
                    test.getRespuesta2().getAnswer(),
                    test.getRespuesta3().getAnswer(),
                    test.getRespuesta4().getAnswer(),
                    test.getRespuesta5().getAnswer()};

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
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
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
        AT_PostChoice pC = new AT_PostChoice();
        String [] strings = {usuario,Integer.toString(seleccionada)};
        pC.execute(strings);
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
            System.out.println("Advise 1: "+ test.getRespuesta1().getAdvice()+" con mime: "+ test.getRespuesta1().getRes().getMime());
            //web.loadUrl(test.getRespuesta1().getAdvice().toString()); Abre otra app, no sirve
            web.loadData(test.getRespuesta1().getAdvice().toString(),test.getRespuesta1().getRes().getMime(),null);
            //web.loadData(getString(R.string.ayuda), "text/html", null);
            web.setBackgroundColor(Color.TRANSPARENT);
            web.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);
            layout.addView(web);
        }else if(selectecIndex == 1){
            String consejo = test.getRespuesta1().getAdvice();
            System.out.println("Advise 1: "+ consejo);
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
            System.out.println("Advise 4: "+ test.getRespuesta4().getAdvice());
            videoView.setVideoURI(Uri.parse(test.getRespuesta4().getAdvice()));
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
                System.out.println("Advise 1: "+ test.getRespuesta5().getAdvice());
                audioPlay.setAudioUri(Uri.parse(test.getRespuesta5().getAdvice()));
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
