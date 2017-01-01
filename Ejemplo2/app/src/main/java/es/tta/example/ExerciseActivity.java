package es.tta.example;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.docencia.ejemplo1.R;

import java.io.File;
import java.io.IOException;

public class ExerciseActivity extends AppCompatActivity {
    public static final int PICTURE_REQUEST_CODE = 1;
    public static final int VIDEO_REQUEST_CODE = 2;
    public static final int AUDIO_REQUEST_CODE = 3;
    public static final int READ_REQUEST_CODE = 4;

    protected Uri pictureUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        Log.d("Estado", "En onCreate ExerciseActivity");
    }

    public void takePicture(View view){
        Log.d("Estado", "En takePicture");
        if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            Log.d("Estado", "En takePicture. No camara");
            Toast.makeText(this,R.string.noCamera, Toast.LENGTH_SHORT).show();
        }
        else
        {
            Log.d("Estado", "En takePicture. Con camara");
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            Log.d("Estado", "En takePicture. Intent creado");
            if(intent.resolveActivity(getPackageManager())!= null){
                File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                try {
                    File file = File.createTempFile("tta",".jpg", dir);
                    pictureUri = Uri.fromFile(file);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,pictureUri);
                    Log.d("Estado", "En takePicture. Antes del StartForResult");
                    startActivityForResult(intent, PICTURE_REQUEST_CODE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else
            {
                Log.d("Estado", "En takePicture. No app disponible");
                Toast.makeText(this,R.string.noApp, Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void recordVideo (View view){
        if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            Toast.makeText(this,R.string.noCamera, Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            if(intent.resolveActivity(getPackageManager())!= null){
                File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                try {
                    File file = File.createTempFile("tta",".jpg", dir);
                    pictureUri = Uri.fromFile(file);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,pictureUri);
                    startActivityForResult(intent, VIDEO_REQUEST_CODE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else
            {
                Toast.makeText(this,R.string.noApp, Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void recordAudio(View view) {
        if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            Toast.makeText(this,R.string.noMicro, Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent intent = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
            if(intent.resolveActivity(getPackageManager())!= null){
                startActivityForResult(intent, AUDIO_REQUEST_CODE);
            }
            else
            {
                Toast.makeText(this,R.string.noApp, Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void sendFile(View view){
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");
        startActivityForResult(intent, READ_REQUEST_CODE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode != Activity.RESULT_OK);
            return;
        /*switch (requestCode){
            case READ_REQUEST_CODE:
            case VIDEO_REQUEST_CODE:
            case AUDIO_REQUEST_CODE:
                sendFile(data.getData());
                //break;
            case PICTURE_REQUEST_CODE:
                sendFile(pictureUri);
                //break;
        }*/
    }
}
