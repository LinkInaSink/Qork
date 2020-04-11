package sample;
public class InHouse extends Part {
    // InHouse Members
    private int machineID;

    // InHouse Constructor
    public InHouse(int partID, String partName, double partPrice, int partInv, int partMax, int partMin, int machineID) {
        this.setPartID(partID);
        this.setPartName(partName);
        this.setPartPrice(partPrice);
        this.setPartInv(partInv);
        this.setPartMax(partMax);
        this.setPartMin(partMin);
        this.setMachineID(machineID);
    }

    // InHouse Getters
    public int getMachineID() {
        return machineID;
    }

    // InHouse Setters
    public void setMachineID(int machineID) {
        this.machineID = machineID;
    }
}