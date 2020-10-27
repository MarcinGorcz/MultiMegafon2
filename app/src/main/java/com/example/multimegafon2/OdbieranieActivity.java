package com.example.multimegafon2;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;

public class OdbieranieActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odbieranie);

        mediaPlayer = MediaPlayer.create(OdbieranieActivity.this, R.raw.req);
        mediaPlayer.start();

    }

    public void onDestroy() {
        mediaPlayer.stop();
        super.onDestroy();
    }


}