package org.project.pharmacy.logic;
public class Person {
    private String name;
    private int age;
    private String contactNumber;
    private String address;

    // Constructor
    public Person(String name, int age, String contactNumber, String address) {
        this.name = name;
        this.age = age;
        this.contactNumber = contactNumber;
        this.address = address;
    }

    // Default constructor
    public Person() {
        this.name = "";
        this.age = 0;
        this.contactNumber = "";
        this.address = "";
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Age: " + age + ", Contact: " + contactNumber + ", Address: " + address;
    }
}