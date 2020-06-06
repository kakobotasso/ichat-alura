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
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    private int clientId = 1;
    @BindView(R.id.et_text)
    EditText editText;

    @BindView(R.id.bt_send)
    Button button;

    @BindView(R.id.lv_messages)
    ListView lvMessages;

    private List<Message> messageList = new ArrayList<>();

    @Inject
    ChatService chatService;

    private ChatComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        ChatApplication app = (ChatApplication) getApplication();
        component = app.getComponent();
        component.inject(this);


        MessageAdapter adapter = new MessageAdapter(messageList, this, clientId);

        getMessagesFromAPI();

        lvMessages.setAdapter(adapter);
    }

    @OnClick(R.id.bt_send)
    public void sendMessage() {
        chatService.send(new Message(clientId, editText.getText().toString())).enqueue(new SendMessageCallback());
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
