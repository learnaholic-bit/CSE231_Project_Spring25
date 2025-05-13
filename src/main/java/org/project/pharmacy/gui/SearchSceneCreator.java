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
import org.project.pharmacy.logic.PharmacyManager;
import org.project.pharmacy.logic.ReferenceItem;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Objects;

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
            Image searchImage = new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("search_icon.png")));
            ImageView searchIcon = new ImageView(searchImage);
            searchIcon.setFitHeight(20);
            searchIcon.setFitWidth(20);
            searchButton.setGraphic(searchIcon);

            // Filter Button with icon
            Button filterButton = new Button();
            Image filterImage = new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("filter_icon.png")));
            ImageView filterIcon = new ImageView(filterImage);
            filterIcon.setFitHeight(20);
            filterIcon.setFitWidth(20);
            filterButton.setGraphic(filterIcon);

            Button dashboardButton = new Button("Dashboard");
            Image dashboardImage = new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("dashboard_icon.png")));
            ImageView dashboardIcon = new ImageView(dashboardImage);
            dashboardIcon.setFitHeight(20);
            dashboardIcon.setFitWidth(20);
            dashboardButton.setGraphic(dashboardIcon);

            /*//search label
            Label searchLabel = new Label("Search");
            //create Hbox for return
            HBox topHbox = new HBox();
            topHbox.getChildren().addAll(dashboardButton, searchLabel);*/


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
                try {
                    mainApp.pharmacyManager.searchValidate(search.getText(), observableSearchItems);
                } catch (IllegalArgumentException ex) {             //empty search
                    showErrorPopup(ex.getMessage());
                } catch (RuntimeException ex) {
                    showErrorPopup(ex.getMessage());                //not found
                }
                finally {
                    if(!observableSearchItems.isEmpty()) {
                        mainApp.switchToMoreInfoScene(mainApp.pharmacyManager.getAvailableItems().indexOf(observableSearchItems.getFirst()));
                    }
                }
            });
            search.setOnAction(e -> {
                try {
                    mainApp.pharmacyManager.searchValidate(search.getText(), observableSearchItems);
                } catch (IllegalArgumentException ex) {             //empty search
                    showErrorPopup(ex.getMessage());
                } catch (RuntimeException ex) {
                    showErrorPopup(ex.getMessage());                //not found
                }
                finally {
                    if(!observableSearchItems.isEmpty()) {
                        mainApp.switchToMoreInfoScene(mainApp.pharmacyManager.getAvailableItems().indexOf(observableSearchItems.getFirst()));
                    }
                }
            });
            filterButton.setOnAction(e -> {
                try {
                    mainApp.pharmacyManager.searchValidate(search.getText(), observableSearchItems);
                } catch (PharmacyManager.ItemNotFoundException ex) {
                    showErrorPopup("Cannot filter for: "+search.getText()+"\nNo Pharmacy items found");                //not found
                }
                catch (IllegalArgumentException ex) {             //empty search
                    //showErrorPopup(ex.getMessage());
                }
                finally {
                    if(!observableSearchItems.isEmpty()) {
                        FXCollections.sort(observableSearchItems);  //generic sort, uses overridden .compareTo() in pharmacyItem
                    }
                    else {
                        if (search.getText() == "") {
                            ArrayList<PharmacyItem> filteredList = (ArrayList<PharmacyItem>) mainApp.pharmacyManager.getAvailableItems().clone();
                            Collections.sort(filteredList);
                            searchResult.setItems(FXCollections.observableArrayList(filteredList));
                        }
                    }
                }

            });
            dashboardButton.setOnAction(e -> mainApp.switchToDashBoardScene());
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
            vbox.getChildren().addAll(dashboardButton, hbox, searchResult);
            vbox.setSpacing(20); // This will apply if you add more children later
            vbox.setPadding(new Insets(20, 10, 10, 10)); // Add padding: top=20, right=10, bottom=10, left=10
            scene = new Scene(vbox, SceneConfig.SCENE_WIDTH*1.5, SceneConfig.SCENE_HEIGHT*1.2);
            //set the focus to search and select it
            search.requestFocus();
            search.selectAll();
        }
        return scene;
    }
    public void showErrorPopup(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    class TableUtils {
        public static TableView<PharmacyItem> createSearchResultTable(ObservableList<PharmacyItem> items) {
            TableView<PharmacyItem> searchResult = new TableView<>(items);

            TableColumn<PharmacyItem, Integer> idColumn = new TableColumn<>("ID");
            idColumn.setCellValueFactory(new PropertyValueFactory<>("itemId"));

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
            idColumn.prefWidthProperty().bind(searchResult.widthProperty().multiply(0.1));        // 10% for ID
            nameColumn.prefWidthProperty().bind(searchResult.widthProperty().multiply(0.5));      // 30% for Name
            priceColumn.prefWidthProperty().bind(searchResult.widthProperty().multiply(0.25));    // 15% for Price
            isAvailableColumn.prefWidthProperty().bind(searchResult.widthProperty().multiply(0.15)); // 10% for Available

            searchResult.getColumns().clear();
            //searchResult.getColumns().addAll(idColumn, nameColumn, priceColumn, descriptionColumn, isAvailableColumn);
            searchResult.getColumns().addAll(idColumn, nameColumn, priceColumn, isAvailableColumn);
            searchResult.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);

            return searchResult;
        }
    }
}










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
