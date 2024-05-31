package prices;

import java.time.LocalDate;

public class DefaultPrice implements Price{

    private Integer id;
    private LocalDate date;
    private String name;
    private Double price;

    public DefaultPrice(Integer id, LocalDate date, String name, Double price) {
        this.id = id;
        this.date = date;
        this.name = name;
        this.price = price;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public LocalDate getDate() {
        return date;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Double getPrice() {
        return price;
    }

    @Override
    public Price clone() throws CloneNotSupportedException {
        return new DefaultPrice(this.id, this.date, this.name, this.price);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id = ").append(getId()).append(", ").append("date = ").append(getDate()).append(", ").append("name = ").append(getName()).append(", ").append("price = ").append(getPrice()).append(".");
        return sb.toString();
    }
}
