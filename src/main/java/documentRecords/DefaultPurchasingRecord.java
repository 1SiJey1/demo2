package documentRecords;

public class DefaultPurchasingRecord implements PurchasingRecord {
    private Integer document_id;
    private Integer product_id;
    private String product_name;
    private Double amount;
    private Double price;

    public DefaultPurchasingRecord(Integer document_id, Integer product_id, String product_name, Double amount, Double price) {
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
    public PurchasingRecord clone() throws CloneNotSupportedException {
        return new DefaultPurchasingRecord(this.document_id, this.product_id, this.product_name, this.amount, this.price);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("document_id = ").append(getDocumentId()).append(", ").append("product_id = ").append(getProductId()).append(", ").append("name = ").append(getProductName()).append(", ").append("amount = ").append(getAmount()).append(", ").append("price = ").append(getPrice()).append(".");
        return sb.toString();
    }
}
