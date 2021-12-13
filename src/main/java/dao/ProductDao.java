package dao;

import entyty.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao<M> {
    void postProduct(String name, int price);
    List<M> getProducts();
    void deleteProduct(int id);
    Optional<M> getOneProduct(int id);
    void updateProduct(int id, String name, int price);
}
