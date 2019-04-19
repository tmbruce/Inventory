/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author tbruce
 */
public class AddProductController implements Initializable {

    @FXML
    private TextField idTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField invTextField;
    @FXML
    private TextField priceTextField;
    @FXML
    private TextField minTextField;
    @FXML
    private TextField maxTextField;
    @FXML
    private TableView<?> tableView;
    @FXML
    private TableColumn<?, ?> partIDColumn;
    @FXML
    private TableColumn<?, ?> partNameColumn;
    @FXML
    private TableColumn<?, ?> partInventoryColumn;
    @FXML
    private TableColumn<?, ?> partPriceColumn;
    @FXML
    private TableView<?> tableView2;
    @FXML
    private TableColumn<?, ?> partIDColumn2;
    @FXML
    private TableColumn<?, ?> partNameColumn2;
    @FXML
    private TableColumn<?, ?> partInventoryColumn2;
    @FXML
    private TableColumn<?, ?> partPriceColumn2;
    @FXML
    private Button searchButton;
    @FXML
    private TextField searchTextField;
    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void searchButtonHandler(ActionEvent event) {
    }

    @FXML
    private void AddButtonHandler(ActionEvent event) {
    }

    @FXML
    private void DeleteButtonHandler(ActionEvent event) {
    }

    @FXML
    private void SaveButtonHandler(ActionEvent event) {
    }

    @FXML
    private void CancelButtonHandler(ActionEvent event) throws IOException {
        Parent mainScreenParent = FXMLLoader.load(getClass().getResource("/Views/mainScreen.fxml"));
        Scene mainScreenScene = new Scene(mainScreenParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(mainScreenScene);
        window.show();
    }
    
}
