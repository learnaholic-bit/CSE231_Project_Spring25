package org.project.pharmacy.logic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.*;

//todo: recheck the access modifiers of the methods

public class PharmacyManager extends Person {
    private ArrayList<PharmacyItem> pharmacyItems;
    private ArrayList<Person> customers; // Changed to ArrayList
    private ArrayList<Order> orders;       // Changed to ArrayList
    private String employeeId;
    private double salary;
    private Map<String, String> passwordMap = new HashMap<>();

    /**
     * Constructor to initialize PharmacyManager with specific details.
     *
     * @param name          The name of the manager.
     * @param age           The age of the manager.
     * @param contactNumber The contact number of the manager.
     * @param address       The address of the manager.
     * @param employeeId    The employee ID of the manager.
     * @param salary        The salary of the manager.
     */
    public PharmacyManager(String name, int age, String contactNumber, String address,
                           String employeeId, double salary) {
        super(name, age, contactNumber, address);
        this.employeeId = employeeId;
        this.salary = salary;
        this.pharmacyItems = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.orders = new ArrayList<>();
    }

    /**
     * Default constructor to initialize PharmacyManager with default values.
     */
    public PharmacyManager() {
        super("admin", 0, "000-000-0000", "Unknown");
        this.employeeId = "";
        this.salary = 0.0;
        this.pharmacyItems = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.orders = new ArrayList<>();
        // Initialize the password map with a default entry for the manager
        loadDefaultCredentials();
    }

    /**
     * Loads default credentials from the configuration file.
     */
    private void loadDefaultCredentials() {
        Properties props = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.err.println("Unable to find config.properties");
                return;
            }

            props.load(input);
            String defaultUsername = props.getProperty("default.username");
            String defaultPassword = props.getProperty("default.password");

            if (defaultUsername != null && defaultPassword != null) {
                this.passwordMap.put(defaultUsername.toLowerCase(), BCrypt.hashpw(defaultPassword, BCrypt.gensalt()));
                System.out.println("Default credentials loaded successfully.");
            }
        } catch (IOException ex) {
            System.err.println("Error loading config.properties file: " + ex.getMessage());
        }
    }


    /**
     * Adds a new item to the pharmacy inventory.
     *
     * @param item The PharmacyItem to be added.
     * @throws IllegalArgumentException If the item is null.
     */
    public void addItem(PharmacyItem item) throws IllegalArgumentException {
        if (item == null) {
            throw new IllegalArgumentException("Cannot add a null item to the inventory.");
        }
        pharmacyItems.add(item);
        System.out.println("Item added: " + item.getName());
    }

    /**
     * Removes an item from the pharmacy inventory.
     *
     * @param item The PharmacyItem to be removed.
     * @throws IllegalArgumentException If the item is null or not found in the inventory.
     */
    public void removeItem(PharmacyItem item) throws IllegalArgumentException {
        if (item == null) {
            throw new IllegalArgumentException("Cannot remove a null item from the inventory.");
        }
        if (pharmacyItems.remove(item)) {
            System.out.println("Item removed: " + item.getName());
        } else {
            throw new IllegalArgumentException("Item not found in inventory.");
        }
    }

    /**
     * Finds an item in the inventory by its ID.
     *
     * @param itemId The ID of the item to find.
     * @return The PharmacyItem if found, otherwise throws an exception.
     * @throws IllegalArgumentException If the item ID is invalid or not found.
     */
    public PharmacyItem findItemById(int itemId) throws IllegalArgumentException {
        if (itemId <= 0) {
            throw new IllegalArgumentException("Item ID must be greater than zero.");
        }
        for (PharmacyItem item : pharmacyItems) {
            if (item.getItemId() == itemId) {
                return item;
            }
        }
        throw new IllegalArgumentException("Item with ID " + itemId + " not found.");
    }

    /**
     * Displays all items in the pharmacy inventory.
     */
    public void displayAllItems() {
        System.out.println("Current Pharmacy Inventory:");
        if (pharmacyItems.isEmpty()) {
            System.out.println("The inventory is empty.");
            return;
        }
        for (PharmacyItem item : pharmacyItems) {
            item.displayInfo();
        }
    }

    /**
     * Sorts items in the inventory by their name.
     *
     * @param descending If true, sorts in descending order; otherwise, ascending.
     */
    public void sortItemsByName(Boolean descending) {
        if (descending != null && descending) {
            pharmacyItems.sort(Comparator.comparing(PharmacyItem::getName).reversed());
        } else {
            pharmacyItems.sort(Comparator.comparing(PharmacyItem::getName));
        }
        System.out.println("Items sorted by name " + (descending != null && descending ? "descending." : "ascending."));
    }

    /**
     * Sorts items in the inventory by their ID.
     *
     * @param descending If true, sorts in descending order; otherwise, ascending.
     */
    public void sortItemsById(Boolean descending) {
        if (descending != null && descending) {
            pharmacyItems.sort(Comparator.comparingInt(PharmacyItem::getItemId).reversed());
        } else {
            pharmacyItems.sort(Comparator.comparingInt(PharmacyItem::getItemId));
        }
        System.out.println("Items sorted by ID " + (descending != null && descending ? "descending." : "ascending."));
    }

    /**
     * Sorts items in the inventory by their quantity.
     *
     * @param descending If true, sorts in descending order; otherwise, ascending.
     */
    public void sortItemsByQuantity(Boolean descending) {
        if (descending != null && descending) {
            pharmacyItems.sort(Comparator.comparingInt(PharmacyItem::getQuantity).reversed());
        } else {
            pharmacyItems.sort(Comparator.comparingInt(PharmacyItem::getQuantity));
        }
        System.out.println("Items sorted by quantity " + (descending != null && descending ? "descending." : "ascending."));
    }

    /**
     * Sorts items in the inventory by their price.
     *
     * @param descending If true, sorts in descending order; otherwise, ascending.
     */
    public void sortItemsByPrice(Boolean descending) {
        if (descending != null && descending) {
            pharmacyItems.sort(Comparator.comparingDouble(PharmacyItem::getPrice).reversed());
        } else {
            pharmacyItems.sort(Comparator.comparingDouble(PharmacyItem::getPrice));
        }
        System.out.println("Items sorted by price " + (descending != null && descending ? "descending." : "ascending."));
    }

    /////////////////////////////////////////////////////////////////
    /**
     * Sorts items in the inventory by their name and return new array.
     *
     * @param descending If true, sorts in descending order; otherwise, ascending.
     */
    public ArrayList<PharmacyItem> sortItemsByNameArr(Boolean descending) {
        ArrayList <PharmacyItem> sortedItem = (ArrayList<PharmacyItem>) pharmacyItems.clone();

        if (descending != null && descending) {
            sortedItem.sort(Comparator.comparing(PharmacyItem::getName).reversed());
        } else {
            sortedItem.sort(Comparator.comparing(PharmacyItem::getName));
        }
       //System.out.println("Items sorted by name " + (descending != null && descending ? "descending." : "ascending."));
        return sortedItem;
    }

    /**
     * Sorts items in the inventory by their ID.
     *
     * @param descending If true, sorts in descending order; otherwise, ascending.
     */
    public ArrayList<PharmacyItem> sortItemsByIdArr(Boolean descending) {
        ArrayList <PharmacyItem> sortedItem = (ArrayList<PharmacyItem>) pharmacyItems.clone();
        if (descending != null && descending) {
            sortedItem.sort(Comparator.comparingInt(PharmacyItem::getItemId).reversed());
        } else {
            sortedItem.sort(Comparator.comparingInt(PharmacyItem::getItemId));
        }
        //System.out.println("Items sorted by ID " + (descending != null && descending ? "descending." : "ascending."));
        return sortedItem;
    }

    /**
     * Sorts items in the inventory by their quantity.
     *
     * @param descending If true, sorts in descending order; otherwise, ascending.
     */
    public ArrayList<PharmacyItem> sortItemsByQuantityArr(Boolean descending) {
        ArrayList <PharmacyItem> sortedItem = (ArrayList<PharmacyItem>) pharmacyItems.clone();
        if (descending != null && descending) {
            sortedItem.sort(Comparator.comparingInt(PharmacyItem::getQuantity).reversed());
        } else {
            sortedItem.sort(Comparator.comparingInt(PharmacyItem::getQuantity));
        }
        //System.out.println("Items sorted by quantity " + (descending != null && descending ? "descending." : "ascending."));
        return sortedItem;
    }

    /**
     * Sorts items in the inventory by their price.
     *
     * @param descending If true, sorts in descending order; otherwise, ascending.
     */
    public ArrayList<PharmacyItem> sortItemsByPriceArr(Boolean descending) {
        ArrayList <PharmacyItem> sortedItem = (ArrayList<PharmacyItem>) pharmacyItems.clone();
        if (descending != null && descending) {
            sortedItem.sort(Comparator.comparingDouble(PharmacyItem::getPrice).reversed());
        } else {
            sortedItem.sort(Comparator.comparingDouble(PharmacyItem::getPrice));
        }
        //System.out.println("Items sorted by price " + (descending != null && descending ? "descending." : "ascending."));
        return sortedItem;
    }


    public ObservableList<PharmacyItem> searchByName(String targetItem) {
        ArrayList <PharmacyItem> sortedByName = this.sortItemsByNameArr(false);
        ObservableList<PharmacyItem> searchItems = FXCollections.observableArrayList();
        for (PharmacyItem o : sortedByName) {
            /*if(o.compareTo(targetItem)<=0) {*/        //using compareTo that takes String
            if(o.getName().compareTo(targetItem)<=0) {
                System.out.println(o.getName().toLowerCase().contains(targetItem.toLowerCase()));
                if(o.getName().toLowerCase().contains(targetItem.toLowerCase())) {
                    //System.out.println("entering second if");
                    searchItems.add(o);
                }
            }
            else {
                //FXCollections.sort(searchItems);
                searchItems.sorted();
                break;
            }
        }
        //for (PharmacyItem o : searchItems) System.out.println(o.getName());
        //System.out.println(searchItems.sorted());
        return searchItems;
    }


    /**
     * Retrieves all available items in the inventory.
     *
     * @return A list of available PharmacyItems.
     */
//    public ArrayList<PharmacyItem> getAvailableItems() {
//        ArrayList<PharmacyItem> availableItems = new ArrayList<>();
//        for (PharmacyItem item : pharmacyItems) {
//            if (item.getAvailable()) {
//                availableItems.add(item);
//            }
//        }
//        return availableItems;
//    }
    public ArrayList<PharmacyItem> getAvailableItems() {

        return this.pharmacyItems;
    }

    /**
     * Displays details of a specific item by its ID.
     *
     * @param itemId The ID of the item to display.
     */
    public void displayItemDetails(int itemId) {
        PharmacyItem item = findItemById(itemId);
        if (item != null) {
            item.displayInfo();
        }
    }

    /**
     * Updates the quantity of a specific item in the inventory.
     *
     * @param itemId      The ID of the item to update.
     * @param newQuantity The new quantity to set.
     * @throws IllegalArgumentException If the quantity is negative or the item is not found.
     */
    public void updateItemQuantity(int itemId, int newQuantity) throws IllegalArgumentException {
        if (newQuantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }
        PharmacyItem item = findItemById(itemId);
        if (item != null) {
            item.setQuantity(newQuantity);
            System.out.println("Updated quantity for item: " + item.getName());
        }
    }

    /**
     * Marks a specific item as unavailable in the inventory.
     *
     * @param itemId The ID of the item to mark as unavailable.
     * @throws IllegalArgumentException If the item is not found.
     */
    public void markItemAsUnavailable(int itemId) throws IllegalArgumentException {
        PharmacyItem item = findItemById(itemId);
        if (item != null) {
            item.setAvailable(false);
            System.out.println("Item marked as unavailable: " + item.getName());
        }
    }

    /**
     * Creates a new order for a customer.
     *
     * @param customer The customer placing the order.
     * @return The created Order object.
     * @throws IllegalArgumentException If the customer is null.
     */
    public Order createOrder(Customer customer) throws IllegalArgumentException {
        Order newOrder;
        if (customer == null)
            newOrder = new Order();
        else
            newOrder = new Order(customer);

        orders.add(newOrder); // Add the order to the ArrayList

        // Add customer if not already in the system
        if (!customers.contains(customer)) {
            customers.add(customer);
            System.out.println("New customer added: " + (customer != null ? customer.getName() : null));
        }

        System.out.println("Order created for customer: " + (customer != null ? customer.getName() : null));
        return newOrder;
    }

    /**
     * Sells an item that implements the Sellable interface.
     *
     * @param item The item to be sold.
     * @throws IllegalArgumentException If the item is null.
     */
    public void sellItem(Sellable item) throws IllegalArgumentException {
        if (item == null) {
            throw new IllegalArgumentException("Cannot sell a null item.");
        }
        item.sellItem();
        System.out.println("Item sold: " + item);
    }

    /**
     * Displays the list of all registered customers.
     */
    public void viewCustomers() {
        System.out.println("Customer List:");
        if (customers.isEmpty()) {
            System.out.println("No customers registered yet.");
            return;
        }

        for (Person customer : customers) {
            System.out.println(customer);
        }
    }

    /**
     * Displays the history of all orders placed.
     */
    public void viewOrders() {
        System.out.println("Order History:");
        if (orders.isEmpty()) {
            System.out.println("No orders placed yet.");
            return;
        }

        for (Order order : orders) {
            System.out.println(order);
        }
    }

    // Getters and Setters
    /**
     * Gets the employee ID of the manager.
     *
     * @return The employee ID.
     */
    public String getEmployeeId() {
        return employeeId;
    }

    /**
     * Sets the employee ID of the manager.
     *
     * @param employeeId The new employee ID.
     */
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * Gets the salary of the manager.
     *
     * @return The salary.
     */
    public double getSalary() {
        return salary;
    }

    /**
     * Sets the salary of the manager.
     *
     * @param salary The new salary.
     */
    public void setSalary(double salary) {
        this.salary = salary;
    }

    /**
     * Gets the list of registered customers.
     *
     * @return The list of customers.
     */
    public ArrayList<Person> getCustomers() {
        return customers;
    }

    /**
     * Gets the list of all orders placed.
     *
     * @return The list of orders.
     */
    public ArrayList<Order> getOrders() {
        return orders;
    }

    @Override
    public String toString() {
        return super.toString() + ", Employee ID: " + employeeId + ", Salary: $" + salary;
    }

    /**
     * Sets the password for the manager.
     *
     * @param password The new password.
     */
    public void setPassword(String password) {
        this.registerCustomer(this, password);
    }

    /**
     * Registers a new customer with a hashed password.
     *
     * @param person The customer to be registered.
     * @param password The password for the customer.
     */
    public void registerCustomer(Person person, String password) throws IllegalArgumentException {
        if (person == null || password == null || person.getName().isEmpty()) {
            throw new IllegalArgumentException("Customer and password must not be null, and customer name must not be empty.");
        }
        if (passwordMap.containsKey(person.getName())) {
            throw new IllegalArgumentException("Customer already exists in the password map.");
        }
        if (!validatePassword(password)) {
            throw new IllegalArgumentException("Password must be at least 8 characters long.");
        }
        // Hash the password using BCrypt
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        customers.add(person);
        passwordMap.put(person.getName().toLowerCase(), hashedPassword);
    }

    /**
     * Authenticates a customer using their username and password.
     *
     * @param username The username of the customer.
     * @param password The password of the customer.
     * @return True if authentication is successful, false otherwise.
     */
    public boolean authenticateCustomer(String username, String password) {
        username = username.toLowerCase();
        if (passwordMap.containsKey(username)) {
            String hashedPassword = passwordMap.get(username);
            return BCrypt.checkpw(password, hashedPassword);
        }
        return false;
    }


    private boolean validatePassword(String password) {
        // Check if the password meets the criteria (e.g., length, complexity)
        return password.length() >= 8; // Example: minimum length of 8 characters
    }

}
