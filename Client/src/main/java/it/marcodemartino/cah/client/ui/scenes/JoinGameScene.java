package it.marcodemartino.cah.client.ui.scenes;

import it.marcodemartino.cah.client.Invoker;
import it.marcodemartino.cah.client.actions.Action;
import it.marcodemartino.cah.client.actions.JoinGameAction;
import it.marcodemartino.cah.client.game.GameManager;
import it.marcodemartino.cah.client.ui.elements.BigWhiteButton;
import it.marcodemartino.cah.client.ui.elements.LabelWrapper;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.UUID;

public class JoinGameScene extends InitPane {

    private final Invoker invoker;
    private final GameManager gameManager;

    public JoinGameScene(Invoker invoker, GameManager gameManager, Stage primaryStage) {
        super(primaryStage);
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

        TextField idInputField = createIdInputField();
        HBox idInputFieldWrapper = wrapIdInputField(idInputField);

        Button joinButton = new BigWhiteButton("Join the game", widthProperty(), heightProperty());
        joinButton.setOnAction(e -> handleJoinButtonClick(idInputField));

        HBox joinButtonWrapper = new HBox(joinButton);
        joinButtonWrapper.setAlignment(Pos.CENTER);

        mainContainer.getChildren().addAll(titleLabel, createSpacer(), enterIdLabel, idInputFieldWrapper, createSpacer(), joinButtonWrapper, createSpacer());
        getChildren().add(mainContainer);
    }

    private void handleJoinButtonClick(TextField idInputField) {
        if (idInputField.getText().isEmpty()) {
            showPopup("The ID can not be empty!");
            return;
        }
        gameManager.createGameWithUUID(UUID.fromString(idInputField.getText()));
        Action joinGame = new JoinGameAction(gameManager);
        invoker.execute(joinGame);
    }

    private HBox wrapIdInputField(TextField idInputField) {
        HBox idInputFieldWrapper = new HBox(idInputField);
        idInputFieldWrapper.prefWidthProperty().bind(widthProperty());
        idInputFieldWrapper.setAlignment(Pos.CENTER);
        return idInputFieldWrapper;
    }

    private TextField createIdInputField() {
        TextField idInputField = new TextField();
        idInputField.setPromptText("63b218eb-9a5d-4194-8201-16d5c86ee772");
        idInputField.setId("idInputField");
        idInputField.prefWidthProperty().bind(widthProperty().divide(3));
        return idInputField;
    }
}
