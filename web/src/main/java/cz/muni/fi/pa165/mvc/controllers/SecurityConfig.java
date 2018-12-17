package cz.muni.fi.pa165.mvc.controllers;


import cz.muni.fi.pa165.mvc.controllers.WebUrls;
import cz.muni.fi.pa165.facade.UserFacade;
import cz.muni.fi.pa165.dto.UserDTO;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.inject.Inject;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserFacade userFacade;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        List<UserDTO> users = userFacade.getAllUser();

        for (UserDTO user : users) {
            auth.inMemoryAuthentication()
                    .withUser(user.getPassword())
                    .password(user.getPassword())
                    .roles(userFacade.getUserById(user.getId()).getRole().getName().equals(WebUrls.Admin) ? WebUrls.Admin : WebUrls.User);
        }
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()

                //User controller
                .antMatchers(WebUrls.URL_USER + "/").hasAnyRole("ADMIN")
                .antMatchers(WebUrls.URL_USER + "/all").hasAnyRole("ADMIN")
                .antMatchers(WebUrls.URL_USER + "/create").hasAnyRole("ADMIN", "USER")
                .antMatchers(WebUrls.URL_USER + "/*/update").hasAnyRole("ADMIN")
                .antMatchers(WebUrls.URL_USER + "/*/delete").hasAnyRole("ADMIN")
                .antMatchers(WebUrls.URL_USER + "/**").hasAnyRole("ADMIN")


                // Others
                .antMatchers(WebUrls.URL_LOGIN + "*").permitAll()
                .antMatchers(WebUrls.NOT_FOUND).permitAll()
                .anyRequest().authenticated()

                // Login
                .and()
                .formLogin()
                .loginPage(WebUrls.URL_LOGIN)
                .loginProcessingUrl(WebUrls.URL_LOGIN)
                .failureUrl(WebUrls.URL_LOGIN + "?error=true")
                .usernameParameter("user_login").passwordParameter("user_password")
                .defaultSuccessUrl(WebUrls.URL_HOME, true)
                .permitAll()

                // Logout
                .and()
                .logout()
                .logoutUrl(WebUrls.URL_LOGOUT)
                .deleteCookies("")
                .permitAll();
    }


}