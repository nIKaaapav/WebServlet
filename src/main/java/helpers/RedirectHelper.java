package helpers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class RedirectHelper {
    public static String checkIsExistTokenInCookie(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Cookie[] cookies = req.getCookies();
        Optional<String> tokenFromCookie = CookiesHelper.getTokenFromCookie(cookies);

        if (!tokenFromCookie.isPresent()){
            resp.sendRedirect("/login");
        }

        return tokenFromCookie.get();
    }

    public static void redirectToLoginIfTokenExistInDB(boolean isExistTokenInDB, HttpServletResponse resp) throws IOException {
        if (isExistTokenInDB){
            resp.sendRedirect("/login");
        }
    }
}
