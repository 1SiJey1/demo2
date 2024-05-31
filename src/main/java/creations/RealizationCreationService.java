package creations;


import documentRecords.DefaultRealizationRecord;
import documentRecords.RealizationRecord;


public class RealizationCreationService {
    private static class InnerHolder {
        public static final RealizationCreationService REALIZATION_CREATION_SERVICE= new RealizationCreationService();
    }

    public static RealizationCreationService getInstance() {
        return InnerHolder.REALIZATION_CREATION_SERVICE;
    }

    public RealizationRecord createRealization(Integer document_id, Integer product_id, String product_name, Double amount, Double price) {
        return new DefaultRealizationRecord(document_id, product_id, product_name, amount, price);
    }
}
