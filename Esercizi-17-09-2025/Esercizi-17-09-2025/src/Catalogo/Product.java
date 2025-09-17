package Catalogo;

import java.util.Objects;

public class Product {
    private Long id;
    private String name;
    private String category;
    private Double price;

    public Product(Long id, String name, String category, Double price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public Double getPrice() { return price; }

    @Override
    public String toString() {
        return String.format("Product" +
                "{id=%d, name='%s', cat='%s', price=%.2f}", id, name, category, price);
    }
    //per usare distinct() e mappe senza duplicati
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }
    @Override
    public int hashCode() { return Objects.hash(id); }
}

