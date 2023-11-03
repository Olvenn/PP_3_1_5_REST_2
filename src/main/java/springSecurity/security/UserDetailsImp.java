package springSecurity.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import springSecurity.models.User;

import java.util.Collection;

public class UserDetailsImp implements UserDetails {
    private final User person;

    public UserDetailsImp(User person) {

        this.person = person;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO: return roles(or authorities) on the user ROLE_ADMIN or ROLE_USER
        return (Collection<? extends GrantedAuthority>) this.person.getRoles();
    }

    @Override
    public String getPassword() {
        return this.person.getPassword();
    }

    @Override
    public String getUsername() {
        return this.person.getUsername();
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

    public User getUser() {
        return this.person;
    }
}
