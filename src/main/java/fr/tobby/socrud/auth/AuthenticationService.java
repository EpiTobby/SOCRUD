package fr.tobby.socrud.auth;

import fr.tobby.socrud.entity.AdminEntity;
import fr.tobby.socrud.repository.AdminRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.List;


@Component
public class AuthenticationService implements UserDetailsService {

    private final AdminRepository repository;

    public AuthenticationService(final AdminRepository repository)
    {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException
    {
        AdminEntity admin = repository.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new UserDetailsImpl(
                admin.getId(),
                admin.getLogin(),
                admin.getPassword());
    }
}

class UserDetailsImpl implements UserDetails {
    private final long id;
    private final String username;
    private final String password;

    public UserDetailsImpl(final long id, final String username, final String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return "admin";
            }
        });
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
