package es.tta.example;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.docencia.ejemplo1.R;

import es.tta.example.model.Test;
import es.tta.example.presentation.Data;

public class TestActivity extends AppCompatActivity {
    protected Data data;
    protected RadioGroup opciones;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        String[] answer ={"answer1","answer2","answer3","answer4","answer5"};
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
    protected void compruebaTest(View v){
        Button bt = (Button)findViewById(R.id.botonEnviarRespuestaTest);
        bt.setVisibility(View.GONE);
        int choices = opciones.getChildCount();
        //obtengo el id de la seleccionada
        int seleccionada = opciones.getCheckedRadioButtonId();
        View opc = opciones.getChildAt(seleccionada);

        if(seleccionada == 3){
            opc.setBackgroundColor(Color.GREEN);
            Toast.makeText(this, R.string.acierto, Toast.LENGTH_SHORT).show();
        }else{
            opc.setBackgroundColor(Color.RED);
            View opcOK = opciones.getChildAt(3);
            opcOK.setBackgroundColor(Color.GREEN);
            findViewById(R.id.botonAyuda).setVisibility(View.VISIBLE);
            Toast.makeText(this, R.string.fallo, Toast.LENGTH_SHORT).show();
        }
    }

    protected void ayudame(View v){
        LinearLayout layout = (LinearLayout)findViewById(R.id.activity_test);
        WebView web = new WebView(this);
        web.loadData(getString(R.string.ayuda), "text/html",null);
        web.setBackgroundColor(Color.TRANSPARENT);
        web.setLayerType(WebView.LAYER_TYPE_SOFTWARE,null);
        layout.addView(web);
    }
}
