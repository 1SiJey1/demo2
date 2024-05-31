package documents;

import clients.Client;
import documentRecordsLists.ListPurchasingRecords;

import java.time.LocalDate;

public class DefaultPurchasingDocument implements PurchasingDocument{

    private Integer id;
    private LocalDate date;
    private Client client;
    private ListPurchasingRecords purchasingRecords;


    public DefaultPurchasingDocument(Integer id, LocalDate date, Client client, ListPurchasingRecords purchasingRecords) {
        this.id = id;
        this.date = date;
        this.client = client;

        this.purchasingRecords = purchasingRecords;

    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public LocalDate getDate() {
        return date;
    }

    @Override
    public Client getClient() {
        return client;
    }

    @Override
    public ListPurchasingRecords getPurchasingRecords() {
        return purchasingRecords;
    }

    @Override
    public PurchasingDocument clone() throws CloneNotSupportedException {
        return new DefaultPurchasingDocument(this.id, this.date, this.client, this.purchasingRecords);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id = ").append(getId()).append("\n").append("date = ").append(getDate()).append("\n").append("client: ").append(getClient()).append("\n").append("purchasingRecords:\n").append(getPurchasingRecords());
        return sb.toString();
    }

}
