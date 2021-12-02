package dao;

import entyty.Product;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class ProductDao {
    private final Connection connection;

    private static String selectAll = "select * from products";
    private static String post = "insert into public.products(name, price, \"creationDate\") VALUES (?, ?, DEFAULT)";
    private static String delete = "delete from public.products where id = ?";
    private static String selectOne = "select * from public.products where id = ?";
    private static String update = "update public.products set name = ?, price = ?, \"creationDate\" = DEFAULT where id = ?";

    public ProductDao(Connection connection) {
        this.connection = connection;
    }

    public void postProduct(String name, int price){
        try( PreparedStatement ps = connection.prepareStatement(post)) {
            ps.setString(1, name);
            ps.setInt(2, price);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Product> getProducts(){
        ArrayList<Product> arrayProducts = new ArrayList();

        try( PreparedStatement ps = connection.prepareStatement(selectAll)) {
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int price = resultSet.getInt("price");
                Timestamp creationDateFromDB = resultSet.getTimestamp("creationDate");
                LocalDateTime localDateTime = creationDateFromDB.toLocalDateTime();
                arrayProducts.add(new Product(id, name, price, localDateTime));
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
       arrayProducts.forEach(x -> x.toString());
        return arrayProducts;

    }

    public void deleteProduct(int id){
        try( PreparedStatement ps = connection.prepareStatement(delete)) {
            ps.setInt(1, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<Product> getOneProduct(int id){
        try( PreparedStatement ps = connection.prepareStatement(selectOne)) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int idPerson = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int price = resultSet.getInt("price");
                Timestamp creationDateFromDB = resultSet.getTimestamp("creationDate");
                LocalDateTime localDateTime = creationDateFromDB.toLocalDateTime();

                return Optional.of(new Product(idPerson, name, price, localDateTime));
            }
            return Optional.empty();
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public void updateProduct(int id, String name, int price){
        try( PreparedStatement ps = connection.prepareStatement(update)) {
            ps.setString(1, name);
            ps.setInt(2, price);
            ps.setInt(3, id);

            ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
