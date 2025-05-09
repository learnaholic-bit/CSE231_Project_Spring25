import java.util.Date;

public class ReferenceItem extends PharmacyItem{
    //Data Fields
    private static final String CATEGORY = "Reference Item";
    private String publisher;
    private Date publicationDate;
    private boolean forReferenceOnly;



    //Constructors
    public ReferenceItem() {
        super.category = CATEGORY;
        this.subCategory = null;
        this.publisher = null;
        this.publicationDate = null;
        this.forReferenceOnly = false;
    }
    public ReferenceItem (int itemId,String name,double price,String category, String subCategory, String description,boolean isAvailable,int quantity,
                          String publisher, Date publicationDate, boolean forReferenceOnly) {

        super(itemId, name, price, CATEGORY, subCategory, description, isAvailable, quantity); //int itemId,String name,double price,String category, String subCategory, String description,boolean isAvailable,int quantity
        this.publisher = publisher;
        this.publicationDate = publicationDate;
        this.forReferenceOnly = forReferenceOnly;
    }

    //Methods
        //Getters
    @Override
    public String getCategory() {
        return CATEGORY;
    }
    public String getsubCategory() {
        return subCategory;
    }
    public String getPublisher() {
        return publisher;
    }
    public Date getPublicationDate() {
        return publicationDate;
    }


    //Setters
    public void setsubCategory(String subCategory) throws IllegalArgumentException {
        if (publisher == null) throw new IllegalArgumentException("Please Enter a Valid String");
        else this.subCategory = subCategory;
    }
    public void setPublisher (String publisher) throws IllegalArgumentException {
        if (publisher == null) throw new IllegalArgumentException("Please Enter a Valid String");
        else this.publisher = publisher;
    }
    public void setPublicationDate (Date date) throws IllegalArgumentException {   //I'm not sure of this exception type
        if (date == null) throw new IllegalArgumentException("Please Enter a Valid Date");
        else this.publicationDate = date;
    }
    public void setForReferenceOnly (boolean forReferenceOnly) {
        this.forReferenceOnly = forReferenceOnly;
    }

    //Other methods
    @Override
    public void displayInfo() {
        super.displayInfo();
        //System.out.println("Category:\t" + CATEGORY);
        System.out.println("Publisher:\t" + publisher);
        System.out.println("Publication Date:\t" + (publicationDate == null ? null : publicationDate.toString()));
        System.out.println("For Reference Only:\t" + (forReferenceOnly ? "Yes" : "No"));
    }
    boolean isLoanable() {
        return !forReferenceOnly;
    }
}
