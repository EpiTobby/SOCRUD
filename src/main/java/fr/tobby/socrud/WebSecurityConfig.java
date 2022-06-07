package fr.tobby.socrud;

import fr.tobby.socrud.auth.AuthenticationService;
import fr.tobby.socrud.auth.JwtFilter;
import fr.tobby.socrud.exception.AdminCreationFailed;
import fr.tobby.socrud.model.request.CreateAccountRequest;
import fr.tobby.socrud.service.AdminService;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);
    private final AuthenticationService service;

    private final JwtFilter jwtFilter;

    public WebSecurityConfig(final JwtFilter jwtFilter, AuthenticationService service) {
        this.jwtFilter = jwtFilter;
        this.service = service;
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .antMatchers("/swagger-ui/**").permitAll()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable();
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return service;
    }

    @Bean
    public CommandLineRunner createDefaultUser(@Value("${spring.security.user.name}") @Nullable String name,
                                               @Value("${spring.security.user.password}") @Nullable String password,
                                               AdminService adminService)
    {
        return args -> {
            if (name == null || password == null || name.isEmpty() || password.isEmpty())
                return;
            try
            {
                adminService.create(CreateAccountRequest.builder().login(name).password(password).build());
                logger.info("Creating default user {}", name);
            }
            catch (AdminCreationFailed ignored)
            {
                // Account already exists
            }
        };
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean(name = "myAuthenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
