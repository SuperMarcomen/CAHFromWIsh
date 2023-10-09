package it.marcodemartino.cah.client.actions;

import com.google.gson.Gson;
import it.marcodemartino.cah.json.GsonInstance;
import it.marcodemartino.cah.json.client.CreateGameObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CreateGameAction implements Action {

    private static final Logger logger = LogManager.getLogger(CreateGameAction.class);
    private final Gson gson;

    public CreateGameAction() {
        this.gson = GsonInstance.get();
    }

    @Override
    public String execute() {
        logger.info("Sent request to create game");
        CreateGameObject jsonObject = new CreateGameObject();
        logger.info(gson.toJson(jsonObject));
        return gson.toJson(jsonObject);
    }
}
