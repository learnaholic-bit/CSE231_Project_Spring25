package org.project.pharmacy.gui;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.project.pharmacy.logic.PharmacyItem;

import java.util.ArrayList;

//if you are going to create orders here
//todo: Create Order , update (Latest) Order,

public class DashBoardSceneCreator implements SceneProvider {
    private MainApp mainApp;
    private Scene scene;

    public DashBoardSceneCreator(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    private TextField[] quantityFields; // To store TextFields for order processing
    @Override
    public Scene getScene() {
          //  Label label = new Label("Dashboard");
            // VBox root = new VBox(label);
            // Create GridPane for the table
            GridPane gridPane = new GridPane();
            gridPane.setAlignment(Pos.CENTER);
            gridPane.setHgap(0);
            gridPane.setVgap(0);
            gridPane.setPadding(new Insets(10));
            gridPane.setGridLinesVisible(false);

            // Set column constraints for resizing
            ColumnConstraints col1 = new ColumnConstraints();
            col1.setPercentWidth(15);
            ColumnConstraints col2 = new ColumnConstraints();
            col2.setPercentWidth(25);
            ColumnConstraints col3 = new ColumnConstraints();
            col3.setPercentWidth(15);
            ColumnConstraints col4 = new ColumnConstraints();
            col4.setPercentWidth(15);
            ColumnConstraints col5 = new ColumnConstraints();
            col5.setPercentWidth(15);
            ColumnConstraints col6 = new ColumnConstraints();
            col6.setPercentWidth(15);
            gridPane.getColumnConstraints().addAll(col1, col2, col3, col4, col5, col6);

            // Add table headers (bold and larger)
            Label idHeader = new Label("Product ID");
            idHeader.setStyle("-fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 5; -fx-border-color: black; -fx-border-width: 1; -fx-background-color: #e0e0e0;");
            idHeader.setMaxWidth(Double.MAX_VALUE);
            gridPane.add(idHeader, 0, 0);

            Label nameHeader = new Label("Name of Medicine");
            nameHeader.setStyle("-fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 5; -fx-border-color: black; -fx-border-width: 1; -fx-background-color: #e0e0e0;");
            nameHeader.setMaxWidth(Double.MAX_VALUE);
            gridPane.add(nameHeader, 1, 0);

            Label priceHeader = new Label("Price ($)");
            priceHeader.setStyle("-fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 5; -fx-border-color: black; -fx-border-width: 1; -fx-background-color: #e0e0e0;");
            priceHeader.setMaxWidth(Double.MAX_VALUE);
            gridPane.add(priceHeader, 2, 0);

            Label qtyHeader = new Label("Available Quantity");
            qtyHeader.setStyle("-fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 5; -fx-border-color: black; -fx-border-width: 1; -fx-background-color: #e0e0e0;");
            qtyHeader.setMaxWidth(Double.MAX_VALUE);
            gridPane.add(qtyHeader, 3, 0);

            Label buyHeader = new Label("Quantity to Buy");
            buyHeader.setStyle("-fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 5; -fx-border-color: black; -fx-border-width: 1; -fx-background-color: #e0e0e0;");
            buyHeader.setMaxWidth(Double.MAX_VALUE);
            gridPane.add(buyHeader, 4, 0);

            Label infoHeader = new Label("Details");
            infoHeader.setStyle("-fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 5; -fx-border-color: black; -fx-border-width: 1; -fx-background-color: #e0e0e0;");
            infoHeader.setMaxWidth(Double.MAX_VALUE);
            gridPane.add(infoHeader, 5, 0);

            // Initialize array to store quantity TextFields
            quantityFields = new TextField[ (mainApp.pharmacyManager.getAvailableItems() ).size()];

            // Add rows for each medicine
            for (int i = 0; i < (mainApp.pharmacyManager.getAvailableItems() ).size(); i++) {
                String id = String.valueOf((mainApp.pharmacyManager.getAvailableItems().get(i)).getItemId());
                String name = (mainApp.pharmacyManager.getAvailableItems().get(i)).getName();
                String price = String.valueOf((mainApp.pharmacyManager.getAvailableItems().get(i)).getPrice());
                String quantity = String.valueOf((mainApp.pharmacyManager.getAvailableItems().get(i)).getQuantity());
                String info = (mainApp.pharmacyManager.getAvailableItems().get(i)).getDescription();

                // Add medicine details (bold and larger)
                Label idLabel = new Label(id);
                idLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 12px; -fx-padding: 5; -fx-border-color: black; -fx-border-width: 1; -fx-background-color: white;");
                idLabel.setMaxWidth(Double.MAX_VALUE);
                gridPane.add(idLabel, 0, i + 1);

                Label nameLabel = new Label(name);
                nameLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 12px; -fx-padding: 5; -fx-border-color: black; -fx-border-width: 1; -fx-background-color: white;");
                nameLabel.setMaxWidth(Double.MAX_VALUE);
                gridPane.add(nameLabel, 1, i + 1);

                Label priceLabel = new Label(price);
                priceLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 12px; -fx-padding: 5; -fx-border-color: black; -fx-border-width: 1; -fx-background-color: white;");
                priceLabel.setMaxWidth(Double.MAX_VALUE);
                gridPane.add(priceLabel, 2, i + 1);

                Label qtyLabel = new Label(quantity);
                qtyLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 12px; -fx-padding: 5; -fx-border-color: black; -fx-border-width: 1; -fx-background-color: white;");
                qtyLabel.setMaxWidth(Double.MAX_VALUE);
                gridPane.add(qtyLabel, 3, i + 1);

                // TextField and buttons for quantity
                TextField quantityField = new TextField();
                quantityField.setPrefWidth(50);
                quantityField.setStyle("-fx-font-size: 12px; -fx-border-color: black; -fx-border-width: 1; -fx-background-color: #f0f0f0;");
                quantityFields[i] = quantityField;

                Button increaseButton = new Button("+");
                increaseButton.setStyle("-fx-font-size: 12px; -fx-padding: 2 5; -fx-border-color: black; -fx-border-width: 1;");
                increaseButton.setOnAction(e -> adjustQuantity(quantityField, quantity, 1));

                Button decreaseButton = new Button("-");
                decreaseButton.setStyle("-fx-font-size: 12px; -fx-padding: 2 5; -fx-border-color: black; -fx-border-width: 1;");
                decreaseButton.setOnAction(e -> adjustQuantity(quantityField, quantity, -1));

                HBox quantityBox = new HBox(5, quantityField, increaseButton, decreaseButton);
                quantityBox.setAlignment(Pos.CENTER_LEFT);
                quantityBox.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-background-color: white; -fx-padding: 5;");
                gridPane.add(quantityBox, 4, i + 1);

                // Eye button for more info
                Button eyeButton = new Button("👁");
                eyeButton.setStyle("-fx-font-size: 14px; -fx-padding: 5; -fx-border-color: black; -fx-border-width: 1; -fx-background-color: #e0e0e0; -fx-border-radius: 50%;");
                eyeButton.setMaxWidth(Double.MAX_VALUE);
                int index =i;
                eyeButton.setOnAction(e ->{mainApp.switchToMoreInfoScene(index);});//showMoreInfo(name, info));
                eyeButton.setOnMouseEntered(e -> eyeButton.setStyle("-fx-font-size: 14px; -fx-padding: 5; -fx-border-color: black; -fx-border-width: 1; -fx-background-color: #d0d0d0; -fx-border-radius: 50%;"));
                eyeButton.setOnMouseExited(e -> eyeButton.setStyle("-fx-font-size: 14px; -fx-padding: 5; -fx-border-color: black; -fx-border-width: 1; -fx-background-color: #e0e0e0; -fx-border-radius: 50%;"));
                gridPane.add(eyeButton, 5, i + 1);

                // Quantity validation
                quantityField.textProperty().addListener((obs, oldValue, newValue) -> {
                    if (!newValue.matches("\\d*")) {
                        quantityField.setText(newValue.replaceAll("[^\\d]", ""));
                    }
                    try {
                        int enteredQty = newValue.isEmpty() ? 0 : Integer.parseInt(newValue);
                        int availableQty = Integer.parseInt(quantity);
                        if (enteredQty > availableQty) {
                            quantityField.setText(quantity);
                            showAlert("Error", "Cannot buy more than available quantity (" + quantity + ").");
                        }
                    } catch (NumberFormatException ignored) {
                        // Ignore if input is empty or invalid
                    }
                });
            }

            // Finish Order button
            Button finishOrderButton = new Button("Finish Order");
            finishOrderButton.setStyle("-fx-font-weight: bold; -fx-font-size: 12px; -fx-background-color: #4CAF50; -fx-text-fill: white; -fx-padding: 5 10;");
            finishOrderButton.setMaxWidth(Double.MAX_VALUE);
            finishOrderButton.setOnAction(e -> processOrder(mainApp.pharmacyManager.getAvailableItems()));
         //   gridPane.add(finishOrderButton, 0,  (mainApp.pharmacyManager.getAvailableItems() ).size() + 1, 6, 1); // Span all 6 columns
            // Finish Order button
            Button searchButton = new Button("Search");
            searchButton.setStyle("-fx-font-weight: bold; -fx-font-size: 12px; -fx-background-color: #0000FF; -fx-text-fill: white; -fx-padding: 5 10;");
            searchButton.setMaxWidth(Double.MAX_VALUE);
            searchButton.setOnAction(e -> mainApp.switchToSearchScene());
         //   gridPane.add(searchButton, 0,  (mainApp.pharmacyManager.getAvailableItems() ).size() +3, 6, 1); // Span all 6 columns
            // Wrap GridPane in ScrollPane for vertical scrolling
            ScrollPane scrollPane = new ScrollPane(gridPane);
            scrollPane.setFitToWidth(true); // GridPane width matches ScrollPane viewport
            scrollPane.setFitToHeight(false); // Allow vertical scrolling
            scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); // Hide horizontal scrollbar
            scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); // Show vertical scrollbar when needed
            scrollPane.setPannable(true); // Allow mouse dragging to scroll

// Create VBox to hold ScrollPane and Finish Order button
            VBox contentBox = new VBox(10,searchButton, scrollPane, finishOrderButton);
            contentBox.setAlignment(Pos.CENTER);
            contentBox.setPadding(new Insets(10));

// Wrap contentBox in VBox for resizing
            VBox root = new VBox(contentBox);
            root.setPadding(new Insets(10));
            root.setAlignment(Pos.CENTER);

            scene = new Scene(root, SceneConfig.SCENE_WIDTH, SceneConfig.SCENE_HEIGHT);
            //scene = new Scene(root, Screen.getPrimary().getVisualBounds().getWidth(), Screen.getPrimary().getVisualBounds().getHeight());

// Bind ScrollPane height to scene height after rendering
            Platform.runLater(() -> {
                scrollPane.prefViewportHeightProperty().bind(scene.heightProperty()
                        .subtract(contentBox.getPadding().getTop() + contentBox.getPadding().getBottom())
                        .subtract(root.getPadding().getTop() + root.getPadding().getBottom())
                        .subtract(30 + contentBox.getSpacing())); // Estimate button height as 30px
            });
/*            // Wrap GridPane in VBox for resizing
            VBox root = new VBox(gridPane);
            root.setPadding(new Insets(10));
            root.setAlignment(Pos.CENTER);
            scene = new Scene(root, SceneConfig.SCENE_WIDTH, SceneConfig.SCENE_HEIGHT);*/

        return scene;
    }
    // Method to adjust quantity using + or - buttons
    private void adjustQuantity(TextField quantityField, String maxQuantity, int change) {
        try {
            int currentQty = quantityField.getText().isEmpty() ? 0 : Integer.parseInt(quantityField.getText());
            int maxQty = Integer.parseInt(maxQuantity);
            int newQty = currentQty + change;

            if (newQty >= 0 && newQty <= maxQty) {
                quantityField.setText(String.valueOf(newQty));
            } else if (newQty > maxQty) {
                quantityField.setText(maxQuantity);
                showAlert("Error", "Cannot buy more than available quantity (" + maxQuantity + ").");
            }
        } catch (NumberFormatException ignored) {
            quantityField.setText("0");
        }
    }

    // Method to process the order
    private void processOrder(ArrayList<PharmacyItem> pharmacyItems) {
        StringBuilder orderSummary = new StringBuilder("Order Summary:\n\n");
        double totalCost = 0.0;
        boolean hasItems = false;

        for (int i = 0; i <  (pharmacyItems ).size(); i++) {
            String id = String.valueOf((pharmacyItems.get(i)).getItemId());
            String name = (pharmacyItems.get(i)).getName();
            double price = (pharmacyItems.get(i)).getPrice();
            String qtyText = quantityFields[i].getText();
            int qty = qtyText.isEmpty() ? 0 : Integer.parseInt(qtyText);

            if (qty > 0) {
                hasItems = true;
                double itemCost = price * qty;
                totalCost += itemCost;
                orderSummary.append(String.format("ID: %s, %s: %d units at $%.2f each = $%.2f\n", id, name, qty, price, itemCost));
            }
        }

        if (!hasItems) {
            showAlert("Order Empty", "No items selected. Please enter quantities to buy.");
            return;
        }

        orderSummary.append(String.format("\nTotal Cost: $%.2f", totalCost));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Order Confirmation");
        alert.setHeaderText("Order Processed Successfully");
        alert.setContentText(orderSummary.toString());
        alert.showAndWait();

        // Clear TextFields after order
        for (TextField field : quantityFields) {
            field.clear();
        }
    }
/*
    // Method to show more info in a new pane (Stage)
    private void showMoreInfo(String medicineName, String info) {
        Stage infoStage = new Stage();
        infoStage.setTitle("More Info: " + medicineName);

        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));

        Label nameLabel = new Label("Medicine: " + medicineName);
        nameLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 12px;");
        Label infoLabel = new Label("Details: " + info);
        infoLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 12px;");
        Button closeButton = new Button("Close");
        closeButton.setStyle("-fx-font-weight: bold; -fx-font-size: 12px;");
        closeButton.setOnAction(e -> infoStage.close());

        vbox.getChildren().addAll(nameLabel, infoLabel, closeButton);

        Scene infoScene = new Scene(vbox, 300, 150);
        infoStage.setScene(infoScene);
        infoStage.show();
    }
    }*/
    // Method to show alert dialogs
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();


}}
