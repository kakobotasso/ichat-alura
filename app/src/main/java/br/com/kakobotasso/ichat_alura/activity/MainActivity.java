package br.com.kakobotasso.ichat_alura.activity;

import br.com.kakobotasso.ichat_alura.adapter.MessageAdapter;
import br.com.kakobotasso.ichat_alura.models.Message;

import androidx.appcompat.app.AppCompatActivity;
import br.com.kakobotasso.ichat_alura.R;

import android.os.Bundle;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private int clientId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lvMessages = findViewById(R.id.lv_messages);
        List<Message> messageList = Arrays.asList(
                new Message(1, "Hello class"),
                new Message(2, "Hi")
        );

        MessageAdapter adapter = new MessageAdapter(messageList, this, clientId);

        lvMessages.setAdapter(adapter);
    }
}
