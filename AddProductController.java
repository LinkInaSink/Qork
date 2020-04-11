package sample;

import sample.Inventory;
import sample.Part;
import sample.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddProductController implements Initializable {
    Inventory inventory;
    @FXML public Button saveBtn;
    @FXML public Button cancelBtn;
    @FXML public Button addAssociatedPartBtn;
    @FXML public Button deleteAssociatedPartBtn;
    @FXML public Button partSearchBtn;
    @FXML public TextField partSearchTextField;
    @FXML public TextField idTextField;
    @FXML public TextField nameTextField;
    @FXML public TextField priceTextField;
    @FXML public TextField invTextField;
    @FXML public TextField maxTextField;
    @FXML public TextField minTextField;
    @FXML public TableView<Part> partsTable;
    @FXML public TableView<Part> selectedPartsTable;
    @FXML public TableColumn<Part, Integer> partsTableIDCol;
    @FXML public TableColumn<Part, String> partsTableNameCol;
    @FXML public TableColumn<Part, Double> partsTablePriceCol;
    @FXML public TableColumn<Part, Integer> partsTableInvCol;
    @FXML public TableColumn<Part, Integer> selectedPartsTableIDCol;
    @FXML public TableColumn<Part, String> selectedPartsTableNameCol;
    @FXML public TableColumn<Part, Double> selectedPartsTablePriceCol;
    @FXML public TableColumn<Part, Integer> selectedPartsTableInvCol;

    // Constructor
    public AddProductController(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override public void initialize(URL url, ResourceBundle rb) {
        // Maps table columns and populates them
        partsTableIDCol.setCellValueFactory(new PropertyValueFactory<Part, Integer>("partID"));
        partsTableNameCol.setCellValueFactory(new PropertyValueFactory<Part, String>("partName"));
        partsTablePriceCol.setCellValueFactory(new PropertyValueFactory<Part, Double>("partPrice"));
        partsTableInvCol.setCellValueFactory(new PropertyValueFactory<Part, Integer>("partInv"));
        partsTable.getItems().setAll(generatePartsTable());
    }

    @FXML public void addAssociatedPartBtnAction(ActionEvent event) {
        if (partsTable.getSelectionModel().getSelectedItem() != null) {
            Part selectedPart = partsTable.getSelectionModel().getSelectedItem();
            addToSelectedPartsTable(selectedPart);
            selectedPartsTable.refresh();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error: Add");
            alert.setContentText("A part must be selected in order to add.");
            alert.show();
        }
    }

    @FXML public void deleteAssociatedPartBtnAction(ActionEvent event) {
        if (partsTable.getSelectionModel().getSelectedItem() != null) {
            Part selectedPart = selectedPartsTable.getSelectionModel().getSelectedItem();
            selectedPartsTable.getItems().remove(selectedPart);
            partsTable.refresh();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error: Delete");
            alert.setContentText("A part must be selected in order to delete.");
            alert.show();
        }
    }

    @FXML public void partSearchBtnAction(ActionEvent event) {
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

    @FXML public void saveBtnAction(ActionEvent event) {
        try {
            int id = Integer.parseInt(idTextField.getText());
            String name = nameTextField.getText();
            double price = Double.parseDouble(priceTextField.getText());
            int inv = Integer.parseInt(invTextField.getText());
            int max = Integer.parseInt(maxTextField.getText());
            int min = Integer.parseInt(minTextField.getText());
            ObservableList<Part> parts = selectedPartsTable.getItems();

            if (parts.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Cannot be Empty");
                alert.setContentText("A product must be associated with at least one part.");
                alert.show();
            } else {
                double totalPrice = 0.0;

                for (int i = 0; i < parts.size(); i++) {
                    totalPrice += parts.get(i).getPartPrice();
                }

                if (price < totalPrice) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Price Error");
                    alert.setContentText("Product price must be at least total parts price.");
                    alert.show();
                } else {
                    Product newProduct = new Product(id, name, price, inv, max, min);
                    Inventory.addProduct(newProduct);
                    backToMain(event);
                }
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Values");
            alert.setContentText("Please verify that all values are valid.");
            alert.show();
        }
    }

    @FXML  public void cancelBtnAction(ActionEvent event) throws Exception {
        backToMain(event);
    }

    private void backToMain(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScreen.fxml"));
        MainScreenController controller = new MainScreenController(inventory);
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private List<Part> generatePartsTable() {
        ArrayList<Part> arrayList = new ArrayList<Part>();
        for (int i = 0; i < Inventory.getAllParts().size(); i++) {
            arrayList.add(Inventory.getAllParts().get(i));
        }
        return arrayList;
    }

    private void addToSelectedPartsTable(Part part) {
        selectedPartsTableIDCol.setCellValueFactory(new PropertyValueFactory<Part, Integer>("partID"));
        selectedPartsTableNameCol.setCellValueFactory(new PropertyValueFactory<Part, String>("partName"));
        selectedPartsTablePriceCol.setCellValueFactory(new PropertyValueFactory<Part, Double>("partPrice"));
        selectedPartsTableInvCol.setCellValueFactory(new PropertyValueFactory<Part, Integer>("partInv"));
        selectedPartsTable.getItems().add(part);
    }
}