package ait.supermarket.dao;

import ait.supermarket.model.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SupermarketImpl implements Supermarket {
    private Collection<Product> products = new ArrayList<>();

    @Override
    public Iterator<Product> iterator() {
        return products.iterator();
    }

    @Override
    public boolean addProduct(Product product) {
        if (product == null || products.contains(product)) {
            return false;
        }
        return products.add(product);
    }

    @Override
    public Product removeProduct(long barCode) {
        Product removed = findByBarCode(barCode);
        products.remove(removed);
        return removed;
    }
//================== HOME WORK ====================
    @Override
    public Product findByBarCode(long barCode) {
        return products.stream()
                .filter(product -> barCode == product.getBarCode())
                .findFirst()
                .orElse(null);
    }
//=================================================
    @Override
    public Iterable<Product> findByCategory(String category) {
        return findByPredicate(p -> category.equalsIgnoreCase(p.getCategory()));
    }

    @Override
    public Iterable<Product> findByBrand(String brand) {
        return findByPredicate(p -> brand.equalsIgnoreCase(p.getBrand()));
    }


    @Override
    public Iterable<Product> findProductsWithExpiredDate() {
        LocalDate current = LocalDate.now();
        return findByPredicate(p -> current.isAfter(p.getExpDate()));
    }

    @Override
    public Iterable<Product> findProductWithExpDate() {
        LocalDate current = LocalDate.now();
        return findByPredicate(p -> current.isAfter(p.getExpDate()));
    }
//================== HOME WORK ====================
    public Iterable<Product> findByPredicate(Predicate<Product> predicate) {
        return products.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }
//==================================================
    @Override
    public int skuQuantity() {
        return products.size();
    }
}
