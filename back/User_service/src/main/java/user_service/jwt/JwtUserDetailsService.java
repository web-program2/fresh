package user_service.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import user_service.jpa.UserRepo2;
import user_service.jpa.Users;

import java.util.HashSet;
import java.util.Set;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepo2 userRepo2;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Users users = userRepo2.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException(id));
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(Role.USER.getValue()));
        if (id.equals("adminnn")) {
            grantedAuthorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        }

        return new User(users.getId(), users.getPw(), grantedAuthorities);
    }

    public Users authenticateByIdAndPassword(String id, String password) {
        Users users = userRepo2.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException(id));

        if(!passwordEncoder.matches(password, users.getPw())) {
            throw new BadCredentialsException("Password not matched");
        }

        return users;
    }
}
