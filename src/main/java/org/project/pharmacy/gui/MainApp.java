// IMPORTANT: Replace "your.actual.package.gui" with the actual package
// where you place this file. For example: org.cse231project.gui
package org.project.pharmacy.gui;

import org.project.pharmacy.logic.*; // Import logic classes
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.Date;

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
        this.switchToScene(dashBoardSceneCreator.getScene());
        //this.switchToSearchScene();
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
    public void switchToMoreInfoScene(PharmacyItem newPharmacyItem) {
        switchToScene(moreInfoSceneCreator.getScene(newPharmacyItem));
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
        pharmacyManager.addItem(new Medicine(101, "Aspirin", 5.99, "Analgesic", "NSAID",
                "Relieves mild to moderate pain and reduces inflammation",
                true, 100, "Acetylsalicylic Acid", "325 mg", false,
                LocalDate.of(2026, 12, 31)));
        pharmacyManager.addItem(new Medicine(102, "Ibuprofen", 7.49, "Analgesic", "NSAID",
                "Treats pain, inflammation, and fever",
                true, 80, "Ibuprofen", "200 mg", false,
                LocalDate.of(2027, 3, 15)));
        pharmacyManager.addItem(new Medicine(103, "Paracetamol", 4.99, "Analgesic", "Non-opioid",
                "Reduces pain and fever",
                true, 120, "Acetaminophen", "500 mg", false,
                LocalDate.of(2026, 10, 20)));
        pharmacyManager.addItem(new Medicine(104, "Amoxicillin", 12.99, "Antibiotic", "Penicillin",
                "Treats bacterial infections",
                true, 50, "Amoxicillin", "500 mg", true,
                LocalDate.of(2026, 8, 10)));
        pharmacyManager.addItem(new Medicine(105, "Metformin", 15.49, "Antidiabetic", "Biguanide",
                "Manages type 2 diabetes",
                true, 60, "Metformin", "500 mg", true,
                LocalDate.of(2027, 1, 25)));
        pharmacyManager.addItem(new Medicine(106, "Lisinopril", 10.99, "Antihypertensive", "ACE Inhibitor",
                "Treats high blood pressure and heart failure",
                true, 70, "Lisinopril", "10 mg", true,
                LocalDate.of(2026, 11, 30)));
        pharmacyManager.addItem(new Medicine(107, "Atorvastatin", 18.99, "Cholesterol", "Statin",
                "Lowers cholesterol levels",
                true, 45, "Atorvastatin", "20 mg", true,
                LocalDate.of(2026, 9, 5)));
        pharmacyManager.addItem(new Medicine(108, "Omeprazole", 13.49, "Gastrointestinal", "Proton Pump Inhibitor",
                "Treats acid reflux and ulcers",
                true, 55, "Omeprazole", "20 mg", false,
                LocalDate.of(2027, 2, 28)));
        pharmacyManager.addItem(new Medicine(109, "Levothyroxine", 11.99, "Hormone", "Thyroid",
                "Treats hypothyroidism",
                true, 65, "Levothyroxine", "50 mcg", true,
                LocalDate.of(2026, 7, 15)));
        pharmacyManager.addItem(new Medicine(110, "Hydrochlorothiazide", 8.99, "Antihypertensive", "Diuretic",
                "Treats high blood pressure and edema",
                true, 90, "Hydrochlorothiazide", "25 mg", true,
                LocalDate.of(2026, 12, 10)));
        /////////////////////////////////////////////////////////////////////////////////////////
        pharmacyManager.addItem(new HealthProduct(201, "Aspirin (Herbal Alternative)", 6.99, "Supplement", "Pain Relief",
                "Natural pain relief supplement inspired by willow bark",
                true, 150, true, LocalDate.of(2026, 6, 30)));
        pharmacyManager.addItem(new HealthProduct(202, "Ibuprofen (Plant-Based)", 8.49, "Supplement", "Anti-Inflammatory",
                "Plant-based anti-inflammatory supplement",
                true, 100, true, LocalDate.of(2026, 9, 15)));
        pharmacyManager.addItem(new HealthProduct(203, "Paracetamol (Herbal Substitute)", 5.99, "Supplement", "Fever Reducer",
                "Herbal supplement for fever and mild pain relief",
                true, 200, true, LocalDate.of(2027, 1, 10)));
        pharmacyManager.addItem(new HealthProduct(204, "Metformin (Natural Support)", 16.99, "Supplement", "Blood Sugar Support",
                "Natural supplement to support healthy blood sugar levels",
                true, 80, true, LocalDate.of(2026, 11, 20)));
        pharmacyManager.addItem(new HealthProduct(205, "Omeprazole (Digestive Aid)", 14.49, "Supplement", "Digestive Health",
                "Natural digestive aid to support stomach acid balance",
                true, 90, true, LocalDate.of(2026, 8, 5)));
        ///////////////////////////////////////////////////////////////////////////////////////////
        pharmacyManager.addItem(new Equipment(301, "Digital Blood Pressure Monitor", 49.99, "Medical Device", "Diagnostic",
                "Accurate blood pressure and pulse measurement",
                true, 20, "Electronic", 24));
        pharmacyManager.addItem(new Equipment(302, "Glucometer", 29.99, "Medical Device", "Monitoring",
                "Measures blood glucose levels for diabetes management",
                true, 30, "Electronic", 12));
        pharmacyManager.addItem(new Equipment(303, "Nebulizer", 79.99, "Medical Device", "Respiratory",
                "Delivers medication in mist form for respiratory conditions",
                true, 15, "Electronic", 36));
        pharmacyManager.addItem(new Equipment(304, "Thermometer (Infrared)", 39.99, "Medical Device", "Diagnostic",
                "Non-contact temperature measurement",
                true, 25, "Electronic", 18));
        pharmacyManager.addItem(new Equipment(305, "Pill Counter Tray", 19.99, "Pharmacy Tool", "Dispensing",
                "Manual tray for counting and sorting pills",
                true, 50, "Manual", 6));
        pharmacyManager.addItem(new Equipment(306, "Pulse Oximeter", 34.99, "Medical Device", "Monitoring",
                "Measures oxygen saturation and heart rate",
                true, 22, "Electronic", 24));
        pharmacyManager.addItem(new Equipment(307, "Pharmacy Scale", 99.99, "Pharmacy Tool", "Weighing",
                "High-precision scale for compounding medications",
                true, 10, "Electronic", 48));
        /////////////////////////////////////////////////////////////////////////////
        pharmacyManager.addItem(new ReferenceItem(401, "Pharmacology Essentials", 89.99, "Reference Book", "Pharmacology",
                "Comprehensive guide to pharmacological principles",
                true, 10, "Lippincott Williams & Wilkins",
                new Date(), true));
        pharmacyManager.addItem(new ReferenceItem(402, "Pharmaceutical Calculations", 59.99, "Reference Book", "Calculations",
                "Textbook for accurate dosage and compounding calculations",
                true, 15, "Wolters Kluwer",
                new Date(), true));
        pharmacyManager.addItem(new ReferenceItem(403, "Drug Interaction Handbook", 74.99, "Reference Book", "Drug Interactions",
                "Detailed guide to drug interactions and contraindications",
                true, 8, "Lexicomp",
                new Date(), true));
        pharmacyManager.addItem(new ReferenceItem(404, "Herbal Supplements Guide", 44.99, "Reference Book", "Herbal Medicine",
                "Overview of herbal supplements and their uses",
                true, 20, "Thieme Medical Publishers",
                new Date(), false));
        pharmacyManager.addItem(new ReferenceItem(405, "Pharmacy Law and Ethics", 69.99, "Reference Book", "Legal",
                "Covers pharmacy regulations and ethical practices",
                true, 12, "American Pharmacists Association",
                new Date(), true));
        pharmacyManager.addItem(new ReferenceItem(406, "OTC Medication Guide", 39.99, "Reference Book", "Over-the-Counter",
                "Consumer guide to over-the-counter medications",
                true, 25, "Consumer Reports",
                new Date(), false));

    }
}