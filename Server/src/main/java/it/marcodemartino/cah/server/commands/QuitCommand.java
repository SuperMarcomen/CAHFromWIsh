package it.marcodemartino.cah.server.commands;

import it.marcodemartino.cah.server.GameManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.UUID;

public class QuitCommand extends Command {

    private final Logger logger = LogManager.getLogger(QuitCommand.class);
    private final GameManager gameManager;
    private final String clientIp;

    public QuitCommand(BufferedReader in, PrintWriter out, GameManager gameManager, String clientIp) {
        super(in, out);
        this.gameManager = gameManager;
        this.clientIp = clientIp;
    }

    @Override
    public void execute(String input) {
        logger.info("Connection with IP terminated: {}", clientIp);

        JSONObject jsonObject = new JSONObject(input);
        out.println("quit");

        if (!jsonObject.has("player_uuid")) return;
        UUID playerUUID = UUID.fromString(jsonObject.getString("player_uuid"));
        gameManager.quitPlayer(playerUUID);
    }
}
