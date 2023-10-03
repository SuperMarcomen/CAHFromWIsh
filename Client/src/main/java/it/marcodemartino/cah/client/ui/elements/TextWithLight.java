package it.marcodemartino.cah.client.ui.elements;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class TextWithLight extends VBox {

    private final Circle circle;
    private final FadeTransition fadeTransition;

    public TextWithLight(String text) {
        circle = new Circle(7);
        circle.getStyleClass().add("circleDisabled");
        Label label = new Label(text);
        label.getStyleClass().add("regular");
        HBox hbox = new HBox(circle, label);
        hbox.setSpacing(10);
        hbox.setAlignment(Pos.CENTER_LEFT);
        getChildren().add(hbox);
        setAlignment(Pos.CENTER);

        fadeTransition = new FadeTransition(Duration.seconds(2), circle);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.setCycleCount(Animation.INDEFINITE);
        fadeTransition.play();
    }

    public void enableCircle() {
        fadeTransition.stop();
        circle.setOpacity(1);
        circle.getStyleClass().remove("circleDisabled");
        circle.getStyleClass().add("circleEnabled");
    }
}
