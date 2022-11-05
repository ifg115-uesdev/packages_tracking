package ues.edu.sv.packages_tracking.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import ues.edu.sv.packages_tracking.security.service.UserDetailService;



@Configuration
@EnableWebSecurity
public class MainSecurity extends WebSecurityConfigurerAdapter {
    
    @Autowired
    UserDetailService service;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();

        auth.setUserDetailsService(service);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/home/**","/auth/login").permitAll()
                                                                        .antMatchers("/users")
                                                                        .hasAuthority("ADMIN")
                                                                        .anyRequest()
                                                                        .authenticated()
                                                                        .and()
                                                                        .formLogin()
                                                                        .loginPage("/auth/login")
                                                                        .permitAll()
                                                                        .and()
                                                                        .logout()
                                                                        .invalidateHttpSession(true)
                                                                        .clearAuthentication(true)
                                                                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                                                        .logoutSuccessUrl("/auth/login?logout");
    }
}
