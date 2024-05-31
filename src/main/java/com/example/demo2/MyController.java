package com.example.demo2;

import clients.Client;
import clients.ListClients;
import clients.ListImplementationClients;
import creations.ClientCreationService;
import creations.ProductCreationService;
import creations.PurchasingCreationService;
import creations.PurchasingDocumentCreationService;
import documentLists.ListImplementationPurchasingDocuments;
import documentLists.ListPurchasingDocuments;
import documentRecords.PurchasingRecord;
import documentRecordsLists.ListImplementationPurchasingRecords;
import documentRecordsLists.ListPurchasingRecords;
import documents.DefaultPurchasingDocument;
import documents.PurchasingDocument;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import products.ListImplementationProducts;
import products.ListProducts;
import products.Product;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class MyController {
    public ListView objectList;
    public ListView objectList2;

    public TableView tableRecords;
    public TableColumn columnProduct;
    public TableColumn columnAmount;
    public TableColumn columnPrice;
    public TableColumn columnSum;
    public TextField docNumber;
    public TextField docDate;

    public TextField docClient;

    static final String userName = "postgres";
    static final String  password = "1";
    static final String connectionUrl = "jdbc:postgresql://127.0.0.1:5432/trade";


    public void newListDirectories(){
        objectList.refresh();
        ObservableList<String> itemsDirectories = FXCollections.observableArrayList ("Clients", "Products");
        objectList.setItems(itemsDirectories);
        objectList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (Objects.equals(objectList.getSelectionModel().getSelectedItem().toString(), "Clients")) {
                    ListClients resultClients = new ListImplementationClients();
                    try (Connection connection = DriverManager.getConnection(connectionUrl, userName, password);
                         Statement statement = connection.createStatement()) {
                        String SelectClients = "select * from clients";
                        ResultSet resClients = statement.executeQuery(SelectClients);

                        while (resClients.next()) {
                            resultClients.addClient(ClientCreationService.getInstance().createClient(resClients.getInt(1), resClients.getString(2), resClients.getString(3), resClients.getString(4), resClients.getString(5), resClients.getString(6), resClients.getString(7)));
                        }
                    } catch (
                            SQLException e) {
                        throw new RuntimeException(e);
                    }
                    List<String> itemsClient = new ArrayList<>();
                    for (Client client : resultClients){
                            itemsClient.add(client.getId() + ". " + client.getName());
                        }
                        objectList2.setItems(FXCollections.observableArrayList(itemsClient));
                } else if (Objects.equals(objectList.getSelectionModel().getSelectedItem().toString(), "Products")) {
                    ListProducts resultProducts = new ListImplementationProducts();
                    try (Connection connection = DriverManager.getConnection(connectionUrl, userName, password);
                         Statement statement = connection.createStatement()) {
                        String SelectProducts = "select * from products";

                        ResultSet resProducts = statement.executeQuery(SelectProducts);

                        while (resProducts.next())

                        {
                            resultProducts.addProduct(ProductCreationService.getInstance().createProduct(resProducts.getInt(1), resProducts.getString(2), resProducts.getString(3), resProducts.getString(4)));
                        }
                    } catch (
                            SQLException e) {
                        throw new RuntimeException(e);
                    }
                    List<String> itemsProduct = new ArrayList<>();
                    for (Product product : resultProducts){
                        itemsProduct.add(product.getId() + ". " + product.getName());
                    }
                    objectList2.setItems(FXCollections.observableArrayList(itemsProduct));
                }
            }
        });
    }
    public void newListPurchasing() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        ListPurchasingDocuments resultPurchasingDocuments = new ListImplementationPurchasingDocuments();
        ListPurchasingRecords resultPurchasingRecords = new ListImplementationPurchasingRecords();
        ListClients resultClients = new ListImplementationClients();

        try (Connection connection = DriverManager.getConnection(connectionUrl, userName, password);

             Statement statement = connection.createStatement()) {
            String SelectPurchasingRecords = "SELECT * FROM purchasing_records";
            ResultSet resPurchasingRecords = statement.executeQuery(SelectPurchasingRecords);
            while (resPurchasingRecords.next()) {

                resultPurchasingRecords.addPurchasingRecord(PurchasingCreationService.getInstance().createPurchasing(resPurchasingRecords.getInt(1), resPurchasingRecords.getInt(2),
                        resPurchasingRecords.getString(3), resPurchasingRecords.getDouble(4), resPurchasingRecords.getDouble(4)));

            }

            String SelectClients = "select * from clients";
            ResultSet resClients = statement.executeQuery(SelectClients);

            while (resClients.next()) {
                resultClients.addClient(ClientCreationService.getInstance().createClient(resClients.getInt(1), resClients.getString(2), resClients.getString(3), resClients.getString(4), resClients.getString(5), resClients.getString(6), resClients.getString(7)));
            }

            String SelectPurchasingDocuments = "SELECT * FROM purchasing_documents";
            ResultSet resPurchasingDocuments = statement.executeQuery(SelectPurchasingDocuments);

            while (resPurchasingDocuments.next()) {

                resultPurchasingDocuments.addPurchasingDocument(PurchasingDocumentCreationService.getInstance().createPurchasingDocument(resPurchasingDocuments.getInt(1),
                        LocalDate.parse(resPurchasingDocuments.getString(4), formatter), resultClients.getClientById(resPurchasingDocuments.getInt(5)), resultPurchasingRecords));

            }
            statement.close();

        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Table Purchasing documents\n");
        System.out.println(resultPurchasingDocuments);
        objectList.refresh();
        objectList2.getItems().clear();
        for (PurchasingDocument purchasingDocument : resultPurchasingDocuments) {
            objectList.setItems(FXCollections.observableArrayList("Purchasing document № " + purchasingDocument.getId()));
        }
        objectList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Integer docId =  Integer.parseInt(objectList.getSelectionModel().getSelectedItem().toString().replace("Purchasing document № ", ""));
                DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                ListPurchasingRecords resultPurchasingRecord = new ListImplementationPurchasingRecords();
                ListClients resultClients = new ListImplementationClients();
                try (Connection connection = DriverManager.getConnection(connectionUrl, userName, password);

                     Statement statement = connection.createStatement()) {
                    String SelectPurchasingRecord = "SELECT * FROM purchasing_records WHERE document_id = '" + docId + "'";
                    ResultSet resPurchasingRecord = statement.executeQuery(SelectPurchasingRecord);
                    while (resPurchasingRecord.next()) {

                        resultPurchasingRecord.addPurchasingRecord(PurchasingCreationService.getInstance().createPurchasing(resPurchasingRecord.getInt(1), resPurchasingRecord.getInt(2),
                                resPurchasingRecord.getString(3), resPurchasingRecord.getDouble(4), resPurchasingRecord.getDouble(4)));

                    }

                    String SelectClients = "select * from clients";
                    ResultSet resClients = statement.executeQuery(SelectClients);

                    while (resClients.next()) {
                        resultClients.addClient(ClientCreationService.getInstance().createClient(resClients.getInt(1), resClients.getString(2), resClients.getString(3), resClients.getString(4), resClients.getString(5), resClients.getString(6), resClients.getString(7)));
                    }

                    String SelectPurchasingDocument = "SELECT * FROM purchasing_documents WHERE id = '" + docId + "'";
                    ResultSet resPurchasingDocument = statement.executeQuery(SelectPurchasingDocument);

                    while (resPurchasingDocument.next()) {

                        PurchasingDocument purDoc = PurchasingDocumentCreationService.getInstance().createPurchasingDocument(resPurchasingDocument.getInt(1),
                                LocalDate.parse(resPurchasingDocument.getString(4), formatter1), resultClients.getClientById(resPurchasingDocument.getInt(5)), resultPurchasingRecord);
                        docNumber.setText(purDoc.getId().toString());
                        docDate.setText(purDoc.getDate().toString());
                        docClient.setText(purDoc.getClient().getName());
                    }
                    statement.close();

                } catch (
                        SQLException e) {
                    throw new RuntimeException(e);
                }
                /*List<String> itemsProduct = new ArrayList<>();
                for (PurchasingRecord purchasingRecord : resultPurchasingRecord){
                    itemsProduct.add(purchasingRecord.getProductName(), purchasingRecord.getAmount());
                }*/
                tableRecords = new TableView<>(FXCollections.observableArrayList(resultPurchasingRecord));
                columnProduct.setCellFactory(new PropertyValueFactory<>("product_name"));
                columnAmount.setCellFactory(new PropertyValueFactory<>("amount"));
                columnPrice.setCellFactory(new PropertyValueFactory<>("price"));

                tableRecords.getColumns().add(columnProduct);
                tableRecords.getColumns().add(columnAmount);
                tableRecords.getColumns().add(columnPrice);

                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("app-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 900, 600);
                // stage.setTitle("Hello!");
                stage.setScene(scene);
                stage.show();
            }
        });
    }
}
