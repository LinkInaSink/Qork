package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    // Inventory Members; stores ALL Parts and Products
    public static ObservableList<Part> allParts = FXCollections.observableArrayList();
    public static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    // ADD
    public static void addPart(Part partToAdd) {
        allParts.add(partToAdd);
    }

    public static void addProduct(Product productToAdd) {
        allProducts.add(productToAdd);
    }

    // DELETE
    public static void deletePart(Part partToDelete) {
        for (int i = 0; i < allParts.size(); i++) {
            if (allParts.get(i) == partToDelete) {
                allParts.remove(i);
            }
        }
    }

    public static void deleteProduct(Product productToDelete) {
        for (int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i) == productToDelete) {
                allProducts.remove(i);
            }
        }
    }

    // UPDATE
    public static void updatePart(Part partToUpdate) {
        for (int i = 0; i < allParts.size(); i++) {
            if (allParts.get(i) == partToUpdate) {
                allParts.set(i, partToUpdate);
            }
        }
    }

    public static void updateProduct(Product productToUpdate) {
        for (int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i) == productToUpdate) {
                allProducts.set(i, productToUpdate);
            }
        }
    }

    // SEARCH
    public Part searchPart(Part partToSearch) {
        if (!allParts.isEmpty()) { // only loop if Parts inventory is not empty
            for (int i = 0; i < allParts.size(); i++) {
                if (allParts.get(i) == partToSearch) {
                    return allParts.get(i);
                }
            }
        }
        return null;
    }

    public Product searchProduct(Product productToSearch) {
        if (!allProducts.isEmpty()) { // only loop if Products inventory is not empty
            for (int i = 0; i < allProducts.size(); i++) {
                if (allProducts.get(i) == productToSearch) {
                    return allProducts.get(i);
                }
            }
        }
        return null;
    }

    // GET ALL (returns ENTIRE list)
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}