package com.example.demo2;

import creations.ProductCreationService;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import products.ListImplementationProducts;
import products.ListProducts;
import products.Product;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class StringController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button stringAdd;

    @FXML
    private TextField stringAmount;

    @FXML
    private TextField stringPrice;

    @FXML
    private TextField stringProduct;

    @FXML
    private TextField stringSum;

    private String productName;
    private Integer productId;
    static final String userName = "postgres";
    static final String password = "1";
    static final String connectionUrl = "jdbc:postgresql://127.0.0.1:5432/trade";


    @FXML
    void initialize() {


    }

    public void onProductClick() {

        ListProducts resultProducts = new ListImplementationProducts();
        try (Connection connection = DriverManager.getConnection(connectionUrl, userName, password);
             Statement statement = connection.createStatement()) {
            String SelectProducts = "select * from products";

            ResultSet resProducts = statement.executeQuery(SelectProducts);

            while (resProducts.next()) {
                resultProducts.addProduct(ProductCreationService.getInstance().createProduct(resProducts.getInt(1), resProducts.getString(2), resProducts.getString(3), resProducts.getString(4)));
            }
        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
        List<String> itemsProduct = new ArrayList<>();
        for (Product product : resultProducts) {
            itemsProduct.add(product.getId() + ". " + product.getName() + ". " + product.getFullName());
        }
        AnchorPane products = new AnchorPane();
        ListView list = new ListView(FXCollections.observableArrayList(itemsProduct));
        products.getChildren().add(list);
        Scene prodScene = new Scene(products, 600, 400);
        //ProductController controller = loader.getController();
        //controller.initData(clientId);
        Stage stage3 = new Stage();
        stage3.setScene(prodScene);
        stage3.setMaximized(true);
        stage3.setTitle("Выбор товара");
        stage3.show();
        list.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String[] productInfo = (list.getSelectionModel().getSelectedItem().toString().split(". "));
                productId = Integer.parseInt(productInfo[0]);
                productName = productInfo[1];
                stringProduct.setText(productName);
                stage3.close();
            }
        });
    }

    public void addPurchasingRecord() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("purchasing-view.fxml"));
        loader.load();
        //Scene docScene = new Scene(docView);
        DocumentController controller = loader.getController();
        controller.initTable(new DefaultPurchasingRecord(0, productId, productName, Double.parseDouble(stringAmount.getText()), Double.parseDouble(stringPrice.getText())));
        //Stage stage3 = new Stage();
        //stage3.setScene(docScene);
        //stage3.setMaximized(true);
        //stage3.setTitle("Новый документ (создание)");
        //stage3.show();
        Stage stagetmp = (Stage) stringAdd.getScene().getWindow();
        stagetmp.close();


        }


}

