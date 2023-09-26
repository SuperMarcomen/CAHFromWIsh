package it.marcodemartino.cah.client.actions;

import it.marcodemartino.cah.client.Client;
import it.marcodemartino.cah.client.Invoker;
import it.marcodemartino.cah.client.game.GameManager;
import it.marcodemartino.cah.game.Player;
import it.marcodemartino.cah.server.entity.RemotePlayer;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class StartGameActionTest {

    @Test
    void execute() throws IOException, InterruptedException {
        final Client[] client = new Client[1];

        Thread thread = new Thread(() -> {
            client[0] = new Client();
            try {
                client[0].startConnection("127.0.0.1", 6666);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            client[0].run();
        });
        thread.start();
        Thread.sleep(5000);

        GameManager gameManager = client[0].getGameManager();
        Action createGame = new CreateGameAction();
        Invoker invoker = new Invoker(client[0]);
        invoker.execute(createGame);
        Thread.sleep(1000);

        Player player = new RemotePlayer("Marco", UUID.randomUUID(), null);
        Action joinGame = new JoinGameAction(gameManager, player, gameManager.getGame().getUuid());
        invoker.execute(joinGame);

        Action startGame = new StartGameAction(gameManager.getGame().getUuid());
        invoker.execute(startGame);
        Thread.sleep(5000);

        assertEquals(10, gameManager.getGame().getWhiteCards().size());
        assertNotNull(gameManager.getGame().getNewBlackCard());

        thread.stop();
    }
}