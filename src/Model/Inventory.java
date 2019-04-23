
package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 *
 * @author tbruce
 */
public class Inventory {
    private static ObservableList<Part> partsList = FXCollections.observableArrayList();
    private static int numParts = 0;
    
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
//Same as above for products list
    
    
}
