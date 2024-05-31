package creations;

import prices.DefaultPrice;
import prices.Price;

import java.time.LocalDate;


public class PriceCreationService {

    private static class InnerHolder {
        public static final PriceCreationService PRICE_CREATION_SERVICE = new PriceCreationService();
    }

    public static PriceCreationService getInstance() {
        return InnerHolder.PRICE_CREATION_SERVICE;
    }

    public Price createPrice(Integer id, LocalDate date, String name, Double price) {
        return new DefaultPrice(id, date, name, price);
    }
}
