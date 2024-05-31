package documentRecordsLists;

import documentRecords.RealizationRecord;

import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Stream;

public interface CollectionRealizationRecords extends Cloneable, Iterable<RealizationRecord>{

    boolean addRealizationRecords(CollectionRealizationRecords realizationRecords);

    Stream<RealizationRecord> stream();

    /**
     * Пытается добавить задачу на доску
     *
     * @return true - если получилось добавить, иначе - false
     */
    boolean addRealizationRecord(RealizationRecord realizationRecord);

    /**
     * Пытается удалить переданную задачу из доски
     *
     * @return true - если получилось удалить, иначе - false
     */
    boolean removeRealizationRecord(RealizationRecord realizationRecord);

    /**
     * Пытается удалить задачу из доски по айди задачи
     *
     * @return true - если получилось удалить, иначе - false
     */
    boolean removeRealizationRecord(Integer id);

    /**
     * @return Список всех задач из доски
     */
    Collection<RealizationRecord> getAllRealizationRecord();

    /**
     * @param id - уникальный идентификатор таски
     * @return Задача из таскбоарда
     */
    Optional<RealizationRecord> getRealizationRecordById(Integer id);

    CollectionRealizationRecords clone() throws CloneNotSupportedException;

    void sort();

    void sort(Comparator<RealizationRecord> comparator);

    static Collector<RealizationRecord, ListRealizationRecords, ListRealizationRecords> toListRealizationRecords() {
        return Collector.of(ListImplementationRealizationRecords::new,
                ListRealizationRecords::addRealizationRecord,
                (realizationRecord1, realizationRecord2) -> {
                    realizationRecord1.addRealizationRecords(realizationRecord2);
                    return realizationRecord1;
                });
    }
}
