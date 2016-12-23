package es.tta.example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.docencia.ejemplo1.R;

import es.tta.example.model.Test;
import es.tta.example.presentation.Data;

public class TestActivity extends AppCompatActivity {
    protected Data data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        String[] answer ={"answer1","answer2","answer3","answer4","answer5"};
        RadioGroup opciones = (RadioGroup) findViewById(R.id.opcionesTest);
        int i=0;
        for (String opcion : answer) {
            RadioButton radio = new RadioButton(this);
            radio.setText(opcion);
            radio.setId(i);
            radio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    findViewById(R.id.botonEnviarRespuestaTest).setVisibility(View.VISIBLE);
                }
            });
            opciones.addView(radio);
            i++;
        }
    }
}
