package ee.ituk.sso.security;

import ee.ituk.sso.model.Role;
import ee.ituk.sso.model.User;
import ee.ituk.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        System.out.println("Loading....");
        User user = userService.findUserByUsername(username);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        // TODO: usernotfoundexception

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getEncryptedPassword(), grantedAuthorities);
    }
}