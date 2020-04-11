package sample;

import sample.Inventory;
import sample.Part;
import sample.Product;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {
    Inventory inventory;
    public Button addPartBtn;
    public Button deletePartBtn;
    public Button updatePartBtn;
    public Button searchPartBtn;
    public Button addProductBtn;
    public Button deleteProductBtn;
    public Button updateProductBtn;
    public Button searchProductBtn;
    public TextField partSearchTextField;
    public TextField productSearchTextField;
    public TableView<Part> partsTable;
    public TableView<Product> productsTable;
    public TableColumn<Part, Integer> partsTableIDCol;
    public TableColumn<Part, String> partsTableNameCol;
    public TableColumn<Part, Double> partsTablePriceCol;
    public TableColumn<Part, Integer> partsTableInvCol;
    public TableColumn<Product, Integer> productsTableIDCol;
    public TableColumn<Product, String> productsTableNameCol;
    public TableColumn<Product, Double> productsTablePriceCol;
    public TableColumn<Product, Integer> productsTableInvCol;

    // MainScreen constructor accepts Inventory object
    public MainScreenController(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override public void initialize(URL url, ResourceBundle rb) {
        // Links part columns to respective values
        partsTableIDCol.setCellValueFactory(new PropertyValueFactory<Part, Integer>("partID"));
        partsTableNameCol.setCellValueFactory(new PropertyValueFactory<Part, String>("partName"));
        partsTablePriceCol.setCellValueFactory(new PropertyValueFactory<Part, Double>("partPrice"));
        partsTableInvCol.setCellValueFactory(new PropertyValueFactory<Part, Integer>("partInv"));
        // Links product columns to respective values
        productsTableIDCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("productID"));
        productsTableNameCol.setCellValueFactory(new PropertyValueFactory<Product, String>("productName"));
        productsTablePriceCol.setCellValueFactory(new PropertyValueFactory<Product, Double>("productPrice"));
        productsTableInvCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("productInv"));
        // Populates both tables using predefined method
        partsTable.getItems().setAll(generatePartsTable());
        productsTable.getItems().setAll(generateProductsTable());
    }

    @FXML public void addPartBtnAction(ActionEvent event) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddPart.fxml"));
            AddPartController controller = new AddPartController(inventory);
            loader.setController(controller);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            System.out.print(e.toString());
        }
    }

    @FXML public void addProductBtnAction(ActionEvent event) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddProduct.fxml"));
            AddProductController controller = new AddProductController(inventory);
            loader.setController(controller);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            System.out.print(e.toString());
        }
    }

    @FXML public void deletePartBtnAction(ActionEvent event) {
        Part partToDelete = partsTable.getSelectionModel().getSelectedItem();
        Inventory.deletePart(partToDelete);
        partsTable.getItems().setAll(generatePartsTable());
    }

    @FXML public void deleteProductBtnAction(ActionEvent event) {
        Product productToDelete = productsTable.getSelectionModel().getSelectedItem();
        Inventory.deleteProduct(productToDelete);
        productsTable.getItems().setAll(generateProductsTable());
    }

    @FXML public void updatePartBtnAction(ActionEvent event) throws Exception {
        // Prevents updating non-existent Parts
        if (partsTable.getSelectionModel().getSelectedItem() != null) {
            UpdatePartController.setPartToUpdate(partsTable.getSelectionModel().getSelectedItem());
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdatePart.fxml"));
                UpdatePartController controller = new UpdatePartController(inventory);
                loader.setController(controller);
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            } catch (Exception e) {
                System.out.print(e.toString());
            }
        }
    }

    @FXML public void updateProductBtnAction(ActionEvent event) throws Exception {
        // Prevents updating non-existent Products
        if (productsTable.getSelectionModel().getSelectedItem() != null) {
            UpdateProductController.setProductToUpdate(productsTable.getSelectionModel().getSelectedItem());
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateProduct.fxml"));
                UpdateProductController controller = new UpdateProductController(inventory);
                loader.setController(controller);
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            } catch (Exception e) {
                System.out.print(e.toString());
            }
        }
    }

    @FXML public void searchPartBtnAction(ActionEvent event) {
        String search = partSearchTextField.getText();

        if (search.isEmpty()) {
            partsTable.getItems().setAll(generatePartsTable());
        } else {
            ObservableList<Part> match = FXCollections.observableArrayList();

            for (int i = 0; i < Inventory.getAllParts().size(); i++) {
                if (Inventory.getAllParts().get(i).getPartName().toLowerCase().contains(search.toLowerCase())) {
                    match.add((Inventory.getAllParts().get(i)));
                }
            }
            partsTable.getItems().setAll(match);
        }
    }

    @FXML public void searchProductBtnAction(ActionEvent event) {
        String search = productSearchTextField.getText();

        if (search.isEmpty()) {
            productsTable.getItems().setAll(generateProductsTable());
        } else {
            ObservableList<Product> match = FXCollections.observableArrayList();

            for (int i = 0; i < Inventory.getAllProducts().size(); i++) {
                if (Inventory.getAllProducts().get(i).getProductName().toLowerCase().contains(search.toLowerCase())) {
                    match.add(Inventory.getAllProducts().get(i));
                }
            }
            productsTable.getItems().setAll(match);
        }
    }

    @FXML public void exitBtnAction(ActionEvent event) {
        Platform.exit();
    }

    private List<Part> generatePartsTable() {
        ArrayList<Part> list = new ArrayList<>();

        for (int i = 0; i < Inventory.getAllParts().size(); i++) {
            list.add(Inventory.getAllParts().get(i));
        }
        return list;
    }

    private List<Product> generateProductsTable() {
        ArrayList<Product> list = new ArrayList<>();

        for (int i = 0; i < Inventory.getAllProducts().size(); i++) {
            list.add(Inventory.getAllProducts().get(i));
        }
        return list;
    }
}