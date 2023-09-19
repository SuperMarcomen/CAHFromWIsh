package it.marcodemartino.cah.client.actions;

import it.marcodemartino.cah.client.Client;
import it.marcodemartino.cah.client.Invoker;
import it.marcodemartino.cah.client.game.GameManager;
import it.marcodemartino.cah.game.Player;
import it.marcodemartino.cah.server.entity.RemotePlayer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.UUID;

class JoinGameActionTest {

    @Test
    @DisplayName("Join a game")
    void execute() throws IOException {
        Client client = new Client();
        client.startConnection("127.0.0.1", 6666);

        GameManager gameManager = new GameManager();
        Action createGame = new CreateGameAction(gameManager);
        Invoker invoker = new Invoker(client);
        invoker.execute(createGame);

        Player player = new RemotePlayer("Marco", UUID.randomUUID(), null);
        Action joinGame = new JoinGameAction(player, gameManager.getGame().getUuid());
        invoker.execute(joinGame);

        client.stopConnection();
    }
}