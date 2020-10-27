package com.example.multimegafon2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class NadawanieActivity extends AppCompatActivity {

    private ListView lv;
    private MediaRecorder recorder;
    private static String fileName = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nadawanie);
        lv = (ListView) findViewById(R.id.listview);
        List<String> your_array_list = new ArrayList<String>();
        your_array_list.add("Test1");
        your_array_list.add("Test2");
        your_array_list.add("Test3");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                your_array_list );
        lv.setAdapter(arrayAdapter);
        oglaszanieNadawania();

        View view = findViewById(R.id.listview);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.RECORD_AUDIO },
                    10);
        } else {
            nagrywanieMikrofonu(view);
        }
    }


    public void oglaszanieNadawania(){

        Intent intent = new Intent();
        intent.setAction("com.example.broadcast.MY_NOTIFICATION");
        intent.putExtra("data","Notice me senpai!");
        sendBroadcast(intent);

    }


    public void nagrywanieMikrofonu(View view) {
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setOutputFile(fileName);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
       try {
           recorder.start();

           new Handler().postDelayed(new Runnable() {
               public void run() {
                   recorder.stop();
               }
           }, 100);
       }
       catch(Exception e) {
           Snackbar MicException = Snackbar.make(view, "Problem z dostępem do mikrofonu", 1000);
           MicException.show();
       }


    }
}