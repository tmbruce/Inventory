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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
        String partName = partNameField.getText();
        double partPrice = Double.parseDouble(partPriceField.getText());
        int partInventory = Integer.parseInt(partInvField.getText());
        int partMin = Integer.parseInt(partMinField.getText());
        int partMax = Integer.parseInt(partMaxField.getText());
        
        try{
            errorMessage = Part.validator(partName, partPrice, partInventory, partMin, partMax);
            if (errorMessage != null){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Parameter Error");
                alert.setHeaderText("Error");
                alert.setContentText(errorMessage);
                errorMessage = "";
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
                    System.out.println(Inventory.getParts());                   //DELETE BEFORE SUBMISSION, DEBUGGING PURPOSE ONLY
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
                    System.out.println(Inventory.getParts());                   //DELETE BEFORE SUBMISSION< DEBUGGING PURPOSE ONLY                
                    }
                Parent mainScreenParent = FXMLLoader.load(getClass().getResource("/Views/mainScreen.fxml"));
                Scene mainScreenScene = new Scene(mainScreenParent);
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(mainScreenScene);
                window.show();
            
            }    
        }
        catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Error adding part");
            alert.setContentText("Form is blank");
            alert.showAndWait();
        } 
    }

    @FXML
    private void cancelButtonHandler(ActionEvent event) throws IOException {
        Parent mainScreenParent = FXMLLoader.load(getClass().getResource("/Views/mainScreen.fxml"));
        Scene mainScreenScene = new Scene(mainScreenParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(mainScreenScene);
        window.show();
    }
    
}
