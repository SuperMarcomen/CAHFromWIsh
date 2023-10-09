package it.marcodemartino.cah.server.commands;

import it.marcodemartino.cah.game.Game;
import it.marcodemartino.cah.json.client.StartGameObject;
import it.marcodemartino.cah.server.GameManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.UUID;

public class StartGameCommand extends Command<StartGameObject> {

    private static final Logger logger = LogManager.getLogger(StartGameCommand.class);
    private final GameManager gameManager;

    public StartGameCommand(BufferedReader in, PrintWriter out, GameManager gameManager) {
        super(in, out, StartGameObject.class);
        this.gameManager = gameManager;
    }

    @Override
    public void execute(StartGameObject object) {
        UUID gameUUID = object.getGameUUID();
        Game game = gameManager.getGame(gameUUID);
        gameManager.setDeck(game, object.getDecksNames());
        game.start();
        game.sendStartCardsToAllPlayer();
        logger.info("Started a game with UUID {}", gameUUID);
    }
}
