package DatabaseFunctions;

import java.sql.Connection;

public interface DatabaseFunctions {
    Connection Connection();
    /*void InsertToDatabase(Integer id, String name, Double cost);*/

    void InsertToDatabase(Integer id, String name, String fullname, String article);
    void SelectFromDatabase(String ColumnName, String selection);

    void SelectAll();
    void DeleteFromDatabase(String ColumnName, String selection);
    void UpdateDatabase(String ColumnName, String value, String selection);
}
