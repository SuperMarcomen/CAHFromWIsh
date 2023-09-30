package it.marcodemartino.cah.client.actions;

import it.marcodemartino.cah.client.Client;
import it.marcodemartino.cah.client.Invoker;
import it.marcodemartino.cah.client.game.GameManager;
import it.marcodemartino.cah.client.ui.scenes.SceneController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class CreateGameActionTest {

    @Test
    @DisplayName("Create new game and get UUID")
    void execute() throws IOException {
        GameManager gameManager = new GameManager();

        Client client = new Client(new GameManager(), new SceneController());
        client.startConnection("127.0.0.1", 6666);
        Action createGame = new CreateGameAction();
        Invoker invoker = new Invoker(client);
        invoker.execute(createGame);

        client.stopConnection();
    }

}