package servlet;

import entyty.Product;
import helpers.RedirectHelper;
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
        String token = RedirectHelper.checkIsExistTokenInCookie(req, resp);

        RedirectHelper.redirectToLoginIfTokenExistInDB(!securityService.isUserHaveToken(token), resp);

        List<Product> products = productService.getProducts();
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
        String token = RedirectHelper.checkIsExistTokenInCookie(req, resp);

        RedirectHelper.redirectToLoginIfTokenExistInDB(!securityService.isUserHaveToken(token), resp);

        String delete = req.getParameter("delete");
        Optional<Integer> deleteInt = parseInt(delete);

        if (deleteInt.isPresent()){
            productService.deleteProduct(deleteInt.get());
        }

        resp.sendRedirect("/products");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = RedirectHelper.checkIsExistTokenInCookie(req, resp);

        RedirectHelper.redirectToLoginIfTokenExistInDB(!securityService.isUserHaveToken(token), resp);

        String delete = req.getParameter("delete");
        if (delete != null){
            doDelete(req, resp);
        }
    }
}
