package br.com.kakobotasso.ichat_alura.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.kakobotasso.ichat_alura.R;
import br.com.kakobotasso.ichat_alura.models.Message;

public class MessageAdapter extends BaseAdapter {

    private List<Message> messageList;
    private Activity activity;
    private int clientId;

    public MessageAdapter(List<Message> messageList, Activity activity, int clientId) {
        this.messageList = messageList;
        this.activity = activity;
        this.clientId = clientId;
    }

    @Override
    public int getCount() {
        return messageList.size();
    }

    @Override
    public Message getItem(int i) {
        return messageList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View viewItem = activity.getLayoutInflater().inflate(R.layout.message_item, viewGroup, false);

        TextView text = viewItem.findViewById(R.id.tv_text);

        Message message = getItem(i);

        if(clientId != message.getId()) {
            viewItem.setBackgroundColor(Color.CYAN);
        }

        text.setText(message.getText());

        return viewItem;
    }
}
