package com.example.demo2;

public interface PurchasingRecord extends Cloneable {

    Integer getDocumentId();

    Integer getProductId();

    String getProductName();

    Double getAmount();

    Double getPrice();

    PurchasingRecord clone() throws CloneNotSupportedException;
}
