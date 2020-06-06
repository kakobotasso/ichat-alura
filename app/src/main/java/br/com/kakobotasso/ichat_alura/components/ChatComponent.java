package br.com.kakobotasso.ichat_alura.components;

import br.com.kakobotasso.ichat_alura.activity.MainActivity;
import br.com.kakobotasso.ichat_alura.modules.ChatModule;
import dagger.Component;

@Component(modules = ChatModule.class)
public interface ChatComponent {
    void inject(MainActivity activity);
}
