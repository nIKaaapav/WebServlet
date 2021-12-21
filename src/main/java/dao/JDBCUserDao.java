package dao;

import entyty.User;
import dao.mappers.UserMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class JDBCUserDao implements UserDao<User>{
    private final Connection connection;
    private static String selectByEmailPassword = "select * from public.users  where email = ? and password = ?";
    private static String set = "INSERT INTO public.users (password, email, name) VALUES (?, ?, ?)";


    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    public Optional<User> getUserByEmail(String email, String password) {
        try( PreparedStatement ps = connection.prepareStatement(selectByEmailPassword)) {
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()){
                return Optional.of(UserMapper.mapRows(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public void setUser(String email, String password, String name) {
        try( PreparedStatement ps = connection.prepareStatement(set)) {
            ps.setString(1, password);
            ps.setString(2, email);
            ps.setString(3, name);

             ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
