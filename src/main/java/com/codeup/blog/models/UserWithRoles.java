package com.codeup.blog.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserWithRoles extends User implements UserDetails {
    // Copy Constructor - for a User object
    // This is so we can use polymorphism to have an object that looks EXACTLY like a user to other parts of our code, but can do additional things, like those in the UserDetails interface.
    public UserWithRoles(User user) {
        super(user);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String roles = "";
        // When you actually want to implement roles, you put them in that string separated by commas
        return AuthorityUtils.commaSeparatedStringToAuthorityList(roles); // this will return NADA roles. (none roles)
        // no roles will be returned. none.
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // Just say that all passwords can last forever, without ever needing to be changed.
    //  (JUST FOR THIS APP! BECAUSE WE DON'T NEED IT! YOU'LL STILL USE THIS FOR REAL, IN THE REAL WORLD!)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
