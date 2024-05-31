package documentRecordsLists;

import documentRecords.RealizationRecord;

public interface ListRealizationRecords extends CollectionRealizationRecords, Cloneable, Iterable<RealizationRecord> {
    ListRealizationRecords clone() throws CloneNotSupportedException;
}
