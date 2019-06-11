package es.urjc.code.daw.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;




@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
    @Autowired
    public UserRepositoryAuthProvider userRepoAuthProvider;
    
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		//Paginas Publicas
		http.authorizeRequests().antMatchers("/").permitAll();
		http.authorizeRequests().antMatchers("/loggin").permitAll();
		http.authorizeRequests().antMatchers("/registrar_cliente").permitAll();
		http.authorizeRequests().antMatchers("/nuevoCliente").permitAll();
		http.authorizeRequests().antMatchers("/cliente/nuevo").permitAll();
		http.authorizeRequests().antMatchers("cliente_registrado").permitAll();
		
		//Paginas Privadas
		http.authorizeRequests().anyRequest().authenticated();
        http.authorizeRequests().antMatchers("/nuevoArticulo").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/eliminarArticulo").hasAnyRole("ADMIN");
		
		//Loggin
		http.formLogin().loginPage("/");
		http.formLogin().usernameParameter("name");
		http.formLogin().passwordParameter("password");
		http.formLogin().defaultSuccessUrl("/tienda");
		http.formLogin().failureUrl("/loggin");
		
		//Logout
		http.logout().logoutUrl("/logout");
		http.logout().logoutSuccessUrl("/");
	}
	
	//Si queremos poner los administradores
		@Override
		 protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			
			auth.authenticationProvider(userRepoAuthProvider);
		}
}
