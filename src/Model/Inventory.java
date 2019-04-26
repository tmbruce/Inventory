
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

    public static int producIDgen(){
        numProducts++;
        return numProducts;
    }
    
    public static ObservableList<Product> getProducts(){
        return productList;
    }
    
    
}
