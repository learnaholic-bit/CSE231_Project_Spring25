import java.util.Date;

public class ReferenceItem {
    private String publisher;
    private Date publicationdate;
    private boolean forreferenceonly;

    public ReferenceItem (String publisher, Date publicationdate, boolean forreferenceonly) {
        this.publisher = publisher;
        this.publicationdate = publicationdate;
        this.forreferenceonly = forreferenceonly;
    }
    public String getCategory() {
        return "Reference Item";
    }
    void displayInfo() {
        System.out.println("Category:\tReference Item");
        System.out.println("Publisher:\t" + publisher);
        System.out.println("Publication Date:\t" + publicationdate.toString());
        System.out.println("For Reference Only:\t" + (forreferenceonly ? "Yes" : "No"));
    }
    boolean isLoanable() {
        return !forreferenceonly;
    }
}
