package com.example.poshell.biz;

import com.example.poshell.db.PosDB;
import com.example.poshell.model.Cart;
import com.example.poshell.model.Item;
import com.example.poshell.model.Order;
import com.example.poshell.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

@Component
public class PosServiceImp implements PosService {

    private PosDB posDB;

    @Autowired
    public void setPosDB(PosDB posDB) {
        this.posDB = posDB;
    }

    @Override
    public Cart getCart() {
        return posDB.getCart();
    }

    @Override
    public Cart newCart() {
        return posDB.saveCart(new Cart());
    }

    @Override
    public void checkout(Cart cart) {
        posDB.checkout(cart);
        newCart();
    }

    @Override
    public Order getOrder(){
        return posDB.getOrder();
    }

    @Override
    public boolean add(Product product, int amount) {
        return false;
    }

    @Override
    public boolean add(String productId, int amount) {

        Product product = posDB.getProduct(productId);
        if (product == null) return false;

        this.getCart().addItem(new Item(product, amount));
        return true;
    }

    @Override
    public boolean remove(String productId){
        if(getCart()==null)return false;
        Iterator<Item> iterator=getCart().getItems().iterator();
        while(iterator.hasNext()){
            Item item=iterator.next();
            if(item.getProduct().getId().equals(productId)){
                iterator.remove();
            }
        }
        return true;
    }


    @Override
    public List<Product> products() {
        return posDB.getProducts();
    }
}
