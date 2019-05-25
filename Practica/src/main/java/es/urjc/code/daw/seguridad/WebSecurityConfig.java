package es.urjc.code.daw.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public UserRepositoryAuthProvider userRepoAuthProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	// Public pages
		http.authorizeRequests().antMatchers("/").permitAll();
		http.authorizeRequests().antMatchers("/principal").permitAll();
		http.authorizeRequests().antMatchers("/verArticulo").permitAll();
		http.authorizeRequests().antMatchers("/login").permitAll();
		http.authorizeRequests().antMatchers("/registrar_cliente").permitAll();
		http.authorizeRequests().antMatchers("/cliente/nuevo").permitAll();
        http.authorizeRequests().antMatchers("/logout").permitAll();

        // Private pages (all other pages)
       // http.authorizeRequests().anyRequest().authenticated();
        http.authorizeRequests().antMatchers("/articulo/nuevo").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/admin").hasAnyRole("ADMIN");

        // Login form
        http.formLogin().loginPage("/login");
		http.formLogin().usernameParameter("username");
		http.formLogin().passwordParameter("password");
		http.formLogin().defaultSuccessUrl("/");
		http.formLogin().failureUrl("/loginerror");

        // Logout
        http.logout().logoutUrl("/logout");
        http.logout().logoutSuccessUrl("/");
        
        // Other URLs can be accessed without authentication
     	http.authorizeRequests().anyRequest().permitAll();
/*
     	// Disable CSRF protection (it is difficult to implement with ng2)
     	http.csrf().disable();

     	// Use Http Basic Authentication
     	http.httpBasic();

     	// Do not redirect when logout
     	http.logout().logoutSuccessHandler((rq, rs, a) -> {	});   */
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        // Database authentication provider
        auth.authenticationProvider(userRepoAuthProvider);
    }
}
