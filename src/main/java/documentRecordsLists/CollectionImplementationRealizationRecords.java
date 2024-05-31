package documentRecordsLists;

import documentRecords.RealizationRecord;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectionImplementationRealizationRecords implements CollectionRealizationRecords, Serializable {

    private Collection<RealizationRecord> realizationRecords;

    public CollectionImplementationRealizationRecords(Collection<RealizationRecord> realizationRecords) {
        /*if (!products.isEmpty()) {
            throw new CollectionNotEmptyException("Collection isn't empty!");
        }*/
        this.realizationRecords = realizationRecords;
    }

    @Override
    public boolean addRealizationRecords(CollectionRealizationRecords realizationRecords) {
        return this.realizationRecords.addAll(realizationRecords.getAllRealizationRecord());
    }

    @Override
    public Stream<RealizationRecord> stream() {
        return this.realizationRecords.stream();
    }

    @Override
    public boolean addRealizationRecord(RealizationRecord realizationRecord) {
        return realizationRecords.add(realizationRecord);
    }

    @Override
    public boolean removeRealizationRecord(RealizationRecord realizationRecord) {
        return removeRealizationRecord(realizationRecord.getProductId());
    }

    @Override
    public boolean removeRealizationRecord(Integer id) {
        for (RealizationRecord realizationRecord : realizationRecords) {
            if (realizationRecord.getProductId().equals(id)) {
                return realizationRecords.remove(realizationRecord);
            }
        }
        return false;
    }

    @Override
    public Collection<RealizationRecord> getAllRealizationRecord() {
        return Collections.unmodifiableCollection(this.realizationRecords);
    }

    @Override
    public Optional<RealizationRecord> getRealizationRecordById(Integer id) {
        for (RealizationRecord realizationRecord : realizationRecords) {
            if (realizationRecord.getProductId().equals(id)) {
                return Optional.of(realizationRecord);
            }
        }
        return Optional.empty();
    }

    @Override
    public CollectionRealizationRecords clone() throws CloneNotSupportedException {
        CollectionImplementationRealizationRecords RealizationRecords = new CollectionImplementationRealizationRecords(new ArrayList<>());
        for (RealizationRecord realizationRecord : this.realizationRecords) {
            RealizationRecords.addRealizationRecord(realizationRecord.clone());
        }
        return RealizationRecords;
    }

    @Override
    public void sort() {
        this.realizationRecords = this.realizationRecords.stream().sorted().collect(Collectors.toList());
    }

    @Override
    public void sort(Comparator<RealizationRecord> comparator) {
        this.realizationRecords = this.realizationRecords.stream().sorted(comparator).collect(Collectors.toList());
    }

    @Override
    public Iterator<RealizationRecord> iterator() {
        return this.realizationRecords.iterator();
    }
}
