import java.time.LocalDate;

public class Medicine extends PharmacyItem implements Sellable
{    
private String dosage;
private boolean requiresPrescription;
private LocalDate expiryDate ;
private String activeIngrediant;
String category;
private static final String CATEGORY = "Medicine";
public Medicine()
{
    
}
public Medicine(int itemId,String name,double price,String category,String description,boolean isAvailable,int quantity,String activeIngrediant,String dosage,boolean requiresPrescription,LocalDate expiryDate )
{
    super(itemId,name,price,category,description,isAvailable,quantity);
    this.dosage =dosage;
    this.requiresPrescription=requiresPrescription;
    this.expiryDate=expiryDate; 
    this.activeIngrediant=activeIngrediant;
}



public void displayInfo ()
{
    super.displayInfo();
    System.out.println("Active Ingediant:"+activeIngrediant+" Dosage:"+dosage);
    System.out.print("Requires Prescription:");
    if(requiresPrescription)
        System.out.println("Yes");
    else
        System.out.println("No");
    System.out.println("Expiry Date:"+expiryDate.getMonthValue()+"/"+expiryDate.getYear());
    if(isExpired())
        System.out.println("this medicien is expired");
    else
        System.out.println("this medicien is not expired");
}

    @Override
    public boolean isAvailable(){
        return super.isAvailable;
    }
    @Override
    public String getCategory(){
        return CATEGORY;
    }

public void sellItem () throws IllegalStateException 
{
    if(isExpired()==true)
        throw new IllegalStateException("the medicine is expired");
    else if(isAvailable()==true)
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
        throw new IllegalArgumentException("the quantity cann't be negative");
    else
        this.quantity += quantity;
}

}

