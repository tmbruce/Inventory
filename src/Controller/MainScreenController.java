/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Part;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author tbruce
 */
public class MainScreenController implements Initializable {

    @FXML
    private Button partsSearchButton;
    @FXML
    private TextField partsSearchTextField;
    @FXML
    private TableView<Part> partsTableView;
    @FXML
    private TableColumn<Part, Integer> partIDColumn;
    @FXML
    private TableColumn<Part, String> partNameColumn;
    @FXML
    private TableColumn<Part, Integer> partInventoryColumn;
    @FXML
    private TableColumn<Part, Double> partsPriceUnitColumn;
    @FXML
    private Button addPartsButton;
    @FXML
    private Button modifyPartsButton;
    @FXML
    private Button deletePartsButton;
    @FXML
    private Button productSearchButton;
    @FXML
    private TextField productSearchTextField;
    @FXML
    private TableView<?> productsTableView;
    @FXML
    private TableColumn<?, ?> productIDColumn;
    @FXML
    private TableColumn<?, ?> productNameColumn;
    @FXML
    private TableColumn<?, ?> productInventoryLevelColumn;
    @FXML
    private TableColumn<?, ?> productPriceUnitColumn;
    @FXML
    private Button addProductsButton;
    @FXML
    private Button modifyProductsButton;
    @FXML
    private Button deleteProdutsButton;
    @FXML
    private Button exitButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        partIDColumn.setCellValueFactory(new PropertyValueFactory<>("partID"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("partName"));
        partInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        partsPriceUnitColumn.setCellValueFactory(new PropertyValueFactory<>("partPrice"));
        
        partsTableView.setItems(getParts());
    }
    
    //This method returns an ObservableList of part objects
    public ObservableList<Part> getParts(){
        ObservableList<Part> parts = FXCollections.observableArrayList();
    }

    @FXML
    private void partsSearchHandler(ActionEvent event) {
    }

    @FXML
    private void addPartsHandler(ActionEvent event) throws IOException {
        Parent addPartParent = FXMLLoader.load(getClass().getResource("/Views/addPart.fxml"));
        Scene addPartScene = new Scene(addPartParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(addPartScene);
        window.show();
    }

    @FXML
    private void modifyPartsHandler(ActionEvent event) throws IOException {
        Parent modifyPartParent = FXMLLoader.load(getClass().getResource("/Views/modifyPart.fxml"));
        Scene modifyPartScene = new Scene(modifyPartParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(modifyPartScene);
        window.show();
    }

    @FXML
    private void deletePartsHandler(ActionEvent event) {
    }

    @FXML
    private void productSearchHandler(ActionEvent event) {
    }

    @FXML
    private void addProductsHandler(ActionEvent event) throws IOException {
        Parent addProductParent = FXMLLoader.load(getClass().getResource("/Views/addProduct.fxml"));
        Scene addProductScene = new Scene(addProductParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(addProductScene);
        window.show();
    }

    @FXML
    private void modifyProductsHandler(ActionEvent event) throws IOException {
        Parent modifyProductParent = FXMLLoader.load(getClass().getResource("/Views/modifyProduct.fxml"));
        Scene modifyProductScene = new Scene(modifyProductParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(modifyProductScene);
        window.show();
    }

    @FXML
    private void deleteProductsHandler(ActionEvent event) {
    }

    @FXML
    private void exitButtonHandler(ActionEvent event) {       
    }
    
}
