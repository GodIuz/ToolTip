package com.droidgeniuslabs.tooltip;

import com.droidgeniuslabs.tooltip.Util.LangUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        fxmlLoader.setResources(ResourceBundle.getBundle("messages", LangUtil.getCurrentLocale()));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 1000);
        stage.setTitle("Tooltip");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}