package org.project.pharmacy.logic;
public abstract class PharmacyItem implements Comparable<PharmacyItem>{
    protected int itemId;
    protected String name;
    protected double price;
    protected String category;
    protected String subCategory;
    protected String description;
    protected boolean isAvailable;
    protected int quantity;


    public PharmacyItem(){
        this.itemId = 0;
        this.name = "";
        this.price = 0;
        this.category = "";
        this.subCategory = "";
        this.description = "";
        this.isAvailable = false;
        this.quantity = 0;
    }

    public PharmacyItem(int itemId,String name,double price,String category, String subCategory, String description,boolean isAvailable,int quantity){
        this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.category = category;
        this.subCategory = subCategory;
        this.description = description;
        this.isAvailable = isAvailable;
        this.quantity = quantity;
    }

    public String getCategory() {
        return this.category;
    }

    public abstract String getSubCategory();


    public void setItemId(int itemId){
        if(itemId <= 0)
            throw new IllegalArgumentException("The id of any item can't be negative");
        else
            this.itemId = itemId;
    }

    public int getItemId(){
        return itemId;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setPrice(double price){
        if(price <= 0)
            throw new IllegalArgumentException("The price of any item can't be negative");
        else
            this.price = price;
    }

    public double getPrice(){
        return price;
    }

    public void setCategory(String category){
        this.category = category;
    }
    public abstract void setSubCategory(String SubCategory);

    public void setDescription(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

    public void setQuantity(int quantity)throws IllegalArgumentException{
        if(quantity < 0)
            throw new IllegalArgumentException("Negative quantities is not valid");
        else if(quantity < this.quantity)
            throw new IllegalArgumentException("Negative quantities is not valid");
        else
            this.quantity = quantity;
    }

    public int getQuantity(){
        return quantity;
    }

    public void setAvailable(boolean isAvailable){
        this.isAvailable = isAvailable;
    }


    public boolean getAvailable(){
        return isAvailable;
    }

    public boolean isSoldOut(){    //added
        if(!isAvailable)
            System.out.println(" Not Available ");
        return !isAvailable;
    }


    public void displayInfo(){
        System.out.println("Item's Id:\t" + itemId);
        System.out.println("Name:\t" + name);
        System.out.println("Price:\t" + price);
        System.out.println("Category:\t" + category);
        System.out.println("Description:\t" + description);
        System.out.println("Availability of the item:\t" + isAvailable);
    }

    @Override
    public int compareTo(PharmacyItem other) {
        if (this.name == null && other == null) {
            return 0;
        }
        else if (this.name == null && other != null) {
            return -1; // Treat null as less than non-null
        }
        else if (this.name != null && other != null) {
            return this.name.compareTo(other.getName());
        }
        else {
            throw new NullPointerException("Cannot compare PharmacyItem to null");
        }
    }
//    public int compareTo(String other) {
//        if (this.name == null && other == null) {
//            return 0;
//        }
//        else if (this.name == null && other != null) {
//            return -1; // Treat null as less than non-null
//        }
//        else if (this.name != null && other != null) {
//            return this.name.compareTo(other);
//        }
//        else {
//            throw new NullPointerException("Cannot compare PharmacyItem to null");
//        }
//    }


}
