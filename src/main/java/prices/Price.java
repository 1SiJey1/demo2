package prices;

import java.time.LocalDate;

public interface Price extends Cloneable{

    Integer getId();

    LocalDate getDate();

    String getName();

    Double getPrice();

    Price clone() throws CloneNotSupportedException;
}
