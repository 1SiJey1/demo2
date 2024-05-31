package documentRecords;

public class DefaultRealizationRecord implements RealizationRecord{
    private Integer document_id;
    private Integer product_id;
    private String product_name;
    private Double amount;
    private Double price;

    public DefaultRealizationRecord(Integer document_id, Integer product_id, String product_name, Double amount, Double price) {
        this.document_id = document_id;
        this.product_id = product_id;
        this.product_name = product_name;
        this.amount = amount;
        this.price = price;
    }

    @Override
    public Integer getDocumentId() {
        return document_id;
    }

    @Override
    public Integer getProductId() {
        return product_id;
    }

    @Override
    public String getProductName() {
        return product_name;
    }

    @Override
    public Double getAmount() {
        return amount;
    }

    @Override
    public Double getPrice() {
        return price;
    }

    @Override
    public RealizationRecord clone() throws CloneNotSupportedException {
        return new DefaultRealizationRecord(this.document_id, this.product_id, this.product_name, this.amount, this.price);
    }
}
