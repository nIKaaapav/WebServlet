package db;

import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    public static final String URL = "jdbc:postgresql://ec2-54-228-99-58.eu-west-1.compute.amazonaws.com:5432/de94fp2qpjp9l7?password=6390225dd44ea0ad4e576f7ec61c9ea6ea231a4a78704c560daefee9a0e87e2a&sslmode=require&user=sgrueeaeocjrkg";
    public static final String USER = "sgrueeaeocjrkg";
    public static final String PASS = "6390225dd44ea0ad4e576f7ec61c9ea6ea231a4a78704c560daefee9a0e87e2a";

    public Connection getConnection(){
        try {
            Properties properties = new Properties();
            properties.setProperty("user", USER);
            properties.setProperty("pass", PASS);

            return new Driver().connect(URL, properties );
        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
