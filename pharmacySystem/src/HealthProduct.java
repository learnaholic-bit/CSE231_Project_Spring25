package pkgfinal.project;


import java.time.LocalDate;



public class HealthProduct extends ParmacyItem
{
    private boolean isOrganic;
    private LocalDate expiryDate ;
    private int quantity;   
    
    public HealthProduct()
    {
    }
    
    public HealthProduct(String category,boolean isOrganic,LocalDate expiryDate,int quantity)
    {
        super(category);
        this.isOrganic = isOrganic;
        this.expiryDate = expiryDate;
        this.quantity = quantity;
    }

public void displayInfo ()
{
    System.out.println("Category"+super.getCategory());
    System.out.println("Quantity:"+quantity);
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

public void sellItem () throws IllegalStateException 
{
    if(isExpired()==true)
        throw new IllegalStateException("the Health product is expired");
    else if(isSoldOut()==true)
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
        throw new IllegalArgumentException("the quantity cann't be negative");
    else
    this.quantity += quantity;
}
    public boolean isSoldOut ()
{
    return (quantity==0);
}
}

