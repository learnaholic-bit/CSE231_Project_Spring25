import java.time.LocalDate;

public class Medicine extends PharmacyItem implements Sellable
{
    private String dosage;
    private boolean requiresPrescription;
    private LocalDate expiryDate ;
    private String activeIngredient;
    private static final String CATEGORY = "Medicine";
    public Medicine()
    {

    }
    public Medicine(int itemId,String name,double price,String category, String subCategory, String description,boolean isAvailable,int quantity,
                    String activeIngredient,String dosage,boolean requiresPrescription,LocalDate expiryDate)
    {
        super(itemId, name, price, CATEGORY, subCategory, description, isAvailable, quantity); //int itemId,String name,double price,String category, String subCategory, String description,boolean isAvailable,int quantity
        this.dosage =dosage;
        this.requiresPrescription=requiresPrescription;
        this.expiryDate=expiryDate;
        this.activeIngredient=activeIngredient;
    }

public void displayInfo ()
{
    super.displayInfo();
    System.out.println("Active Ingredient:"+activeIngredient+" Dosage:"+dosage);
    System.out.print("Requires Prescription:");
    if(requiresPrescription)
        System.out.println("Yes");
    else
        System.out.println("No");
    System.out.println("Expiry Date:"+expiryDate.getMonthValue()+"/"+expiryDate.getYear());
    if(isExpired())
        System.out.println("this medicine is expired");
    else
        System.out.println("this medicine is not expired");
}

    @Override
    public boolean isAvailable(){
        return super.isAvailable;
    }
    public LocalDate getExpiryDate(){return expiryDate;}
    @Override
    public String getSubCategory(){return subCategory;}
    public String getDosage(){return dosage;}
    public boolean getRequiresPrescription(){return requiresPrescription;}
    public String getActiveIngredient(){return activeIngredient;}

public void sellItem () throws IllegalStateException 
{
    if(isExpired())
        throw new IllegalStateException("the medicine is expired");
    else if(isAvailable())
        throw new IllegalStateException("the medicine is sold out");
    else 
    quantity -=1;
}
public boolean isExpired()
{
    LocalDate currentDate = LocalDate.now();
    
    if(currentDate.isAfter(expiryDate))
        return true;
    else
        return false;        
}

public void addQuantity(int quantity) throws IllegalArgumentException
{
    if(quantity<0)
        throw new IllegalArgumentException("the quantity can't be negative");
    else
        this.quantity += quantity;
}
    public void setSubCategory(String subCategory) throws IllegalArgumentException {
        if (subCategory == null) throw new IllegalArgumentException("Please Enter a Valid String");
        else this.subCategory = subCategory;
    }
}

