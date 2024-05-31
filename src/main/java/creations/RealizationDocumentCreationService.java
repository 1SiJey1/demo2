package creations;

import clients.Client;
import documentRecordsLists.ListRealizationRecords;
import documents.DefaultRealizationDocument;
import documents.RealizationDocument;

import java.util.Date;

public class RealizationDocumentCreationService {
    private static class InnerHolder {
        public static final RealizationDocumentCreationService REALIZATIONDOCUMENT_CREATION_SERVICE = new RealizationDocumentCreationService();
    }

    public static RealizationDocumentCreationService getInstance() {
        return InnerHolder.REALIZATIONDOCUMENT_CREATION_SERVICE;
    }

    public RealizationDocument createRealizationDocument(Integer id, Date date, Client client, ListRealizationRecords realizationRecords) {
        return new DefaultRealizationDocument(id, date, client, realizationRecords);
    }
}
