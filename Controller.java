package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Controller
{
    @FXML
    private Label labelS;
    @FXML
    private Label labelR;
    @FXML
    public TextField userName;
    @FXML
    public TextField passWord;

    @FXML
    public TextField newUser;
    @FXML
    public TextField newPass;
    @FXML
    public TextField newUserCOPY;
    @FXML
    public TextField newPassCOPY;

    int test;

    @FXML
    Label clockL;

    public void Login(ActionEvent event) throws IOException
    {
        //if ( userName.getText().equals(newUser) && passWord.getText().equals(newPass))
        if (userName.getText().equals("Nils") && passWord.getText().equals("Six")) {
            labelS.setText("Success!");
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
        } else
        {
            labelS.setText("Incorrect info");
        }
    }

    public void pressNewUser(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("registration.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

    public int pressRegister(ActionEvent event) throws IOException {
        //TextField combo = new TextField();
        if (newUser.getText().equals(newUserCOPY.getText()) && newPass.getText().equals(newPassCOPY.getText())) {
            //combo.textProperty().bind(Bindings.concat(newUser, " ", newPass));
            test = 1;

            Parent tableViewParent = FXMLLoader.load(getClass().getResource("sample.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
        } else {
            labelR.setText("Information doesn't match. Please check again.");
        }
        return test; //Doesn't work yet...
    }

    public void pressBack(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

    public void pressEmployeeButton(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("employeeInfo.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

    public void clock()
    {
        Calendar cal = new GregorianCalendar();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);

        int second = cal.get(Calendar.SECOND);
        int minute = cal.get(Calendar.MINUTE);
        int hour = cal.get(Calendar.HOUR);

        clockL.setText("Time: " + hour + ":" + minute + ":" + second);
    }
}