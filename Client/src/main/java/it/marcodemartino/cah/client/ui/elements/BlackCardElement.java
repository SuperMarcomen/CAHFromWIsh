package it.marcodemartino.cah.client.ui.elements;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class BlackCardElement extends VBox {

    public BlackCardElement(String text) {
        setPrefWidth(200);
        setMinWidth(200);
        setMaxWidth(200);

        setPrefHeight(300);
        setMaxHeight(300);
        setPadding(new Insets(20));
        setStyle("-fx-background-color: #d77c7c;");

        Label label = new Label(text);
        label.setWrapText(true);

        getChildren().add(label);
    }
}
