/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Inventory;
import static Model.Inventory.deletePart;
import static Model.Inventory.getParts;
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
    private TableView<Product> productsTableView;
    @FXML
    private TableColumn<Product, Integer> productIDColumn;
    @FXML
    private TableColumn<Product, String> productNameColumn;
    @FXML
    private TableColumn<Product, Integer> productInventoryLevelColumn;
    @FXML
    private TableColumn<Product, Double> productPriceUnitColumn;
    @FXML
    private Button addProductsButton;
    @FXML
    private Button modifyProductsButton;
    @FXML
    private Button deleteProdutsButton;
    @FXML
    private Button exitButton;
    private static Part modifyPart;
    private static Product modifyProduct;
    private static int modifyPartIndexNum;
    private static int modifyProductIndexNum;
    private static Part deletePart;
    private static Product deleteProduct;
    private static int deletePartNum;
    private static int deleteProductNum;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Set up table view columns for parts table
        partIDColumn.setCellValueFactory(new PropertyValueFactory<>("partID"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("partName"));
        partInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        partsPriceUnitColumn.setCellValueFactory(new PropertyValueFactory<>("partPrice"));
        
        //Grab items from observable list for parts table
        partsTableView.setItems(Inventory.getParts());
        
        //Set up table view columns for products table
        productIDColumn.setCellValueFactory(new PropertyValueFactory<>("productID"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        productInventoryLevelColumn.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        productPriceUnitColumn.setCellValueFactory(new PropertyValueFactory<>("productPrice"));
        
        //Grab items from observable list for prodcts table
        productsTableView.setItems(Inventory.getProducts());        
    }
    
    public static int partToModify(){
        return modifyPartIndexNum;
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
        
        modifyPart = partsTableView.getSelectionModel().getSelectedItem();
        modifyPartIndexNum = getParts().indexOf(modifyPart);
        if (modifyPartIndexNum == -1){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Selection Error");
            alert.setContentText("Please select a part to modify.");
            alert.showAndWait();
        }
        else{
            Parent modifyPartParent = FXMLLoader.load(getClass().getResource("/Views/modifyPart.fxml"));
            Scene modifyPartScene = new Scene(modifyPartParent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(modifyPartScene);
            window.show();
        }
    }
    public void updatePartsTable() {
        partsTableView.setItems(getParts());
    }
    
    
    @FXML
    private void deletePartsHandler(ActionEvent event) {
        deletePart = partsTableView.getSelectionModel().getSelectedItem();
        deletePartNum = getParts().indexOf(deletePart);
        String deleteMessage = "";
        if (deletePartNum == -1){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Delete Error");
            alert.setContentText("Please select a part to delete");
            alert.showAndWait();
        }
        else{
            for (Product product : Inventory.getProducts()) {
                if (product.getProductParts().contains(deletePart)){
                    deleteMessage += "This part is associated with " + product.getProductName() + "\nPlease delete from the product first.\n";
                }
            }
            if (deleteMessage.length() > 0){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("Delete Error");
                alert.setContentText(deleteMessage);
                alert.showAndWait();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Error");
                alert.setHeaderText("Confirm Delete");
                alert.setContentText("Deleted items are not able to be recovered\n");
                Optional<ButtonType> result = alert.showAndWait();
            
                if (result.get() == ButtonType.OK){
                    deletePart(deletePart);
                    updatePartsTable();
                }
            }
        }
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
    
    public static int productToModify(){
        return modifyProductIndexNum;
    }

    @FXML
    private void modifyProductsHandler(ActionEvent event) throws IOException {
        modifyProduct = productsTableView.getSelectionModel().getSelectedItem();
        modifyProductIndexNum = Inventory.getProducts().indexOf(modifyProduct);
        if (modifyProductIndexNum == -1){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Selection Error");
            alert.setContentText("Please select a product to modify.");
            alert.showAndWait();
        }
        else {
            Parent modifyProductParent = FXMLLoader.load(getClass().getResource("/Views/modifyProduct.fxml"));
            Scene modifyProductScene = new Scene(modifyProductParent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(modifyProductScene);
            window.show();
        }
    }
    
    public void updateProductsTable() {
        productsTableView.setItems(Inventory.getProducts());
    }

    @FXML
    private void deleteProductsHandler(ActionEvent event) {
        deleteProduct = productsTableView.getSelectionModel().getSelectedItem();
        deleteProductNum = Inventory.getProducts().indexOf(deleteProduct);
        if (deleteProductNum == -1){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Selection Error");
            alert.setContentText("Please select a product to delete.");
            alert.showAndWait();
        }
        else{
            if (deleteProduct.getProductParts().isEmpty()){
                Inventory.removeProduct(deleteProduct);
                updateProductsTable();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("Delete Error");
                alert.setContentText("This product is currently associated with one or more parts.\nPlease delete associated parts first.");
                alert.showAndWait();
            }
            
        }
    }

    @FXML
    private void exitButtonHandler(ActionEvent event) {       
    }
}
