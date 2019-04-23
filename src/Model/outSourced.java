/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author tbruce
 */
public class outSourced extends Part {
    
    private SimpleStringProperty companyName;
    
    public outSourced(int partID, String partName, double partPrice, int inStock, int min, int max, String companyName) {
        super(partID, partName, partPrice, inStock, min, max);
        this.companyName = new SimpleStringProperty(companyName);
    }
    
    public SimpleStringProperty getCompanyName(){
        return companyName;
    }
    public void setCompanyName(SimpleStringProperty companyName){
        this.companyName = companyName;
    }
}
