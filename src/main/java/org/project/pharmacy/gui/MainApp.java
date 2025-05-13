// IMPORTANT: Replace "your.actual.package.gui" with the actual package
// where you place this file. For example: org.cse231project.gui
package org.project.pharmacy.gui;

import org.project.pharmacy.logic.*; // Import logic classes
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainApp extends Application {
    // Initialize logic classes
    PharmacyManager pharmacyManager = new PharmacyManager();

    //Initialize the GUI components
    CheckOutSceneCreator checkoutSceneCreator = new CheckOutSceneCreator(this);
    DashBoardSceneCreator dashBoardSceneCreator = new DashBoardSceneCreator(this);
    LoginSceneCreator loginSceneCreator = new LoginSceneCreator(this);
    MoreInfoSceneCreator moreInfoSceneCreator = new MoreInfoSceneCreator(this);
    SearchSceneCreator searchSceneCreator = new SearchSceneCreator(this);
    SignUpSceneCreator signUpSceneCreator = new SignUpSceneCreator(this);
    Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        // Basic UI
        Label welcomeLabel = new Label("Welcome to the JavaFX Application!");
        StackPane rootLayout = new StackPane();
        rootLayout.getChildren().add(welcomeLabel);

        Scene mainScene = new Scene(rootLayout, 450, 300);

        primaryStage.setTitle("My JavaFX GUI");
        primaryStage.setScene(mainScene);
        primaryStage.show();
        this.switchToLoginScene();

        // Initialize the application
        initialize();
    }

    public static void main(String[] args) {
        launch(args);
    }

//    // Optional: Call this method if you want to launch the GUI
//    // from another part of your existing application code.
//    public static void launchGui() {
//        // Ensures JavaFX launches on its own thread if called from an existing main.
//        new Thread(() -> Application.launch(MainApp.class)).start();
//    }



    // switch to another scene
    public void switchToScene(Scene newScene) {
        // Stage stage = (Stage) newScene.getWindow();
        primaryStage.setScene(newScene);
    }

    public void switchToLoginScene() {
        switchToScene(loginSceneCreator.getScene());
    }
    public void switchToDashBoardScene() {
        switchToScene(dashBoardSceneCreator.getScene());
    }
    public void switchToCheckOutScene() {
        switchToScene(checkoutSceneCreator.getScene());
    }
    public void switchToMoreInfoScene() {
        switchToScene(moreInfoSceneCreator.getScene());
    }
    public void switchToSearchScene() {
        switchToScene(searchSceneCreator.getScene());
    }
    public void switchToSignUpScene() {
        switchToScene(signUpSceneCreator.getScene());
    }


    private void initialize() {
        // Initialize any necessary components or data here
        // For example, you can set up the pharmacy manager or load data
        //pharmacyManager.initialize();
    }
}