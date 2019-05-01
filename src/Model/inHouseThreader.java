
package Model;

import Controller.MainScreenController;

/**
 *
 * @author travi
 */

public class inHouseThreader implements Runnable{

    public inHouseThreader(Part part) {
        for (Product product : Inventory.getProducts()){
            for (int i = 0; i < product.getProductParts().size(); i++){
                if (product.getProductParts().get(i).equals(MainScreenController.partModel())){
                    int index = product.getProductParts().indexOf(i);
                    product.updateProductPart(i, part);
                }
            }
        }
    }

    @Override
    public void run() {
    }
    
}