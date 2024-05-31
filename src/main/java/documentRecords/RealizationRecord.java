package documentRecords;

public interface RealizationRecord extends Cloneable {

    Integer getDocumentId();

    Integer getProductId();

    String getProductName();

    Double getAmount();

    Double getPrice();

    RealizationRecord clone() throws CloneNotSupportedException;
}
