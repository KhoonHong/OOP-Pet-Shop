import java.time.LocalDateTime;

public class Card {
    private String number;
    private String type;
    private LocalDateTime expireDate;
    private String nameOnCard;
    private String issuer;
    private Address billingAddress;

    // Constructor
    Card() {
        // no-args
    }

    Card(String number, String type, LocalDateTime expireDate, String nameOnCard, String issuer, Address billingAddress) {
        this.number = number;
        this.type = type;
        this.expireDate = expireDate;
        this.nameOnCard = nameOnCard;
        this.issuer = issuer;
        this.billingAddress = billingAddress;
    }

    // Getter & Setter
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDateTime expireDate) {
        this.expireDate = expireDate;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    // Methods
    public void displayCardMenu() {

    }



}
