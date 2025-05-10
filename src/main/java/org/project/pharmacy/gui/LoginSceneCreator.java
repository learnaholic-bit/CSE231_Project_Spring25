package org.project.pharmacy.gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class LoginSceneCreator implements SceneProvider {
    private MainApp mainApp;
    private Scene scene;

    public LoginSceneCreator(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    public Scene getScene() {
        if (scene == null) {
            Label titleLabel = new Label("Login");
            TextField usernameField = new TextField();
            usernameField.setPromptText("Username");
            PasswordField passwordField = new PasswordField();
            passwordField.setPromptText("Password");
            Button loginButton = new Button("Login");
            Label messageLabel = new Label();

            loginButton.setOnAction(e -> {
                String username = usernameField.getText();
                String password = passwordField.getText();
                if (username.isEmpty() || password.isEmpty()) {
                    messageLabel.setText("Please enter username and password.");
                } else {
                    boolean authenticated = mainApp.pharmacyManager.authenticateCustomer(username, password);
                    if (authenticated) {
                        messageLabel.setText("Login successful!");
                        mainApp.switchToDashBoardScene();
                    } else {
                        messageLabel.setText("Invalid username or password.");
                    }
                }
            });

            Text signUpText = new Text("Don't have an account? ");
            Text signUpLink = new Text("Sign Up");
            signUpLink.setStyle("-fx-underline: true; -fx-fill: blue;"); // Make it look like a hyperlink
            signUpLink.setOnMouseClicked(e -> mainApp.switchToSignUpScene());

            TextFlow signUpFlow = new TextFlow(signUpText, signUpLink);

            VBox root = new VBox(10, titleLabel, usernameField, passwordField, loginButton, signUpFlow, messageLabel);
            root.setPadding(new Insets(20));
            scene = new Scene(root, SceneConfig.SCENE_WIDTH, SceneConfig.SCENE_HEIGHT);
        }
        return scene;
    }
}