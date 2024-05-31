package documents;

import clients.Client;
import documentRecordsLists.ListRealizationRecords;

import java.util.Date;

public interface RealizationDocument extends Cloneable{


    Integer getId();
    Date getDate();
    Client getClient();
    ListRealizationRecords getRealizationRecords();


    RealizationDocument clone() throws CloneNotSupportedException;
}
