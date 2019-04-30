/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Controller.MainScreenController.productToModify;
import Model.Inventory;
import static Model.Inventory.getParts;
import Model.Part;
import Model.Product;
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
import javafx.scene.control.Alert;
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
public class ModifyProductController implements Initializable {

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
    private Button cancelButton;
    private int modifyProductIndexNum = productToModify();
    private int productID;
    private static Part deletePart;
    private static int deletePartIndexNum;
    private static Part addPart;
    private static int addPartIndexNum;
    private String errorMessage = new String();
    private Product newProduct;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Product product = Inventory.getProducts().get(modifyProductIndexNum);
        productID = Inventory.getProducts().get(modifyProductIndexNum).getProductID();
        
        idTextField.setText("Auto Generated: " + productID);
        nameTextField.setText(product.getProductName());
        invTextField.setText(Integer.toString(product.getInStock()));
        priceTextField.setText(Double.toString(product.getProductPrice()));
        minTextField.setText(Integer.toString(product.getMin()));
        maxTextField.setText(Integer.toString(product.getMax()));
        
        partIDColumn.setCellValueFactory(new PropertyValueFactory<>("partID"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("partName"));
        partInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("partPrice"));
        tableView.setItems(Inventory.getParts());
        
        partIDColumn2.setCellValueFactory(new PropertyValueFactory<>("partID"));
        partNameColumn2.setCellValueFactory(new PropertyValueFactory<>("partName"));
        partInventoryColumn2.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        partPriceColumn2.setCellValueFactory(new PropertyValueFactory<>("partPrice"));
        tableView2.setItems(Product.getProductParts());
    }    

    @FXML
    private void searchButtonHandler(ActionEvent event) {
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
            Product.setProductParts(addPart);
            updatePartsTable2();
            
        }
    }

    @FXML
    private void deleteButtonHandler(ActionEvent event) {
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
        else{
            String productName = nameTextField.getText();
            int productInventory = Integer.parseInt(invTextField.getText());
            double productPrice = Double.parseDouble(priceTextField.getText());
            int min = Integer.parseInt(minTextField.getText());
            int max = Integer.parseInt(maxTextField.getText());
            
            errorMessage = Product.validator(productName, productPrice, productInventory, min, max);
            if (errorMessage.length() > 0){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Parameter Error");
                alert.setHeaderText("Error");
                alert.setContentText(errorMessage);
                alert.showAndWait();
            }
            
            else {
                newProduct = new Product(tableView2.getItems(),
                                         productID,
                                         productName,
                                         productPrice,
                                         productInventory,
                                         min,
                                         max);
                Inventory.updateProduct(modifyProductIndexNum, newProduct);
                
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
        Parent mainScreenParent = FXMLLoader.load(getClass().getResource("/Views/mainScreen.fxml"));
        Scene mainScreenScene = new Scene(mainScreenParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(mainScreenScene);
        window.show();
    }
    public void updatePartsTable() {
        tableView.setItems(Inventory.getParts());
    }
    public void updatePartsTable2(){
        tableView2.setItems(Product.getProductParts());
    }
    
}
