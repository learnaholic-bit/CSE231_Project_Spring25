package org.project.pharmacy.gui;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MoreInfoSceneCreator implements SceneProvider {

    private MainApp mainApp;
    private Scene scene;

    public MoreInfoSceneCreator(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    public Scene getScene() {
        if (scene == null) {
            Label label = new Label(" More Info Scene");
            VBox root = new VBox(label);
            scene = new Scene(root, SceneConfig.SCENE_WIDTH, SceneConfig.SCENE_HEIGHT);
        }
        return scene;
    }



}
