package br.com.kakobotasso.ichat_alura.models;

public class Message {
    private String text;
    private int id;

    public Message(int id, String text) {
        this.text = text;
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
