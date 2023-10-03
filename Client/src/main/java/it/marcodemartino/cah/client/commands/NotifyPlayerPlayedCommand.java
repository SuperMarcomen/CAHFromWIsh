package it.marcodemartino.cah.client.commands;

import it.marcodemartino.cah.client.game.GameManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import java.io.PrintWriter;
import java.util.UUID;

public class NotifyPlayerPlayedCommand extends Command {

    private final Logger logger = LogManager.getLogger(NotifyPlayerPlayedCommand.class);
    private final GameManager gameManager;


    public NotifyPlayerPlayedCommand(PrintWriter out, GameManager gameManager) {
        super(out);
        this.gameManager = gameManager;
    }

    @Override
    public void execute(String input) {
        JSONObject jsonObject = new JSONObject(input);
        UUID playerUUID = UUID.fromString(jsonObject.getString("player_uuid"));
        String playerName = jsonObject.getString("player_name");
        logger.info("Player with UUID {} and name {} played in this round", playerUUID, playerName);

        gameManager.getGame().addPlayersWhoPlayed(playerUUID);
    }
}
