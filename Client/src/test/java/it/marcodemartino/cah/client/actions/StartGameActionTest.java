package it.marcodemartino.cah.client.actions;

import it.marcodemartino.cah.client.Client;
import it.marcodemartino.cah.client.Invoker;
import it.marcodemartino.cah.client.game.GameManager;
import it.marcodemartino.cah.client.ui.scenes.SceneController;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class StartGameActionTest {

    @Test
    void execute() throws InterruptedException {
        Client client = new Client(new GameManager(), new SceneController());
        client.start();
        Thread.sleep(5000);

        GameManager gameManager = client.getGameManager();
        Action createGame = new CreateGameAction();
        Invoker invoker = new Invoker(client);
        invoker.execute(createGame);
        Thread.sleep(1000);

        gameManager.createDummyPlayer("Marco");
        Action joinGame = new JoinGameAction(gameManager, gameManager.getGame().getUuid());
        invoker.execute(joinGame);

        Action startGame = new StartGameAction(gameManager.getGame().getUuid());
        invoker.execute(startGame);
        Thread.sleep(5000);

        assertEquals(10, gameManager.getGame().getWhiteCards().size());
        assertNotNull(gameManager.getGame().getNewBlackCard());
    }
}