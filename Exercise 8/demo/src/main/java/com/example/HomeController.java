package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class HomeController implements Initializable {

    
    ObservableList<User> mylist = FXCollections.observableArrayList();

    @FXML
    Label usernameLabel;

        @FXML
    private TableColumn<User, String> AccountCrtClm;

    @FXML
    private Button CreateButton;

    @FXML
    private Button DeleteButton;

    @FXML
    private TableView<User> mytable;

    @FXML
    private TableColumn<User, String> PasswordClm;

    @FXML
    private TextField PasswordField;

    @FXML
    private TableColumn<User, String> StatusClm;

    @FXML
    private ChoiceBox<String> ChoiceBox;

    @FXML
    private TableColumn<String, User> UsernameClm;

    @FXML
    private TextField UsernameField;

    @FXML
    private Button UpdateButton;

@Override
    public void initialize(URL url, ResourceBundle rb){

        String username  = LoginController.user.getUsername();
        usernameLabel.setText(username);

        initializeCol();
        loadData();

        // Add items to the choice box
        ChoiceBox.getItems().addAll("Active", "Inactive");

        // Listener to update text fields when a row is selected
        mytable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            
            if (newSelection != null) {
                UsernameField.setText(newSelection.getUsername());
                PasswordField.setText(newSelection.getPassword());
                ChoiceBox.setValue(newSelection.getAccountstatus());
            }
        });
    }
    private void initializeCol(){

        UsernameClm.setCellValueFactory(new PropertyValueFactory<>("username"));
        PasswordClm.setCellValueFactory(new PropertyValueFactory<>("password"));
        AccountCrtClm.setCellValueFactory(new PropertyValueFactory<>("accountcreated"));
        StatusClm.setCellValueFactory(new PropertyValueFactory<>("accountstatus"));
    }

    private void loadData(){
        mylist.clear();

        try {
            // Create object from File class
            File myFile = new File("Accounts.txt");

            // .exists() method checks if a file exists in the pathname
            if (myFile.exists()) {

                Scanner filescanner = new Scanner(myFile);

                while (filescanner.hasNextLine()) {

                    String data = filescanner.nextLine();
                    System.out.print(data);
                    String username = data.split(",")[0];
                    String password = data.split(",")[1];
                    String dcreated = data.split(",")[2];
                    String status = data.split(",")[3];

                    mylist.add(new User(username, password, dcreated, status));
                } 
                mytable.setItems(mylist);

                filescanner.close();
            }
            else {
                System.out.println(myFile.getName() + " does not exist!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("There is an error");
        } 
    }

    @FXML
    private boolean createuser(ActionEvent event) {

        String username = UsernameField.getText();

        String password = PasswordField.getText();

        String status = ChoiceBox.getValue();
        System.out.println(status);
        username = username.trim();
        password = password.trim();
        status = status.trim();

        if(username.length() == 0)
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("no username provided");
            return false;
        }

        if(password.length() == 0)
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("no password provided");
            return false;
        }

        // Get current date
        LocalDate today = LocalDate.now();

        // Format as MM-dd-yyyy
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        String formattedDate = today.format(formatter);

        User user = new User(username, password, formattedDate, status);

        try {

            BufferedWriter myWriter = new BufferedWriter(new FileWriter("Accounts.txt", true));
      
            // .write() methods adds content to the file
            myWriter.newLine();
            myWriter.write(user.getUsername() + "," + user.getPassword() + "," + user.getAccountcreated() + "," + user.getAccountstatus());

            // Close FileWriter
            myWriter.close();

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("This is the header");
            alert.setContentText("This is an alert message!");
            alert.showAndWait();
            loadData();

        } catch (IOException e) {
            System.out.println("An error occurred.");
        }

        return true;
    }

    @FXML
    public boolean updateuser(ActionEvent event) {

        User user = mytable.getSelectionModel().getSelectedItem();

        String username = UsernameField.getText();

        String password = PasswordField.getText();

        String status = ChoiceBox.getValue();

        username = username.trim();
        password = password.trim();
        status = status.trim();

        if(username.length() == 0)
        {
            System.out.println("No username!");
            return false;
        }

        if(password.length() == 0)
        {
            System.out.println("No password!");
            return false;
        }

        String targetUsername = user.getUsername();
        String newPassword = password;
        String newStatus = status;

        List<String> updatedLines = new ArrayList<>();

        // Step 1: Read and update
        try (BufferedReader reader = new BufferedReader(new FileReader("Accounts.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String[] parts = line.split(",");

                    if (parts.length == 4 && parts[0].equalsIgnoreCase(targetUsername)) {
                        updatedLines.add(parts[0] + "," + newPassword + "," + user.getAccountcreated() + "," + newStatus);
                    } else {
                        updatedLines.add(line);
                    }

                    // if (parts.length == 2 && parts[0].equalsIgnoreCase(targetUsername)) {
                    //     // Update password
                    //     updatedLines.add(parts[0] + "," + newPassword + "," + newStatus);
                    // } else {
                    //     updatedLines.add(line);
                    }
                }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        // Step 2: Write updated lines back
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Accounts.txt"))) {
            for (int i = 0; i < updatedLines.size(); i++) {
                writer.write(updatedLines.get(i));
                if (i < updatedLines.size() - 1) {
                    writer.newLine(); // no extra blank line
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("This is the header");
        alert.setContentText("User '" + targetUsername + "' has been updated.");
        alert.showAndWait();
        loadData();
        return true;
    }

    @FXML
    public boolean deleteuser(ActionEvent event) {

        // Get selected row from table
        User user = mytable.getSelectionModel().getSelectedItem();

        String username = (user.getUsername());

        System.out.println(username);

        //String filename = "accounts.txt";
        String userToDelete = username;

        List<String> updatedLines = new ArrayList<>();

        // Step 1: Read and filter lines
        try (BufferedReader reader = new BufferedReader(new FileReader("Accounts.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) { // skip empty lines
                    String[] parts = line.split(",");
                    if (!parts[0].equalsIgnoreCase(userToDelete)) {
                        updatedLines.add(line);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        // Step 2: Write back without trailing newline
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Accounts.txt"))) {
            for (int i = 0; i < updatedLines.size(); i++) {
                writer.write(updatedLines.get(i));
                if (i < updatedLines.size() - 1) {
                    writer.newLine(); // add newline except after the last line
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("This is the header");
        alert.setContentText("User '" + userToDelete + "' has been deleted (if existed).");
        alert.showAndWait();
        loadData();

        return true;
    }
    
}
