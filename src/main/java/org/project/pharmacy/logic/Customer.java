package org.project.pharmacy.logic;
public class Customer extends Person {
    private int customerID;

    //constructor
    public Customer (){
        super();
        this.customerID=0;
    }
    public Customer (int customerID,String name,String contact){
        super();
        this.customerID=customerID;
        super.setName(name);
        super.setContactNumber(contact);
    }
    //GET
    public int getCustomerID (){
        return this.customerID;
    }

    //SET
    public void setCustomerID(int customerID)throws IllegalArgumentException{
        if (customerID<0)
            throw new IllegalArgumentException("you can not add negative value to customer ID");
        else
            this.customerID=customerID;
    }
    //methods
    public void displayInfo (){
        System.out.println("Customer ID : "+customerID);
        System.out.println("Name : "+super.getName());
        System.out.println("Contact : "+super.getContactNumber());
    }
    public Order makeOrder (){
        Order Makeorder = new Order(this);
        return Makeorder;
    }

}
