package servlet;

import entyty.Product;
import helpers.RedirectHelper;
import helpers.StringToInt;
import servise.ProductService;
import servise.SecurityService;
import templete.TemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ProductServlet extends HttpServlet {
    private final TemplateEngine te = TemplateEngine.folder();
    final Connection connection;
    final ProductService productService;
    final SecurityService securityService;

    public ProductServlet(Connection conn, SecurityService security) {
        connection = conn;
        securityService = security;
        productService = new ProductService(conn);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Product> products = productService.getProducts();
            Map<String, Object> data = new HashMap<>();
            data.put("products", products);

            te.render("/products.ftl", data, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String delete = req.getParameter("delete");
        Optional<Integer> deleteInt = StringToInt.parseInt(delete);

        if (deleteInt.isPresent()){
            productService.deleteProduct(deleteInt.get());
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
