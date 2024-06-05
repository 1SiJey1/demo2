package com.example.demo2;

import java.net.URL;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import clients.Client;
import clients.ListClients;
import clients.ListImplementationClients;
import creations.ClientCreationService;
import creations.PurchasingCreationService;
import documentRecordsLists.ListImplementationPurchasingRecords;
import documentRecordsLists.ListPurchasingRecords;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ClientController {

    public Button clientInsert;
    public Button clientUpdate;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField clientAcc;

    @FXML
    private TextField clientBic;

    @FXML
    private TextField clientCorracc;

    @FXML
    private TextField clientINN;

    @FXML
    private TextField clientId;

    @FXML
    private TextField clientName;

    @FXML
    private TextField clientOGRN;

    static final String userName = "postgres";
    static final String password = "1";
    static final String connectionUrl = "jdbc:postgresql://127.0.0.1:5432/trade";

    @FXML
    void initialize() {


    }

    void initData(Integer id) {

        //Client resultClient = new ListImplementationPurchasingRecords();
        //ListClients resultClients = new ListImplementationClients();
        try (Connection connection = DriverManager.getConnection(connectionUrl, userName, password);
             Statement statement = connection.createStatement()) {
            String SelectPurchasingRecord = "SELECT * FROM clients WHERE id = '" + id + "'";
            ResultSet resPurchasingRecord = statement.executeQuery(SelectPurchasingRecord);
            while (resPurchasingRecord.next()) {

                Client client = ClientCreationService.getInstance().createClient(resPurchasingRecord.getInt(1), resPurchasingRecord.getString(2),
                        resPurchasingRecord.getString(3), resPurchasingRecord.getString(4), resPurchasingRecord.getString(5),
                        resPurchasingRecord.getString(6), resPurchasingRecord.getString(7));
                clientId.setText(client.getId().toString());
                clientName.setText(client.getName());
                clientINN.setText(client.getINN());
                clientOGRN.setText(client.getOGRN());
                clientBic.setText(client.getBIC());
                clientAcc.setText(client.getAcc());
                clientCorracc.setText(client.getCorrAcc());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void insertButton(){
        try (Connection connection = DriverManager.getConnection(connectionUrl, userName, password);
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("INSERT INTO clients (id, name, inn, ogrn, bic, acc, corracc) VALUES ('" +Integer.parseInt(clientId.getText())+ "', '" +clientName.getText()+ "', '" + clientINN.getText() + "', '" + clientOGRN.getText() + "', '" + clientBic.getText() + "', '" + clientAcc.getText() + "', '" + clientCorracc.getText() + "')");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

