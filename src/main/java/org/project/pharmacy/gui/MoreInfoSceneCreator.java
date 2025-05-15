package org.project.pharmacy.gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import org.project.pharmacy.logic.*;


import java.time.LocalDate;

public class MoreInfoSceneCreator implements SceneProvider {

    private MainApp mainApp;
    private Scene scene;
    public MoreInfoSceneCreator(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    @Override
    public Scene getScene() {
      return scene;
    }

    public Scene getScene(int index) {


            Label label = new Label(" More Info Scene");
            GridPane root = new GridPane();
            root.setHgap(10);
            root.setVgap(3.5);
            int column = 1;
            scene = new Scene(root, 700,310);

            boolean isMedicine=false,isReferenceItem=false,isEquipment=false,isHealthProduct=false;

            //

            if(mainApp.pharmacyManager.getAvailableItems().get(index) instanceof Medicine)
                isMedicine = true;
            else if(mainApp.pharmacyManager.getAvailableItems().get(index) instanceof HealthProduct)
                isHealthProduct = true;
            else if(mainApp.pharmacyManager.getAvailableItems().get(index) instanceof Equipment)
                isEquipment=true;
            else if(mainApp.pharmacyManager.getAvailableItems().get(index) instanceof ReferenceItem)
                isReferenceItem=true;

            //mainApp.pharmacyManager.getAvailableItems().get(index) data
            String itemId ="",name ="",price ="",CATEGORY ="",subCategory ="",description ="",isAvailable ="",quantity ="";
            //medicine data
            String dosage ="",requiresPrescription ="",expiryDate ="",activeIngredient ="";
            //health product data
            String isOrganic ="";
            //reference item data
            String publicationDate ="",publisher ="";
            //equipment data
            String warrantyPeriod="",type ="";

            //setting general data strings
            itemId ="Item Id:\t"+mainApp.pharmacyManager.getAvailableItems().get(index).getItemId();
            name = "Name:\t"+mainApp.pharmacyManager.getAvailableItems().get(index).getName();
            price ="Price:\t"+mainApp.pharmacyManager.getAvailableItems().get(index).getPrice();
            CATEGORY ="category:\t"+mainApp.pharmacyManager.getAvailableItems().get(index).getCategory();
            subCategory ="SubCategory:\t"+mainApp.pharmacyManager.getAvailableItems().get(index).getSubCategory();
            description ="Description:\t"+mainApp.pharmacyManager.getAvailableItems().get(index).getDescription();
            isAvailable="Available:\t"+mainApp.pharmacyManager.getAvailableItems().get(index).getAvailable();
            quantity ="Quantity:\t"+mainApp.pharmacyManager.getAvailableItems().get(index).getQuantity();

            //setting general data labels
            Label itemIdLable = new Label(itemId);
            Label nameLable = new Label(name);
            Label priceLable = new Label(price);
            Label CATEGORYLable = new Label(CATEGORY);
            Label subCategoryLable = new Label(subCategory);
            Label descriptionLable = new Label(description);
            Label isAvailableLable = new Label(isAvailable);

            Label quantityLable = new Label();
            quantityLable.setText(quantity);
            Label errorLable = new Label("");

            TextField quantityField = new TextField();
            TextField priceField = new TextField();

            quantityField.setText("0");
            priceField.setText("0");
            //setting medicine labels
            Label activeIngrediantLable = new Label(activeIngredient);
            Label DosageLable = new Label(dosage);
            Label requiresPrescriptionLable = new Label(requiresPrescription);
            Label expiryDateLable = new Label(expiryDate);

            //setting health product labels
            Label isOraganicLable = new Label(isOrganic);

            //setting  reference item labels
            Label publisherLable = new Label(publisher);
            Label publicationDateLable = new Label(publicationDate);

            //setting equipment labels
            Label typeLable = new Label(type);
            Label warrantyPeriodLable = new Label(warrantyPeriod);

            Button DashBoard = new Button("Dash Board");
            DashBoard.setOnAction(e -> {mainApp.switchToDashBoardScene();});
            //setting adding quantity feature
            Button addQuantity = new Button("Add Quantity");
            addQuantity.setOnAction(e -> {addingQuantity(index,quantityField,quantityLable,errorLable);});
            //setting edit price
            Button editPrice = new Button("Edit Price");
            editPrice.setOnAction(e-> editingPrice(index,priceField,priceLable,errorLable));

            //general data
            root.add(itemIdLable, column, 2);
            root.add(nameLable, column, 3);
            root.add(priceLable, column, 4);
            root.add(CATEGORYLable, column, 5);
            root.add(subCategoryLable, column, 6);
            root.add(descriptionLable, column, 7);
            root.add(isAvailableLable, column, 8);
            root.add(quantityLable, column, 9);
            root.add(addQuantity, column, 0);
            root.add(editPrice, column, 1);
            root.add(quantityField, column+1, 0);
            root.add(priceField, column+1, 1);
            root.add(errorLable, column+1, 2);
            root.add(DashBoard, 5, 13);
            //setting rest of data according to its type
            if(isMedicine)
            {
                //medicine
                expiryDate = "Expiry Date:\t"+((Medicine) mainApp.pharmacyManager.getAvailableItems().get(index)).getExpiryDate();
                activeIngredient= "ActiveIngredient:\t"+((Medicine) mainApp.pharmacyManager.getAvailableItems().get(index)).getActiveIngredient();
                dosage ="Dosage:\t"+((Medicine) mainApp.pharmacyManager.getAvailableItems().get(index)).getDosage();
                requiresPrescription ="Requires Prescription:\t"+((Medicine) mainApp.pharmacyManager.getAvailableItems().get(index)).getRequiresPrescription();

                expiryDateLable.setText("Expiry Date:\t"+((Medicine) mainApp.pharmacyManager.getAvailableItems().get(index)).getExpiryDate());
                activeIngrediantLable.setText("ActiveIngredient:\t"+((Medicine) mainApp.pharmacyManager.getAvailableItems().get(index)).getActiveIngredient());
                DosageLable.setText("Dosage:\t"+((Medicine) mainApp.pharmacyManager.getAvailableItems().get(index)).getDosage());
                if(((Medicine)mainApp.pharmacyManager.getAvailableItems().get(index)).getRequiresPrescription())
                requiresPrescriptionLable.setText("Requires Prescription:\tyes");
                else
                requiresPrescriptionLable.setText("Requires Prescription:\tno");

                root.add(expiryDateLable, column, 10);
                root.add(activeIngrediantLable, column, 11);
                root.add(DosageLable, column, 12);
                root.add(requiresPrescriptionLable, column, 13);

            }
            if(isHealthProduct)
            {
                //health product
                expiryDate = "Expiry Date:\t"+((HealthProduct) mainApp.pharmacyManager.getAvailableItems().get(index)).getExpiryDate();
                isOrganic = "is Organic:\t"+((HealthProduct) mainApp.pharmacyManager.getAvailableItems().get(index)).getIsOrganic();

                expiryDateLable.setText("Expiry Date:\t"+expiryDate);
                if(((HealthProduct)mainApp.pharmacyManager.getAvailableItems().get(index)).getIsOrganic())
                isOraganicLable.setText("isOrganic:\tyes");
                else
                    isOraganicLable.setText("isOrganic:\tno");

                root.add(isOraganicLable, column, 10);
                root.add(expiryDateLable, column, 11);
            }
            if(isReferenceItem)
            {
                //reference item
                publisher = "Publisher:\t"+((ReferenceItem) mainApp.pharmacyManager.getAvailableItems().get(index)).getPublisher();
                publicationDate = "Publication Date:\t"+((ReferenceItem) mainApp.pharmacyManager.getAvailableItems().get(index)).getPublicationDate();

                publicationDateLable.setText("Publication Date:\t"+((ReferenceItem) mainApp.pharmacyManager.getAvailableItems().get(index)).getPublicationDate());
                publisherLable.setText("Publisher:\t"+((ReferenceItem) mainApp.pharmacyManager.getAvailableItems().get(index)).getPublisher());

                root.add(publisherLable, column, 10);
                root.add(publicationDateLable, column, 11);
            }
            if(isEquipment)
            {
                //equipment
                type = "type:\t"+((Equipment) mainApp.pharmacyManager.getAvailableItems().get(index)).getType();
                warrantyPeriod = "Warranty Period:\t"+((Equipment) mainApp.pharmacyManager.getAvailableItems().get(index)).getWarrantyPeriod();

                warrantyPeriodLable.setText("Warranty Period:\t"+warrantyPeriod);
                typeLable.setText("Type:\t"+((Equipment) mainApp.pharmacyManager.getAvailableItems().get(index)).getType());

                root.add(typeLable, column, 10);
                root.add(warrantyPeriodLable, column, 11);
            }
        return scene;
    }
    void addingQuantity(int index,TextField quantityField,Label quantityLabel,Label errorLable) {
        String addedQuantityString = quantityField.getText();
        int addedQuantity ;

        try{
            addedQuantity = Integer.parseInt(addedQuantityString);
            mainApp.pharmacyManager.getAvailableItems().get(index).setQuantity(mainApp.pharmacyManager.getAvailableItems().get(index).getQuantity() + addedQuantity);
            quantityLabel.setText("Quantity:\t" + mainApp.pharmacyManager.getAvailableItems().get(index).getQuantity());
            errorLable.setText("");
        }
        catch(NumberFormatException e)
        {
            errorLable.setText("ErrorLable: enter number not a text");
        }
        catch(IllegalArgumentException e)
        {
            errorLable.setText("Error: enter postive number not a negative number ");
        }
        finally {
            quantityField.setText("0");

        }

    }
    void editingPrice(int index,TextField priceField,Label priceLabel,Label errorLable)
    {
        String editPriceString = priceField.getText();
        int newPrice ;

        try{
            newPrice = Integer.parseInt(editPriceString);
            mainApp.pharmacyManager.getAvailableItems().get(index).setPrice( newPrice);
            priceLabel.setText("Price:\t" + newPrice);
            errorLable.setText("");
        }
        catch(NumberFormatException e)
        {
            errorLable.setText("ErrorLable: enter number not a text");
        }
        catch(IllegalArgumentException e)
        {
            errorLable.setText("Error: enter postive number not a negative number ");
        }
        finally {
            priceField.setText("0");
        }

    }
}
