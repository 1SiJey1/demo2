package clients;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Stream;


public class ListImplementationClients implements ListClients, Serializable {

    private List<Client> clients = new ArrayList<>();
    @Override
    public boolean addClients(CollectionClients clients) {
        return this.clients.addAll(clients.getAllClient());
    }



    @Override
    public Stream<Client> stream() {
        return this.clients.stream();
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
    public List<Client> getAllClient() {
        return Collections.unmodifiableList(this.clients);
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
    public ListClients clone() throws CloneNotSupportedException {
        ListImplementationClients Clients = new ListImplementationClients();
        for (Client client : this.clients) {
            Clients.addClient(client.clone());
        }
        return Clients;
    }

    @Override
    public void sort() {

    }

    @Override
    public void sort(Comparator<Client> comparator) {
        this.clients.sort(comparator);
    }

    @Override
    public Iterator<Client> iterator() {
        return this.clients.iterator();
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Client client:this.clients){
            sb.append(client.toString()).append("\n");

        }
        return sb.toString();
    }

}
