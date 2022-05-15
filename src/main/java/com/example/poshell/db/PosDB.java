package com.example.poshell.db;

import com.example.poshell.model.Cart;
import com.example.poshell.model.Order;
import com.example.poshell.model.Product;

import java.util.List;

public interface PosDB {

    public List<Product> getProducts();

    public Cart saveCart(Cart cart);

    public Cart getCart();

    public Order getOrder();

    public Product getProduct(String productId);

    public void checkout(Cart cart);

}
