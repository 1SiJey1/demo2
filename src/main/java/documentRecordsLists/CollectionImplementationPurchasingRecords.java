package documentRecordsLists;

import documentRecords.PurchasingRecord;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectionImplementationPurchasingRecords implements CollectionPurchasingRecords, Serializable {

    private Collection<PurchasingRecord> purchasingRecords;

    public CollectionImplementationPurchasingRecords(Collection<PurchasingRecord> purchasingRecords) {
        /*if (!products.isEmpty()) {
            throw new CollectionNotEmptyException("Collection isn't empty!");
        }*/
        this.purchasingRecords = purchasingRecords;
    }

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
    public Collection<PurchasingRecord> getAllPurchasingRecord() {
        return Collections.unmodifiableCollection(this.purchasingRecords);
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
    public CollectionPurchasingRecords clone() throws CloneNotSupportedException {
        CollectionImplementationPurchasingRecords PurchasingRecords = new CollectionImplementationPurchasingRecords(new ArrayList<>());
        for (PurchasingRecord purchasingRecord : this.purchasingRecords) {
            PurchasingRecords.addPurchasingRecord(purchasingRecord.clone());
        }
        return PurchasingRecords;
    }

    @Override
    public void sort() {
        this.purchasingRecords = this.purchasingRecords.stream().sorted().collect(Collectors.toList());
    }

    @Override
    public void sort(Comparator<PurchasingRecord> comparator) {
        this.purchasingRecords = this.purchasingRecords.stream().sorted(comparator).collect(Collectors.toList());
    }

    @Override
    public Iterator<PurchasingRecord> iterator() {
        return this.purchasingRecords.iterator();
    }
}
