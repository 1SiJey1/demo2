package documents;

import clients.Client;
import documentRecordsLists.ListRealizationRecords;

import java.util.Date;

public class DefaultRealizationDocument implements RealizationDocument{

    private Integer id;
    private Date date;
    private Client client;
    private ListRealizationRecords realizationRecords;


    public DefaultRealizationDocument(Integer id, Date date, Client client,  ListRealizationRecords realizationRecords) {
        this.id = id;
        this.date = date;
        this.client = client;
        this.realizationRecords = realizationRecords;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public Client getClient() {
        return client;
    }

    @Override
    public ListRealizationRecords getRealizationRecords() {
        return realizationRecords;
    }

    @Override
    public RealizationDocument clone() throws CloneNotSupportedException {
        return new DefaultRealizationDocument(this.id, this.date, this.client, this.realizationRecords);
    }

}
