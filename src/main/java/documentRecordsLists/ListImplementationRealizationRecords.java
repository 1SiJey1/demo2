package documentRecordsLists;


import documentRecords.RealizationRecord;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Stream;

public class ListImplementationRealizationRecords implements ListRealizationRecords, Serializable {
    private List<RealizationRecord> realizationRecords = new ArrayList<>();

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
    public List<RealizationRecord> getAllRealizationRecord() {
        return Collections.unmodifiableList(this.realizationRecords);
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
    public void sort() {

    }

    @Override
    public void sort(Comparator<RealizationRecord> comparator) {
        this.realizationRecords.sort(comparator);
    }

    @Override
    public ListRealizationRecords clone() throws CloneNotSupportedException {
        ListImplementationRealizationRecords RealizationRecords = new ListImplementationRealizationRecords();
        for (RealizationRecord realizationRecord : this.realizationRecords) {
            RealizationRecords.addRealizationRecord(realizationRecord.clone());
        }
        return RealizationRecords;
    }

    @Override
    public Iterator<RealizationRecord> iterator() {
        return this.realizationRecords.iterator();
    }
}
