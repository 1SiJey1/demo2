package clients;

public interface Client extends Cloneable {

    Integer getId();

    String getName();

    String getINN();

    String getOGRN();

    String getBIC();

    String getAcc();

    String getCorrAcc();

    Client clone() throws CloneNotSupportedException;
}
