package it.marcodemartino.cah.client.actions;

import it.marcodemartino.cah.client.Client;
import it.marcodemartino.cah.client.Invoker;
import it.marcodemartino.cah.client.game.GameManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class CreateGameActionTest {

    @Test
    @DisplayName("Create new game and get UUID")
    void execute() throws IOException {
        Client client = new Client();
        client.startConnection("127.0.0.1", 6666);

        GameManager gameManager = new GameManager();
        Action createGame = new CreateGameAction();
        Invoker invoker = new Invoker(client);
        invoker.execute(createGame);

        client.stopConnection();
    }

}