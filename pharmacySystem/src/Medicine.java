
package pkgfinal.project;

import java.time.LocalDate;

public class Medicine extends ParmacyItem
{    
private String dosage;
private boolean requiresPrescription;
private LocalDate expiryDate ;
private int quantity;
private String activeIngrediant;
public Medicine()
{
    
}
public Medicine(String category,String activeIngrediant,String dosage,boolean requiresPrescription,LocalDate expiryDate,int quantity )
{
    super(category);
    this.dosage =dosage;
    this.requiresPrescription=requiresPrescription;
    this.expiryDate=expiryDate;
    this.quantity =quantity; 
    this.activeIngrediant=activeIngrediant;
}



public void displayInfo ()
{
    System.out.println("Active Ingediant:"+activeIngrediant+" Dosage:"+dosage);
    System.out.println("Category:"+super.getCategory());
    System.out.println("Quantity:"+quantity);
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

public void sellItem () throws IllegalStateException 
{
    if(isExpired()==true)
        throw new IllegalStateException("the medicine is expired");
    else if(isSoldOut()==true)
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
public boolean isSoldOut ()
{
    return (quantity==0);
}
}

