package design.abdelhak.kahrakib.auth;

import design.abdelhak.kahrakib.dao.utilisateur.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfigration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private JwtFilter jwtFilter;

    @Autowired JwtEntryPoint jwtEntryPoint;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(utilisateurService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        String[] adminUrl = {"/"};
        String[] clientUrl = {
                "/",
        };

        String[] cassierUrl = {
                "/",
        };

        String[] cassierRespoUrl = {
                "/",
        };

        String[] comptableUrl = {
                "/",
        };

        String[] allUrl = {
                "/auth/login","/auth/register","/auth/motDePasse","/api"
        };

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(adminUrl).hasRole(SecurityKeys.HAS_ADMIN_ROLE)
                .antMatchers(clientUrl).hasAnyRole(SecurityKeys.HAS_CLIENT_ROLE)
                .antMatchers(cassierUrl).hasAnyRole(SecurityKeys.HAS_CASSIER_ROLE)
                .antMatchers(cassierRespoUrl).hasAnyRole(SecurityKeys.HAS_CASSIER_RESPO_ROLE)
                .antMatchers(comptableUrl).hasAnyRole(SecurityKeys.HAS_COMPTABLE_ROLE)
                .antMatchers(allUrl).permitAll()
                .anyRequest().authenticated()
                //if any exception occurs call this
                .and().exceptionHandling()
                .authenticationEntryPoint(jwtEntryPoint).and()
                // make sure we use stateless session; session won't be used to
                // store user's state.
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Add a filter to validate the tokens with every request
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
