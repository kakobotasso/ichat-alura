package br.com.kakobotasso.ichat_alura.callbacks;

import br.com.kakobotasso.ichat_alura.activity.MainActivity;
import br.com.kakobotasso.ichat_alura.models.Message;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetMessagesCallback implements Callback<Message> {
    private MainActivity activity;

    public GetMessagesCallback(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onResponse(Call<Message> call, Response<Message> response) {
        if(response.isSuccessful()) {
            Message message = response.body();
            activity.addToList(message);
        }
    }

    @Override
    public void onFailure(Call<Message> call, Throwable t) {
        activity.getMessagesFromAPI();
    }
}
