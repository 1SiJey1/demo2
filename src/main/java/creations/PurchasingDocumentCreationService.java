package creations;

import clients.Client;
import documentRecordsLists.ListPurchasingRecords;
import documents.DefaultPurchasingDocument;
import documents.PurchasingDocument;

import java.time.LocalDate;

public class PurchasingDocumentCreationService {
    private static class InnerHolder {
        public static final PurchasingDocumentCreationService PURCHASINGDOCUMENT_CREATION_SERVICE = new PurchasingDocumentCreationService();
    }

    public static PurchasingDocumentCreationService getInstance() {
        return InnerHolder.PURCHASINGDOCUMENT_CREATION_SERVICE;
    }

    public PurchasingDocument createPurchasingDocument(Integer id, LocalDate date, Client client, ListPurchasingRecords purchasingRecords) {
        return new DefaultPurchasingDocument(id, date, client, purchasingRecords);
    }
}
