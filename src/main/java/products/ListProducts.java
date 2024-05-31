package products;

public interface ListProducts extends CollectionProducts, Cloneable, Iterable<Product>{
    ListProducts clone() throws CloneNotSupportedException;
}
