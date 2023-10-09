package it.marcodemartino.cah.client.actions;

import it.marcodemartino.cah.client.Client;
import it.marcodemartino.cah.client.Invoker;
import it.marcodemartino.cah.client.game.GameManager;
import it.marcodemartino.cah.client.ui.scenes.SceneController;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayCardsActionTest {

    @Test
    void execute() throws InterruptedException {
        Client client = new Client(new GameManager(), new SceneController());
        client.start();
        GameManager gameManager = client.getGameManager();
        Action createGame = new CreateGameAction();
        Invoker invoker = new Invoker(client);
        invoker.execute(createGame);
        Thread.sleep(1000);

        gameManager.createDummyPlayer("Marco");
        Action joinGame = new JoinGameAction(gameManager);
        invoker.execute(joinGame);

        Action startGame = new StartGameAction(gameManager.getGame().getUuid());
        invoker.execute(startGame);
        Thread.sleep(5000);

        List<WhiteCard> cardsToBePlayed = new ArrayList<>();
        cardsToBePlayed.add(gameManager.getGame().getWhiteCards().get(0));
        cardsToBePlayed.add(gameManager.getGame().getWhiteCards().get(1));

        Action playCards = new PlayCardsAction(cardsToBePlayed, gameManager);
        invoker.execute(playCards);
        Thread.sleep(2000);

        assertEquals(8, gameManager.getGame().getWhiteCards().size());
        assertEquals(1, gameManager.getGame().getAllPlayedCards().size());
        assertEquals(2, gameManager.getGame().getAllPlayedCards().get("Marco").size());

        client.interrupt();
    }
}