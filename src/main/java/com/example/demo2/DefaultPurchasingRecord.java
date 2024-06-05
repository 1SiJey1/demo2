package com.example.demo2;

import documentRecords.PurchasingRecord;

public class DefaultPurchasingRecord implements PurchasingRecord {
    private Integer documentId;
    private Integer productId;
    private String productName;
    private Double amount;
    private Double price;

    public DefaultPurchasingRecord(Integer document_id, Integer product_id, String product_name, Double amount, Double price) {
        this.documentId = document_id;
        this.productId = product_id;
        this.productName = product_name;
        this.amount = amount;
        this.price = price;
    }
    @Override
    public Integer getDocumentId() {
        return documentId;
    }

    @Override
    public Integer getProductId() {
        return productId;
    }

    @Override
    public String getProductName() {
        return productName;
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
        return new DefaultPurchasingRecord(this.documentId, this.productId, this.productName, this.amount, this.price);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("document_id = ").append(getDocumentId()).append(", ").append("product_id = ").append(getProductId()).append(", ").append("name = ").append(getProductName()).append(", ").append("amount = ").append(getAmount()).append(", ").append("price = ").append(getPrice()).append(".");
        return sb.toString();
    }
}
