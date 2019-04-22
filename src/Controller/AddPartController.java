/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.inHouse;
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
    private ToggleGroup sourceToggleGroup;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sourceToggleGroup = new ToggleGroup();
        this.inHouseRadio.setToggleGroup(sourceToggleGroup);
        this.outsourcedRadio.setToggleGroup(sourceToggleGroup);
        
    }    

    @FXML
    private void radioButtonChange(ActionEvent event) {
        if (this.sourceToggleGroup.getSelectedToggle().equals(this.inHouseRadio))
            companyMachineLabel.setText("Machine ID");
        if (this.sourceToggleGroup.getSelectedToggle().equals(this.inHouseRadio))
            companyMachineField.setPromptText("Machine ID");
        if (this.sourceToggleGroup.getSelectedToggle().equals(this.outsourcedRadio))
            companyMachineLabel.setText("Company Name");
        if (this.sourceToggleGroup.getSelectedToggle().equals(this.outsourcedRadio))
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
