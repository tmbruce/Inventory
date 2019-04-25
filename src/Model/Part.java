/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author tbruce
 */
public abstract class Part {
    private SimpleIntegerProperty partID;
    private SimpleStringProperty partName;
    private SimpleDoubleProperty partPrice;
    private SimpleIntegerProperty inStock;
    private SimpleIntegerProperty min;
    private SimpleIntegerProperty max;
    
    //Constructor
    public Part(int partID, String partName, double partPrice, int inStock, int min, int max){
    this.partID = new SimpleIntegerProperty(partID);
    this.partName = new SimpleStringProperty(partName);
    this.partPrice = new SimpleDoubleProperty(partPrice);
    this.inStock = new SimpleIntegerProperty(inStock);
    this.min = new SimpleIntegerProperty(min);
    this.max = new SimpleIntegerProperty(max);
    }
    
    //Setters and Getters
    public int getPartID(){
        return partID.get();
    }
    public void setPartID(SimpleIntegerProperty partID){
        this.partID = partID;
    }
    
    public String getPartName(){
        return partName.get();
    }
    public void setPartName(SimpleStringProperty partName){
        this.partName = partName;
    }
    
    public double getPartPrice(){
        return partPrice.get();
    }
    public void setPartPrice(SimpleDoubleProperty partPrice){
        this.partPrice = partPrice;
    }
    
    public int getInStock(){
        return inStock.get();
    }
    public void SetInStock(SimpleIntegerProperty inStock){
        this.inStock = inStock;
    }
    
    public int getMin(){
        return min.get();
    }
    public void setMin(SimpleIntegerProperty min){
        this.min = min; 
    }
    
    public int getMax(){
        return max.get();
    }
    public void setMax(SimpleIntegerProperty max){
        this.max = max;
    }
    
    //Validation checks
    public static String errorMessage ="";
    
    //@SuppressWarnings("ResultOfMethodCallIgnored")
    public static String validator(String partName, double partPrice, int inStock, int min, int max){
        errorMessage = "";
        if (partName == null || partName.isEmpty()){
            errorMessage += "Part name required\n ";
        }
        if (partPrice <= 0 || partPrice == 0){
            errorMessage += "Price must be greater than $0.00\n";
        }
        if (inStock < 0){
            errorMessage += "Number in stock must be positive\n";
        }
        if (inStock < min || inStock > max){
            errorMessage += "Stock must be between minimum and maximum\n";
        }
        if (max <  min){
            errorMessage += "Maximum must be greater than minimum\n";
        }
        return errorMessage;        
    }
}
