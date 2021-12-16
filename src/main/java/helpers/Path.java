package helpers;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class Path {
    public static Optional<Integer> getPath(HttpServletRequest rq) {
        String path = rq.getPathInfo();
        try {
            if (path.charAt(0) == '/') {
                path = path.substring(1);
            }
            if (StringToInt.parseInt(path).isPresent()) {
                return Optional.of(StringToInt.parseInt(path).get());
            } else {
                return Optional.empty();
            }
        } catch (NullPointerException e){
            return Optional.empty();
        }
    }
}
