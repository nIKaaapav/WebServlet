package entyty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class User {
    private String name;
    private String email;
    private String password;
    private int id;
}
