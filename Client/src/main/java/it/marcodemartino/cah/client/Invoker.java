package it.marcodemartino.cah.client;

import it.marcodemartino.cah.client.actions.Action;

public class Invoker {

    private final Client client;

    public Invoker(Client client) {
        this.client = client;
    }

    public void execute(Action action) {
        client.sendOutput(action.execute());
    }
}
