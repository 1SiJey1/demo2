package clients;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectionImplementationClients implements CollectionClients, Serializable {

    private Collection<Client> clients;

    public CollectionImplementationClients(Collection<Client> clients) {
        /*if (!clients.isEmpty()) {
            throw new CollectionNotEmptyException("Collection isn't empty!");
        }*/
        this.clients = clients;
    }
    @Override
    public boolean addClients(CollectionClients clients) {
        return this.clients.addAll(clients.getAllClient());
    }

    @Override
    public Stream<Client> stream() {return this.clients.stream();
    }

    @Override
    public boolean addClient(Client client) {
        return clients.add(client);
    }


    @Override
    public boolean removeClient(Client client) {
        return removeClient(client.getId());
    }

    @Override
    public boolean removeClient(Integer id) {
        for (Client client : clients) {
            if (client.getId().equals(id)) {
                return clients.remove(client);
            }
        }
        return false;
    }

    @Override
    public Collection<Client> getAllClient() {
        return Collections.unmodifiableCollection(this.clients);
    }

    @Override
    public Client getClientById(Integer id) {
        for (Client client : clients) {
            if (client.getId().equals(id)) {
                return client;
            }
        }
        return null;
    }

    @Override
    public CollectionClients clone() throws CloneNotSupportedException {
        CollectionImplementationClients Clients = new CollectionImplementationClients(new ArrayList<>());
        for (Client client : this.clients) {
            Clients.addClient(client.clone());
        }
        return Clients;
    }

    @Override
    public void sort() {
        this.clients = this.clients.stream().sorted().collect(Collectors.toList());
    }

    @Override
    public void sort(Comparator<Client> comparator) {
        this.clients = this.clients.stream().sorted(comparator).collect(Collectors.toList());
    }

    @Override
    public Iterator<Client> iterator() {
        return this.clients.iterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Client client : this.clients) {
            sb.append(client.toString()).append("\n");

        }
        return sb.toString();
    }
}
