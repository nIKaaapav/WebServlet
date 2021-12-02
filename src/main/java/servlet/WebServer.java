package servlet;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import db.DBConnection;

import java.sql.Connection;

public class WebServer {
    public void main() throws Exception {
        Connection connection = new DBConnection().getConnection();

        ServletContextHandler handler = new ServletContextHandler();
        handler.addServlet(new ServletHolder(new ProductAddServlet(connection)), "/products/add/*");
        handler.addServlet(new ServletHolder(new ProductServlet(connection)), "/products");

        Server server = new Server(8001);
        server.setHandler(handler);

        server.start();
        server.join();

    }
}
