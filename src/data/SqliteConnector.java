package data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqliteConnector {

    private static Connection conne = null;

    private PreparedStatement ps;
    private Statement st;
    private ResultSet rs;

    private final String db_storage = "jdbc:sqlite:storage.sqlite";


    // Connection with database



    private Connection getConnection() throws SQLException, ClassNotFoundException {



        Class.forName("org.sqlite.JDBC");
        conne = DriverManager.getConnection(db_storage);
        return conne;

    }

    public void createAll() throws SQLException, ClassNotFoundException {

        if (conne == null){

            conne = getConnection();

        }

        String CREATE_ADMIN = "CREATE TABLE IF NOT EXISTS admin (id INTEGER PRIMARY KEY, name TEXT, dni TEXT, address TEXT, shop TEXT, phone TEXT, password TEXT);";
        String CREATE_CUSTOMER = "CREATE TABLE IF NOT EXISTS customer (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, dni TEXT, sex TEXT, address TEXT, phone TEXT);";
        String CREATE_EMPLOYEE = "CREATE TABLE IF NOT EXISTS employee (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, dni TEXT, sex TEXT, employment TEXT, address TEXT, phone TEXT);";
        String CREATE_PRODUCT = "CREATE TABLE IF NOT EXISTS product (id TEXT PRIMARY KEY, name TEXT, cost REAL, stock_in INTEGER, provider TEXT);";
        String CREATE_SERVICE = "CREATE TABLE IF NOT EXISTS service (id TEXT PRIMARY KEY, name TEXT, cost REAL);";
        String CREATE_BILL = "CREATE TABLE IF NOT EXISTS bill (id INTEGER PRIMARY KEY, customer TEXT, employee TEXT, total INTEGER, date TEXT);";

        String INSERT_DEFAULT_ADMIN= "INSERT INTO admin VALUES(?,?,?,?,?,?,?);";

        ps = conne.prepareStatement(CREATE_ADMIN);
        ps.executeUpdate();

        ps = conne.prepareStatement(CREATE_CUSTOMER);
        ps.executeUpdate();

        ps = conne.prepareStatement(CREATE_EMPLOYEE);
        ps.executeUpdate();

        ps = conne.prepareStatement(CREATE_PRODUCT);
        ps.executeUpdate();

        ps = conne.prepareStatement(CREATE_SERVICE);
        ps.executeUpdate();

        ps = conne.prepareStatement(CREATE_BILL);
        ps.executeUpdate();

        ps = conne.prepareStatement(INSERT_DEFAULT_ADMIN);
        ps.setInt(1,1);
        ps.setString(2,"Default");
        ps.setString(3,"Default");
        ps.setString(4,"Default");
        ps.setString(5,"Mi Tienda");
        ps.setString(6,"Default");
        ps.setString(7,"admin");
        ps.executeUpdate();



    }


    // INSERT ADMIN DATA

    public void updateAdmin(AdminModel adminModel) throws SQLException, ClassNotFoundException {

        if (conne == null){

            conne = getConnection();

        }

        String DELETE_ADMIN = "DELETE FROM admin WHERE id = 1;";
        String UPDATE_ADMIN = "INSERT INTO admin VALUES(?,?,?,?,?,?,?);";

        ps = conne.prepareStatement(DELETE_ADMIN);
        ps.executeUpdate();

        ps = conne.prepareStatement(UPDATE_ADMIN);
        ps.setInt(1,1);
        ps.setString(2,adminModel.getName());
        ps.setString(3,adminModel.getDni());
        ps.setString(4,adminModel.getAddress());
        ps.setString(5,adminModel.getShop());
        ps.setString(6,adminModel.getPhone());
        ps.setString(7,adminModel.getPassword());
        ps.executeUpdate();

    }



    // SELECT ADMIN PASSWORD

    public void selectAdmin(AdminModel adminModel) throws SQLException, ClassNotFoundException {

        if (conne == null) {

            conne = getConnection();

        }

        Statement st1 = conne.createStatement();
        ResultSet rs1 = st1.executeQuery("SELECT * FROM admin;");

        while (rs1.next()) {

            adminModel.setName(rs1.getString("name"));
            adminModel.setDni(rs1.getString("dni"));
            adminModel.setAddress(rs1.getString("address"));
            adminModel.setShop(rs1.getString("shop"));
            adminModel.setPhone(rs1.getString("phone"));
            adminModel.setPassword(rs1.getString("password"));


        }


    }


    // INSERT AND SELECT CUSTOMER DATA

    public void insertCustomer(CustomerModel customerModel) throws SQLException, ClassNotFoundException {


        if (conne == null){

            conne = getConnection();

        }

        String INSERT_CUSTOMER = "INSERT INTO customer VALUES (?,?,?,?,?,?);";

        ps = conne.prepareStatement(INSERT_CUSTOMER);

        ps.setString(1,null);
        ps.setString(2,customerModel.getName());
        ps.setString(3,customerModel.getDni());
        ps.setString(4,customerModel.getSex());
        ps.setString(5,customerModel.getAddress());
        ps.setString(6,customerModel.getPhone());
        ps.executeUpdate();

    }

    public ObservableList<EmployeeModel> selectEmployee(ObservableList<EmployeeModel> employeeModel) throws SQLException, ClassNotFoundException {


        if (conne == null){

            conne = getConnection();

        }

        String INSERT_EMPLOYEE = "SELECT * FROM employee;";


        Statement st = conne.createStatement();
        ResultSet rs = st.executeQuery(INSERT_EMPLOYEE);



        while (rs.next()) {

            EmployeeModel employee = new EmployeeModel();

            employee.setName(rs.getString("name"));
            employee.setSex(rs.getString("sex"));
            employee.setEmployment(rs.getString("employment"));

            employeeModel.add(employee);

        }

        return employeeModel;

    }



    public ObservableList<String> selectCustomerName(ObservableList<String> names) throws SQLException, ClassNotFoundException {


        if (conne == null){

            conne = getConnection();

        }

        String INSERT_EMPLOYEE = "SELECT * FROM customer;";


        Statement st = conne.createStatement();
        ResultSet rs = st.executeQuery(INSERT_EMPLOYEE);



        while (rs.next()) {

            String name;

            name = rs.getString("name");

            names.add(name);

        }

        return names;

    }

    public ObservableList<Integer> selectCustomerCodes(ObservableList<Integer> customer_codes) throws SQLException, ClassNotFoundException {

        if (conne == null){

            conne = getConnection();

        }

        String INSERT_EMPLOYEE = "SELECT id FROM customer;";


        Statement st = conne.createStatement();
        ResultSet rs = st.executeQuery(INSERT_EMPLOYEE);



        while (rs.next()) {

            int code;

            code = rs.getInt("id");

            customer_codes.add(code);

        }

        return customer_codes;

    }

    public ObservableList<String> selectProductCodes(ObservableList<String> product_codes) throws SQLException, ClassNotFoundException {

        if (conne == null){

            conne = getConnection();

        }

        String SELECT_CUSTOMER = "SELECT id FROM product;";


        Statement st = conne.createStatement();
        ResultSet rs = st.executeQuery(SELECT_CUSTOMER);



        while (rs.next()) {

            String code;

            code = rs.getString("id");

            product_codes.add(code);

        }

        return product_codes;

    }

    public ObservableList<String> selectEmployeeName(ObservableList<String> names) throws SQLException, ClassNotFoundException {


        if (conne == null){

            conne = getConnection();

        }

        String INSERT_EMPLOYEE = "SELECT * FROM employee WHERE employment = 'Cajero' OR employment = 'Cajera';";


        Statement st = conne.createStatement();
        ResultSet rs = st.executeQuery(INSERT_EMPLOYEE);



        while (rs.next()) {

            String name;

            name = rs.getString("name");

            names.add(name);

        }

        return names;

    }

    public void updateCustomer(String column, String change, String id) throws SQLException, ClassNotFoundException {

        if (conne == null){

            conne = getConnection();

        }

        String UPDATE_CUSTOMER = "UPDATE customer SET "+(column)+ " = '"+(change)+"' WHERE id = "+(id)+";";
        ps = conne.prepareStatement(UPDATE_CUSTOMER);
        ps.executeUpdate();



    }

    public void deleteCustomer(String id) throws SQLException, ClassNotFoundException {

        if (conne == null){

            conne = getConnection();

        }

        String DELETE_CUSTOMER = "DELETE FROM customer WHERE id = "+(id)+";";
        ps = conne.prepareStatement(DELETE_CUSTOMER);
        ps.executeUpdate();


    }

    public void deleteProduct(String id) throws SQLException, ClassNotFoundException {

        if (conne == null){

            conne = getConnection();

        }

        String DELETE_CUSTOMER = "DELETE FROM product WHERE id = '"+(id)+"';";
        ps = conne.prepareStatement(DELETE_CUSTOMER);
        ps.executeUpdate();


    }

    // INSERT AND SELECT EMPLOYEE DATA

    public void insertEmployee(EmployeeModel employeeModel) throws SQLException, ClassNotFoundException {


        if (conne == null){

            conne = getConnection();

        }

        String INSERT_EMPLOYEE = "INSERT INTO employee VALUES (?,?,?,?,?,?,?);";

        ps = conne.prepareStatement(INSERT_EMPLOYEE);

        ps.setString(1,null);
        ps.setString(2,employeeModel.getName());
        ps.setString(3,employeeModel.getDni());
        ps.setString(4,employeeModel.getSex());
        ps.setString(5,employeeModel.getEmployment());
        ps.setString(6,employeeModel.getAddress());
        ps.setString(7,employeeModel.getPhone());

        ps.executeUpdate();

    }

    public ObservableList<ProductModel> selectProduct(ObservableList<ProductModel> productModel) throws SQLException, ClassNotFoundException {


        if (conne == null){

            conne = getConnection();

        }

        String INSERT_CUSTOMER = "SELECT * FROM product;";


        Statement st = conne.createStatement();
        ResultSet rs = st.executeQuery(INSERT_CUSTOMER);



        while (rs.next()) {

            ProductModel product = new ProductModel();

            product.setId(rs.getString("id"));
            product.setName(rs.getString("name"));
            product.setCost(rs.getDouble("cost"));
            product.setStock_in(rs.getInt("stock_in"));
            product.setProvider(rs.getString("provider"));

            productModel.add(product);

        }

        return productModel;

    }


    // INSERT AND SELECT PRODUCT DATA

    public void insertProduct(ProductModel productModel) throws SQLException, ClassNotFoundException {


        if (conne == null){

            conne = getConnection();

        }

        String INSERT_PRODUCT = "INSERT INTO product VALUES (?,?,?,?,?);";

        ps = conne.prepareStatement(INSERT_PRODUCT);

        ps.setString(1,productModel.getId());
        ps.setString(2,productModel.getName());
        ps.setDouble(3,productModel.getCost());
        ps.setInt(4,productModel.getStock_in());
        ps.setString(5,productModel.getProvider());

        ps.executeUpdate();

    }

    public ObservableList<CustomerModel> selectCustomer(ObservableList<CustomerModel> customerModel) throws SQLException, ClassNotFoundException {


        if (conne == null){

            conne = getConnection();

        }

        String INSERT_CUSTOMER = "SELECT * FROM customer;";


        Statement st = conne.createStatement();
        ResultSet rs = st.executeQuery(INSERT_CUSTOMER);



        while (rs.next()) {

            CustomerModel customer = new CustomerModel();

            customer.setId(rs.getInt("id"));
            customer.setName(rs.getString("name"));
            customer.setDni(rs.getString("dni"));
            customer.setSex(rs.getString("sex"));
            customer.setAddress(rs.getString("address"));
            customer.setPhone(rs.getString("phone"));

            customerModel.add(customer);

        }

        return customerModel;

    }

    public void updateProduct(String column, String change, String id) throws SQLException, ClassNotFoundException {

        if (conne == null){

            conne = getConnection();

        }

        String UPDATE_CUSTOMER = "UPDATE product SET "+(column)+ " = '"+(change)+"' WHERE id = '"+(id)+"';";
        ps = conne.prepareStatement(UPDATE_CUSTOMER);
        ps.executeUpdate();



    }

    public BillModel searchProduct(BillModel bill, String code) throws SQLException, ClassNotFoundException {

        if (conne == null){

            conne = getConnection();

        }

        String SELECT_BILLING = "SELECT name, cost FROM product WHERE id = '" +code+ "' UNION SELECT name, cost FROM service WHERE id = '" + code + "' ;";


        Statement st = conne.createStatement();
        ResultSet rs = st.executeQuery(SELECT_BILLING);

        while (rs.next()){

            bill.setName(rs.getString("name"));
            bill.setCost(rs.getDouble("cost"));

        }

      return bill;

    }

    public ObservableList<ProductModel> selectCodes(ObservableList<ProductModel> productModels) throws SQLException, ClassNotFoundException {

        if (conne == null){

            conne = getConnection();

        }

        String SELECT_BILLING = "SELECT id, name FROM product UNION SELECT id, name FROM service;";


        Statement st = conne.createStatement();
        ResultSet rs = st.executeQuery(SELECT_BILLING);

        while (rs.next()){

            ProductModel product = new ProductModel();

            product.setId(rs.getString("id"));
            product.setName(rs.getString("name"));

            productModels.add(product);

        }

        return productModels;

    }

    public String countProduct() throws SQLException {


        String LAST_PRODUCT = "SELECT * FROM product ORDER BY Id DESC LIMIT 1";

        Statement st = conne.createStatement();
        ResultSet rs = st.executeQuery(LAST_PRODUCT);
        String number = null;

        if (!rs.next()){

            number = "1";

        } else {

            number = rs.getString("id");
            char f = number.charAt(number.length() - 1);
            String s = String.valueOf(f);
            int l = Integer.parseInt(s) + 1;

            number = String.valueOf(l);

        }


        return number;

    }

    public String countService() throws SQLException {


        String LAST_PRODUCT = "SELECT * FROM service ORDER BY Id DESC LIMIT 1";

        Statement st = conne.createStatement();
        ResultSet rs = st.executeQuery(LAST_PRODUCT);
        String number = null;

        if (!rs.next()){

            number = "1";

        } else {

            number = rs.getString("id");
            char f = number.charAt(number.length() - 1);
            String s = String.valueOf(f);
            int l = Integer.parseInt(s) + 1;

            number = String.valueOf(l);

        }


        return number;

    }

    // INSERT AND SELECT SERVICE DATA

    public void insertService(ServicesModel servicesModel) throws SQLException, ClassNotFoundException {


        if (conne == null){

            conne = getConnection();

        }

        String INSERT_SERVICE = "INSERT INTO service VALUES (?,?,?);";

        ps = conne.prepareStatement(INSERT_SERVICE);

        ps.setString(1,servicesModel.getId());
        ps.setString(2,servicesModel.getName());
        ps.setDouble(3,servicesModel.getCost());

        ps.executeUpdate();

    }

    public ObservableList<ServicesModel> selectService(ObservableList<ServicesModel> servicesModel) throws SQLException, ClassNotFoundException {


        if (conne == null){

            conne = getConnection();

        }

        String INSERT_CUSTOMER = "SELECT * FROM service;";


        Statement st = conne.createStatement();
        ResultSet rs = st.executeQuery(INSERT_CUSTOMER);



        while (rs.next()) {

            ServicesModel service = new ServicesModel();

            service.setId(rs.getString("id"));
            service.setName(rs.getString("name"));
            service.setCost(rs.getDouble("cost"));

            servicesModel.add(service);

        }

        return servicesModel;

    }


}
