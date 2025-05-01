import java.util.Date;

public class ReferenceItem extends PhamacyItem{
    //Data Fields
    private String publisher;
    private Date publicationDate;
    private boolean forReferenceOnly;
    private static final String CATEGORY = "Reference Item";


    //Constructors
    public ReferenceItem() {
        super.category = CATEGORY;
        this.publisher = null;
        this.publicationDate = null;
        this.forReferenceOnly = false;
    }
    public ReferenceItem (int itemID, String name, double price, boolean isAvailable, String description, String publisher, Date publicationDate, boolean forReferenceOnly) {
        super(itemID, name, price, isAvailable, CATEGORY, description);
        this.publisher = publisher;
        this.publicationDate = publicationDate;
        this.forReferenceOnly = forReferenceOnly;
    }

    //Methods
        //Getters
    public String getPublisher() {
        return publisher;
    }
    public Date getPublicationDate() {
        return publicationDate;
    }
    @Override
    public String getCategory() {
        return CATEGORY;
    }

        //Setters
    void setPublisher (String publisher) throws IllegalArgumentException {
        if (publisher == null) throw new IllegalArgumentException("Please Enter a Valid String");
        else this.publisher = publisher;
    }
    void setPublicationDate (Date date) throws IllegalArgumentException {   //I'm not sure of this exception type
        if (date == null) throw new IllegalArgumentException("Please Enter a Valid Date");
        else this.publicationDate = date;
    }
    void setForReferenceOnly (boolean forReferenceOnly) {
        this.forReferenceOnly = forReferenceOnly;
    }
        //Other methods
    @Override
    void displayInfo() {
        super.displayInfo();
        //System.out.println("Category:\t" + CATEGORY);
        System.out.println("Publisher:\t" + publisher);
        System.out.println("Publication Date:\t" + publicationDate.toString());
        System.out.println("For Reference Only:\t" + (forReferenceOnly ? "Yes" : "No"));
    }
    boolean isLoanable() {
        return !forReferenceOnly;
    }
}
