package it.marcodemartino.cah.client.commands;

import it.marcodemartino.cah.client.game.DummyPlayer;
import it.marcodemartino.cah.client.game.GameManager;
import it.marcodemartino.cah.client.ui.scenes.SceneController;
import it.marcodemartino.cah.game.Player;
import org.json.JSONObject;

import java.io.PrintWriter;
import java.util.UUID;

public class NotifyPlayerJoinCommand extends Command {

    private final GameManager gameManager;
    private final SceneController sceneController;

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
        gameManager.getGame().addPlayer(new DummyPlayer(playerUUID, playerName));
        StringBuilder allPlayers = new StringBuilder();

        for (Player player : gameManager.getGame().getAllPlayers()) {
            allPlayers.append(player.getName()).append(", ");
        }
        sceneController.playersJoinedTextProperty().set(allPlayers.toString());
    }
}
