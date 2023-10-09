package it.marcodemartino.cah.server.commands;

import it.marcodemartino.cah.json.JSONObject;
import it.marcodemartino.cah.json.client.CreateGameObject;
import it.marcodemartino.cah.json.server.GameCreatedObject;
import it.marcodemartino.cah.server.GameManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.UUID;

public class CreateNewGameCommand extends Command<CreateGameObject> {

    private static final Logger logger = LogManager.getLogger(CreateNewGameCommand.class);
    private final GameManager gameManager;


    public CreateNewGameCommand(BufferedReader in, PrintWriter out, GameManager gameManager) {
        super(in, out, CreateGameObject.class);
        this.gameManager = gameManager;
    }

    @Override
    public void execute(CreateGameObject object) {
        UUID uuid = gameManager.createGame();
        logger.info("New game with UUID " + uuid.toString() + " created");
        JSONObject jsonObject = new GameCreatedObject(uuid);
        send(jsonObject);
    }
}
