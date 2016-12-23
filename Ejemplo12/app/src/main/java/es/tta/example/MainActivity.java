package es.tta.example;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.example.docencia.ejemplo1.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, MenuActivity.class);
        String login = ((EditText) findViewById(R.id.nombreUsuario)).getText().toString();
        String pwd = ((EditText) findViewById(R.id.claveUsuario)).getText().toString();
        if (authenticate(login, pwd)) {
            intent.putExtra(MenuActivity.EXTRA_LOGIN, login); //¿lo esta guardando directamente en la vble de la otra activity?
            startActivity(intent);
        }
    }

    public boolean authenticate (String login, String pwd){
        boolean comprobacion = true;
        return comprobacion;
    }
}
