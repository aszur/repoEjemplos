package es.tta.example;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.docencia.ejemplo1.R;

public class MenuActivity extends AppCompatActivity {

    public final static String EXTRA_LOGIN = "es.tta.ejemplo.login";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Intent intent = getIntent();
        /*Esto de pasar datos se podría hacer mediante una interfaz? ¿Como?*/
        Bundle bundle = intent.getExtras();
        String usuario = bundle.getString(EXTRA_LOGIN);
        TextView textLogin = (TextView)findViewById(R.id.bienvenidaUsuario);
        textLogin.setText(R.string.bienvenida + usuario + R.string.bienvenida2);



   }

}
