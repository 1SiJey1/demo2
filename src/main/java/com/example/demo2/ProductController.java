package com.example.demo2;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import clients.Client;
import creations.ClientCreationService;
import creations.ProductCreationService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import products.Product;

public class ProductController {

    public Button productInsert;
    public Button productUpdate;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField productArticle;

    @FXML
    private TextField productFullname;

    @FXML
    private TextField productId;

    @FXML
    private TextField productName;

    static final String userName = "postgres";
    static final String password = "1";
    static final String connectionUrl = "jdbc:postgresql://127.0.0.1:5432/trade";

    @FXML
    void initialize() {


    }

    void initData(Integer id){
        try (Connection connection = DriverManager.getConnection(connectionUrl, userName, password);
             Statement statement = connection.createStatement()) {
            String SelectPurchasingRecord = "SELECT * FROM products WHERE id = '" + id + "'";
            ResultSet resPurchasingRecord = statement.executeQuery(SelectPurchasingRecord);
            while (resPurchasingRecord.next()) {

                Product product = ProductCreationService.getInstance().createProduct(resPurchasingRecord.getInt(1), resPurchasingRecord.getString(2),
                        resPurchasingRecord.getString(3), resPurchasingRecord.getString(4));
                productId.setText(product.getId().toString());
                productName.setText(product.getName());
                productFullname.setText(product.getFullName());
                productArticle.setText(product.getArticle());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertButton(){
        try (Connection connection = DriverManager.getConnection(connectionUrl, userName, password);
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("INSERT INTO products (id, name, fullname, article) VALUES ('" +Integer.parseInt(productId.getText())+ "', '" +productName.getText()+ "', '" + productFullname.getText() + "', '" + productArticle.getText() + "')");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) productInsert.getScene().getWindow();
        stage.close();
    }

}
