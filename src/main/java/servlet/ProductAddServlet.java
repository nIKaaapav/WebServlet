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
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ProductAddServlet extends HttpServlet {
    private final TemplateEngine te = TemplateEngine.folder();
    final Connection connection;
    final ProductService service;

    public ProductAddServlet(Connection conn) {
        connection = conn;
        service = new ProductService(conn);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> data = new HashMap<>();
        Optional<Integer> idProduct = getPath(req);

        if (idProduct.isPresent()){
            Optional<Product> oneProduct = service.getOneProduct(idProduct.get());
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
        Integer priceInteger = parseInt(price).get();
        Optional<Integer> idProduct = getPath(req);
        if (idProduct.isPresent()){
            Integer id = idProduct.get();
            service.updateProduct(id, name, priceInteger);

            resp.sendRedirect("/products");
        } else {
            service.postProduct(name, priceInteger);
            resp.sendRedirect("/products/add");
        }
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

    private static Optional<Integer> getPath(HttpServletRequest rq) {
        String path = rq.getPathInfo();
        try {
            if (path.charAt(0) == '/') {
                path = path.substring(1);
            }
            if (parseInt(path).isPresent()) {
                return Optional.of(parseInt(path).get());
            } else {
                return Optional.empty();
            }
        } catch (NullPointerException e){
            return Optional.empty();
        }
    }
}
