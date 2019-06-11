package model;

public class ProductModel {

    private String id;
    private String name;
    private double cost;
    private int stock_in;
    private String provider;

    public ProductModel(String name, double cost, int stock_in, String provider) {
        this.name = name;
        this.cost = cost;
        this.stock_in = stock_in;
        this.provider = provider;
    }

    public ProductModel(String id, String name, double cost, int stock_in, String provider) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.stock_in = stock_in;
        this.provider = provider;
    }

    public ProductModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getStock_in() {
        return stock_in;
    }

    public void setStock_in(int stock_in) {
        this.stock_in = stock_in;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }
}
