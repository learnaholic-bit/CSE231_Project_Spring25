import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

//todo: re-enable pharmacy items as well as gui

public class PharmacyManager extends Person {
//    private PharmacyItem[] pharmacyItems;
    private Customer[] customers;
    private Order[] orders;
    private String employeeId;
    private double salary;

    // Constructor
    public PharmacyManager(String name, int age, String contactNumber, String address,
                           String employeeId, double salary) {
        super(name, age, contactNumber, address);
        this.employeeId = employeeId;
        this.salary = salary;
//        this.pharmacyItems = new PharmacyItem[0];
        this.customers = new Customer[0];
        this.orders = new Order[0];
    }

    // Default constructor
    public PharmacyManager() {
        super();
        this.employeeId = "";
        this.salary = 0.0;
//        this.pharmacyItems = new PharmacyItem[0];
        this.customers = new Customer[0];
        this.orders = new Order[0];
    }

    // Required methods from UML
//    public void addItem(PharmacyItem item) {
//        PharmacyItem[] newItems = Arrays.copyOf(pharmacyItems, pharmacyItems.length + 1);
//        newItems[pharmacyItems.length] = item;
//        pharmacyItems = newItems;
//        System.out.println("Item added: " + item.getName());
//    }

//    public void removeItem(PharmacyItem item) {
//        List<PharmacyItem> itemList = new ArrayList<>(Arrays.asList(pharmacyItems));
//        boolean removed = itemList.remove(item);
//
//        if (removed) {
//            pharmacyItems = itemList.toArray(new PharmacyItem[0]);
//            System.out.println("Item removed: " + item.getName());
//        } else {
//            System.out.println("Item not found in inventory.");
//        }
//    }

//    public PharmacyItem findItemById(int itemId) {
//        for (PharmacyItem item : pharmacyItems) {
//            if (item.getId() == itemId) {
//                return item;
//            }
//        }
//        System.out.println("Item with ID " + itemId + " not found.");
//        return null;
//    }

//    public void displayAllItems() {
//        System.out.println("Current Pharmacy Inventory:");
//        if (pharmacyItems.length == 0) {
//            System.out.println("The inventory is empty.");
//            return;
//        }
//
//        for (PharmacyItem item : pharmacyItems) {
//            System.out.println(item);
//        }
//    }

//    public void sortItems() {
//        Arrays.sort(pharmacyItems, Comparator.comparing(PharmacyItem::getName));
//        System.out.println("Items sorted by name.");
//    }

    public Order createOrder(Customer customer) {
        Order newOrder = new Order(customer);

        // Add the order to the orders array
        Order[] newOrders = Arrays.copyOf(orders, orders.length + 1);
        newOrders[orders.length] = newOrder;
        orders = newOrders;

        // Add customer if not already in the system
        if (!isCustomerRegistered(customer)) {
            addCustomer(customer);
        }

        System.out.println("Order created for customer: " + customer.getName());
        return newOrder;
    }

    public void sellItem(Sellable item) {
        item.sellItem();
        System.out.println("Item sold: " + item);
    }

    // Additional useful methods
    private boolean isCustomerRegistered(Customer customer) {
        for (Customer c : customers) {
            if (c.equals(customer)) {
                return true;
            }
        }
        return false;
    }

    private void addCustomer(Customer customer) {
        Customer[] newCustomers = Arrays.copyOf(customers, customers.length + 1);
        newCustomers[customers.length] = customer;
        customers = newCustomers;
        System.out.println("New customer added: " + customer.getName());
    }

    public void viewCustomers() {
        System.out.println("Customer List:");
        if (customers.length == 0) {
            System.out.println("No customers registered yet.");
            return;
        }

        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    public void viewOrders() {
        System.out.println("Order History:");
        if (orders.length == 0) {
            System.out.println("No orders placed yet.");
            return;
        }

        for (Order order : orders) {
            System.out.println(order);
        }
    }

    // Getters and Setters
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

//    public PharmacyItem[] getPharmacyItems() {
//        return pharmacyItems;
//    }

    public Customer[] getCustomers() {
        return customers;
    }

    public Order[] getOrders() {
        return orders;
    }

    @Override
    public String toString() {
        return super.toString() + ", Employee ID: " + employeeId + ", Salary: $" + salary;
    }
}
