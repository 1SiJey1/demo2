package products;

import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Stream;

public interface CollectionProducts extends Cloneable, Iterable<Product>{



    boolean addProducts(CollectionProducts products);

    Stream<Product> stream();

    /**
     * Пытается добавить задачу на доску
     *
     * @return true - если получилось добавить, иначе - false
     */
    boolean addProduct(Product product);

    /**
     * Пытается удалить переданную задачу из доски
     *
     * @return true - если получилось удалить, иначе - false
     */
    boolean removeProduct(Product product);

    /**
     * Пытается удалить задачу из доски по айди задачи
     *
     * @return true - если получилось удалить, иначе - false
     */
    boolean removeProduct(Integer id);

    /**
     * @return Список всех задач из доски
     */
    Collection<Product> getAllProduct();

    /**
     * @param id - уникальный идентификатор таски
     * @return Задача из таскбоарда
     */
    Optional<Product> getProductById(Integer id);

    CollectionProducts clone() throws CloneNotSupportedException;

    void sort();

    void sort(Comparator<Product> comparator);

    static Collector<Product, ListProducts, ListProducts> toListProducts() {
        return Collector.of(ListImplementationProducts::new,
                ListProducts::addProduct,
                (products1, products2) -> {
                    products1.addProducts(products2);
                    return products1;
                });
    }

    /* Collector<Product, MapProduct, MapProduct> toMapTaskBoard() {
        return Collector.of(MapImplementationTaskBoard::new,
                MapTaskBoard::addTask,
                (MapTaskBoard tasks1, MapTaskBoard tasks2) -> {
                    tasks2.addTasks(tasks1);
                    return tasks1;
                });
    }*/
}
