package it.marcodemartino.cah.client.commands;

import it.marcodemartino.cah.client.game.GameManager;
import it.marcodemartino.cah.json.server.NotifyPlayerPlayedObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.UUID;

public class NotifyPlayerPlayedCommand extends Command<NotifyPlayerPlayedObject> {

    private final Logger logger = LogManager.getLogger(NotifyPlayerPlayedCommand.class);
    private final GameManager gameManager;


    public NotifyPlayerPlayedCommand(GameManager gameManager) {
        super(NotifyPlayerPlayedObject.class);
        this.gameManager = gameManager;
    }

    @Override
    public void execute(NotifyPlayerPlayedObject object) {
        UUID playerUUID = object.getPlayerUUID();
        String playerName = object.getPlayerName();

        logger.info("Player with UUID {} and name {} played in this round", playerUUID, playerName);

        gameManager.getGame().addPlayersWhoPlayed(playerUUID);
    }
}
