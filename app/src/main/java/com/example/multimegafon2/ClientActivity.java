package com.example.multimegafon2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientActivity extends AppCompatActivity {
    Thread Thread1 = null;
    EditText etIP, etPort;
    TextView tvMessages;
    String SERVER_IP;
    int SERVER_PORT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        etIP = findViewById(R.id.etIP_client);
        etPort = findViewById(R.id.etPort_client);
        tvMessages = findViewById(R.id.tvMessages_client);
        Button btnConnect = findViewById(R.id.btnConnect_client);
        btnConnect.setOnClickListener(v -> {
            System.out.println("DEBUG: Client - Próba łączenia");
            tvMessages.setText("");
            SERVER_IP = etIP.getText().toString().trim();
            SERVER_PORT = Integer.parseInt(etPort.getText().toString().trim());
            Thread1 = new Thread(new ClientThread());
            Thread1.start();
        });
    }

    public void onDestroy() {
        super.onDestroy();
        Thread1.interrupt();
    }

    private PrintWriter output;
    private BufferedReader input;
    class ClientThread implements Runnable {
        @Override
        public void run() {
            System.out.println("DEBUG: Client - Próba otwarcia gniazda");
            Socket socket;
            try {
                socket = new Socket(SERVER_IP, SERVER_PORT);
                System.out.println("DEBUG: Client - Socket DONE");
                output = new PrintWriter(socket.getOutputStream());
                System.out.println("DEBUG: Client - PrintWriter DONE");
                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                System.out.println("DEBUG: Client - BufferedReader DONE");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("DEBUG: Client - Connected State");
                        tvMessages.setText("Connected\n");
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Thread1: problem with socket");
            }
        }
    }
}