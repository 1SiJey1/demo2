package DatabaseFunctions;

import java.sql.*;

public class DatabaseFunctionsImplementation implements DatabaseFunctions{
    private String message = "";


    private static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/trade";
    private static final String USER = "postgres";
    private static final String PASS = "1";
    @Override
    public Connection Connection() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("PostgresSQL JDBC Driver successfully connected.");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("You successfully connected to database now.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    @Override
    public void InsertToDatabase(Integer id, String name, String fullname, String article) {
        try {
            Statement stmt = null;
            stmt = Connection().createStatement();
            String InsertSql = "insert into products values('" +id+ "', '" +name+ "', '" + fullname + "', '" + article + "')";
            stmt.executeUpdate(InsertSql);
            stmt.close();
            System.out.println("Insertion finished successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /*public void InsertToDatabase(Integer id, String name, String fullname, String article) {
        try {
            Statement stmt = null;
            stmt = Connection().createStatement();
            String InsertSql = "insert into products values('" +id+ "', '" +name+ "', '" +fullname+ "')";
            stmt.executeUpdate(InsertSql);
            stmt.close();
            System.out.println("Insertion finished successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }*/

    @Override
    public void SelectFromDatabase(String ColumnName, String selection) {
        try {
            Statement stmt = null;
            stmt = Connection().createStatement();
            String SelectSql = "select * from products WHERE " + ColumnName + " = '" + selection + "'";

            ResultSet rs = stmt.executeQuery(SelectSql);
            if (rs.next())
            {
                System.out.println("Id : " + rs.getString(1));
                System.out.println("Name :" + rs.getString(2));
                System.out.println("Full name :" + rs.getString(3));
            }
            else
            {
                System.out.println("No such " + ColumnName + " is already registered");
            }
            stmt.close();
            System.out.println("Selection finished successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void SelectAll() {
        try {
            Statement stmt = null;
            stmt = Connection().createStatement();
            String SelectSql = "select * from products";

            ResultSet rs = stmt.executeQuery(SelectSql);
            if (rs.next())
            {
                System.out.println("Id : " + rs.getString(1));
                System.out.println("Name :" + rs.getString(2));
                System.out.println("Full name :" + rs.getString(3));
                System.out.println("Article :" + rs.getString(4));
            }
            else
            {
                System.out.println("No such table is already registered");
            }
            stmt.close();
            System.out.println("Selection finished successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void DeleteFromDatabase(String ColumnName, String selection) {
        try {
            Statement stmt = null;
            stmt = Connection().createStatement();
            String DeleteSql = "delete * from products WHERE " + ColumnName + " = '" + selection + "'";
            stmt.executeUpdate(DeleteSql);
            stmt.close();
            System.out.println("Deleting finished successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void UpdateDatabase(String ColumnName, String value, String selection) {
        try {
            Statement stmt = null;
            stmt = Connection().createStatement();
            String UpdateSql = "UPDATE products SET " + ColumnName + " = '" + value + "' WHERE " + ColumnName + " = '" + selection + "'";
            stmt.executeUpdate(UpdateSql);
            stmt.close();
            SelectFromDatabase(ColumnName, selection);
            System.out.println("Update finished successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
