package es.tta.example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        Test test = data.getTest();
        TextView preguntaTest = (TextView)findViewById(R.id.preguntaTest);
        preguntaTest.setText();
    }
}
