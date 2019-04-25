/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author tbruce
 */
public class inHouse extends Part {
    
    private SimpleIntegerProperty machineID;
    
    public inHouse(int partID, String partName, double partPrice, int inStock, int min, int max, int machineID) {
        super(partID, partName, partPrice, inStock, min, max);
        this.machineID = new SimpleIntegerProperty(machineID);
    }
    
    public int getMachineID(){
        return machineID.get();
    }
    public void setMachineID(SimpleIntegerProperty machineID){
        this.machineID = machineID;
    }
    
}
