package servlet;

import entyty.Product;
import servise.ProductService;
import templete.TemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.*;

public class ProductServlet extends HttpServlet {
    private final TemplateEngine te = TemplateEngine.folder();
    final Connection connection;
    final ProductService service;

    public ProductServlet(Connection conn) {
        connection = conn;
        service = new ProductService(conn);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
            List<Product> products = service.getProducts();
            Map<String, Object> data = new HashMap<>();
            data.put("products", products);

            te.render("/products.ftl", data, resp);
    }

    private static int toInt (String s){
        return Integer.parseInt(s);
    }

    private static Optional<Integer> parseInt(String s){
        try{
            int value = toInt(s);
            return Optional.of(value);
        } catch (NumberFormatException x){
            return  Optional.empty();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String delete = req.getParameter("delete");
        Optional<Integer> deleteInt = parseInt(delete);

        if (deleteInt.isPresent()){
            service.deleteProduct(deleteInt.get());
        }

        resp.sendRedirect("/products");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String delete = req.getParameter("delete");
        if (delete != null){
            doDelete(req, resp);
        }
    }
}
