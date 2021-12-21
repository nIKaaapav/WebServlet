package servise;

import dao.JDBCProductDao;
import entyty.Product;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class ProductService {
    private final JDBCProductDao productDAOSQL;

    public ProductService(Connection connection) {
        this.productDAOSQL = new JDBCProductDao(connection);
    }

    public List<Product> getProducts(){
        return productDAOSQL.getProducts();
    }

    public Optional<Product> getOneProduct(int id){
        return productDAOSQL.getOneProduct(id);
    }

    public void postProduct(String name, int price){
        productDAOSQL.postProduct(name, price);
    }

    public void deleteProduct(int id){
        productDAOSQL.deleteProduct(id);
    }

    public void updateProduct(int id, String name, int price){
        productDAOSQL.updateProduct(id, name, price);
    }
}
