package it.marcodemartino.cah.client;

import it.marcodemartino.cah.client.actions.Action;

import java.util.Optional;

public class Invoker {

    private final Client client;

    public Invoker(Client client) {
        this.client = client;
    }

    public void execute(Action action) {
        client.sendOutput(action.execute());
        Optional<String> response = client.getInput();
        if (response.isEmpty()) return;
        action.handleResponse(response.get());
    }
}
