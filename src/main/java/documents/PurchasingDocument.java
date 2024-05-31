package documents;

import clients.Client;
import documentRecordsLists.ListPurchasingRecords;

import java.time.LocalDate;

public interface PurchasingDocument extends Cloneable{

    Integer getId();
    LocalDate getDate();
    Client getClient();
    ListPurchasingRecords getPurchasingRecords();

    PurchasingDocument clone() throws CloneNotSupportedException;

}
