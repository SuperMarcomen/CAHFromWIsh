package it.marcodemartino.cah.client.actions;

public interface Action {

    String execute();
    void handleResponse(String response);
}
