import java.util.Objects;

/**
 * The address class is used to ensure that not a single detail is left off when the user key in the information
 * regarding the address when requested. Every single detail of an address such as city, state, country etc.
 * are all included in this class. Every single address that key in will be stored as an object in this class, and
 * methods are provided to display the address. Every single component of the particular address will be shown out
 * in a single row accordingly to the proper address format. 
 *
 * @author Lee Khoon Hong
 */
public class Address implements Displayable{
    private String city;
    private String zipcode;
    private String state;
    private String country;
    private String street;
    private String region;

    // Constructor

    /**
     * Creates a {@code Address} class object when called
     *
     * @param street Street name
     * @param city City name
     * @param zipcode zipcode
     * @param region Region in a state
     * @param state State name
     * @param country Country name
     */
    public Address(String street, String zipcode, String city, String region, String state, String country) {
        this.zipcode = zipcode;
        this.city = city;
        this.state = state;
        this.region = region;
        this.country = country;
        this.street = street;
    }

    public Address() {
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

    /**
     * Overrides the {@code displayRow()} method in {@code Displayable} interface.
     *
     * @return formatted address attribute in row
     */
    @Override
    public String displayRow() {
        return String.format("%s, %s %s, %s, %s", getStreet(), getZipcode(), getCity(), getState(), getCountry());
    }

    /**
     * Overrides the {@code toString()} method in {@code Object}.
     *
     * @return Formatted {@code Address} attributes
     */
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

    /**
     * Overrides the {@code equals()} method in {@code Object}.
     *
     * @param o Object to be compared
     * @return True if equals, else return false
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Address address) {
            return Objects.equals(address.getStreet(), this.getStreet()) &&
                    Objects.equals(address.getZipcode(), this.getZipcode()) &&
                    Objects.equals(address.getCity(), this.getCity()) &&
                    Objects.equals(address.getRegion(), this.getRegion()) &&
                    Objects.equals(address.getState(), this.getState()) &&
                    Objects.equals(address.getCountry(), this.getCountry());
        }
        return false;
    }

    /**
     * Restricted the length of the address to ensure proper display
     *
     * @return address with length restricted with ...
     */
    public String limitAddress() {
        if (displayRow().length() > 59) {
            return displayRow().substring(0, 56) + "...";
        }
        return displayRow();
    }
}
