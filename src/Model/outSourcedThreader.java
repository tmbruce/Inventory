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
