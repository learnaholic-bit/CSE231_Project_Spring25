import java.util.ArrayList;
import java.util.InputMismatchException;

public class Order {
    private int orderId;
    private Customer customer;
    private ArrayList<PharmacyItem> items;
    private double totalAmount;
    //constructor
    public Order(){
        this.orderId=0;
        this.customer=new Customer();
        this.items=new ArrayList<>();
        this.totalAmount=0;
    }



    public Order(Customer customer){
        this.orderId=0;
        this.customer=customer;
        this.items=new ArrayList<>();
        this.totalAmount=0;
    }
    public Order(int orderId,Customer customer,ArrayList <PharmacyItem> items,double totalAmount){
        this.orderId=orderId;
        this.customer=customer;
        this.items=items;
        this.totalAmount=totalAmount;
    }
    //GET
    public int getOrderId (){
        return this.orderId;
    }
    public Customer getCustomer(){
        return this.customer;
    }
    public ArrayList<PharmacyItem> getItems(){
        return this.items;
    }
    public double getTotalAmount(){
        return this.totalAmount;
    }
    //SET
    public void setOrderId(int orderId){
        if (orderId<0)
            throw new IllegalArgumentException("you can not add negative value to order ID");
        else
            this.orderId=orderId;
    }
    public void setCustomer(Customer customer){
        this.customer=customer;
    }
    public void setItems (ArrayList<PharmacyItem>items){
        this.items=items;
    }
    public void setTotalAmount(double totalAmount)throws IllegalArgumentException{
        if (totalAmount<0)
            throw new IllegalArgumentException("you can not add negative value to total Amount");
     /*   elseif (totalAmount instanceof double)
            throw new InputMismatchException("total amount should be double");*/
        else
        this.totalAmount=totalAmount;
    }
    //methods
    public void addItem (PharmacyItem item_add){
        this.items.add(item_add);
    }

    public void removeItem (PharmacyItem item_remove)throws IllegalArgumentException {
        if (this.items.contains(item_remove)) {
            this.items.remove(item_remove);
        }
        else {
            throw new IllegalArgumentException("The List does not contain this item");
        }
    }
    public double calculateTotal() throws IllegalArgumentException{
        double sum=0;
        for (PharmacyItem i : items){
            if(i.price<0)
                throw new IllegalArgumentException("Negative price error");
           sum=sum+i.price;
        }
        return sum;
    }
    public boolean processPayment (){
        return true;
    }
}
