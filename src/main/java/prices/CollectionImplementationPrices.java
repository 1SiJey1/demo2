package prices;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectionImplementationPrices implements CollectionPrices, Serializable {

    private Collection<Price> prices;

    public CollectionImplementationPrices(Collection<Price> prices) {
        /*if (!prices.isEmpty()) {
            throw new CollectionNotEmptyException("Collection isn't empty!");
        }*/
        this.prices = prices;
    }
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
    public Collection<Price> getAllPrice() {
        return Collections.unmodifiableCollection(this.prices);
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
public CollectionPrices clone() throws CloneNotSupportedException {
    CollectionImplementationPrices Prices = new CollectionImplementationPrices(new ArrayList<>());
    for (Price price : this.prices) {
        Prices.addPrice(price.clone());
    }
    return Prices;
}

@Override
public void sort() {
    this.prices = this.prices.stream().sorted().collect(Collectors.toList());
}

@Override
public void sort(Comparator<Price> comparator) {
    this.prices = this.prices.stream().sorted(comparator).collect(Collectors.toList());
}

@Override
public Iterator<Price> iterator() {
    return this.prices.iterator();
}

@Override
public String toString() {
    StringBuilder sb = new StringBuilder();
    for (Price price : this.prices) {
        sb.append(price.toString()).append("\n");

    }
    return sb.toString();
}
}