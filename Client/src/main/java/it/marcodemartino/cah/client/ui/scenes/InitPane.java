package it.marcodemartino.cah.client.ui.scenes;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public abstract class InitPane extends StackPane {

    private final Stage primaryStage;

    protected InitPane(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public abstract void init();

    protected Node createSpacer() {
        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);
        return spacer;
    }

    public void showPopup(String message) {
        Stage popupStage = new Stage(StageStyle.TRANSPARENT);
        popupStage.resizableProperty().setValue(Boolean.FALSE);
        popupStage.initModality(Modality.WINDOW_MODAL);
        popupStage.initOwner(primaryStage);

        popupStage.setTitle("Error");
        Label messageLabel = new Label(message);
        messageLabel.getStyleClass().add("regular");

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> closePopupWithFade(popupStage));

        VBox popupLayout = new VBox();
        popupLayout.setAlignment(Pos.CENTER);
        popupLayout.setSpacing(50);
        popupLayout.getStyleClass().add("popup");
        popupLayout.getChildren().addAll(messageLabel, closeButton);

        Scene popupScene = new Scene(popupLayout, 500, 300);
        popupScene.setFill(null);
        popupScene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

        popupStage.setScene(popupScene);

        popupStage.show();

        // Automatically close the popup after 3 seconds with a fade-out effect
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), e -> closePopupWithFade(popupStage)));
        timeline.setCycleCount(1);
        timeline.play();
    }

    private void closePopupWithFade(Stage popupStage) {
        Parent popupLayout = popupStage.getScene().getRoot();

        FadeTransition fadeOut = new FadeTransition(Duration.millis(150), popupLayout);
        fadeOut.setToValue(0.0);
        fadeOut.setOnFinished(finishedEvent -> popupStage.close());
        fadeOut.play();
    }
}
