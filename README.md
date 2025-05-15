# Pharmacy Management System

## Description

This project is a Pharmacy Management System designed to manage pharmacy inventory, customer information, and orders. It features a JavaFX-based graphical user interface (GUI) for user interaction.

## Core Features

The system is built around several key components:

*   **`PharmacyManager`**: The central class responsible for managing the inventory of pharmacy items, customer data, orders, and user authentication. It handles operations like adding, removing, finding, and sorting items, as well as registering and authenticating users.
*   **`PharmacyItem`**: An abstract base class representing a generic item in the pharmacy. It includes common attributes like item ID, name, price, category, description, availability, and quantity.
    *   **`Medicine`**: Represents medicinal products, including details like dosage, prescription requirements, expiry date, and active ingredients. Implements `Sellable`.
    *   **`HealthProduct`**: Represents general health products, with attributes like organic status and expiry date. Implements `Sellable`.
    *   **`Equipment`**: Represents medical equipment, including type and warranty period. Implements `Sellable`.
    *   **`ReferenceItem`**: Represents reference materials like books or journals, with details like publisher and publication date.
*   **`Sellable`**: An interface defining common operations for items that can be sold, such as `sellItem()`, `getPrice()`, `isAvailable()`, and `isSoldOut()`.
*   **`Person`**: A base class for individuals, storing name, age, contact number, and address.
    *   **`Customer`**: Extends `Person` to represent pharmacy customers, including a customer ID.
*   **`Order`**: Manages customer orders, including a list of items and the total amount.
*   **GUI Components (`org.project.pharmacy.gui`)**:
    *   **`MainApp`**: The main entry point for the JavaFX application, managing scene transitions.
    *   **`SceneProvider`**: An interface for classes that create and provide JavaFX scenes.
    *   **`SignUpSceneCreator`**: Creates the user sign-up scene.
    *   **`SearchSceneCreator`**: Creates the item search scene, allowing users to search and view pharmacy items in a table.
    *   Other scenes (implied): Login, Dashboard, More Info.

## Key Functionalities

*   **Inventory Management**:
    *   Add, remove, and find items by ID.
    *   Sort items by name, ID, quantity, or price.
    *   Update item quantity and availability.
    *   Display detailed information for each item.
*   **Customer Management**:
    *   Register new customers with hashed passwords (using BCrypt).
    *   Authenticate existing customers.
*   **Order Processing**:
    *   Create new orders for customers.
    *   Add or remove items from an order.
    *   Calculate the total amount for an order.
*   **Search**:
    *   Search for pharmacy items by name.
    *   Filter and sort search results.
*   **User Interface**:
    *   Interactive GUI built with JavaFX.
    *   Scenes for login, sign-up, dashboard, item search, and detailed item view.

## Technologies Used

*   **Java**: Core programming language.
*   **JavaFX**: For building the graphical user interface.
*   **Maven**: Project build and dependency management.
*   **JUnit**: For unit testing (e.g., `MedicineTest.java`).
*   **BCrypt**: For secure password hashing.

## Project Structure

*   `src/main/java/org/project/pharmacy/logic`: Contains the core business logic classes.
*   `src/main/java/org/project/pharmacy/gui`: Contains the JavaFX GUI classes.
*   `src/test/java/org/project/pharmacy/logic`: Contains unit tests for the logic classes.
*   `src/main/resources`: Contains resource files like `config.properties` and UI icons.

## How to Run

This is a Maven project. Ensure you have Java and Maven installed.
The application can be run by executing the `main` method in the `org.project.pharmacy.gui.MainApp` class.