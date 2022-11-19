package com.geanca;

import org.springframework.stereotype.Component;

@Component
public class ShoppingCart {
    public void checkout(String status){
        //logging
        //Authentication
        //Sanitize

        System.out.println("checkout method from Shopping cart called");
    }
    public int quantity(){
        return 2;
    }
}
