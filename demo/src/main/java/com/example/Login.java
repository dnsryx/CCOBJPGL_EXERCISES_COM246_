package com.example;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Login {

    @FXML
    private Hyperlink FG;

    @FXML
    private Button LGBUTTON;

    @FXML
    private PasswordField PFIELD;

    @FXML
    private CheckBox RM;

    @FXML
    private TextField UFIELD;

    @FXML
    private Button signupButton;

    @FXML
    private Label userid;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public static User user;

    @FXML
    private void HandleLogin (ActionEvent event) throws IOException {

        String username = UFIELD.getText();
        String password = PFIELD.getText();

        File accountsfile = new File("Accounts.txt");
    
        user = new User(username, password);        

        if (accountsfile.exists()) {

            boolean userFound = false;
            boolean passwordCorrect = false;

            try (Scanner filescanner = new Scanner(accountsfile)) {

                while (filescanner.hasNextLine()) {
                    String data = filescanner.nextLine();

                    String[] parts = data.split(",");
                    if (parts.length < 2) continue;

                    String username_from_file = parts[0];
                    String password_from_file = parts[1];

                    if (username_from_file.equals(user.getUsername())) {
                        userFound = true;
                        if (password_from_file.equals(user.getPassword())) {
                            passwordCorrect = true;
                        }
                        break;
                    }
                }

                if (userFound && passwordCorrect) {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setContentText("Login Successful!");
                    alert.showAndWait();

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("homeui.fxml"));
                    root = loader.load();

                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.setResizable(false);
                    userid.setText(username);
                    stage.show();

                } else if (userFound) {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setHeaderText("Error");
                    alert.setContentText("Incorrect Password.");
                    alert.showAndWait();

                } else {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setHeaderText("Error");
                    alert.setContentText("No user found.");
                    alert.showAndWait();
                }
            }

        } else {
            System.out.println("file does not exist");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
}

    @FXML
    private void registerButtonHandler(ActionEvent event) throws IOException {
        String username = UFIELD.getText().trim();
        String password = PFIELD.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Registration Failed", "Username or password caanot be empty.");
            return;
        }

        File accountsFile = new File("accounts.txt");

        if (accountsFile.exists()) {
            try (Scanner scanner = new Scanner(accountsFile)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] parts = line.split(",");

                    if (parts.length >= 1 && parts[0].equals(username)) {
                        showAlert("Registration Failed", "Username already Exists.");
                        return;
                    }
                }
            }
        }

        try (java.io.FileWriter fw = new java.io.FileWriter(accountsFile, true)) {
            fw.write(username + "," + password + "\n");

        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to write to file.");
            return;
        }

        showAlert("Registration Successful", "Account created! You can now log in.");

    }
    
}


