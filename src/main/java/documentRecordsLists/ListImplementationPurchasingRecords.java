package documentRecordsLists;

import documentRecords.PurchasingRecord;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Stream;

public class ListImplementationPurchasingRecords implements ListPurchasingRecords, Serializable {
    private List<PurchasingRecord> purchasingRecords = new ArrayList<>();

    @Override
    public boolean addPurchasingRecords(CollectionPurchasingRecords purchasingRecords) {
        return this.purchasingRecords.addAll(purchasingRecords.getAllPurchasingRecord());
    }

    @Override
    public Stream<PurchasingRecord> stream() {
        return this.purchasingRecords.stream();
    }

    @Override
    public boolean addPurchasingRecord(PurchasingRecord purchasingRecord) {
        return purchasingRecords.add(purchasingRecord);
    }

    @Override
    public boolean removePurchasingRecord(PurchasingRecord purchasingRecord) {
        return removePurchasingRecord(purchasingRecord.getProductId());
    }

    @Override
    public boolean removePurchasingRecord(Integer id) {
        for (PurchasingRecord purchasingRecord : purchasingRecords) {
            if (purchasingRecord.getProductId().equals(id)) {
                return purchasingRecords.remove(purchasingRecord);
            }
        }
        return false;
    }

    @Override
    public List<PurchasingRecord> getAllPurchasingRecord() {
        return Collections.unmodifiableList(this.purchasingRecords);
    }

    @Override
    public Optional<PurchasingRecord> getPurchasingRecordById(Integer id) {
        for (PurchasingRecord purchasingRecord : purchasingRecords) {
            if (purchasingRecord.getProductId().equals(id)) {
                return Optional.of(purchasingRecord);
            }
        }
        return Optional.empty();
    }

    @Override
    public void sort() {

    }

    @Override
    public void sort(Comparator<PurchasingRecord> comparator) {
        this.purchasingRecords.sort(comparator);
    }

    @Override
    public ListPurchasingRecords clone() throws CloneNotSupportedException {
        ListImplementationPurchasingRecords PurchasingRecords = new ListImplementationPurchasingRecords();
        for (PurchasingRecord purchasingRecord : this.purchasingRecords) {
            PurchasingRecords.addPurchasingRecord(purchasingRecord.clone());
        }
        return PurchasingRecords;
    }

    @Override
    public Iterator<PurchasingRecord> iterator() {
        return this.purchasingRecords.iterator();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (PurchasingRecord record:this.purchasingRecords){
            sb.append(record.toString()).append("\n");

        }
        return sb.toString();
    }
}
