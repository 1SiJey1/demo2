package documentRecordsLists;

import documentRecords.PurchasingRecord;

import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Stream;

public interface CollectionPurchasingRecords extends Cloneable, Iterable<PurchasingRecord>{

    boolean addPurchasingRecords(CollectionPurchasingRecords purchasingRecords);

    Stream<PurchasingRecord> stream();

    /**
     * Пытается добавить задачу на доску
     *
     * @return true - если получилось добавить, иначе - false
     */
    boolean addPurchasingRecord(PurchasingRecord purchasingRecord);

    /**
     * Пытается удалить переданную задачу из доски
     *
     * @return true - если получилось удалить, иначе - false
     */
    boolean removePurchasingRecord(PurchasingRecord purchasingRecord);

    /**
     * Пытается удалить задачу из доски по айди задачи
     *
     * @return true - если получилось удалить, иначе - false
     */
    boolean removePurchasingRecord(Integer id);

    /**
     * @return Список всех задач из доски
     */
    Collection<PurchasingRecord> getAllPurchasingRecord();

    /**
     * @param id - уникальный идентификатор таски
     * @return Задача из таскбоарда
     */
    Optional<PurchasingRecord> getPurchasingRecordById(Integer id);

    CollectionPurchasingRecords clone() throws CloneNotSupportedException;

    void sort();

    void sort(Comparator<PurchasingRecord> comparator);

    static Collector<PurchasingRecord, ListPurchasingRecords, ListPurchasingRecords> toListPurchasingRecords() {
        return Collector.of(ListImplementationPurchasingRecords::new,
                ListPurchasingRecords::addPurchasingRecord,
                (purchasingRecord1, purchasingRecord2) -> {
                    purchasingRecord1.addPurchasingRecords(purchasingRecord2);
                    return purchasingRecord1;
                });
    }
}
