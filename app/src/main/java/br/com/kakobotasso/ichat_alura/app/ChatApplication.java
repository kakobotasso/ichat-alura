package br.com.kakobotasso.ichat_alura.app;

import android.app.Application;

import br.com.kakobotasso.ichat_alura.components.ChatComponent;
import br.com.kakobotasso.ichat_alura.components.DaggerChatComponent;

public class ChatApplication extends Application {
    private ChatComponent chatComponent;

    @Override
    public void onCreate() {
        chatComponent = DaggerChatComponent.builder().build();
    }

    public ChatComponent getComponent() {
        return chatComponent;
    }

}
