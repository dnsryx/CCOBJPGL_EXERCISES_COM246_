package com.example;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class HomeController implements Initializable {

    ObservableList<User> myList = FXCollections.observableArrayList();

    @FXML
    Label usernamelabel;
    
    @FXML
    private TableColumn<User,String> AccountCrtClm;

    @FXML
    private ChoiceBox<String> ChoiceBox;

    @FXML
    private Button CreateButton;

    @FXML
    private Button DeleteButton;

    @FXML
    private TableColumn<User, String> PasswordClm;

    @FXML
    private TextField PasswordField;

    @FXML
    private TableColumn<User,String> StatusClm;

    @FXML
    private Button UpdateButton;

    @FXML
    private TableColumn<User,String> UsernameClm;

    @FXML
    private TextField UsernameField;

    @FXML
    private TableView<User> mytable;
    

    private Stage stage;
    private Scene scene;
    private Parent root;

    String filename = "Accounts.txt";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeCol();
        loadData();

        // TODO Auto-generated method stub
        String username = LoginController.user.getUsername();
        usernamelabel.setText(username);



        //  // Add items to the choice box
        //  statuschoicebox.getItems().addAll("Active", "Inactive");

        //  // Listener to update text fields when a row is selected
        //  myList.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
             
        //      if (newSelection != null) {
        //          usernametextfield.setText(newSelection.getUsername());
        //          passwordtextfield.setText(newSelection.getPassword());
        //          statuschoicebox.setValue(newSelection.getAccountstatus());
        //      }
        //  });
     }
 
    private void initializeCol(){

        UsernameClm.setCellValueFactory(new PropertyValueFactory<>("username"));
        PasswordClm.setCellValueFactory(new PropertyValueFactory<>("password"));
        AccountCrtClm.setCellValueFactory(new PropertyValueFactory<>("accountcreated"));
        StatusClm.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private void loadData(){

        myList.clear();

        try {
            // Create object from File class
            File myFile = new File("accounts.txt");

            // .exists() method checks if a file exists in the pathname
            if (myFile.exists()) {

                Scanner filescanner = new Scanner(myFile);

                while (filescanner.hasNextLine()) {

                    String data = filescanner.nextLine();
        
                    String username = data.split(",")[0];
                    String password = data.split(",")[1];
                    String dcreated = data.split(",")[2];
                    String status = data.split(",")[3];

                    myList.add(new User(username, password, dcreated, status));
                } 
                mytable.setItems(myList);

                filescanner.close();
            }
            else {
                System.out.println(myFile.getName() + " does not exist!");
            }
        } catch (Exception e) {
            System.out.println("There is an error");
        } 
    }
}
