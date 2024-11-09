package com.br.gabrielmartins.services;

import com.br.gabrielmartins.models.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService {

    private List<Product> products;

    public ProductService() {
        this.products = new ArrayList<>();
        loadProducts();
    }

    private void loadProducts() {
        products.add(new Product("Cosmético #1", 10.0));
        products.add(new Product("Cosmético #2", 15.0));
        products.add(new Product("Habilidade Especial #1", 20.0));
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Product> getProductsByCategory(String category) {
        List<Product> filteredProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getName().toLowerCase().contains(category.toLowerCase())) {
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }
}
