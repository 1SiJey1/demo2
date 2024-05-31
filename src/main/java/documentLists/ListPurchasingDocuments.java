package documentLists;

import documents.PurchasingDocument;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

public interface ListPurchasingDocuments extends Cloneable, Iterable<PurchasingDocument> {

    Stream<PurchasingDocument> stream();

    boolean addPurchasingDocument(PurchasingDocument purchasingDocument);

    boolean removePurchasingDocument(PurchasingDocument purchasingDocument);

    boolean removePurchasingDocument(Integer id);

    Optional<PurchasingDocument> getPurchasingDocumentById(Integer id);

    void sort();

    void sort(Comparator<PurchasingDocument> comparator);

}
