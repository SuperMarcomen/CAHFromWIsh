package it.marcodemartino.cah.client.commands;

import it.marcodemartino.cah.client.ui.scenes.SceneController;
import it.marcodemartino.cah.json.client.CheckDeckObject;

public class CheckDeckCommand extends Command<CheckDeckObject> {

    private final SceneController sceneController;

    public CheckDeckCommand(SceneController sceneController) {
        super(CheckDeckObject.class);
        this.sceneController = sceneController;
    }

    @Override
    protected void execute(CheckDeckObject checkDeckObject) {
        sceneController.getDecksCheckboxesProperty().get(checkDeckObject.getDeckName()).setValue(checkDeckObject.isSelected());
    }
}
