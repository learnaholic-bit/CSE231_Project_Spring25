package org.project.pharmacy.gui;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

//if you are going to create orders here
//todo: Create Order , update (Latest) Order,

public class DashBoardSceneCreator implements SceneProvider {
    private MainApp mainApp;
    private Scene scene;

    public DashBoardSceneCreator(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    public Scene getScene() {
        if (scene == null) {
            Label label = new Label("Dashboard");
            VBox root = new VBox(label);
            scene = new Scene(root, SceneConfig.SCENE_WIDTH, SceneConfig.SCENE_HEIGHT);
        }
        return scene;
    }

}
