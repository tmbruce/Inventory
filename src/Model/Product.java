/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author tbruce
 */
public class Product {
    private static ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private SimpleIntegerProperty productID;
    private SimpleStringProperty productName;
    private SimpleDoubleProperty productPrice;
    private SimpleIntegerProperty inStock;
    private SimpleIntegerProperty min;
    private SimpleIntegerProperty max;
    
    public Product(int productID, String productName, double productPrice, int inStock, int min, int max){
        this.productID = new SimpleIntegerProperty(productID);
        this.productName = new SimpleStringProperty(productName);
        this.productPrice = new SimpleDoubleProperty(productPrice);
        this.inStock = new SimpleIntegerProperty(inStock);
        this.min = new SimpleIntegerProperty(min);
        this.max = new SimpleIntegerProperty(max);
    }
    
    public int getProductID(){
        return productID.get();        
    }
    public void setProductID(SimpleIntegerProperty productID){
        this.productID = productID;
    }
    
    public String getProductName(){
        return productName.get();
    }
    public void setProductName(SimpleStringProperty productName){
        this.productName = productName;
    }
    
    public double getProductPrice(){
        return productPrice.get();
    }
    public void setProductPrice(SimpleDoubleProperty productPrice){
        this.productPrice = productPrice;
    }
    public int getInStock(){
        return inStock.get();
    }
    public void setInStock(SimpleIntegerProperty inStock){
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
    
    public static ObservableList getProductParts(){
        return associatedParts;
    }
    public void setProductParts(ObservableList<Part> parts){
        this.associatedParts = parts;
    }
    public static void deletePart(Part part){
        associatedParts.remove(part);
    }
    public static void updateProductPart(int index, Part part){
        associatedParts.set(index, part);
    }
    public static void resetProductParts(){
        associatedParts.clear();
    }
    
    //validation for product
    public static String errorMessage = "";
    public static String validator(String productName, double productPrice, int inStock, int min, int max, ObservableList<Part> parts){
    errorMessage = "";
    double partsSum = 0.00;;
    for (int i = 0; i < parts.size(); i++){
        partsSum += parts.get(i).getPartPrice();
        System.out.println(partsSum);
    }
    
    if (productName == null){
        errorMessage += "Part name required\n ";
    }
    if (productPrice <= 0){
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
    if (productPrice < partsSum){
        errorMessage += "Product price must be greater than the sum of the products";
    }
    return errorMessage;
    }
}


