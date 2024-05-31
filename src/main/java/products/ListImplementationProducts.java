package products;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Stream;

public class ListImplementationProducts implements ListProducts, Serializable {

    private List<Product> products = new ArrayList<>();
    @Override
    public boolean addProducts(CollectionProducts products) {
        return this.products.addAll(products.getAllProduct());
    }



    @Override
    public Stream<Product> stream() {
        return this.products.stream();
    }

    @Override
    public boolean addProduct(Product product) {
        return products.add(product);
    }

    @Override
    public boolean removeProduct(Product product) {
        return removeProduct(product.getId());
    }

    @Override
    public boolean removeProduct(Integer id) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return products.remove(product);
            }
        }
        return false;
    }

    @Override
    public List<Product> getAllProduct() {
        return Collections.unmodifiableList(this.products);
    }

    @Override
    public Optional<Product> getProductById(Integer id) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return Optional.of(product);
            }
        }
        return Optional.empty();
    }

    @Override
    public ListProducts clone() throws CloneNotSupportedException {
        ListImplementationProducts Products = new ListImplementationProducts();
        for (Product product : this.products) {
            Products.addProduct(product.clone());
        }
        return Products;
    }

    @Override
    public void sort() {

    }

    @Override
    public void sort(Comparator<Product> comparator) {
        this.products.sort(comparator);
    }

    @Override
    public Iterator<Product> iterator() {
        return this.products.iterator();
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Product product:this.products){
            sb.append(product.toString()).append("\n");

        }
        return sb.toString();
    }

}
