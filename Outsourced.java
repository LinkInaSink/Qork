package sample;
public class Outsourced extends Part {
    // Outsourced Members
    private String compName;

    // Outsourced Constructor
    public Outsourced(int partID, String partName, double partPrice, int partInv, int partMax, int partMin, String compName) {
        this.setPartID(partID);
        this.setPartName(partName);
        this.setPartPrice(partPrice);
        this.setPartInv(partInv);
        this.setPartMax(partMax);
        this.setPartMin(partMin);
        this.setCompName(compName);
    }

    // Outsourced Getters
    public String getCompName() {
        return compName;
    }

    // Outsourced Setters
    public void setCompName(String compName) {
        this.compName = compName;
    }
}