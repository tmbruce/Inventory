/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Inventory;
import Model.Part;
import Model.inHouse;
import Model.outSourced;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author tbruce
 */
public class AddPartController implements Initializable {

    @FXML
    private RadioButton inHouseRadio;
    @FXML
    private RadioButton outsourcedRadio;
    @FXML
    private Label companyMachineLabel;
    @FXML
    private TextField partIDField;
    @FXML
    private TextField partNameField;
    @FXML
    private TextField partInvField;
    @FXML
    private TextField partPriceField;
    @FXML
    private TextField partMinField;
    @FXML
    private TextField partMaxField;
    @FXML
    private TextField companyMachineField;
    @FXML
    private Button saveButton;
    @FXML
    private boolean clicked = false;
    private Button cancelButton;
    private boolean outSourced;
    private ToggleGroup sourceToggleGroup;
    private inHouse newInHouse;
    private outSourced newOutSourced;
    private String errorMessage = new String();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Initialization of the toggle group
        sourceToggleGroup = new ToggleGroup();
        this.inHouseRadio.setToggleGroup(sourceToggleGroup);
        this.outsourcedRadio.setToggleGroup(sourceToggleGroup);  
    }    

    @FXML
    //Handler to determine state of toggle button
    private void inHousePartsRadio(ActionEvent event){
        outSourced = false;
        companyMachineLabel.setText("Machine ID");
        companyMachineField.setPromptText("Machine ID");
    }
    
    @FXML
    //Handler to determine state of toggle button
    private void outSourcedRadio(ActionEvent event){
        outSourced = true;
        companyMachineLabel.setText("Company Name");
        companyMachineField.setPromptText("Company Name");
    }

    @FXML
    private void saveButtonHandler(ActionEvent event) throws IOException {
        
        if (partPriceField.getText().isEmpty() ||
            partInvField.getText().isEmpty() ||
            partMinField.getText().isEmpty() ||
            partMaxField.getText().isEmpty() ||
            partNameField.getText().isEmpty() ||
            companyMachineField.getText().isEmpty()){
            Alert alert = new Alert (Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR");
            alert.setHeaderText("Empty Fields");
            alert.setContentText("Empty fields present in form.");
            alert.showAndWait();
            }
        
        else {
            //Preemptive error handling for blank fields on form
            String partName = partNameField.getText();
            double partPrice = Double.parseDouble(partPriceField.getText());
            int partInventory = Integer.parseInt(partInvField.getText());
            int partMin = Integer.parseInt(partMinField.getText());
            int partMax = Integer.parseInt(partMaxField.getText());

            //logic validation 
            errorMessage = Part.validator(partName, partPrice, partInventory, partMin, partMax);
            if (errorMessage.length() > 0){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Parameter Error");
                alert.setHeaderText("Error");
                alert.setContentText(errorMessage);
                alert.showAndWait();
            }
            if (sourceToggleGroup.getSelectedToggle() == null){
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("Selection Error");
                alert.setContentText("Please select a part source.");
                alert.showAndWait(); 
            }
            
            else {
                if (outSourced == false){
                    newInHouse = new inHouse(
                    Inventory.partIDgen(),
                    partName,
                    partPrice,
                    partInventory,
                    partMin,
                    partMax,
                    Integer.parseInt(companyMachineField.getText()));
                    Inventory.addPart(newInHouse);
                    }
                else if (outSourced == true){
                    newOutSourced = new outSourced(
                    Inventory.partIDgen(),
                    partName,
                    partPrice,
                    partInventory,
                    partMin,
                    partMax,
                    companyMachineField.getText());
                    Inventory.addPart(newOutSourced);
                    }
                Parent mainScreenParent = FXMLLoader.load(getClass().getResource("/Views/mainScreen.fxml"));
                Scene mainScreenScene = new Scene(mainScreenParent);
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(mainScreenScene);
                window.show();
                }    
            }
    }
    @FXML
    void clickChecker(MouseEvent event) {
        clicked = true;
    }

    @FXML
    private void cancelButtonHandler(ActionEvent event) throws IOException {
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
}
