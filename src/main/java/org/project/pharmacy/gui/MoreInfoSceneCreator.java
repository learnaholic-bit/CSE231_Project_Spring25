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
    PharmacyItem pharmacyItem;

    public MoreInfoSceneCreator(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    @Override
    public Scene getScene() {
      return scene;
    }

    public Scene getScene(PharmacyItem pharmacyItem) {
        if (scene == null) {

            Label label = new Label(" More Info Scene");
            GridPane root = new GridPane();
            root.setHgap(15);
            root.setVgap(15);
            scene = new Scene(root, SceneConfig.SCENE_WIDTH, SceneConfig.SCENE_HEIGHT);

            boolean isMedicine=false,isReferenceItem=false,isEquipment=false,isHealthProduct=false;

            //testing
            if(pharmacyItem instanceof Medicine)
                isMedicine = true;
            else if(pharmacyItem instanceof HealthProduct)
                isHealthProduct = true;
            else if(pharmacyItem instanceof Equipment)
                isEquipment=true;
            else if(pharmacyItem instanceof ReferenceItem)
                isReferenceItem=true;

            //pharmacyItem data
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
            itemId ="Item Id:"+pharmacyItem.getItemId();
            name = "Name:"+pharmacyItem.getName();
            price ="Price:"+pharmacyItem.getPrice();
            CATEGORY ="category:"+pharmacyItem.getCategory();
            subCategory ="SubCategory:"+pharmacyItem.getSubCategory();
            description ="Description:"+pharmacyItem.getDescription();
            isAvailable="Available:"+pharmacyItem.getAvailable();
            quantity ="Quantity:"+pharmacyItem.getQuantity();

            //setting general data labels
            Label itemIdLable = new Label(itemId);
            Label nameLable = new Label(name);
            Label priceLable = new Label(price);
            Label CATEGORYLable = new Label(CATEGORY);
            Label subCategoryLable = new Label(subCategory);
            Label descriptionLable = new Label(description);
            Label isAvailableLable = new Label(isAvailable);
            Label quantityLable = new Label(quantity);

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

            //setting adding quantity feature
            Button addQuantity = new Button("Add Quantity");
            TextField quantityField = new TextField();
            String addedQuantity = quantityField.getText();
            addQuantity.setOnAction(e -> {pharmacyItem.setQuantity(pharmacyItem.getQuantity()+Integer.parseInt(addedQuantity));});

            //general data
            root.add(itemIdLable, 5, 2);
            root.add(nameLable, 5, 3);
            root.add(priceLable, 5, 4);
            root.add(CATEGORYLable, 5, 5);
            root.add(subCategoryLable, 5, 6);
            root.add(descriptionLable, 5, 7);
            root.add(isAvailableLable, 5, 8);
            root.add(quantityLable, 5, 9);
            root.add(addQuantity, 7, 3);
            root.add(quantityField, 9, 3);

            //setting rest of data according to its type
            if(isMedicine)
            {
                //medicine
                expiryDate = "Expiry Date:"+((Medicine) pharmacyItem).getExpiryDate();
                activeIngredient= "ActiveIngredient:"+((Medicine) pharmacyItem).getActiveIngredient();
                dosage ="Dosage:"+((Medicine) pharmacyItem).getDosage();
                requiresPrescription ="Requires Prescription:"+((Medicine) pharmacyItem).getRequiresPrescription();
                root.add(expiryDateLable, 5, 10);
                root.add(activeIngrediantLable, 5, 11);
                root.add(DosageLable, 5, 12);
                root.add(requiresPrescriptionLable, 5, 13);
            }
            if(isHealthProduct)
            {
                //health product
                expiryDate = "Expiry Date:"+((HealthProduct) pharmacyItem).getExpiryDate();
                isOrganic = "is Organic:"+((HealthProduct) pharmacyItem).getIsOrganic();

                root.add(isOraganicLable, 5, 10);
                root.add(expiryDateLable, 5, 11);
            }
            if(isReferenceItem)
            {
                //reference item
                publisher = "Publisher:"+((ReferenceItem) pharmacyItem).getPublisher();
                publicationDate = "Publication Date:"+((ReferenceItem) pharmacyItem).getPublicationDate();

                root.add(publisherLable, 5, 10);
                root.add(publicationDateLable, 5, 11);
            }
            if(isEquipment)
            {
                //equipment
                type = "type:"+((Equipment) pharmacyItem).getType();
                warrantyPeriod = "Warranty Period:"+((Equipment) pharmacyItem).getWarrantyPeriod();

                root.add(typeLable, 5, 10);
                root.add(warrantyPeriodLable, 5, 11);
            }
        }
        return scene;
    }
}
