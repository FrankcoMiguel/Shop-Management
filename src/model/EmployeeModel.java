package model;

public class EmployeeModel {

    private int id;
    private String name;
    private String dni;
    private String sex;
    private String employment;
    private String address;
    private String phone;

    public EmployeeModel(String name, String dni, String sex, String employment, String address, String phone) {
        this.name = name;
        this.dni = dni;
        this.sex = sex;
        this.employment = employment;
        this.address = address;
        this.phone = phone;
    }

    public EmployeeModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmployment() {
        return employment;
    }

    public void setEmployment(String employment) {
        this.employment = employment;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
