package it.marcodemartino.cah.client.commands;

import it.marcodemartino.cah.client.Client;
import it.marcodemartino.cah.client.actions.Action;
import it.marcodemartino.cah.client.actions.JoinGameAction;
import it.marcodemartino.cah.client.game.GameManager;
import it.marcodemartino.cah.client.ui.scenes.SceneController;
import it.marcodemartino.cah.json.server.GameCreatedObject;
import javafx.application.Platform;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.UUID;

public class GameCreatedCommand extends Command<GameCreatedObject> {

    private static final Logger logger = LogManager.getLogger(GameCreatedCommand.class);
    private final GameManager gameManager;
    private final Client client;
    private final SceneController sceneController;

    public GameCreatedCommand(GameManager gameManager, Client client, SceneController sceneController) {
        super(GameCreatedObject.class);
        this.gameManager = gameManager;
        this.client = client;
        this.sceneController = sceneController;
    }

    @Override
    public void execute(GameCreatedObject object) {
        UUID uuid = object.getUuid();
        logger.info("Created new game with UUID {}", uuid);
        gameManager.createGameWithUUID(uuid);

        Action joinAction = new JoinGameAction(gameManager);
        String output = joinAction.execute();
        Platform.runLater(() -> {
            client.sendOutput(output);
            sceneController.activate("start_game");
        });

    }
}
