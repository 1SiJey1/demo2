package documentLists;

import documents.RealizationDocument;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

public interface ListRealizationDocuments extends Cloneable, Iterable<RealizationDocument> {

    Stream<RealizationDocument> stream();

    boolean addRealizationDocument(RealizationDocument realizationDocument);

    boolean removeRealizationDocument(RealizationDocument realizationDocument);

    boolean removeRealizationDocument(Integer id);

    Optional<RealizationDocument> getRealizationDocumentById(Integer id);

    void sort();

    void sort(Comparator<RealizationDocument> comparator);

}
