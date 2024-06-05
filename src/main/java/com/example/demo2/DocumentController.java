package com.example.demo2;

import clients.ListClients;
import clients.ListImplementationClients;
import creations.ClientCreationService;
import creations.PurchasingCreationService;
import creations.PurchasingDocumentCreationService;
import documentRecords.PurchasingRecord;
import documentRecordsLists.ListImplementationPurchasingRecords;
import documentRecordsLists.ListPurchasingRecords;
import documents.PurchasingDocument;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DocumentController {

    public Button docInsert;
    public Button addString;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<PurchasingRecord,Double> columnAmount;

    @FXML
    private TableColumn<PurchasingRecord,Integer> columnNumber;

    @FXML
    private TableColumn<PurchasingRecord,Double> columnPrice;

    @FXML
    private TableColumn<PurchasingRecord,String> columnProduct;

    @FXML
    private TableColumn<PurchasingRecord,Integer> columnSum;

    @FXML
    private TextField docClient;

    @FXML
    private TextField docDate;

    @FXML
    private TextField docNumber;

    @FXML
    private TableView<PurchasingRecord> tableRecords;

    static final String userName = "postgres";
    static final String  password = "1";
    static final String connectionUrl = "jdbc:postgresql://127.0.0.1:5432/trade";

    @FXML
    void initialize() {
        /*assert columnAmount != null : "fx:id=\"columnAmount\" was not injected: check your FXML file 'purchasing-view.fxml'.";
        assert columnNumber != null : "fx:id=\"columnNumber\" was not injected: check your FXML file 'purchasing-view.fxml'.";
        assert columnPrice != null : "fx:id=\"columnPrice\" was not injected: check your FXML file 'purchasing-view.fxml'.";
        assert columnProduct != null : "fx:id=\"columnProduct\" was not injected: check your FXML file 'purchasing-view.fxml'.";
        assert columnSum != null : "fx:id=\"columnSum\" was not injected: check your FXML file 'purchasing-view.fxml'.";
        assert docClient != null : "fx:id=\"docClient\" was not injected: check your FXML file 'purchasing-view.fxml'.";
        assert docDate != null : "fx:id=\"docDate\" was not injected: check your FXML file 'purchasing-view.fxml'.";
        assert docNumber != null : "fx:id=\"docNumber\" was not injected: check your FXML file 'purchasing-view.fxml'.";
        assert tableRecords != null : "fx:id=\"tableRecords\" was not injected: check your FXML file 'purchasing-view.fxml'.";*/

    }

    public void stringAdd(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("tableString-view.fxml"));
        try {
            Parent stringView = loader.load();
            Scene docScene = new Scene(stringView);
            //StringController controller = loader.getController();
            Stage stageString = new Stage();
            stageString.setScene(docScene);
            stageString.setMaximized(true);
            stageString.setTitle("Добавление строки табличной части");
            stageString.show();
            //PurchasingRecord purchasingRecord = controller.addPurchasingRecord(Integer.parseInt(docNumber.getText()));
            //tableRecords.setItems(FXCollections.observableArrayList(purchasingRecord));
            //stageString.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void initTable(PurchasingRecord purchasingRecord){
        List<PurchasingRecord> itemsProduct = new ArrayList<>();
            itemsProduct.add(new DefaultPurchasingRecord(purchasingRecord.getDocumentId(), purchasingRecord.getProductId(), purchasingRecord.getProductName(), purchasingRecord.getAmount(), purchasingRecord.getPrice()));

        columnProduct.setCellValueFactory(new PropertyValueFactory<>("name"));
        //columnAmount = new TableColumn<>("Amount");
        columnAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        //columnPrice = new TableColumn<>("Price");
        columnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        tableRecords.setItems(FXCollections.observableArrayList(itemsProduct));
        tableRecords.refresh();
        ObservableList<PurchasingRecord> dd = tableRecords.getItems();
    }
    public void initData(Integer docId){
         DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    ListPurchasingRecords resultPurchasingRecord = new ListImplementationPurchasingRecords();
                    ListClients resultClients = new ListImplementationClients();
                    try (Connection connection = DriverManager.getConnection(connectionUrl, userName, password);
                         Statement statement = connection.createStatement()) {
                        String SelectPurchasingRecord = "SELECT * FROM purchasing_records WHERE document_id = '" + docId + "'";
                        ResultSet resPurchasingRecord = statement.executeQuery(SelectPurchasingRecord);
                        while (resPurchasingRecord.next()) {

                            resultPurchasingRecord.addPurchasingRecord(PurchasingCreationService.getInstance().createPurchasing(resPurchasingRecord.getInt(1), resPurchasingRecord.getInt(2),
                                    resPurchasingRecord.getString(3), resPurchasingRecord.getDouble(4), resPurchasingRecord.getDouble(5)));

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
                            //docNumber = new TextField(purDoc.getId().toString());
                            docNumber.setText(purDoc.getId().toString());
                            //docDate = new TextField(purDoc.getDate().toString());
                            docDate.setText(purDoc.getDate().toString());
                            //docClient = new TextField(purDoc.getClient().getName());
                            docClient.setText(purDoc.getClient().getName());
                        }
                        statement.close();
                    } catch (
                        SQLException e) {
                        throw new RuntimeException(e);
                    }
                List<PurchasingRecord> itemsProduct = new ArrayList<>();
                for (PurchasingRecord purchasingRecord : resultPurchasingRecord){
                    itemsProduct.add(new DefaultPurchasingRecord(purchasingRecord.getDocumentId(), purchasingRecord.getProductId(), purchasingRecord.getProductName(), purchasingRecord.getAmount(), purchasingRecord.getPrice()));
                    }

                    //= new TableView<>(FXCollections.observableArrayList(resultPurchasingRecord));
                    //columnProduct = new TableColumn<>("Product");
                    //columnNumber.setCellValueFactory(new PropertyValueFactory<>("productId"));
                    columnProduct.setCellValueFactory(new PropertyValueFactory<>("productName"));
                    //columnAmount = new TableColumn<>("Amount");
                    columnAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
                    //columnPrice = new TableColumn<>("Price");
                    columnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
                    //columnSum.setCellValueFactory(new PropertyValueFactory<>("documentId"));

                    tableRecords.setItems(FXCollections.observableArrayList(itemsProduct));
                    //tableRecords.getColumns().add(columnProduct);
                    //tableRecords.getColumns().add(columnAmount);
                    //tableRecords.getColumns().add(columnPrice);
    }

}
