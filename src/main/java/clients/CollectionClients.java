package clients;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collector;
import java.util.stream.Stream;

public interface CollectionClients extends Cloneable, Iterable<Client>{



    boolean addClients(CollectionClients client);

    Stream<Client> stream();

    /**
     * Пытается добавить задачу на доску
     *
     * @return true - если получилось добавить, иначе - false
     */
    boolean addClient(Client client);

    /**
     * Пытается удалить переданную задачу из доски
     *
     * @return true - если получилось удалить, иначе - false
     */
    boolean removeClient(Client client);

    /**
     * Пытается удалить задачу из доски по айди задачи
     *
     * @return true - если получилось удалить, иначе - false
     */
    boolean removeClient(Integer id);

    /**
     * @return Список всех задач из доски
     */
    Collection<Client> getAllClient();

    /**
     * @param id - уникальный идентификатор таски
     * @return Задача из таскбоарда
     */
    Client getClientById(Integer id);

    CollectionClients clone() throws CloneNotSupportedException;

    void sort();

    void sort(Comparator<Client> comparator);

    static Collector<Client, ListClients, ListClients> toListClients() {
        return Collector.of(ListImplementationClients::new,
                ListClients::addClient,
                (clients1, clients2) -> {
                    clients1.addClients(clients2);
                    return clients1;
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

