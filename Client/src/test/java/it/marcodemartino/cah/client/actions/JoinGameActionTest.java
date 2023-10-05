package it.marcodemartino.cah.client.actions;

import it.marcodemartino.cah.client.Client;
import it.marcodemartino.cah.client.Invoker;
import it.marcodemartino.cah.client.game.GameManager;
import it.marcodemartino.cah.client.ui.scenes.SceneController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class JoinGameActionTest {

    @Test
    @DisplayName("Join a game")
    void execute() throws IOException {

        GameManager gameManager = new GameManager();
        Action createGame = new CreateGameAction(gson);
        Client client = new Client(gameManager, new SceneController());
        client.startConnection("127.0.0.1", 6666);
        Invoker invoker = new Invoker(client);
        invoker.execute(createGame);

        gameManager.createDummyPlayer("Marco");
        Action joinGame = new JoinGameAction(gameManager);
        invoker.execute(joinGame);

        client.stopConnection();
    }
}