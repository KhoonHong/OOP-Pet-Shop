import java.time.LocalDateTime;

public class Card {
    private String number;
    private String type;
    private LocalDateTime expireDate;
    private String nameOnCard;
    private String issuer;
    private Address billingAddress;
    private String CVV;

    // Constructor
    Card() {
        // no-args
    }

    Card(String nameOnCard, String number, String CVV, LocalDateTime expireDate, String type, String issuer, Address billingAddress) {
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

    public String getCVV() {
        return CVV;
    }

    public void setCVV(String CVV) {
        this.CVV = CVV;
    }

    // Methods

    @Override
    public String toString(){
        return String.format("""
      
						Name on Card > %s
						Card Number > %s
						CVV Number > %s
						Expire Date > %s
						Type of Card > %s
						Issuer > %s
						
						Billing Address >
						%s""",

                getNameOnCard(),
                censorCardNo(),
                getCVV(),
                getExpireDate().getMonthValue()+"/"+Integer.toString(getExpireDate().getYear()).substring(2,4),
                getType(),
                getIssuer(),
                getBillingAddress().toString());
    }


    @Override
    public boolean equals(Object o) {
        if (o instanceof Card card) {
            return card.equals(this);
        }
        return false;
    }

    public String censorCardNo() {
        return "************" + this.number.substring(12, 16);
    }
}
