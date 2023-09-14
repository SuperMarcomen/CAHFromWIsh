package it.marcodemartino.cah.client.actions;

import it.marcodemartino.cah.client.Client;
import it.marcodemartino.cah.client.Invoker;
import it.marcodemartino.cah.client.game.GameManager;
import it.marcodemartino.cah.game.Player;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class StartGameActionTest {

    @Test
    void execute() throws IOException {
        Client client = new Client();
        client.startConnection("127.0.0.1", 6666);

        GameManager gameManager = new GameManager();
        Action createGame = new CreateGameAction(gameManager);
        Invoker invoker = new Invoker(client);
        invoker.execute(createGame);

        Player player = new Player("Marco", UUID.randomUUID());
        Action joinGame = new JoinGameAction(player, gameManager.getGame().getUuid());
        invoker.execute(joinGame);

        Action startGame = new StartGameAction(gameManager.getGame().getUuid());
        invoker.execute(startGame);

        client.stopConnection();
    }
}