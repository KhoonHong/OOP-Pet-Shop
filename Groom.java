import java.util.HashMap;

public class Groom extends Service{
    private boolean hairCut;
    private boolean fancyCut;
    private boolean sanitaryTrim;
    private boolean nailClip;
    private boolean nailGrind;
    private boolean scissoringFeet;
    private boolean padShaving;
    private boolean coatStyling;

    // Constructor
    Groom() {
        // no-args
    }

    Groom(boolean hairCut,
          boolean fancyCut,
          boolean sanitaryTrim,
          boolean nailClip,
          boolean nailGrind,
          boolean scissoringFeet,
          boolean padShaving,
          boolean coatStyling,
          String desc) {
        super(desc);
        this.hairCut = hairCut;
        this.fancyCut = fancyCut;
        this.sanitaryTrim = sanitaryTrim;
        this.nailClip = nailClip;
        this.nailGrind = nailGrind;
        this.scissoringFeet = scissoringFeet;
        this.padShaving = padShaving;
        this.coatStyling = coatStyling;
    }

    // Getter & Setter
    public boolean isHairCut() {
        return hairCut;
    }

    public void setHairCut(boolean hairCut) {
        this.hairCut = hairCut;
    }

    public boolean isFancyCut() {
        return fancyCut;
    }

    public void setFancyCut(boolean fancyCut) {
        this.fancyCut = fancyCut;
    }

    public boolean isSanitaryTrim() {
        return sanitaryTrim;
    }

    public void setSanitaryTrim(boolean sanitaryTrim) {
        this.sanitaryTrim = sanitaryTrim;
    }

    public boolean isNailClip() {
        return nailClip;
    }

    public void setNailClip(boolean nailClip) {
        this.nailClip = nailClip;
    }

    public boolean isNailGrind() {
        return nailGrind;
    }

    public void setNailGrind(boolean nailGrind) {
        this.nailGrind = nailGrind;
    }

    public boolean isScissoringFeet() {
        return scissoringFeet;
    }

    public void setScissoringFeet(boolean scissoringFeet) {
        this.scissoringFeet = scissoringFeet;
    }

    public boolean isPadShaving() {
        return padShaving;
    }

    public void setPadShaving(boolean padShaving) {
        this.padShaving = padShaving;
    }

    public boolean isCoatStyling() {
        return coatStyling;
    }

    public void setCoatStyling(boolean coatStyling) {
        this.coatStyling = coatStyling;
    }

    public double getPrice() {
        return Groom.price;
    }

    public void setPrice(double price) {
        Groom.price = price;
    }

    public HashMap<String, Double> getAddOnPrice() {
        return Groom.addOnPrice;
    }

    public void setAddOnPrice(HashMap<String, Double> addOnPrice) {
        Groom.addOnPrice = addOnPrice;
    }
    // Methods

}
