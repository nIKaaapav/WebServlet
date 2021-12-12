package servlet;

import helpers.CookiesHelper;
import helpers.RedirectHelper;
import servise.SecurityService;
import servise.UserService;
import templete.TemplateEngine;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class LoginServlet extends HttpServlet {
    private final TemplateEngine te = TemplateEngine.folder();
    final Connection connection;
    final UserService userService;
    final SecurityService securityService;

    public LoginServlet(Connection conn, SecurityService security) {
        connection = conn;
        securityService = security;

        userService = new UserService(conn);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String, Object> data = new HashMap<>();
        Cookie[] cookies = req.getCookies();

        Optional<String> tokenFromCookie = CookiesHelper.getTokenFromCookie(cookies);

        if (tokenFromCookie.isPresent() && securityService.isUserHaveToken(tokenFromCookie.get())){
            resp.sendRedirect("/products");
        }

        te.render("/login.ftl", data, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String password  = req.getParameter("password");
        String email = req.getParameter("email");

        boolean userByEmail = userService.getUserByEmail(email, password);

        if (userByEmail){
            String token = securityService.setTokenByUser();
            Cookie cookie = CookiesHelper.setCookies(token);

            resp.addCookie(cookie);
            resp.sendRedirect("/products");
        } else {
            Map<String, Object> data = new HashMap<>();
            data.put("errorMessage", "Email does not exist!");
            te.render("/login.ftl", data, resp);
        }

    }
}
