package org.project.pharmacy.gui;

//import org.project.pharmacy.gui.images.*;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.project.pharmacy.logic.Equipment;
import org.project.pharmacy.logic.PharmacyItem;
import org.project.pharmacy.logic.ReferenceItem;

import java.util.ArrayList;
import java.util.Date;

public class SearchSceneCreator implements SceneProvider{
    //private ArrayList<PharmacyItem> items = new ArrayList<>();
    ObservableList<PharmacyItem> observableSearchItems = FXCollections.observableArrayList();
    private TableView<PharmacyItem> searchResult;
    private MainApp mainApp;
    private Scene scene;

    public SearchSceneCreator(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    public Scene getScene() throws RuntimeException{
        if (scene == null) {
            VBox vbox = new VBox();
            HBox hbox = new HBox();

            // TextField for search
            TextField search = new TextField("Search?");
            //search.setPrefWidth(250);
            // Search Button with icon
            Button searchButton = new Button();
            /*Image searchImage = new Image("images/filter_icon.png");
            ImageView searchIcon = new ImageView(searchImage);
            searchIcon.setFitHeight(20);
            searchIcon.setFitWidth(20);
            searchButton.setGraphic(searchIcon);*/

            // Filter Button with icon
            Button filterButton = new Button();
            /*Image filterImage = new Image("images/filter_icon.png");
            ImageView filterIcon = new ImageView(filterImage);
            filterIcon.setFitHeight(20);
            filterIcon.setFitWidth(20);
            filterButton.setGraphic(filterIcon);*/

            // Add components to HBox
            hbox.getChildren().addAll(search, searchButton, filterButton);
            hbox.setSpacing(10);
            hbox.setAlignment(Pos.CENTER);
            HBox.setHgrow(search, Priority.ALWAYS);


            //Display Array List
            //searchResult = TableUtils.createSearchResultTable(observableSearchItems);
            searchResult = TableUtils.createSearchResultTable(FXCollections.observableArrayList(mainApp.pharmacyManager.getAvailableItems()));
            search.setOnKeyTyped(e -> {
                //System.out.println("entering");
                observableSearchItems = mainApp.pharmacyManager.searchByName(search.getText());
                searchResult.setItems(observableSearchItems);
                if (search.getText() == "") searchResult.setItems(FXCollections.observableArrayList(mainApp.pharmacyManager.getAvailableItems()));
            });
            searchButton.setOnAction(e -> {
                if (search.getText() == "") {
                    throw new IllegalArgumentException("Invalid String");
                }
                if (observableSearchItems.isEmpty()) {
                    throw new IllegalArgumentException("Not Found");
                }
                else if(!observableSearchItems.isEmpty()) {
                    mainApp.switchToMoreInfoScene(mainApp.pharmacyManager.getAvailableItems().indexOf(observableSearchItems.getFirst()));
                }
            });
            search.setOnAction(e -> {
                if (search.getText() == "") {
                    throw new IllegalArgumentException("Invalid String");
                }
                if (observableSearchItems.isEmpty()) {
                    throw new IllegalArgumentException("Not Found");
                }
                else if(!observableSearchItems.isEmpty()) {
                    mainApp.switchToMoreInfoScene(mainApp.pharmacyManager.getAvailableItems().indexOf(observableSearchItems.getFirst()));
                }
            });
            filterButton.setOnAction(e -> {
                if (search.getText() == "") {
                    throw new IllegalArgumentException("Invalid String");
                }
                if (observableSearchItems.isEmpty()) {
                    throw new IllegalArgumentException("Not Found");
                }
                else if(!observableSearchItems.isEmpty()) {
                    FXCollections.sort(observableSearchItems);
                }
            });
            //searchButton.setOnAction();
            /*
            observableSearchItems = mainApp.pharmacyManager.searchByName("dig");
            searchResult = TableUtils.createSearchResultTable(observableSearchItems);  */      //for test

//            //for test
//            observableSearchItems.addAll(
//                    mainApp.pharmacyManager.getAvailableItems()
//            );

            //observableItems.setAll(items);
            // Add HBox to VBox
            vbox.getChildren().addAll(hbox, searchResult);
            vbox.setSpacing(20); // This will apply if you add more children later
            vbox.setPadding(new Insets(20, 10, 10, 10)); // Add padding: top=20, right=10, bottom=10, left=10

            scene = new Scene(vbox, SceneConfig.SCENE_WIDTH*1.2, SceneConfig.SCENE_HEIGHT);
        }
        return scene;
    }
    class TableUtils {
        public static TableView<PharmacyItem> createSearchResultTable(ObservableList<PharmacyItem> items) {
            TableView<PharmacyItem> searchResult = new TableView<>(items);
            /*
            TableColumn<PharmacyItem, Integer> idColumn = new TableColumn<>("ID");
            idColumn.setCellValueFactory(new PropertyValueFactory<>("itemId"));
            */
            TableColumn<PharmacyItem, String> nameColumn = new TableColumn<>("Name");
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

            TableColumn<PharmacyItem, Double> priceColumn = new TableColumn<>("Price");
            priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
            /*
            TableColumn<PharmacyItem, String> descriptionColumn = new TableColumn<>("Description");
            descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
            */
            TableColumn<PharmacyItem, Boolean> isAvailableColumn = new TableColumn<>("Available");
            isAvailableColumn.setCellValueFactory(cellData -> {
                PharmacyItem item = cellData.getValue();
                if (item instanceof ReferenceItem) {
                    ReferenceItem refItem = (ReferenceItem) item;
                    return new SimpleBooleanProperty(refItem.isLoanable()).asObject();
                } else {
                    return new SimpleBooleanProperty(cellData.getValue().getAvailable()).asObject();
                }
            });
            isAvailableColumn.setCellFactory(column -> new TableCell<PharmacyItem, Boolean>() {
                @Override
                protected void updateItem(Boolean item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item ? "Yes" : "No");
                    }
                }
            });
            /*
            // Bind column widths to a percentage of TableView width
            idColumn.prefWidthProperty().bind(searchResult.widthProperty().multiply(0.05));        // 10% for ID
            nameColumn.prefWidthProperty().bind(searchResult.widthProperty().multiply(0.3));      // 30% for Name
            priceColumn.prefWidthProperty().bind(searchResult.widthProperty().multiply(0.1));    // 15% for Price
            descriptionColumn.prefWidthProperty().bind(searchResult.widthProperty().multiply(0.45)); // 35% for Description
            isAvailableColumn.prefWidthProperty().bind(searchResult.widthProperty().multiply(0.1)); // 10% for Available
            */
            // Bind column widths to a percentage of TableView width
            nameColumn.prefWidthProperty().bind(searchResult.widthProperty().multiply(0.5));      // 30% for Name
            priceColumn.prefWidthProperty().bind(searchResult.widthProperty().multiply(0.4));    // 15% for Price
            isAvailableColumn.prefWidthProperty().bind(searchResult.widthProperty().multiply(0.1)); // 10% for Available

            searchResult.getColumns().clear();
            //searchResult.getColumns().addAll(idColumn, nameColumn, priceColumn, descriptionColumn, isAvailableColumn);
            searchResult.getColumns().addAll(nameColumn, priceColumn, isAvailableColumn);
            searchResult.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);

            return searchResult;
        }
    }
    void searchValid() {

    }
}

/*

 */








/*
public void start(Stage stage) {

        // Scene and Stage setup
        Scene scene = new Scene(vbox, 500, 400);
        stage.setTitle("Search");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
 */
