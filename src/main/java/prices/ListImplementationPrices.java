package prices;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Stream;

public class ListImplementationPrices implements ListPrices, Serializable {

    private List<Price> prices = new ArrayList<>();
    @Override
    public boolean addPrices(CollectionPrices prices) {
        return this.prices.addAll(prices.getAllPrice());
    }



    @Override
    public Stream<Price> stream() {
        return this.prices.stream();
    }

    @Override
    public boolean addPrice(Price price) {
        return prices.add(price);
    }

    @Override
    public boolean removePrice(Price price) {
        return removePrice(price.getId());
    }

    @Override
    public boolean removePrice(Integer id) {
        for (Price price : prices) {
            if (price.getId().equals(id)) {
                return prices.remove(price);
            }
        }
        return false;
    }

    @Override
    public List<Price> getAllPrice() {
        return Collections.unmodifiableList(this.prices);
    }

    @Override
    public Optional<Price> getPriceById(Integer id) {
        for (Price price : prices) {
            if (price.getId().equals(id)) {
                return Optional.of(price);
            }
        }
        return Optional.empty();
    }

    @Override
    public ListPrices clone() throws CloneNotSupportedException {
        ListImplementationPrices Prices = new ListImplementationPrices();
        for (Price price : this.prices) {
            Prices.addPrice(price.clone());
        }
        return Prices;
    }

    @Override
    public void sort() {

    }

    @Override
    public void sort(Comparator<Price> comparator) {
        this.prices.sort(comparator);
    }

    @Override
    public Iterator<Price> iterator() {
        return this.prices.iterator();
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Price price:this.prices){
            sb.append(price.toString()).append("\n");

        }
        return sb.toString();
    }

}
