package org.project.pharmacy.logic;
public class Customer {
    private int customerID;
    private String name;
    private String contact;
    //constructor
    public Customer (){
        this.customerID=0;
        this.name=null;
        this.contact=null;
    }
    public Customer (int customerID,String name,String contact){
        this.customerID=customerID;
        this.name=name;
        this.contact=contact;
    }
    //GET
    public int getCustomerID (){
        return this.customerID;
    }
    public String getName (){
        return this.name;
    }
    public String getContact(){
        return this.contact;
    }
    //SET
    public void setCustomerID(int customerID)throws IllegalArgumentException{
        if (customerID<0)
            throw new IllegalArgumentException("you can not add negative value to customer ID");
        else
            this.customerID=customerID;
    }
    public void setName (String name){
        this.name=name;
    }
    public void setContact(String contact){
        this.contact=contact;
    }
    //methods
    public void displayInfo (){
        System.out.println("Customer ID : "+customerID);
        System.out.println("Name : "+name);
        System.out.println("Contact : "+contact);
    }
    public Order makeOrder (){
        Order Makeorder = new Order(this);
        return Makeorder;
    }

}
