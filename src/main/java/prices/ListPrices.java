package prices;

public interface ListPrices extends CollectionPrices, Cloneable, Iterable<Price>{
    ListPrices clone() throws CloneNotSupportedException;
}
