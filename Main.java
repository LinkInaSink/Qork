package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Scanner;

public class Main extends Application
{

    @Override
    public void start(Stage stage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        stage.setTitle("Qork");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }

}