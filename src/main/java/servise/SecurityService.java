package servise;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SecurityService {
    private List<String> tokens = new ArrayList<>();

    public boolean isUserHaveToken(String token){
        return tokens.contains(token);
    }

    public String setTokenByUser(){
        String userToken = UUID.randomUUID().toString();

        tokens.add(userToken);
        return userToken;
    }
}
