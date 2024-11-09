package com.br.gabrielmartins.services;

import com.br.gabrielmartins.models.Product;
import java.util.HashMap;
import java.util.Map;

public class ProductService {

    private Map<String, Product> products;

    public ProductService() {
        products = new HashMap<>();
        products.put("Kit Iniciante", new Product("Kit Iniciante", "Um kit básico para começar", 20.0, "Iniciante", "Item", "Ganha Kit"));
        products.put("Kit VIP", new Product("Kit VIP", "Um kit especial para membros VIP", 50.0, "VIP", "Item", "Ganha Kit"));
        products.put("VIP Plus", new Product("VIP Plus", "Acesso VIP Plus com benefícios exclusivos", 100.0, "VIP", "Permissão", "Ganha Acesso"));
    }

    public Product getProductByName(String name) {
        return products.get(name);
    }

    public Map<String, Product> getAllProducts() {
        return products;
    }
}
