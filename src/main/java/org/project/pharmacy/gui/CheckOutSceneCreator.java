package org.project.pharmacy.gui;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
//todo: display the latest Order information (last in the arraylist)
public class CheckOutSceneCreator implements SceneProvider{
    private MainApp mainApp;
    private Scene scene;

    public CheckOutSceneCreator(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    public Scene getScene() {
        if (scene == null) {
            Label label = new Label("Check Out Scene");
            VBox root = new VBox(label);
            scene = new Scene(root, SceneConfig.SCENE_WIDTH, SceneConfig.SCENE_HEIGHT);
        }
        return scene;
    }
}
