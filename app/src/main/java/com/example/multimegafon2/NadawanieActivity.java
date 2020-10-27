package com.example.multimegafon2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class NadawanieActivity extends AppCompatActivity {

    private ListView lv;

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
    }

    public void oglaszanieNadawania(){

        Intent intent = new Intent();
        intent.setAction("com.example.broadcast.MY_NOTIFICATION");
        intent.putExtra("data","Notice me senpai!");
        sendBroadcast(intent);

    }

}