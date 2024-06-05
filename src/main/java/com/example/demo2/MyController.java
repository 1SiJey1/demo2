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
import documentRecordsLists.ListImplementationPurchasingRecords;
import documentRecordsLists.ListPurchasingRecords;
import documents.PurchasingDocument;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import products.ListImplementationProducts;
import products.ListProducts;
import products.Product;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    public Button documentCreate;
    public Button documentDelete;
    public Button directoryCreate;
    public Button directoryDelete;

    public void documentCreateClicked(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("purchasing-view.fxml"));
        try {
            Parent docView = loader.load();
            Scene docScene = new Scene(docView);
            //ProductController controller = loader.getController();
            //controller.initData(clientId);
            Stage stage3 = new Stage();
            stage3.setScene(docScene);
            stage3.setMaximized(true);
            stage3.setTitle("Новый документ (создание)");
            stage3.show();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    public void createButtonClicked(){
     
                if (Objects.equals(objectList.getSelectionModel().getSelectedItem().toString(), "Clients")) {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("client-view.fxml"));
                    try {
                        Parent clientView = loader.load();
                        Scene clientScene = new Scene(clientView);
                        //ProductController controller = loader.getController();
                        //controller.initData(clientId);
                        Stage stage3 = new Stage();
                        stage3.setScene(clientScene);
                        stage3.setMaximized(true);
                        stage3.setTitle("Новый клиент (создание)");
                        stage3.show();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }    
                } else if (Objects.equals(objectList.getSelectionModel().getSelectedItem().toString(), "Products")) {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("product-view.fxml"));
                    try {
                        Parent productView = loader.load();
                        Scene productScene = new Scene(productView);
                        //ProductController controller = loader.getController();
                        //controller.initData(clientId);
                        Stage stage3 = new Stage();
                        stage3.setScene(productScene);
                        stage3.setMaximized(true);
                        stage3.setTitle("Новый товар (создание)");
                        stage3.show();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
    }
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
                    objectList2.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            Integer clientId =  Integer.parseInt(objectList2.getSelectionModel().getSelectedItem().toString().replaceAll("[\\s.].*",""));
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("client-view.fxml"));
                            try {
                                Parent docView = loader.load();
                                Scene docScene = new Scene(docView);
                                ClientController controller = loader.getController();
                                controller.initData(clientId);
                                Stage stage2 = new Stage();
                                stage2.setScene(docScene);
                                stage2.setMaximized(true);
                                stage2.setTitle(objectList2.getSelectionModel().getSelectedItem().toString());
                                stage2.show();
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    });
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
                    objectList2.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            Integer clientId =  Integer.parseInt(objectList2.getSelectionModel().getSelectedItem().toString().replaceAll("[\\s.].*",""));
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("product-view.fxml"));
                            try {
                                Parent docView = loader.load();
                                Scene docScene = new Scene(docView);
                                ProductController controller = loader.getController();
                                controller.initData(clientId);
                                Stage stage3 = new Stage();
                                stage3.setScene(docScene);
                                stage3.setMaximized(true);
                                stage3.setTitle(objectList2.getSelectionModel().getSelectedItem().toString());
                                stage3.show();
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    });

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
                        resPurchasingRecords.getString(3), resPurchasingRecords.getDouble(4), resPurchasingRecords.getDouble(5)));

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
        List<String> itemsDocs = new ArrayList<>();
        for (PurchasingDocument purchasingDocument : resultPurchasingDocuments) {
            itemsDocs.add("Purchasing document № " + purchasingDocument.getId());
        }
        objectList.setItems(FXCollections.observableArrayList(itemsDocs));
        objectList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Integer docId =  Integer.parseInt(objectList.getSelectionModel().getSelectedItem().toString().replace("Purchasing document № ", ""));
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("purchasing-view.fxml"));
                try {
                    Parent docView = loader.load();
                    Scene docScene = new Scene(docView);
                    DocumentController controller = loader.getController();
                    controller.initData(docId);
                    Stage stage1 = new Stage();
                    stage1.setScene(docScene);
                    stage1.setMaximized(true);
                    stage1.setTitle(objectList.getSelectionModel().getSelectedItem().toString());
                    stage1.show();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}
