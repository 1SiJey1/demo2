package products;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectionImplementationProducts implements CollectionProducts, Serializable {

    private Collection<Product> products;

    public CollectionImplementationProducts(Collection<Product> products) {
        /*if (!products.isEmpty()) {
            throw new CollectionNotEmptyException("Collection isn't empty!");
        }*/
        this.products = products;
    }
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
    public Collection<Product> getAllProduct() {
        return Collections.unmodifiableCollection(this.products);
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
    public CollectionProducts clone() throws CloneNotSupportedException {
        CollectionImplementationProducts Products = new CollectionImplementationProducts(new ArrayList<>());
        for (Product product : this.products) {
            Products.addProduct(product.clone());
        }
        return Products;
    }

    @Override
    public void sort() {
        this.products = this.products.stream().sorted().collect(Collectors.toList());
    }

    @Override
    public void sort(Comparator<Product> comparator) {
        this.products = this.products.stream().sorted(comparator).collect(Collectors.toList());
    }

    @Override
    public Iterator<Product> iterator() {
        return this.products.iterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Product product : this.products) {
            sb.append(product.toString()).append("\n");

        }
        return sb.toString();
    }
}
