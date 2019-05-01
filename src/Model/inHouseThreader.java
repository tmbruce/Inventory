
package Model;

import Controller.MainScreenController;

/**
 *
 * @author travi
 */

public class inHouseThreader implements Runnable{

    public inHouseThreader(Part part) {
        for (Product product : Inventory.getProducts()){
            int index = product.getProductParts().indexOf(MainScreenController.partModel());
            product.updateProductPart(index, part);
                
            
        }
    }

    @Override
    public void run() {
    }
    
}