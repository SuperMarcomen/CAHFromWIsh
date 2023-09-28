package it.marcodemartino.cah.client.ui.elements;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class LabelWrapper extends HBox {

    public LabelWrapper(String text, String id) {
        Label titleLabel = new Label(text);
        titleLabel.setId(id);
        getChildren().add(titleLabel);
        prefWidthProperty().bind(widthProperty());
        setAlignment(Pos.CENTER);
    }
}
