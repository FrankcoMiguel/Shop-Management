package model;

public class ServicesModel {

    private String id;
    private String name;
    private double cost;

    public ServicesModel(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }

    public ServicesModel(String id, String name, double cost) {
        this.id = id;
        this.name = name;
        this.cost = cost;
    }

    public ServicesModel() {
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
}
