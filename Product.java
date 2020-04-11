package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Product {
    private int productID;
    private String productName;
    private double productPrice;
    private int productInv;
    private int productMax;
    private int productMin;
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList(); // stores Parts associated with a Product

    // Product Constructor
    public Product(int productID, String productName, double productPrice, int productInv, int productMax, int productMin) {
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productInv = productInv;
        this.productMax = productMax;
        this.productMin = productMin;
    }

    // Product Getters
    public int getProductID() {
        return productID;
    }
    public String getProductName() {
        return productName;
    }
    public double getProductPrice() {
        return productPrice;
    }
    public int getProductInv() {
        return productInv;
    }
    public int getProductMax() {
        return productMax;
    }
    public int getProductMin() {
        return productMin;
    }

    // Product Setters
    public void setProductID(int productID) {
        this.productID = productID;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
    public void setProductInv(int productInv) {
        this.productInv = productInv;
    }
    public void setProductMax(int productMax) {
        this.productMax = productMax;
    }
    public void setProductMin(int productMin) {
        this.productMin = productMin;
    }

    // Methods for Parts associated with a Product
    public void addAssociatedPart(Part associatedPartToAdd) {
        associatedParts.add(associatedPartToAdd);
    }

    public void deleteAssociatedPart(Part associatedPartToDelete) {
        for (int i = 0; i < associatedParts.size(); i++) {
            if (associatedParts.get(i) == associatedPartToDelete) {
                associatedParts.remove(i);
            }
        }
    }

    public ObservableList<Part> getAssociatedParts() {
        return associatedParts;
    }
}