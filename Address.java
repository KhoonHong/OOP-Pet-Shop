public class Address implements Displayable{
    private String city;
    private String zipcode;
    private String state;
    private String country;
    private String street;
    private String region;

    // Constructor
    Address(String street, String city, String zipcode, String region, String state, String country) {
        this.city = city;
        this.zipcode = zipcode;
        this.state = state;
        this.region = region;
        this.country = country;
        this.street = street;
    }

    Address() {
        // no-args
    }

    // Getter & Setter
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
    // Methods

    // display row in table form
    @Override
    public String displayRow() {
        return String.format("%s, %s %s, %s, %s", getStreet(), getZipcode(), getCity(), getState(), getCountry());
    }

    @Override
    public String toString() {
        return String.format("""
                 Street   > %s
                 Zip Code > %s
                 City     > %s
                 Region   > %s
                 State    > %s
                 Country  > %s""", getStreet(), getZipcode(), getCity(), getRegion(), getState(), getCountry());
    }
}
