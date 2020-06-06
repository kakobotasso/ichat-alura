package br.com.kakobotasso.ichat_alura.activity;

import br.com.kakobotasso.ichat_alura.adapter.MessageAdapter;
import br.com.kakobotasso.ichat_alura.models.Message;

import androidx.appcompat.app.AppCompatActivity;
import br.com.kakobotasso.ichat_alura.R;
import br.com.kakobotasso.ichat_alura.services.ChatService;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private int clientId = 1;
    private EditText editText;
    private Button button;
    private ListView lvMessages;
    private List<Message> messageList = new ArrayList<>();
    private ChatService chatService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvMessages = findViewById(R.id.lv_messages);
//        messageList = Arrays.asList(
//                new Message(1, "Hello class"),
//                new Message(2, "Hi")
//        );

        MessageAdapter adapter = new MessageAdapter(messageList, this, clientId);

        chatService = new ChatService(this);
        chatService.getMessages();

        lvMessages.setAdapter(adapter);

        editText = findViewById(R.id.et_text);

        button = findViewById(R.id.bt_send);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chatService.send(new Message(clientId, editText.getText().toString()));
            }
        });
    }

    public void addToList(Message message) {
        messageList.add(message);

        MessageAdapter adapter = new MessageAdapter(messageList, this, clientId);

        lvMessages.setAdapter(adapter);

        chatService.getMessages();
    }
}
