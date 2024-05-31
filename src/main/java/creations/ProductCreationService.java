package creations;

import products.DefaultProduct;
import products.Product;

public class ProductCreationService {

    private static class InnerHolder {
        public static final ProductCreationService PRODUCT_CREATION_SERVICE = new ProductCreationService();
    }

    public static ProductCreationService getInstance() {
        return InnerHolder.PRODUCT_CREATION_SERVICE;
    }

    public Product createProduct(Integer id, String name, String fullname, String article) {
        return new DefaultProduct(id, name, fullname, article);
    }
}
