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
    String addedQuantityString ="0";
    int addedQuantity =0;
    Label quantityLable = new Label();
    TextField quantityField = new TextField();
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
            root.setHgap(10);
            root.setVgap(3.5);
            int column = 1;
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
            itemId ="Item Id:\t"+pharmacyItem.getItemId();
            name = "Name:\t"+pharmacyItem.getName();
            price ="Price:\t"+pharmacyItem.getPrice();
            CATEGORY ="category:\t"+pharmacyItem.getCategory();
            subCategory ="SubCategory:\t"+pharmacyItem.getSubCategory();
            description ="Description:\t"+pharmacyItem.getDescription();
            isAvailable="Available:\t"+pharmacyItem.getAvailable();
            quantity ="Quantity:\t"+pharmacyItem.getQuantity();

            //setting general data labels
            Label itemIdLable = new Label(itemId);
            Label nameLable = new Label(name);
            Label priceLable = new Label(price);
            Label CATEGORYLable = new Label(CATEGORY);
            Label subCategoryLable = new Label(subCategory);
            Label descriptionLable = new Label(description);
            Label isAvailableLable = new Label(isAvailable);
            quantityLable.setText(quantity);
            quantityField.setText("0");

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


            addQuantity.setOnAction(e -> {addingQuantity(pharmacyItem);});

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
            root.add(quantityField, column+1, 0);

            //setting rest of data according to its type
            if(isMedicine)
            {
                //medicine
                expiryDate = "Expiry Date:\t"+((Medicine) pharmacyItem).getExpiryDate();
                activeIngredient= "ActiveIngredient:\t"+((Medicine) pharmacyItem).getActiveIngredient();
                dosage ="Dosage:\t"+((Medicine) pharmacyItem).getDosage();
                requiresPrescription ="Requires Prescription:\t"+((Medicine) pharmacyItem).getRequiresPrescription();

                expiryDateLable.setText("Expiry Date:\t"+((Medicine) pharmacyItem).getExpiryDate());
                activeIngrediantLable.setText("ActiveIngredient:\t"+((Medicine) pharmacyItem).getActiveIngredient());
                DosageLable.setText("Dosage:\t"+((Medicine) pharmacyItem).getDosage());
                if(((Medicine)pharmacyItem).getRequiresPrescription())
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
                expiryDate = "Expiry Date:\t"+((HealthProduct) pharmacyItem).getExpiryDate();
                isOrganic = "is Organic:\t"+((HealthProduct) pharmacyItem).getIsOrganic();

                expiryDateLable.setText("Expiry Date:\t"+expiryDate);
                if(((HealthProduct)pharmacyItem).getIsOrganic())
                isOraganicLable.setText("isOrganic:\tyes");
                else
                    isOraganicLable.setText("isOrganic:\tno");

                root.add(isOraganicLable, column, 10);
                root.add(expiryDateLable, column, 11);
            }
            if(isReferenceItem)
            {
                //reference item
                publisher = "Publisher:\t"+((ReferenceItem) pharmacyItem).getPublisher();
                publicationDate = "Publication Date:\t"+((ReferenceItem) pharmacyItem).getPublicationDate();

                publicationDateLable.setText("Publication Date:\t"+((ReferenceItem) pharmacyItem).getPublicationDate());
                publisherLable.setText("Publisher:\t"+((ReferenceItem) pharmacyItem).getPublisher());

                root.add(publisherLable, column, 10);
                root.add(publicationDateLable, column, 11);
            }
            if(isEquipment)
            {
                //equipment
                type = "type:\t"+((Equipment) pharmacyItem).getType();
                warrantyPeriod = "Warranty Period:\t"+((Equipment) pharmacyItem).getWarrantyPeriod();

                warrantyPeriodLable.setText("Warranty Period:\t"+warrantyPeriod);
                typeLable.setText("Type:\t"+((Equipment) pharmacyItem).getType());

                root.add(typeLable, column, 10);
                root.add(warrantyPeriodLable, column, 11);
            }
        }
        return scene;
    }
    void addingQuantity(PharmacyItem pharmacyItem) {
        addedQuantityString = quantityField.getText();
        try{
            addedQuantity = Integer.parseInt(addedQuantityString);
            pharmacyItem.setQuantity(pharmacyItem.getQuantity() + addedQuantity);
            quantityLable.setText("Quantity:\t" + pharmacyItem.getQuantity());

        }
        catch(NumberFormatException e)
        {}
        catch(IllegalArgumentException e)
        {}
        finally {
            quantityField.setText("0");
        }

    }
}
