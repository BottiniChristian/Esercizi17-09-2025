package Catalogo;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        //creazione prodotti
        Product p1 = new Product(1L, "Java Book",        "Books",        110.0);
        Product p2 = new Product(2L, "Baby Toy",         "Baby",          40.0);
        Product p3 = new Product(3L, "Laptop",           "Electronics",  1200.0);
        Product p4 = new Product(4L, "Cooking Book",     "Books",         70.0);
        Product p5 = new Product(5L, "Bottle",           "Baby",          20.0);
        Product p6 = new Product(6L, "Soccer Shirt",     "Boys",          100.0);
        Product p7 = new Product(7L, "Action Figure",    "Boys",          50.0);
        Product p8 = new Product(8L, "Algorithms Book",  "Books",        120.0);

        List<Product> allProducts = Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8);

        //creazione clienti
        Customer c1 = new Customer(1L, "Roberto Baggio", 1);
        Customer c2 = new Customer(2L, "Alessandro Del Piero", 2);
        Customer c3 = new Customer(3L, "Giorgio Chiellini",  2);

        //creazione ordini
        Order o1 = new Order(1L, "Delivered",
                LocalDate.of(2021, 2, 10), LocalDate.of(2021, 2, 15),
                Arrays.asList(p1, p2, p6), c1);

        Order o2 = new Order(2L, "Shipped",
                LocalDate.of(2021, 3, 5), LocalDate.of(2021, 3, 10),
                Arrays.asList(p3, p5, p7), c2);

        Order o3 = new Order(3L, "Processing",
                LocalDate.of(2021, 4, 1), LocalDate.of(2021, 4, 6),
                Arrays.asList(p4, p6, p8), c2);

        Order o4 = new Order(4L, "Delivered",
                LocalDate.of(2021, 1, 20), LocalDate.of(2021, 1, 25),
                Arrays.asList(p7, p8), c3);

        List<Order> allOrders = Arrays.asList(o1, o2, o3, o4);

        //Esercizio #1: Products categoria "Books" con prezzo > 100
        List<Product> ex1 = allProducts.stream()
                .filter(p -> "Books".equalsIgnoreCase(p.getCategory()))
                .filter(p -> p.getPrice() > 100)
                .collect(Collectors.toList());

        //Esercizio #2: Ordini con almeno un prodotto categoria "Baby"
        List<Order> ex2 = allOrders.stream()
                .filter(o -> o.getProducts().stream()
                        .anyMatch(p -> "Baby".equalsIgnoreCase(p.getCategory())))
                .collect(Collectors.toList());

        //Esercizio #3: Prodotti categoria "Boys" con sconto 10% (copie scontate)
        List<Product> ex3 = allProducts.stream()
                .filter(p -> "Boys".equalsIgnoreCase(p.getCategory()))
                .map(p -> new Product(p.getId(), p.getName(), p.getCategory(), p.getPrice() * 0.90))
                .collect(Collectors.toList());

        //Esercizio #4: Prodotti ordinati da clienti tier = 2 tra 01-Feb-2021 e 01-Apr-2021
        LocalDate start = LocalDate.of(2021, 2, 1);
        LocalDate end   = LocalDate.of(2021, 4, 1);
        List<Product> ex4 = allOrders.stream()
                .filter(o -> o.getCustomer().getTier() == 2)
                .filter(o -> !o.getOrderDate().isBefore(start) && !o.getOrderDate().isAfter(end))
                .flatMap(o -> o.getProducts().stream())
                .distinct() // grazie a equals/hashCode su Product (per id)
                .collect(Collectors.toList());

        //stampa risultati
        printSection("Esercizio #1 — Books con prezzo > 100", ex1);
        printSection("Esercizio #2 — Ordini con prodotti 'Baby'", ex2);
        printSection("Esercizio #3 — Prodotti 'Boys' scontati 10%", ex3);
        printSection("Esercizio #4 — Prodotti ordinati da clienti tier = 2 tra 01-Feb e 01-Apr 2021", ex4);
    }

    private static <T> void printSection(String title, List<T> list) {
        System.out.println("\n=== " + title + " ===");
        if (list.isEmpty()) {
            System.out.println("(nessun risultato)");
            return;
        }
        list.forEach(System.out::println);
    }
}
