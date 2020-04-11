package sample;

// Part abstract because we cannot just create a "Part", it must be specifically an InHouse or Outsourced Part
// InHouse and Outsourced are "concrete" classes, unlike Part which is "abstract"
public abstract class Part {
    protected int partID;
    protected String partName;
    protected double partPrice;
    protected int partInv;
    protected int partMax;
    protected int partMin;

    // Part doesn't have a constructor (cannot just create a "Part"); respective Part constructors are within InHouse or Outsourced

    // Part Getters
    public int getPartID() {
        return partID;
    }
    public String getPartName() {
        return partName;
    }
    public double getPartPrice() {
        return partPrice;
    }
    public int getPartInv() {
        return partInv;
    }
    public int getPartMax() {
        return partMax;
    }
    public int getPartMin() {
        return partMin;
    }

    // Part Setters
    public void setPartID(int partID) {
        this.partID = partID;
    }
    public void setPartName(String partName) {
        this.partName = partName;
    }
    public void setPartPrice(double partPrice) {
        this.partPrice = partPrice;
    }
    public void setPartInv(int partInv) {
        this.partInv = partInv;
    }
    public void setPartMax(int partMax) {
        this.partMax = partMax;
    }
    public void setPartMin(int partMin) {
        this.partMin = partMin;
    }
}