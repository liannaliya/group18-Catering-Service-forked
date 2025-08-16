package com.oop.groupeighteen.group18cateringservice.rahul;

public class MenuItem {
    private String name;
    private String type;
    private String ingredients;
    private double price;
    private String dietaryTags;

    public MenuItem(String name, String type, String ingredients, double price, String dietaryTags) {
        this.name = name;
        this.type = type;
        this.ingredients = ingredients;
        this.price = price;
        this.dietaryTags = dietaryTags;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDietaryTags() {
        return dietaryTags;
    }

    public void setDietaryTags(String dietaryTags) {
        this.dietaryTags = dietaryTags;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", price=" + price +
                ", dietaryTags='" + dietaryTags + '\'' +
                '}';
    }
}
