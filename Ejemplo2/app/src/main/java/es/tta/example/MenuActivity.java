package es.tta.example;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.docencia.ejemplo1.R;

public class MenuActivity extends AppCompatActivity {
    Button botonEjercicio;
    Button botonSeguimiento;
    public final static String EXTRA_LOGIN = "es.tta.ejemplo.login";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Intent intent = getIntent();
        botonEjercicio = (Button)findViewById(R.id.botonEjercicio);
        botonSeguimiento = (Button)findViewById(R.id.botonSeguimiento);
        botonEjercicio.setOnClickListener(toOption);
        botonSeguimiento.setOnClickListener(toOption);
        /*Esto de pasar datos se podría hacer mediante una interfaz? ¿Como?*/
        Bundle bundle = intent.getExtras();
        String usuario = bundle.getString(EXTRA_LOGIN);
        //Log.d("Usuario/Usr",usuario+"/"+usr);
        TextView textLogin = (TextView) findViewById(R.id.bienvenidaUsuario);
        String bienvenida = getString(R.string.bienvenida) +" "+ usuario +" "+ getString(R.string.bienvenida2);
        Log.d("Mensaje bienvenida",bienvenida);
        textLogin.setText(bienvenida);

    }
    //Funcion llamada desde layout
    public void test(View v){
        Intent intentTest = new Intent(this, TestActivity.class);
        intentTest.putExtra("usuario", EXTRA_LOGIN); //Se pasa el login por si hay que subir la respuesta asociada al usuario
        startActivity(intentTest);
    }
    //Funcion llamada desde un listener
    View.OnClickListener toOption = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d("Estado", "En onClick por Java");
            if(v.getId() == R.id.botonEjercicio){
                Intent intent = new Intent(MenuActivity.this, ExerciseActivity.class);
                startActivity(intent);
            }else if(v.getId() == R.id.botonSeguimiento){
                //Intent a Seguimiento
            }


        }
    };


}
