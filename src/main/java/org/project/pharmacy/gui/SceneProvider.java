package org.project.pharmacy.gui;

import javafx.scene.Scene;

public interface SceneProvider {
    Scene getScene();
    //void update(Order order); // Optional, for scenes that need updates
}