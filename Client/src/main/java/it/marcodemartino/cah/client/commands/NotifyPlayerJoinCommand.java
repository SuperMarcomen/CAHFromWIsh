package it.marcodemartino.cah.client.commands;

import it.marcodemartino.cah.client.game.DummyPlayer;
import it.marcodemartino.cah.client.game.GameManager;
import it.marcodemartino.cah.client.ui.scenes.SceneController;
import it.marcodemartino.cah.game.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import java.io.PrintWriter;
import java.util.UUID;

public class NotifyPlayerJoinCommand extends Command {

    private final GameManager gameManager;
    private final SceneController sceneController;
    private final Logger logger = LogManager.getLogger(NotifyPlayerJoinCommand.class);

    public NotifyPlayerJoinCommand(PrintWriter out, GameManager gameManager, SceneController sceneController) {
        super(out);
        this.gameManager = gameManager;
        this.sceneController = sceneController;
    }

    @Override
    public void execute(String input) {
        JSONObject jsonObject = new JSONObject(input);
        UUID playerUUID = UUID.fromString(jsonObject.getString("player_uuid"));
        String playerName = jsonObject.getString("player_name");
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
