package it.marcodemartino.cah.client.ui.elements;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class BlackCardElement extends VBox {

    public BlackCardElement(String text) {
        setPadding(new Insets(20));

        String newText = text.replace("\n", System.lineSeparator());
        Label label = new Label(newText);
        label.setWrapText(true);

        getChildren().add(label);
    }
}
