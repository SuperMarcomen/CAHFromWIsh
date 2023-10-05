package it.marcodemartino.cah.client.commands;

import it.marcodemartino.cah.client.game.DummyPlayer;
import it.marcodemartino.cah.client.game.GameManager;
import it.marcodemartino.cah.client.ui.scenes.SceneController;
import it.marcodemartino.cah.game.Player;
import it.marcodemartino.cah.json.server.NotifyPlayerJoinObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.UUID;

public class NotifyPlayerJoinCommand extends Command<NotifyPlayerJoinObject> {

    private final GameManager gameManager;
    private final SceneController sceneController;
    private final Logger logger = LogManager.getLogger(NotifyPlayerJoinCommand.class);

    public NotifyPlayerJoinCommand(GameManager gameManager, SceneController sceneController) {
        super(NotifyPlayerJoinObject.class);
        this.gameManager = gameManager;
        this.sceneController = sceneController;
    }

    @Override
    public void execute(NotifyPlayerJoinObject object) {
        UUID playerUUID = object.getPlayerUUID();
        String playerName = object.getPlayerName();

        logger.info("Player with UUID {} and name {} joined the game", playerUUID, playerName);
        gameManager.getGame().addPlayer(new DummyPlayer(playerUUID, playerName));

        StringBuilder allPlayers = new StringBuilder();

        for (int i = 0; i < gameManager.getGame().getAllPlayers().size(); i++) {
            Player player = gameManager.getGame().getAllPlayers().get(i);
            allPlayers.append(player.getName());
            if (i < gameManager.getGame().getAllPlayers().size() - 1) {
                allPlayers.append(", ");
            }
        }

        sceneController.playersJoinedTextProperty().setValue(allPlayers.toString());
    }
}
