package products;

public class DefaultProduct implements Product{

    private Integer id;
    private String name;
    private String fullname;
    private String article;

    public DefaultProduct(Integer id, String name, String fullname, String article) {
        this.id = id;
        this.name = name;
        this.fullname = fullname;
        this.article = article;
    }

    /*public DefaultProduct(Integer id, String name, String fullname, String article) {
        this(id, name, fullname, article);
        this.status = status;
    }*/

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getFullName() {
        return fullname;
    }

    @Override
    public String getArticle() {
        return article;
    }

    @Override
    public Product clone() throws CloneNotSupportedException {
        return new DefaultProduct(this.id, this.name, this.fullname, this.article);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id = ").append(getId()).append(", ").append("name = ").append(getName()).append(", ").append("fullname = ").append(getFullName()).append(", ").append("article = ").append(getArticle()).append(".");
        return sb.toString();
    }
}
