package com.br.gabrielmartins.models;

public class Product {
    private String name;
    private String description;
    private double price;
    private String category;
    private String resourceType;
    private String action;

    public Product(String name, String description, double price, String category, String resourceType, String action) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.resourceType = resourceType;
        this.action = action;
    }

    public Product(String name, double v) {
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public String getResourceType() {
        return resourceType;
    }

    public String getAction() {
        return action;
    }
}
