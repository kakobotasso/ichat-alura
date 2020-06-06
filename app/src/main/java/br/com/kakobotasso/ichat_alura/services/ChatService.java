package br.com.kakobotasso.ichat_alura.services;

import br.com.kakobotasso.ichat_alura.models.Message;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ChatService {

    @POST("polling")
    Call<Void> send(@Body Message message);

    @GET("polling")
    Call<Message> getMessages();
}
