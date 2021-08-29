import java.time.LocalDate;


/**
 * The card class is used to store card data separately in order to ensure that not a single detail is left off
 * when the users key in the information before they can proceed to billing. Every detail of a card such as the
 * card number, card type, expire date etc. are included in this class, and they must be keyed in when users
 * request to add a card record. The card class also store the address of the same customer within the same record,
 * but the address will not be required to be keyed in as long as the address is keyed in during sign up.
 *
 * @author Lee Khoon Hong
 */
public class Card {
    private String number;
    private String type;
    private LocalDate expireDate;
    private String nameOnCard;
    private String issuer;
    private Address billingAddress;
    private String CVV;

    // Constructor
    Card() {
        // no-args
    }

    /**
     *
     * @param nameOnCard Cardholder name
     * @param number Debit or credit card number
     * @param CVV 3-digits security code
     * @param expireDate Card expiry date in year & month
     * @param type Credit card or debit card
     * @param issuer Card issuer bank name
     * @param billingAddress Card billing address
     */
    Card(String nameOnCard, String number, String CVV, LocalDate expireDate, String type, String issuer, Address billingAddress) {
        this.number = number;
        this.type = type;
        this.expireDate = expireDate;
        this.nameOnCard = nameOnCard;
        this.issuer = issuer;
        this.billingAddress = billingAddress;
        this.CVV = CVV;
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

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDate expireDate) {
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

    public String getCVV() {
        return CVV;
    }

    public void setCVV(String CVV) {
        this.CVV = CVV;
    }

    // Methods

    /**
     * Overrides the {@code toString()} method in {@code Object}.
     *
     * @return Formatted {@code Card} attributes
     */
    @Override
    public String toString(){
        return String.format("""
                                		
                        \t\t\t\t HERE IS YOUR CARD INFORMATION !
                        +---------------------------------------------------------------+
                        |   %-41s         %6s    |
                        |---------------------------------------------------------------|
                        |                                                               |
                        |   Name On Card > %-15s                              |
                        |                                                               |
                        |   Card Number  > %-16s                             |
                        |   CVV Number   > %-3s                Expire Date > %-4s        |
                        |                                                               |
                        |   Billing Address >                                           |
                        |   %-60s|
                        |                                                               |
                        +---------------------------------------------------------------+
                        """,
                getIssuer(),getType(),
                getNameOnCard(),
                censorCardNo(),
                getCVV(),
                getExpireDate().getMonthValue()+"/"+Integer.toString(getExpireDate().getYear()).substring(2,4),
                getBillingAddress().displayRow());
    }

    /**
     * Overrides the {@code equals()} method in {@code Object}.
     *
     * @param o Object to be compared
     * @return True if equals, else return false
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Card card) {
            return card.equals(this);
        }
        return false;
    }

    /**
     * Convert card number into censored String of text for security purposes.
     * Only last 4 digits are visible.
     *
     * @return Censored card number
     */
    public String censorCardNo() {
        return "************" + this.number.substring(12, 16);
    }
}
