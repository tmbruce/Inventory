/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.MainScreenController;

/**
 *
 * @author travi
 */
public class outSourcedThreader implements Runnable{
    
    public outSourcedThreader(Part part){
        for (Product product : Inventory.getProducts()){
            int index = product.getProductParts().indexOf(MainScreenController.partModel());
            product.updateProductPart(index, part);
        }
    }
    
    @Override
    public void run() {
    }
    
}
