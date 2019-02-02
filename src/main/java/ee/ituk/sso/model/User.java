package ee.ituk.sso.model;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class User {
    private String username;
    private String encryptedPassword;
    private List<Role> roles;
}
