package products;

public interface Product extends Cloneable {

    Integer getId();

    String getName();

    String getFullName();

    String getArticle();

    Product clone() throws CloneNotSupportedException;
}
