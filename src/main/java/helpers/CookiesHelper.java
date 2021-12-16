package helpers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class CookiesHelper {
    private static final String cookieName = "user-token";

    public static Cookie setCookies(String token){
        return new Cookie(cookieName, token);
    }

    public static Optional<String> getTokenFromCookie(Cookie[] cookies){
        String token = "";

        if (cookies != null){
            for (Cookie cookie:cookies) {
                if(cookie.getName().equals(cookieName)){
                    token = cookie.getValue();
                    return Optional.of(token);
                }
            }
        }

        return Optional.empty();
    }

}
