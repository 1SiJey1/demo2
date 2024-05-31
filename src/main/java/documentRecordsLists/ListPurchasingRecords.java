package documentRecordsLists;

import documentRecords.PurchasingRecord;

public interface ListPurchasingRecords extends CollectionPurchasingRecords, Cloneable, Iterable<PurchasingRecord> {
    ListPurchasingRecords clone() throws CloneNotSupportedException;
}
