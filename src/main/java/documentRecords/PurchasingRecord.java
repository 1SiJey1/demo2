package documentRecords;

public interface PurchasingRecord extends Cloneable {

    Integer getDocumentId();

    Integer getProductId();

    String getProductName();

    Double getAmount();

    Double getPrice();

    PurchasingRecord clone() throws CloneNotSupportedException;
}
