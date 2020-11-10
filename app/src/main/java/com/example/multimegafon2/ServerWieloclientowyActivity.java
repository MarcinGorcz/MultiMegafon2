package com.example.multimegafon2;

import androidx.appcompat.app.AppCompatActivity;

import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;

public class ServerWieloclientowyActivity extends AppCompatActivity {
    ServerSocket serverSocket;
    //Thread Thread1 = null;
    ThreadHandler th = null;
    TextView tvIP, tvPort;
    TextView tvMessages;

    public static String SERVER_IP = "";
    public static final int SERVER_PORT = 8080;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_wieloclientowy);
        tvIP = findViewById(R.id.tvIP_server_multi);
        tvPort = findViewById(R.id.tvPort_server_multi);
        tvMessages = findViewById(R.id.tvMessages_server_multi);
        try {
            SERVER_IP = getLocalIpAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        //Thread1 = new Thread(new MultiServerThread());
        //Thread1.start();
        th = new ThreadHandler();
        th.start();
    }

    public void onDestroy() {
        super.onDestroy();
        th.interrupt();
    }

    private String getLocalIpAddress() throws UnknownHostException {
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        assert wifiManager != null;
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int ipInt = wifiInfo.getIpAddress();
        return InetAddress.getByAddress(ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(ipInt).array()).getHostAddress();
    }

    class ThreadHandler extends Thread {
        private ServerSocket ss;
        private ArrayList<MultiServerThread> serverThreads = new ArrayList<MultiServerThread>();

        public void run() {
            try {
                    ss = new ServerSocket(SERVER_PORT);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            tvMessages.setText("Not connected");
            tvIP.setText("IP: " + SERVER_IP);
            tvPort.setText("Port: " + String.valueOf(SERVER_PORT));

            while (!isInterrupted()) { //You should handle interrupts in some way, so that the thread won't keep on forever if you exit the app.
                MultiServerThread thread = null;
                try {
                    thread = new MultiServerThread( ss.accept());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                serverThreads.add(thread);
                thread.start();
            }
        }
    }
    class MultiServerThread extends Thread {
        private Socket socket;
        private PrintWriter output;
        private BufferedReader input;

        public MultiServerThread(Socket socket){
            this.socket = socket;
        }

        @Override
        public void run() {
                try {
                    output = new PrintWriter(socket.getOutputStream());
                    input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvMessages.setText("Connected\n");
                            System.out.println("DEBUG: MultiServer ConnectedState");
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}