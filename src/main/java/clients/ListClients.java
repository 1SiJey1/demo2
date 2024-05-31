package clients;

public interface ListClients extends CollectionClients, Cloneable, Iterable<Client>{
    ListClients clone() throws CloneNotSupportedException;
}