package it.marcodemartino.cah.client.ui.elements;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.scene.control.Button;

public class BigWhiteButton extends Button {

    public BigWhiteButton(String text, ReadOnlyDoubleProperty widthProperty, ReadOnlyDoubleProperty heightProperty) {
        super(text);
        prefWidthProperty().bind(widthProperty.divide(3));
        prefHeightProperty().bind(heightProperty.divide(10));
    }
}
