package servlet;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import db.DBConnection;
import servise.SecurityService;

import java.sql.Connection;

public class WebServer {
    public void main() throws Exception {
        Connection connection = new DBConnection().getConnection();
        SecurityService securityService = new SecurityService();

        ServletContextHandler handler = new ServletContextHandler();
        handler.addServlet(new ServletHolder(new ProductAddServlet(connection, securityService)), "/products/add/*");
        handler.addServlet(new ServletHolder(new ProductServlet(connection, securityService)), "/products");
        handler.addServlet(new ServletHolder(new LoginServlet(connection, securityService)), "/login");
        handler.addServlet(new ServletHolder(new RegisterServlet(connection, securityService)), "/register");

        Server server = new Server(8001);
        server.setHandler(handler);

        server.start();
        server.join();

    }
}
