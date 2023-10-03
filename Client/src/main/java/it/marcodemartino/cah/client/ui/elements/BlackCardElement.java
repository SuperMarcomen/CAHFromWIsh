package it.marcodemartino.cah.client.ui.elements;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class BlackCardElement extends VBox {

    public BlackCardElement(String text, ReadOnlyDoubleProperty widthProperty) {
        setPadding(new Insets(20));

        String newText = text.replace("\\n", System.lineSeparator());
        Label label = new Label(newText);
        label.setWrapText(true);

        setId("blackCard");
        prefWidthProperty().bind(widthProperty.divide(7));
        prefHeightProperty().bind(prefWidthProperty().multiply(1.3));
        setMaxWidth(Region.USE_PREF_SIZE);
        setMinWidth(Region.USE_PREF_SIZE);
        setMinHeight(Region.USE_PREF_SIZE);
        setMaxHeight(Region.USE_PREF_SIZE);

        getChildren().add(label);
    }
}
