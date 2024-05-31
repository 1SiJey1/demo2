package creations;


import documentRecords.DefaultPurchasingRecord;
import documentRecords.PurchasingRecord;



public class PurchasingCreationService {
    private static class InnerHolder {
        public static final PurchasingCreationService PURCHASING_CREATION_SERVICE = new PurchasingCreationService();
    }

    public static PurchasingCreationService getInstance() {
        return InnerHolder.PURCHASING_CREATION_SERVICE;
    }

    public PurchasingRecord createPurchasing(Integer document_id, Integer product_id, String product_name, Double amount, Double price) {
        return new DefaultPurchasingRecord(document_id, product_id, product_name, amount, price);
    }
}
