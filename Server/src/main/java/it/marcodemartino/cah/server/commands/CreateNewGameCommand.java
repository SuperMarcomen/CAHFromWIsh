package it.marcodemartino.cah.server.commands;

import it.marcodemartino.cah.server.GameManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.UUID;

public class CreateNewGameCommand extends Command {

    private static final Logger logger = LogManager.getLogger();
    private final GameManager gameManager;


    public CreateNewGameCommand(BufferedReader in, PrintWriter out, GameManager gameManager) {
        super(in, out);
        this.gameManager = gameManager;
    }

    @Override
    public void execute(String input) {
        UUID uuid = gameManager.createGame();
        logger.info("New game with UUID " + uuid.toString() + " created");
        out.println(generateResponse(uuid));
        out.flush();
    }

    private String generateResponse(UUID uuid) {
        return new JSONObject()
                .put("method", "game_created")
                .put("uuid", uuid.toString())
                .toString();
    }
}
