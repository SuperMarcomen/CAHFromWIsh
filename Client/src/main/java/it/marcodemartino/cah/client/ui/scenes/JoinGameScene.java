package it.marcodemartino.cah.client.ui.scenes;

import it.marcodemartino.cah.client.Invoker;
import it.marcodemartino.cah.client.actions.Action;
import it.marcodemartino.cah.client.actions.JoinGameAction;
import it.marcodemartino.cah.client.game.GameManager;
import it.marcodemartino.cah.client.ui.elements.LabelWrapper;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.util.UUID;

public class JoinGameScene extends InitPane {

    private final Invoker invoker;
    private final GameManager gameManager;

    public JoinGameScene(Invoker invoker, GameManager gameManager) {
        this.invoker = invoker;
        this.gameManager = gameManager;
    }

    @Override
    public void init() {
        VBox mainContainer = new VBox();
        mainContainer.setPadding(new Insets(20));
        setId("background");

        HBox titleLabel = new LabelWrapper("Cards Against Humanity", "titleLabel");

        HBox enterIdLabel = new LabelWrapper("Enter the id of the game", "enterIdLabel");

        TextField idInputField = new TextField("DH76-PO86-DJE4-48DA");
        idInputField.setId("idInputField");
        idInputField.prefWidthProperty().bind(widthProperty().divide(3));
        HBox idInputFieldWrapper = new HBox(idInputField);
        idInputFieldWrapper.prefWidthProperty().bind(widthProperty());
        idInputFieldWrapper.setAlignment(Pos.CENTER);

        Button joinButton = new Button("Join the game");
        joinButton.setOnAction(e -> {
            if (idInputField.getText().isEmpty()) {
                showAlert();
                return;
            }
            gameManager.createGameWithUUID(UUID.fromString(idInputField.getText()));
            Action joinGame = new JoinGameAction(gameManager);
            invoker.execute(joinGame);
        });
        joinButton.prefWidthProperty().bind(widthProperty().divide(3));
        joinButton.prefHeightProperty().bind(heightProperty().divide(10));
        HBox joinButtonWrapper = new HBox(joinButton);
        joinButtonWrapper.setAlignment(Pos.CENTER);

        mainContainer.getChildren().addAll(titleLabel, createSpacer(), enterIdLabel, idInputFieldWrapper, createSpacer(), joinButtonWrapper, createSpacer());
        getChildren().add(mainContainer);
    }

    private Node createSpacer() {
        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);
        return spacer;
    }

    private void showAlert() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setContentText("Insert an id");
        alert.show();
    }
}
