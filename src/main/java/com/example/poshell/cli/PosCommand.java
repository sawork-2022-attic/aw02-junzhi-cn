package com.example.poshell.cli;

import com.example.poshell.biz.PosService;
import com.example.poshell.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class PosCommand {

    private PosService posService;

    @Autowired
    public void setPosService(PosService posService) {
        this.posService = posService;
    }

    @ShellMethod(value = "List Products", key = "p")
    public String products() {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        for (Product product : posService.products()) {
            stringBuilder.append("\t").append(++i).append("\t").append(product).append("\n");
        }
        return stringBuilder.toString();
    }

    @ShellMethod(value = "New Cart", key = "n")
    public String newCart() {
        return posService.newCart() + " OK";
    }

    @ShellMethod(value = "Add a Product to Cart", key = "a")
    public String addToCart(String productId, int amount) {
        if (posService.add(productId, amount)) {
            return posService.getCart().toString();
        }
        return "ERROR";
    }

    @ShellMethod(value = "Checkout", key = "c")
    public String checkout() {
        posService.checkout(posService.getCart());
        return "Checkout!\n" + order();
    }

    @ShellMethod(value = "Show Orders", key = "o")
    public String order() {
        return posService.getOrder().toString();
    }

    @ShellMethod(value = "Print Cart", key = "pc")
    public String print() {
        if (posService.getCart() == null)
            return "ERROR: No Cart!";
        return posService.getCart().toString();
    }

    @ShellMethod(value = "Remove Item by ProductId", key = "r")
    public String remove(String productId) {
        if (posService.remove(productId)) {
            return posService.getCart().toString();
        }
        return "ERROR";
    }
}
