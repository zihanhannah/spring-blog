package com.codeup.blog;

import com.codeup.blog.services.UserDetailsLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private UserDetailsLoader usersLoader;

    public SecurityConfiguration(UserDetailsLoader usersLoader) {
        this.usersLoader = usersLoader;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(usersLoader)  // How to find users by their username
                .passwordEncoder(passwordEncoder()) // How to encode and decode/verify passwords
        ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                /********* Login Configuration *********/
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/posts") // when they successfully log in, redirect to /posts
                .permitAll() // anyone can go to the login page
                /********** Logout Configuration **********/
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout") // we set a parameter 'logout', so we can display a message to the user on the logout page "Congrats on successfully logging out!"
                /********** Pages that can be viewed by anyone ************/
                .and()
                .authorizeRequests() // let visitors view pages, based on the next argument
                .antMatchers("/","/posts") // Another Neat Tool matcher - if someone hits these URLs in their browser (i
                // .e.
                // http://localhost:8080/posts) they are allowed to view, even if not logged in
                .permitAll()  // like a catch-all
                /************** Pages that DO require authentication ***********/
                .and()
                .authorizeRequests()
                .antMatchers("/posts/create", "/posts/{id}/edit") // pages that we DO want users to be logged in to
                // view/access
                .authenticated() // for the previously mentioned Another Neat Tool Matched URL patterns, users should be authenticated (logged in) to access them

        ;

    }
}
