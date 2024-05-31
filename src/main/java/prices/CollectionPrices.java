package prices;

import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Stream;

public interface CollectionPrices extends Cloneable, Iterable<Price>{



    boolean addPrices(CollectionPrices prices);

    Stream<Price> stream();

    /**
     * Пытается добавить задачу на доску
     *
     * @return true - если получилось добавить, иначе - false
     */
    boolean addPrice(Price price);

    /**
     * Пытается удалить переданную задачу из доски
     *
     * @return true - если получилось удалить, иначе - false
     */
    boolean removePrice(Price price);

    /**
     * Пытается удалить задачу из доски по айди задачи
     *
     * @return true - если получилось удалить, иначе - false
     */
    boolean removePrice(Integer id);

    /**
     * @return Список всех задач из доски
     */
    Collection<Price> getAllPrice();

    /**
     * @param id - уникальный идентификатор таски
     * @return Задача из таскбоарда
     */
    Optional<Price> getPriceById(Integer id);

    CollectionPrices clone() throws CloneNotSupportedException;

    void sort();

    void sort(Comparator<Price> comparator);

    static Collector<Price, ListPrices, ListPrices> toListPrices() {
        return Collector.of(ListImplementationPrices::new,
                ListPrices::addPrice,
                (prices1, prices2) -> {
                    prices1.addPrices(prices2);
                    return prices1;
                });
    }

    /* Collector<Price, MapPrice, MapPrice> toMapTaskBoard() {
        return Collector.of(MapImplementationTaskBoard::new,
                MapTaskBoard::addTask,
                (MapTaskBoard tasks1, MapTaskBoard tasks2) -> {
                    tasks2.addTasks(tasks1);
                    return tasks1;
                });
    }*/
}
