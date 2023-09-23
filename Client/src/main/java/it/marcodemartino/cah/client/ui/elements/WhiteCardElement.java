package it.marcodemartino.cah.client.ui.elements;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;

public class WhiteCardElement extends StackPane {

    public WhiteCardElement(String text) {
        HBox mainContainer = new HBox();
        mainContainer.setAlignment(Pos.TOP_LEFT);
        HBox.setHgrow(mainContainer, Priority.NEVER);
        mainContainer.setMinSize(0, 0);
        setOnMouseClicked(e -> {
            mainContainer.setStyle("-fx-background-color: #21869d;");

        });
        mainContainer.setStyle("-fx-background-color: #9b0f0f;");
        mainContainer.setMinWidth(150);
        Label cardText = new Label(text);
        cardText.setWrapText(true);
        mainContainer.getChildren().add(cardText);

        getChildren().add(mainContainer);
    }
}
