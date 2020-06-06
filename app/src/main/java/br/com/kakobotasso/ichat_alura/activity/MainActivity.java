package br.com.kakobotasso.ichat_alura.activity;

import br.com.kakobotasso.ichat_alura.adapter.MessageAdapter;
import br.com.kakobotasso.ichat_alura.app.ChatApplication;
import br.com.kakobotasso.ichat_alura.callbacks.GetMessagesCallback;
import br.com.kakobotasso.ichat_alura.callbacks.SendMessageCallback;
import br.com.kakobotasso.ichat_alura.components.ChatComponent;
import br.com.kakobotasso.ichat_alura.models.Message;

import androidx.appcompat.app.AppCompatActivity;
import br.com.kakobotasso.ichat_alura.R;
import br.com.kakobotasso.ichat_alura.services.ChatService;
import retrofit2.Call;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    private int clientId = 1;
    private EditText editText;
    private Button button;
    private ListView lvMessages;
    private List<Message> messageList = new ArrayList<>();

    @Inject
    ChatService chatService;

    private ChatComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ChatApplication app = (ChatApplication) getApplication();
        component = app.getComponent();
        component.inject(this);

        lvMessages = findViewById(R.id.lv_messages);

        MessageAdapter adapter = new MessageAdapter(messageList, this, clientId);

        getMessagesFromAPI();

        lvMessages.setAdapter(adapter);

        editText = findViewById(R.id.et_text);

        button = findViewById(R.id.bt_send);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chatService.send(new Message(clientId, editText.getText().toString())).enqueue(new SendMessageCallback());
            }
        });
    }

    public void addToList(Message message) {
        messageList.add(message);

        MessageAdapter adapter = new MessageAdapter(messageList, this, clientId);

        lvMessages.setAdapter(adapter);

        getMessagesFromAPI();
    }

    public void getMessagesFromAPI() {
        Call<Message> call = chatService.getMessages();
        call.enqueue(new GetMessagesCallback(this));
    }
}
