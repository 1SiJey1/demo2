package clients;

public class DefaultClient implements Client {

    private Integer id;
    private String name;
    private String inn;
    private String ogrn;
    private String bic;
    private String acc;
    private String corracc;


    public DefaultClient(Integer id, String name, String inn, String ogrn, String bic, String acc, String corracc) {
        this.id = id;
        this.name = name;
        this.inn = inn;
        this.ogrn = ogrn;
        this.bic = bic;
        this.acc = acc;
        this.corracc = corracc;
    }
    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getINN() {
        return inn;
    }

    @Override
    public String getOGRN() {
        return ogrn;
    }

    @Override
    public String getBIC() {
        return bic;
    }

    @Override
    public String getAcc() {
        return acc;
    }

    @Override
    public String getCorrAcc() {
        return corracc;
    }

    @Override
    public Client clone() throws CloneNotSupportedException {
        return new DefaultClient(this.id, this.name, this.inn, this.ogrn, this.bic, this.acc, this.corracc);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id = ").append(getId()).append(", ").append("name = ").append(getName()).append(", ").append("inn = ").append(getINN()).append(", ")
                .append("ogrn = ").append(getOGRN()).append("bic = ").append(getBIC()).append(", ").append("acc = ").append(getAcc()).append(", ")
                .append("corracc = ").append(getCorrAcc()).append(".");
        return sb.toString();
    }
}
