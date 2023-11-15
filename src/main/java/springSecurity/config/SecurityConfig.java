package springSecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import springSecurity.service.UsersDetailsService;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UsersDetailsService personDetailsService;
    private final SuccessUserHandler successUserHandler;

    @Autowired
    public SecurityConfig(UsersDetailsService personDetailsService, SuccessUserHandler successUserHandler) {
        this.personDetailsService = personDetailsService;
        this.successUserHandler = successUserHandler;
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/auth/login", "/error").permitAll()
                .antMatchers("/auth/**").hasRole("ADMIN")
                .antMatchers("/user").hasAnyRole("USER","ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin().successHandler(successUserHandler)
                .permitAll()
                .and()
                .logout()
                .permitAll();
//        http.authorizeRequests()
//                .antMatchers("/api/user", "/api/all").hasAnyRole("ADMIN", "USER")
//                .antMatchers("/admin/**").hasRole("ADMIN")
//                .antMatchers("/auth/login", "/auth/registration", "/error").permitAll()
//                .antMatchers("/user").hasAnyRole("USER", "ADMIN")
//                .anyRequest().hasAnyRole("USER", "ADMIN")
//                .and()
//                .formLogin()
//                .loginPage("/auth/login")
//                .loginProcessingUrl("/process_login")
//                .successHandler(successUserHandler)
//                .failureUrl("/auth/login?error")
//                .and()
//                .logout().logoutUrl("/logout")
//                .logoutSuccessUrl("/auth/login");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(personDetailsService)
                .passwordEncoder(getPasswordEncoder());
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {

        return new BCryptPasswordEncoder();
    }
}
