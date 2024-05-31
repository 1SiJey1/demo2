package compile;

import clients.Client;
import clients.ListClients;
import clients.ListImplementationClients;
import creations.*;
import documentLists.ListImplementationPurchasingDocuments;
import documentLists.ListPurchasingDocuments;
import documentRecords.PurchasingRecord;
import documentRecordsLists.ListImplementationPurchasingRecords;
import documentRecordsLists.ListPurchasingRecords;
import documents.PurchasingDocument;
import prices.ListImplementationPrices;
import prices.ListPrices;
import products.ListImplementationProducts;
import products.ListProducts;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {

    static final String userName = "postgres";
    static final String  password = "1";
    static final String connectionUrl = "jdbc:postgresql://127.0.0.1:5432/trade";

    public static void main(String[] args) {
        /*
        ListProducts products = new ListImplementationProducts();
        Product product1 = ProductCreationService.getInstance().createProduct(1,"Iphone 13", "Iphone 13 256gb rainbow", "454-242");
        Product product2 = ProductCreationService.getInstance().createProduct(2,"Headphones", "Headphones Samsung wx 256", "194-234");
        Product product3 = ProductCreationService.getInstance().createProduct(3,"Table", "Table Ikea", "354-353");
        Product product4 = ProductCreationService.getInstance().createProduct(4,"Microphone", "Microphone Rode nt-1", "337-228");
        Product product5 = ProductCreationService.getInstance().createProduct(5,"Keyboard", "Keyboard HyperX", "235-255");
        System.out.println("Add products.");
        products.addProduct(product1);
        products.addProduct(product2);
        products.addProduct(product3);
        products.addProduct(product4);
        products.addProduct(product5);

        ListClients clients = new ListImplementationClients();*/
        Client client1 = ClientCreationService.getInstance().createClient(1,"Dmitriy", "283704289371", "2938163958271", "352956381","29381957924726918492","14259257612964823910");
        /*Client client2 = ClientCreationService.getInstance().createClient(2,"Evgeniy", "283700749371", "3938167528271", "985456381","41943957924726918492","93525677612964823910");
        Client client3 = ClientCreationService.getInstance().createClient(3,"Mikhail", "283797689371", "1234163958271", "285936381","29381957924726918492","83479257612964823910");
        Client client4 = ClientCreationService.getInstance().createClient(4,"Jodee", "229804289371", "0938144768271", "732156381","74014957924726918492","24657425612964823910");
        Client client5 = ClientCreationService.getInstance().createClient(5,"Jordan", "283754889371", "6788163321271", "045256381","28613957924726918492","25851456712964823910");
        System.out.println("Add clients.");
        clients.addClient(client1);
        clients.addClient(client2);
        clients.addClient(client3);
        clients.addClient(client4);
        clients.addClient(client5);

        ListPrices prices = new ListImplementationPrices();
        //Price price1 = PriceCreationService.getInstance().createPrice(1, LocalDate.of(2024, 5, 20), "Iphone 13 256gb rainbow", 69000.0);
        Price price2 = PriceCreationService.getInstance().createPrice(2, LocalDate.of(2024, 5, 21), "Headphones Samsung wx 256", 24000.0);
        Price price3 = PriceCreationService.getInstance().createPrice(3, LocalDate.of(2024, 5, 21), "Table Ikea", 9000.0);
        Price price4 = PriceCreationService.getInstance().createPrice(4, LocalDate.of(2024, 5, 22), "Microphone Rode nt-1", 24000.0);
        Price price5 = PriceCreationService.getInstance().createPrice(5, LocalDate.of(2024, 5, 23), "Keyboard HyperX", 7000.0);
        System.out.println("Add prices.");
        //prices.addPrice(price1);
        prices.addPrice(price2);
        prices.addPrice(price3);
        prices.addPrice(price4);
        prices.addPrice(price5);*/

        ListPurchasingDocuments purchasingDocuments = new ListImplementationPurchasingDocuments();
        ListPurchasingRecords purchasingRecords = new ListImplementationPurchasingRecords();

        PurchasingRecord purchasingRecord1 = PurchasingCreationService.getInstance().createPurchasing(1, 1, "Iphone 13", 3.00, 47500.00);
        PurchasingRecord purchasingRecord2 = PurchasingCreationService.getInstance().createPurchasing(1, 2, "Headphones", 3.00, 2000.00);

        purchasingRecords.addPurchasingRecord(purchasingRecord1);
        purchasingRecords.addPurchasingRecord(purchasingRecord2);

        PurchasingDocument purchasingDocument1 = PurchasingDocumentCreationService.getInstance().createPurchasingDocument(1,  LocalDate.of(2024, 5, 20), client1, purchasingRecords);
        purchasingDocuments.addPurchasingDocument(purchasingDocument1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        ListProducts resultProducts = new ListImplementationProducts();
        ListClients resultClients = new ListImplementationClients();
        ListPrices resultPrices = new ListImplementationPrices();
        ListPurchasingDocuments resultPurchasingDocuments = new ListImplementationPurchasingDocuments();
        ListPurchasingRecords resultPurchasingRecords= new ListImplementationPurchasingRecords();

        //@Override


        try(Connection connection = DriverManager.getConnection(connectionUrl, userName, password);

            Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS products (id integer NOT NULL ,name VARCHAR (30) not null, fullname VARCHAR (30) not null," +
                    " article VARCHAR (30) not null, PRIMARY key(id)); ");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS clients (id integer NOT NULL ,name VARCHAR (30) not null, inn VARCHAR (30) not null, ogrn VARCHAR (30) not null," +
                    " bic VARCHAR (30) not null, acc VARCHAR (30) not null, corracc VARCHAR (30) not null, PRIMARY key(id)); ");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS prices (id integer NOT NULL, date DATE not null,name VARCHAR (30) not null, price NUMERIC not null, PRIMARY key(id)); ");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS purchasing_documents (id integer NOT NULL, document_name VARCHAR (50) not null, document_number VARCHAR (30) not null, date DATE not null," +
                    " client_id integer not null, client_name VARCHAR (30) not null, PRIMARY key(id)); ");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS purchasing_records (document_id integer NOT NULL, product_id integer NOT NULL, product_name VARCHAR (30) not null, amount NUMERIC not null," +
                    " price NUMERIC not null)");
            /*for (Product product : products) {
                statement.executeUpdate("INSERT INTO products (id, name, fullname, article) VALUES ('" +product.getId()+ "', '" +product.getName()+ "', '" + product.getFullName() + "', '" + product.getArticle() + "')");
            }

            for (Client client : clients){
                statement.executeUpdate("INSERT INTO clients (id, name, inn, ogrn, bic, acc, corracc) VALUES ('" +client.getId()+ "', '" +client.getName()+ "', '" + client.getINN() + "', '" + client.getOGRN() + "', '" + client.getBIC() + "', '" + client.getAcc() + "', '" + client.getCorrAcc() + "')");
            }

            for (Price price : prices){
                statement.executeUpdate("INSERT INTO prices (id, date, name, price) VALUES ('" +price.getId()+ "', '" +price.getDate()+ "', '" + price.getName() + "', '" + price.getPrice() + "')");
            }*/
            //  statement.executeUpdate("INSERT INTO product (id, name, fullname, article) VALUES ('" +id+ "', '" +name+ "', '" + fullname + "', '" + article + "')";

            /*for (PurchasingDocument purchasingDocument : purchasingDocuments){
                statement.executeUpdate("INSERT INTO purchasing_documents (id, document_name, document_number, date, client_id, client_name) VALUES ('" +purchasingDocument.getId() + "', '" + "Purchasing document №1" + "', '" + "№ 1" + "', '" +purchasingDocument.getDate()+ "', '" + purchasingDocument.getClient().getId() + "', '" + purchasingDocument.getClient().getName() + "')");
            }*/

            /*for (PurchasingRecord purchasingRecord : purchasingRecords){
                statement.executeUpdate("INSERT INTO purchasing_records (document_id, product_id, product_name, amount, price) VALUES ('" +purchasingRecord.getDocumentId()+ "', '" +purchasingRecord.getProductId()+ "', '" + purchasingRecord.getProductName() + "', '" + purchasingRecord.getAmount() + "', '" + purchasingRecord.getPrice() + "')");
            }*/


            String SelectProducts = "select * from products";

            ResultSet resProducts = statement.executeQuery(SelectProducts);

            while (resProducts.next())

            {
                resultProducts.addProduct(ProductCreationService.getInstance().createProduct(resProducts.getInt(1), resProducts.getString(2), resProducts.getString(3), resProducts.getString(4)));
            }

            String SelectClients = "select * from clients";
            ResultSet resClients = statement.executeQuery(SelectClients);

            while (resClients.next())

            {
                resultClients.addClient(ClientCreationService.getInstance().createClient(resClients.getInt(1), resClients.getString(2), resClients.getString(3), resClients.getString(4), resClients.getString(5), resClients.getString(6), resClients.getString(7)));
            }

            String SelectPrices = "select * from prices";
            ResultSet resPrices = statement.executeQuery(SelectPrices);

            while (resPrices.next())

            {
                resultPrices.addPrice(PriceCreationService.getInstance().createPrice(resPrices.getInt(1), LocalDate.parse(resPrices.getString(2), formatter), resPrices.getString(3), resPrices.getDouble(4)));
            }

            String SelectPurchasingRecords = "SELECT * FROM purchasing_records";
            ResultSet resPurchasingRecords = statement.executeQuery(SelectPurchasingRecords);
            while (resPurchasingRecords.next()) {

                resultPurchasingRecords.addPurchasingRecord(PurchasingCreationService.getInstance().createPurchasing(resPurchasingRecords.getInt(1), resPurchasingRecords.getInt(2),
                        resPurchasingRecords.getString(3), resPurchasingRecords.getDouble(4), resPurchasingRecords.getDouble(4)));

            }

            String SelectPurchasingDocuments = "SELECT * FROM purchasing_documents";
            ResultSet resPurchasingDocuments = statement.executeQuery(SelectPurchasingDocuments);

            while (resPurchasingDocuments.next()) {

                resultPurchasingDocuments.addPurchasingDocument(PurchasingDocumentCreationService.getInstance().createPurchasingDocument(resPurchasingDocuments.getInt(1),
                        LocalDate.parse(resPurchasingDocuments.getString(4), formatter), resultClients.getClientById(resPurchasingDocuments.getInt(5)), resultPurchasingRecords));

            }
                statement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        System.out.println("\n");
        System.out.println("Table products\n");
        System.out.println(resultProducts);
        System.out.println("Table clients\n");
        System.out.println(resultClients);
        System.out.println("Table prices\n");
        System.out.println(resultPrices);
        System.out.println("Table Purchasing documents\n");
        System.out.println(resultPurchasingDocuments);

        /*for (Product product : products) {
            System.out.println(product);
        }*/
    }
}
