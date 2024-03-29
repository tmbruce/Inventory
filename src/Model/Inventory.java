
package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 *
 * @author tbruce
 */
public class Inventory {
    private static ObservableList<Product> productList = FXCollections.observableArrayList();
    private static ObservableList<Part> partsList = FXCollections.observableArrayList();
    private static ObservableList<Product> partsOfProducts = FXCollections.observableArrayList();
    private static int numParts = 0;
    private static int numProducts = 0;
    
    public static int partIDgen(){
        numParts++;
        return numParts;
    }
    public static ObservableList<Part> getParts(){
        return partsList;
    }
    public static void addPart(Part part){
        partsList.add(part);
    }
    public static void deletePart(Part part){
        partsList.remove(part);
    }
    public static void updateParts(int index, Part part){
        partsList.set(index, part);
    }
    //Function added per requirements, not used in implementation
    public static Part lookupPart(int index){
        return partsList.get(index);
    }
    public static int producIDgen(){
        numProducts++;
        return numProducts;
    }
    public static void addProduct(Product product){
        productList.add(product);
    }
    public static void removeProduct(Product product){
        productList.remove(product);
    }
    public static ObservableList<Product> getProducts(){
        return productList;
    }
    public static void updateProduct(int index, Product product){
        productList.set(index, product);
    }
    //Function added per requirements, not used in implementation
    public static Product lookupProduct(int index){
        return productList.get(index);
    } 
    
}