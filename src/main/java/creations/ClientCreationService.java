package creations;


import clients.Client;
import clients.DefaultClient;

public class ClientCreationService {
    private static class InnerHolder {
        public static final ClientCreationService CLIENT_CREATION_SERVICE = new ClientCreationService();
    }

    public static ClientCreationService getInstance() {
        return InnerHolder.CLIENT_CREATION_SERVICE;
    }

    public Client createClient(Integer id, String name, String inn, String ogrn, String bic, String acc, String corracc) {
        return new DefaultClient(id, name, inn, ogrn, bic, acc, corracc);
    }
}
