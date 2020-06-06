package br.com.kakobotasso.ichat_alura.services;

import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import br.com.kakobotasso.ichat_alura.activity.MainActivity;
import br.com.kakobotasso.ichat_alura.models.Message;

public class ChatService {
    private MainActivity activity;

    public ChatService(MainActivity activity) {
        this.activity = activity;
    }

    public void send(final Message message) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String text = message.getText();
                try {
                    HttpURLConnection httpURLConnection = (HttpURLConnection) new URL("http://192.168.15.102:8080/polling").openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setRequestProperty("content-type", "application/json");

                    JSONStringer json = new JSONStringer()
                            .object()
                            .key("text")
                            .value(text)
                            .key("id")
                            .value(message.getId()).endObject();

                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    PrintStream ps = new PrintStream(outputStream);

                    ps.println(json.toString());
                    httpURLConnection.connect();
                    httpURLConnection.getInputStream();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    public void getMessages() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpURLConnection httpURLConnection = (HttpURLConnection) new URL("http://192.168.15.102:8080/polling").openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setRequestProperty("content-type", "application/json");

                    httpURLConnection.connect();
                    Scanner scanner = new Scanner(httpURLConnection.getInputStream());
                    StringBuilder builder = new StringBuilder();
                    while (scanner.hasNextLine()) {
                        builder.append(scanner.nextLine());
                    }

                    String json = builder.toString();

                    JSONObject jsonObject = new JSONObject(json);

                    final Message message = new Message(jsonObject.getInt("id"), jsonObject.getString("text"));

                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            activity.addToList(message);
                        }
                    });
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}
