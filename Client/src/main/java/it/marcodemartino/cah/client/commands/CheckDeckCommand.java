package it.marcodemartino.cah.client.commands;

import it.marcodemartino.cah.client.ui.scenes.SceneController;
import it.marcodemartino.cah.json.client.CheckDeckObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CheckDeckCommand extends Command<CheckDeckObject> {

    private final SceneController sceneController;
    private final Logger logger = LogManager.getLogger(CheckDeckCommand.class);

    public CheckDeckCommand(SceneController sceneController) {
        super(CheckDeckObject.class);
        this.sceneController = sceneController;
    }

    @Override
    protected void execute(CheckDeckObject checkDeckObject) {
        sceneController.getDecksCheckboxesProperty().get(checkDeckObject.getDeckName()).setValue(checkDeckObject.isSelected());
        logger.info("Checked the deck {} to {}", checkDeckObject.getDeckName(), checkDeckObject.isSelected());
    }
}
