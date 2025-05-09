import java.time.LocalDate;

public class HealthProduct extends PharmacyItem implements Sellable
{
    private boolean isOrganic;
    private LocalDate expiryDate ;
    private static final String CATEGORY = "Health Product";
    public HealthProduct()
    {
    }

    public HealthProduct(int itemId,String name,double price,String category, String subCategory, String description,boolean isAvailable,int quantity,
                         boolean isOrganic,LocalDate expiryDate)
    {
        super(itemId, name, price, CATEGORY, subCategory, description, isAvailable, quantity); //int itemId,String name,double price,String category, String subCategory, String description,boolean isAvailable,int quantity
        this.isOrganic = isOrganic;
        this.expiryDate = expiryDate;
    }

public void displayInfo ()
{
    super.displayInfo();
    System.out.print("Organic:");
    if(isOrganic)
        System.out.println("Yes");
    else
        System.out.println("No");
    System.out.println("Expiry Date:"+expiryDate.getMonthValue()+"/"+expiryDate.getYear());
    System.out.println("this Health product is");
    if(isExpired())
        System.out.println("expired");
    else
        System.out.println("not expired");
}
    @Override
    public boolean isAvailable(){
        return super.isAvailable;
    }
    @Override
    public String getCategory(){return CATEGORY;}
    public LocalDate getExpiryDate(){return expiryDate;}
    public String getSubCategory(){return subCategory;}
    public boolean getIsOrganic(){return isOrganic;}



public void sellItem () throws IllegalStateException 
{
    if(isExpired())
        throw new IllegalStateException("the Health product is expired");
    else if(isSoldOut())
        throw new IllegalStateException("the Health product is sold out");
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
        public boolean isSoldOut ()
{
    return (quantity==0);
}
}

