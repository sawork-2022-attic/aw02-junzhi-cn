package com.example.poshell.model;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Order {
    private List<Cart> carts=new ArrayList<>();

    public boolean addCart(Cart cart){
        return carts.add(cart);
    }

    @Override
    public String toString(){
        if(carts.size()==0){
            return "No orders.";
        }
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("__________Orders__________\n");
        for(Cart cart:getCarts()){
            stringBuilder.append("\n"+cart.toString()+"\n");
        }
        return stringBuilder.toString();
    }
}
