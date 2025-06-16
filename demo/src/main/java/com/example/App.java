package com.example;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * JavaFX App
 */ 
public class App extends Application {

    private static Scene scene;
@Override
public void start(Stage stage) throws IOException {
    Font.loadFont(getClass().getResource("/Fonts/Poppins-Light.ttf").toExternalForm(), 12);
    Font font = Font.loadFont(getClass().getResource("/Fonts/GulfsDisplay-Normal.ttf").toExternalForm(), 12);
    System.out.println("Loaded font: " + font.getName()); // Should print "Gulfs Display"

    scene = new Scene(loadFXML("loginui"), 1080, 720);
    stage.setScene(scene);
    stage.show();

}


    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}