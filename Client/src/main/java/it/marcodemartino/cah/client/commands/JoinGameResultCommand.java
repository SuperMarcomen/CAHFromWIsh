package it.marcodemartino.cah.client.commands;

import it.marcodemartino.cah.client.ui.scenes.SceneController;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import org.json.JSONObject;

import java.io.PrintWriter;

public class JoinGameResultCommand extends Command {

    private final SceneController sceneController;

    public JoinGameResultCommand(PrintWriter out, SceneController sceneController) {
        super(out);
        this.sceneController = sceneController;
    }

    @Override
    public void execute(String input) {
        JSONObject jsonObject = new JSONObject(input);
        String result = jsonObject.getString("result");
        if (result.equals("non_existent")) {
            showAlert("The game you tried to join doesn't exist");
            return;
        }
        if (result.equals("already_started")) {
            showAlert("The game you tried to join has already started");
            return;
        }
        if (result.equals("successful")) {
            Platform.runLater(() -> sceneController.activate("start_game"));
        }
    }

    private void showAlert(String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText(message);
            alert.show();
        });
    }
}
