package model;

public class AdminModel {

    private String name;
    private String dni;
    private String address;
    private String shop;
    private String phone;
    private String password;

    public AdminModel(String name, String dni, String address, String shop, String phone, String password) {
        this.name = name;
        this.dni = dni;
        this.address = address;
        this.shop = shop;
        this.phone = phone;
        this.password = password;
    }

    public AdminModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
