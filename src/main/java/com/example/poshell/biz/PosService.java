package com.example.poshell.biz;

import com.example.poshell.model.Cart;
import com.example.poshell.model.Order;
import com.example.poshell.model.Product;

import java.util.List;

public interface PosService {
    public Cart getCart();

    public Cart newCart();

    public void checkout(Cart cart);

    public Order getOrder();

    public boolean add(Product product, int amount);

    public boolean add(String productId, int amount);

    public boolean remove(String productId);


    public List<Product> products();
}
