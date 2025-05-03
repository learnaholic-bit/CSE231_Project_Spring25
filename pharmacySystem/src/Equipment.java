public class Equipment extends PharmacyItem implements Sellable{
    private static final String CATEGORY = "Equipment";
    private String subCategory;
    private String type;
    private int warrantyPeriod;

    public Equipment(){
        this.subCategory = "";
        this.type = "";
        this.warrantyPeriod = 0;
    }

    public Equipment(int itemId,String name,double price,String category,String description
            ,boolean isAvailable,String subCategory,String type, int warrantyPeriod,int quantity){
        super(itemId,name,price,CATEGORY,description,isAvailable,quantity);
        this.subCategory = subCategory;
        this.type = type;
        this.warrantyPeriod = warrantyPeriod;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getType(){
        return type;
    }

    public void setWarrantyPeriod(int warrantyPeriod){
        if(warrantyPeriod<=0)
            throw new IllegalArgumentException("Equipment's warranty period can't be negative");
        else
            this.warrantyPeriod = warrantyPeriod;
    }

    public int getWarrantyPeriod(){
        return warrantyPeriod;
    }

    @Override
    public String getCategory(){
        return CATEGORY;
    }

    @Override
    public void displayInfo(){
        super.displayInfo();
        //        System.out.println("Category:\tEquipment");  ??
        System.out.println("Type:\t" + type);
        System.out.println("Warranty Period:\t" + warrantyPeriod);
    }

    public void sellItem(){
        if(isSoldOut() == true)
            throw new IllegalStateException("This equipment is sold out");
        else
            quantity-=1;
    }

//    @Override
//    public double getPrice(){
//        return super.getPrice();
//    }

    public boolean isAvailable(){
        return super.isAvailable;
    }
}

