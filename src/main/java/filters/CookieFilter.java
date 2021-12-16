package filters;

import helpers.CookiesHelper;
import servise.SecurityService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class CookieFilter implements HttpsFilters{
    final SecurityService securityService;

    public CookieFilter(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    public void doHttpFilter(HttpServletRequest rq, HttpServletResponse rs, FilterChain chain) throws ServletException, IOException {
        Cookie[] cookies = rq.getCookies();
        Optional<String> tokenFromCookie = CookiesHelper.getTokenFromCookie(cookies);

        if (tokenFromCookie.isPresent() && securityService.isUserHaveToken(tokenFromCookie.get())) {
            chain.doFilter(rq, rs);
        } else {
            rs.setStatus(401);
            rs.sendRedirect("/login");
        }
    }
}
