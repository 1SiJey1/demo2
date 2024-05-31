package documentLists;

import documents.RealizationDocument;

import java.util.*;
import java.util.stream.Stream;

public class ListImplementationRealizationDocuments implements ListRealizationDocuments{
    private List<RealizationDocument> realizationDocuments = new ArrayList<>();

    /*@Override
    public boolean addPurchasingDocuments(CollectionPurchasingDocuments purchasingRecords) {
        return this.purchasingDocuments.addAll(purchasingDocuments.getAllPurchasingDocument());
    }*/

    @Override
    public Stream<RealizationDocument> stream() {
        return this.realizationDocuments.stream();
    }

    @Override
    public boolean addRealizationDocument(RealizationDocument realizationDocument) {
        return realizationDocuments.add(realizationDocument);
    }

    @Override
    public boolean removeRealizationDocument(RealizationDocument realizationDocument) {
        return removeRealizationDocument(realizationDocument.getId());
    }

    @Override
    public boolean removeRealizationDocument(Integer id) {
        for (RealizationDocument realizationDocument : realizationDocuments) {
            if (realizationDocument.getId().equals(id)) {
                return realizationDocuments.remove(realizationDocument);
            }
        }
        return false;
    }

    /*@Override
    public List<PurchasingDocument> getAllPurchasingDocument() {
        return Collections.unmodifiableList(this.purchasingDocuments);
    }*/

    @Override
    public Optional<RealizationDocument> getRealizationDocumentById(Integer id) {
        for (RealizationDocument realizationDocument : realizationDocuments) {
            if (realizationDocument.getId().equals(id)) {
                return Optional.of(realizationDocument);
            }
        }
        return Optional.empty();
    }

    @Override
    public void sort() {

    }

    @Override
    public void sort(Comparator<RealizationDocument> comparator) {
        this.realizationDocuments.sort(comparator);
    }

    @Override
    public ListRealizationDocuments clone() throws CloneNotSupportedException {
        ListImplementationRealizationDocuments RealizationDocuments = new ListImplementationRealizationDocuments();
        for (RealizationDocument realizationDocument : this.realizationDocuments) {
            RealizationDocuments.addRealizationDocument(realizationDocument.clone());
        }
        return RealizationDocuments;
    }

    @Override
    public Iterator<RealizationDocument> iterator() {
        return this.realizationDocuments.iterator();
    }
}
