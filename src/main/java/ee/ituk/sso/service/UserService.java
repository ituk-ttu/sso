package ee.ituk.sso.service;

import ee.ituk.sso.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {

    public User findUserByUsername(String userName) {
        // TODO: remove hardcoded, replace with call to api
        User user = new User();
        user.setUsername("john");
        user.setRoles(new ArrayList<>());
        String realHash = new BCryptPasswordEncoder().encode("123");
        user.setEncryptedPassword("$2y$12$DUqVInC6xJM6p1DTNv.5guetEwVJiZC9PtbHJF8EEExbiSOBX/wBy");
        if (userName.equalsIgnoreCase("john")) user.setEncryptedPassword(realHash);
        System.out.println(user);
        return user;
    }

}
