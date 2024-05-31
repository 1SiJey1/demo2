package documentLists;

import documents.PurchasingDocument;

import java.util.*;
import java.util.stream.Stream;

public class ListImplementationPurchasingDocuments  implements ListPurchasingDocuments{
    private List<PurchasingDocument> purchasingDocuments = new ArrayList<>();

    /*@Override
    public boolean addPurchasingDocuments(CollectionPurchasingDocuments purchasingRecords) {
        return this.purchasingDocuments.addAll(purchasingDocuments.getAllPurchasingDocument());
    }*/

    @Override
    public Stream<PurchasingDocument> stream() {
        return this.purchasingDocuments.stream();
    }

    @Override
    public boolean addPurchasingDocument(PurchasingDocument purchasingDocument) {
        return purchasingDocuments.add(purchasingDocument);
    }

    @Override
    public boolean removePurchasingDocument(PurchasingDocument purchasingDocument) {
        return removePurchasingDocument(purchasingDocument.getId());
    }

    @Override
    public boolean removePurchasingDocument(Integer id) {
        for (PurchasingDocument purchasingDocument : purchasingDocuments) {
            if (purchasingDocument.getId().equals(id)) {
                return purchasingDocuments.remove(purchasingDocument);
            }
        }
        return false;
    }

    /*@Override
    public List<PurchasingDocument> getAllPurchasingDocument() {
        return Collections.unmodifiableList(this.purchasingDocuments);
    }*/

    @Override
    public Optional<PurchasingDocument> getPurchasingDocumentById(Integer id) {
        for (PurchasingDocument purchasingDocument : purchasingDocuments) {
            if (purchasingDocument.getId().equals(id)) {
                return Optional.of(purchasingDocument);
            }
        }
        return Optional.empty();
    }

    @Override
    public void sort() {

    }

    @Override
    public void sort(Comparator<PurchasingDocument> comparator) {
        this.purchasingDocuments.sort(comparator);
    }

    @Override
    public ListPurchasingDocuments clone() throws CloneNotSupportedException {
        ListImplementationPurchasingDocuments PurchasingDocuments = new ListImplementationPurchasingDocuments();
        for (PurchasingDocument purchasingDocument : this.purchasingDocuments) {
            PurchasingDocuments.addPurchasingDocument(purchasingDocument.clone());
        }
        return PurchasingDocuments;
    }

    @Override
    public Iterator<PurchasingDocument> iterator() {
        return this.purchasingDocuments.iterator();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (PurchasingDocument document:this.purchasingDocuments){
            sb.append(document.toString()).append("\n");

        }
        return sb.toString();
    }
}
