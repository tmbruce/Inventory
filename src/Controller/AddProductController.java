/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Inventory;
import static Model.Inventory.getParts;
import static Model.Inventory.producIDgen;
import Model.Part;
import Model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
    private TableView<Part> tableView;
    @FXML
    private TableColumn<Part, Integer> partIDColumn;
    @FXML
    private TableColumn<Part, String> partNameColumn;
    @FXML
    private TableColumn<Part, Integer> partInventoryColumn;
    @FXML
    private TableColumn<Part, Double> partPriceColumn;
    @FXML
    private TableView<Part> tableView2;
    @FXML
    private TableColumn<Part, Integer> partIDColumn2;
    @FXML
    private TableColumn<Part, String> partNameColumn2;
    @FXML
    private TableColumn<Part, Integer> partInventoryColumn2;
    @FXML
    private TableColumn<Part, Double> partPriceColumn2;
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
    private boolean clicked = false;
    private Button cancelButton;
    private static Part addPart;
    private static int addPartIndexNum;
    private static Part deletePart;
    private static int deletePartIndexNum;
    private String errorMessage = new String();
    private Product newProduct;
    private ObservableList<Part> tempParts = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //set up parts table view
        partIDColumn.setCellValueFactory(new PropertyValueFactory<>("partID"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("partName"));
        partInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("partPrice"));
        tableView.setItems(Inventory.getParts());
        
        //set up associated parts table view
        partIDColumn2.setCellValueFactory(new PropertyValueFactory<>("partID"));
        partNameColumn2.setCellValueFactory(new PropertyValueFactory<>("partName"));
        partInventoryColumn2.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        partPriceColumn2.setCellValueFactory(new PropertyValueFactory<>("partPrice"));
        
        
        //set up product ID field
        idTextField.setText("Auto Generated");
    }    

    @FXML
    private void searchButtonHandler(ActionEvent event) {
        boolean found = false;
        try{
            int intSearch = Integer.parseInt(searchTextField.getText());
            for (Part p : Inventory.getParts()){
                if (p.getPartID() == intSearch){
                    tableView.getSelectionModel().select(p);
                    found = true;
                }
            }
        }
        catch(NumberFormatException e){
            String stringSearch = searchTextField.getText();
            for (Part p : Inventory.getParts()){
                if (p.getPartName().equalsIgnoreCase(stringSearch)){
                    tableView.getSelectionModel().select(p);
                    found = true;
                }
            }
        }
        if (found == false){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Search Error");
            alert.setContentText("Product not found in system");
            alert.showAndWait();
        }
    }

    @FXML
    private void AddButtonHandler(ActionEvent event) {
        addPart = tableView.getSelectionModel().getSelectedItem();
        addPartIndexNum = getParts().indexOf(addPart);
        if (addPartIndexNum == -1){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Selection Error");
            alert.setContentText("Please select a part to add.");
            alert.showAndWait();
        }
        else{
            tableView2.setItems(tempParts);
            tempParts.add(addPart);
            updatePartsTable2();
            
        }
        
    }

    @FXML
    private void DeleteButtonHandler(ActionEvent event) {
        deletePart = tableView2.getSelectionModel().getSelectedItem();
        System.out.println(deletePart);
        deletePartIndexNum = Product.getProductParts().indexOf(deletePart);
        if (deletePartIndexNum == -1){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Selection Error");
            alert.setContentText("Please select a part to delete.");
            alert.showAndWait();
        }
        else{
            Product.deletePart(deletePart);
            updatePartsTable2();
        }
    }

    @FXML
    private void SaveButtonHandler(ActionEvent event) throws IOException {
        if (nameTextField.getText().isEmpty() ||
            invTextField.getText().isEmpty() ||
            priceTextField.getText().isEmpty() ||
            minTextField.getText().isEmpty() ||
            maxTextField.getText().isEmpty()){
            Alert alert = new Alert (Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR");
            alert.setHeaderText("Empty Fields");
            alert.setContentText("Empty fields present in form.");
            alert.showAndWait();
        }
        else {
            String productName = nameTextField.getText();
            int productInventory = Integer.parseInt(invTextField.getText());
            double productPrice = Double.parseDouble(priceTextField.getText());
            int min = Integer.parseInt(minTextField.getText());
            int max = Integer.parseInt(maxTextField.getText());
            
            errorMessage = Product.validator(productName, productPrice, productInventory, min, max, tempParts);
            if (errorMessage.length() > 0){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Parameter Error");
                alert.setHeaderText("Error");
                alert.setContentText(errorMessage);
                alert.showAndWait();
            }
            else {
                newProduct = new Product(producIDgen(),
                                         productName,
                                         productPrice,
                                         productInventory,
                                         min,
                                         max);
                newProduct.setProductParts(tempParts);
                Inventory.addProduct(newProduct);
                
                Parent mainScreenParent = FXMLLoader.load(getClass().getResource("/Views/mainScreen.fxml"));
                Scene mainScreenScene = new Scene(mainScreenParent);
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(mainScreenScene);
                window.show();
                }            
        }
    }
    
    

    @FXML
    private void CancelButtonHandler(ActionEvent event) throws IOException {
        if (clicked == true){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Unsaved Information");
            alert.setContentText("Modified fields not saved will be lost.\n");
            Optional<ButtonType> result = alert.showAndWait();
            
                if (result.get() == ButtonType.OK){
                    Parent mainScreenParent = FXMLLoader.load(getClass().getResource("/Views/mainScreen.fxml"));
                    Scene mainScreenScene = new Scene(mainScreenParent);
                    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(mainScreenScene);
                    window.show();
                    clicked = false;
                }
        }
        else{
            Parent mainScreenParent = FXMLLoader.load(getClass().getResource("/Views/mainScreen.fxml"));
            Scene mainScreenScene = new Scene(mainScreenParent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(mainScreenScene);
            window.show();
        }
        
    }
    
    public void updatePartsTable() {
        tableView.setItems(Inventory.getParts());
    }
    public void updatePartsTable2(){
        tableView2.setItems(tempParts);
    }
    @FXML
    void clickChecker(MouseEvent event) {
        clicked = true;
    }
    
    
}
