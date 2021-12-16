package servlet;

import entyty.Product;
import helpers.Path;
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
import java.util.Map;
import java.util.Optional;

public class ProductAddServlet extends HttpServlet {
    private final TemplateEngine te = TemplateEngine.folder();
    final Connection connection;
    final ProductService productService;
    final SecurityService securityService;

    public ProductAddServlet(Connection conn, SecurityService security) {
        connection = conn;
        securityService = security;
        productService = new ProductService(conn);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> data = new HashMap<>();
        Optional<Integer> idProduct = Path.getPath(req);

        if (idProduct.isPresent()){
            Optional<Product> oneProduct = productService.getOneProduct(idProduct.get());
            if (oneProduct.isPresent()){
                Product product = oneProduct.get();

                data.put("name", product.getName());
                data.put("price", product.getPrice());
                data.put("id", product.getId());
            }
        }

        te.render("/products_add.ftl", data, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String price = req.getParameter("price");
        Integer priceInteger = StringToInt.parseInt(price).get();
        Optional<Integer> idProduct = Path.getPath(req);

        if (idProduct.isPresent()){
            Integer id = idProduct.get();
            productService.updateProduct(id, name, priceInteger);

            resp.sendRedirect("/products");
        } else {
            productService.postProduct(name, priceInteger);
            resp.sendRedirect("/products/add");
        }
    }
}
