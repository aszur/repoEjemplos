package es.tta.example;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.docencia.ejemplo1.R;

import es.tta.example.model.AT_GetUserData;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle(R.string.alumnoTitle);
    }

    public boolean authenticate (String login, String pwd){
        boolean comprobacion = true;
        return comprobacion;
    }
    public void accede(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        String login = ((EditText) findViewById(R.id.nombreUsuario)).getText().toString();
        String pwd = ((EditText) findViewById(R.id.claveUsuario)).getText().toString();
        if (authenticate(login, pwd)) {
            intent.putExtra(MenuActivity.EXTRA_LOGIN, login);
            /*AT_GetUserData guD = new AT_GetUserData();
            guD.execute().get();*/
            startActivity(intent);
        }
    }
}
