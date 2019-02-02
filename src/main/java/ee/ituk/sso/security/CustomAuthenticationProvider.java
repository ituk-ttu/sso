package ee.ituk.sso.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        String name = authentication.getName();
        Object credentials = authentication.getCredentials();
        System.out.println("credentials class: " + credentials.getClass());
        if (!(credentials instanceof String)) {
            return null;
        }
        String password = credentials.toString();
        System.out.println("password input: " + password);
        User user = null;
        if (name.equalsIgnoreCase("john")) user = new User("john", "$2y$11$fHcLdGhw302fMNnMvTL5feRzWIIcCVXLQ5b7dROcLBYJFT2I4.iju", "ADMIN");

        if (user == null) {
            throw new BadCredentialsException("Authentication failed for " + name);
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.role));
        return new UsernamePasswordAuthenticationToken(name, password, grantedAuthorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    private static class User {
        private String name;
        private String password;
        private String role;

        public User(String name, String password, String role) {
            this.name = name;
            this.password = password;
            this.role = role;
        }

        public boolean match(String name, String password) {
            return this.name.equals(name) && this.password.equals(password);
        }
    }

}
