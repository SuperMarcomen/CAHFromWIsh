package it.marcodemartino.cah.client.commands;

import it.marcodemartino.cah.client.ui.scenes.SceneController;
import it.marcodemartino.cah.json.server.JoinGameResultObject;
import javafx.application.Platform;

public class JoinGameResultCommand extends Command<JoinGameResultObject> {

    private final SceneController sceneController;

    public JoinGameResultCommand(SceneController sceneController) {
        super(JoinGameResultObject.class);
        this.sceneController = sceneController;
    }

    @Override
    public void execute(JoinGameResultObject object) {
        switch (object.getJoinResult()) {
            case NON_EXISTENT:
                showAlert("The game you tried to join doesn't exist");
                return;
            case ALREADY_STARTED:
                showAlert("The game you tried to join has already started");
                return;
            case SUCCESSFUL:
                Platform.runLater(() -> sceneController.activate("start_game"));
        }
    }

    private void showAlert(String message) {
        Platform.runLater(() -> sceneController.getCurrentlyActive().showPopup(message));
    }
}
