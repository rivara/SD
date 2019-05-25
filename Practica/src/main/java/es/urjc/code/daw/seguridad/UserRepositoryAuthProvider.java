package es.urjc.code.daw.seguridad;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import es.urjc.code.daw.Cliente;
import es.urjc.code.daw.ClienteComponent;
import es.urjc.code.daw.ClienteRepository;


@Component
public class UserRepositoryAuthProvider implements AuthenticationProvider {

	@Autowired
	private ClienteRepository userRepository;

	@Autowired
	private ClienteComponent userComponent;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String username = authentication.getName();
		String password = (String) authentication.getCredentials();

		Cliente user = userRepository.findByNombre(username);

		if (user == null) {
			throw new BadCredentialsException("User not found");
		}

		if (!new BCryptPasswordEncoder().matches(password, user.getPasswordHash())) {

			throw new BadCredentialsException("Wrong password");
		} else {

			userComponent.setLoggedUser(user);

			List<GrantedAuthority> roles = new ArrayList<>();
			for (String role : user.getRoles()) {
				roles.add(new SimpleGrantedAuthority(role));
			}

			return new UsernamePasswordAuthenticationToken(username, password, roles);
		}
	}

	@Override
	public boolean supports(Class<?> authenticationObject) {
		return true;
	}
}
