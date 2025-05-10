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
import org.project.pharmacy.logic.Customer;

public class SignUpSceneCreator implements SceneProvider {
    private MainApp mainApp;
    private Scene scene;

    public SignUpSceneCreator(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    public Scene getScene() {
        if (scene == null) {
            Label titleLabel = new Label("Sign Up");
            TextField usernameField = new TextField();
            usernameField.setPromptText("Username");
            PasswordField passwordField = new PasswordField();
            passwordField.setPromptText("Password");
            Button signUpButton = new Button("Sign Up");
            Label messageLabel = new Label();

            signUpButton.setOnAction(e -> {
                String username = usernameField.getText();
                String password = passwordField.getText();
                if (username.isEmpty() || password.isEmpty()) {
                    messageLabel.setText("Please enter username and password.");
                } else {

                    // Here you can add your signup logic
                    messageLabel.setText("Sign up successful for " + username + "!");
                    // register the user in the system
                    Customer newCustomer = new Customer();
                    newCustomer.setName(username);
                    try {
                        mainApp.pharmacyManager.registerCustomer(newCustomer, password);
                    } catch (Exception ex) {
                        messageLabel.setText("Error : " + ex.getMessage());
                        return;
                    }

                    // Switch to login scene after successful signup
                    mainApp.switchToLoginScene();
                }
            });

            Text loginText = new Text("Have an account? ");
            Text loginLink = new Text("Sign Up");
            loginLink.setStyle("-fx-underline: true; -fx-fill: blue;"); // Make it look like a hyperlink
            loginLink.setOnMouseClicked(e -> mainApp.switchToLoginScene());

            TextFlow loginFlow = new TextFlow(loginText, loginLink);

            VBox root = new VBox(10, titleLabel, usernameField, passwordField, signUpButton, loginFlow,messageLabel);
            root.setPadding(new Insets(20));
            scene = new Scene(root, SceneConfig.SCENE_WIDTH, SceneConfig.SCENE_HEIGHT);
        }
        return scene;
    }


}
