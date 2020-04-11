package sample;

import sample.InHouse;
import sample.Inventory;
import sample.Outsourced;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddPartController implements Initializable {
    Inventory inventory;
    @FXML public Button saveBtn;
    @FXML public Button cancelBtn;
    @FXML public ToggleGroup partType;
    @FXML public RadioButton inHouseRadio;
    @FXML public RadioButton outsourcedRadio;
    @FXML public TextField idTextField;
    @FXML public TextField nameTextField;
    @FXML public TextField priceTextField;
    @FXML public TextField invTextField;
    @FXML public TextField maxTextField;
    @FXML public TextField minTextField;
    @FXML public TextField machineIDTextField;
    @FXML public TextField compNameTextField;
    @FXML public HBox machHBox;
    @FXML public HBox compHBox;

    // Constructor
    public AddPartController(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override public void initialize(URL url, ResourceBundle rb) {
        inHouseRadio.setSelected(true);
        machHBox.setVisible(true);
        compHBox.setVisible(false);
    }

    @FXML public void partRadioToggle(ActionEvent event) {
        if (inHouseRadio.isSelected()) {
            machHBox.setVisible(true);
            compHBox.setVisible(false);
        }
        if (outsourcedRadio.isSelected()) {
            compHBox.setVisible(true);
            machHBox.setVisible(false);
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
            // machineID or compName will be parsed later
            if (min > max) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error: Minimum");
                alert.setContentText("Min amount cannot be greater than the max allowed.");
                alert.show();
            } else if (inv < min || inv > max ) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error: Inventory");
                alert.setContentText("Inventory must be in between min and max allowed.");
                alert.show();
            } else {
                if (inHouseRadio.isSelected()) {
                    int machineID = Integer.parseInt(machineIDTextField.getText());
                    InHouse newInHousePart = new InHouse(id, name, price, inv, max, min, machineID);
                    Inventory.addPart(newInHousePart);
                }
                if (outsourcedRadio.isSelected()) {
                    String compName = compNameTextField.getText();
                    Outsourced newOutsourcedPart = new Outsourced(id, name, price, inv, max, min, compName);
                    Inventory.addPart(newOutsourcedPart);
                }
                backToMain(event);
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Values");
            alert.setContentText("Please verify that all values are valid.");
            alert.show();
        }
    }

    @FXML public void cancelBtnAction(ActionEvent event) throws Exception {
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
}