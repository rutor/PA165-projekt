package cz.muni.fi.pa165.mvc.config;


import cz.muni.fi.pa165.dto.RoleDTO;
import cz.muni.fi.pa165.dto.UserAuthenticateDTO;
import cz.muni.fi.pa165.dto.UserDTO;
import cz.muni.fi.pa165.enums.AuthenticateUserStatus;
import cz.muni.fi.pa165.facade.RoleFacade;
import cz.muni.fi.pa165.facade.UserFacade;
import cz.muni.fi.pa165.mvc.controllers.WebUrls;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Inject
    private UserFacade userFacade;

    @Inject
    private RoleFacade roleFacade;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(new AuthenticationProvider() {
            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                String userEmail = "";
                String password = "";
                Set<GrantedAuthority> authorities = new HashSet<>();
                authorities.add(new SimpleGrantedAuthority("ROLE_" + "Guest"));

                Object principal = authentication.getPrincipal();
                if (principal instanceof String) {
                    userEmail = (String) principal;
                }
                Object credentials = authentication.getCredentials();
                if (credentials instanceof String) {
                    password = (String) credentials;
                }

                Enum<AuthenticateUserStatus> authenticationStatus = userFacade.authenticate(new UserAuthenticateDTO(userEmail, password));
                if (authenticationStatus == AuthenticateUserStatus.OK) {
                    UserDTO userByEmail = userFacade.getUserByEmail(userEmail);
                    RoleDTO roleById = roleFacade.getRoleById(userByEmail.getRole().getId());
                    if (roleById != null) {
                        authorities.add(new SimpleGrantedAuthority("ROLE_" + roleById.getName()));
                    }
                    return new UsernamePasswordAuthenticationToken(principal, credentials, authorities);
                }
                return null;
            }

            @Override
            public boolean supports(Class<?> authentication) {
                return true;
            }
        });
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            // Permit anonymous access
            .antMatchers("/").permitAll()
            .antMatchers("/*.css").permitAll()
            .antMatchers("/pictures/*.jpg").permitAll()
            .antMatchers("/pictures/*.png").permitAll()
            .antMatchers(WebUrls.URL_AUTH + WebUrls.URL_LOGIN).permitAll()
            .antMatchers(WebUrls.URL_AUTH + WebUrls.URL_LOGIN + "/").permitAll()
            .antMatchers(WebUrls.NOT_FOUND).permitAll()
            .antMatchers(WebUrls.URL_AUTH + WebUrls.URL_LOGOUT + "/").hasAnyRole("Admin", "User")
                // Accessible only to logged in administrator
            .antMatchers(WebUrls.URL_HALL + "/new").hasAnyRole("Admin")
            .antMatchers(WebUrls.URL_SHOW + "/new").hasAnyRole("Admin")
            .antMatchers(WebUrls.URL_PERFORMANCE + "/new").hasAnyRole("Admin")
            .antMatchers(WebUrls.URL_GENRE + "/new").hasAnyRole("Admin")
            .antMatchers(WebUrls.URL_BOOKING + "/create").hasAnyRole("Admin", "User")
            // Accessible to logged in user
            .antMatchers(WebUrls.URL_BOOKING + "/*").hasAnyRole("User", "Admin")
            .antMatchers(WebUrls.URL_TICKET + "/*").hasAnyRole("User", "Admin")
            .antMatchers(WebUrls.URL_BOOKINGS_TICKETS + "/*").permitAll()//hasAnyRole("User")
            .antMatchers(WebUrls.URL_BOOKINGS_TICKETS).permitAll()
            //User controller
            .antMatchers(WebUrls.URL_USER + "/").hasAnyRole("Admin")
            .antMatchers(WebUrls.URL_USER + "/all").hasAnyRole("Admin")
            .antMatchers(WebUrls.URL_USER + "/create").hasAnyRole("Admin", "User")
            .antMatchers(WebUrls.URL_USER + "/*/update").hasAnyRole("Admin")
            .antMatchers(WebUrls.URL_USER + "/*/delete").hasAnyRole("Admin")
            .antMatchers(WebUrls.URL_USER + "/**").hasAnyRole("Admin")
            // Publicly accessible parts
            .antMatchers(WebUrls.URL_SHOW + "/*").permitAll()
            .antMatchers(WebUrls.URL_HALL + "/*").permitAll()
            .antMatchers(WebUrls.URL_GENRE + "/*").permitAll()
            .antMatchers(WebUrls.URL_PERFORMANCE + "/*").permitAll()
            .antMatchers(WebUrls.URL_SHOW_DETAIL + "/*").permitAll()
            // Not specified pages are accessible only to logged on admins
            .antMatchers("/**").hasAnyRole("Admin")
            .anyRequest().authenticated()

            // Login
            .and()
            .formLogin()
            .loginPage(WebUrls.URL_AUTH + WebUrls.URL_LOGIN)
            .loginProcessingUrl(WebUrls.URL_AUTH + WebUrls.URL_LOGIN)
            .failureUrl(WebUrls.URL_AUTH + WebUrls.URL_LOGIN + "?error=true")
            .usernameParameter("email").passwordParameter("password")
            .successHandler(
                (request, response, authentication) -> {
                    String email = (String) authentication.getPrincipal();
                    UserDTO user = userFacade.getUserByEmail(email);
                    HttpSession session = request.getSession(true);
                    session.setAttribute("authUser", user);
                    response.sendRedirect(request.getRequestURI());
            })
            .permitAll()

            // Logout
            .and()
            .logout()
            .logoutUrl(WebUrls.URL_AUTH + WebUrls.URL_LOGOUT)
            .logoutSuccessUrl("/")
            .invalidateHttpSession(true)
            .deleteCookies("JSESSIONID")
            .permitAll();
    }


}