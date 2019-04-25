/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Controller.MainScreenController.partToModify;
import static Model.Inventory.getParts;
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
public class ModifyPartController implements Initializable {

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
    private ToggleGroup sourceToggleGroup;
    private boolean outSourced;
    private int partID;
    int modifyPartIndexNum = partToModify();
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sourceToggleGroup = new ToggleGroup();
        this.inHouseRadio.setToggleGroup(sourceToggleGroup);
        this.outsourcedRadio.setToggleGroup(sourceToggleGroup);
        
        Part part = getParts().get(modifyPartIndexNum);
        partID = getParts().get(modifyPartIndexNum).getPartID();
        partIDField.setText("Auto Generated: " + partID);
        partNameField.setText(part.getPartName());
        partInvField.setText(Integer.toString(part.getInStock()));
        partPriceField.setText(Double.toString(part.getPartPrice()));
        partMinField.setText(Integer.toString(part.getMin()));
        partMaxField.setText(Integer.toString(part.getMax()));
        if (part instanceof inHouse){
            companyMachineLabel.setText("Machine ID");
            companyMachineField.setText(Integer.toString(((inHouse) part).getMachineID()));
            inHouseRadio.setSelected(true);
        }
        else {
            companyMachineLabel.setText("Company Name");
            companyMachineField.setText(((outSourced) part).getCompanyName());
            outsourcedRadio.setSelected(true);
        }
    }    

    @FXML
    private void inHousePartsRadio(ActionEvent event){
        outSourced = false;
        companyMachineLabel.setText("Machine ID");
        companyMachineField.setPromptText("Machine ID");
    }
    
    @FXML
    private void outSourcedRadio(ActionEvent event){
        outSourced = true;
        companyMachineLabel.setText("Company Name");
        companyMachineField.setPromptText("Company Name");
    }

    @FXML
    private void saveButtonHandler(ActionEvent event) {
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
