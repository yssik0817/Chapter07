package kr.co.sol.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private BoardUserDetailsService boardUserDetailsService;
	
	@Autowired
	private DataSource dataSource;
	@Override
	protected void configure(HttpSecurity security) throws Exception {
		security.authorizeRequests().antMatchers("/").permitAll();
		security.authorizeRequests().antMatchers("/member/**").authenticated();
		security.authorizeRequests().antMatchers("/manager/**").hasRole("MANAGER");
		security.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN");

		security.csrf().disable();
		security.formLogin().loginPage("/login").defaultSuccessUrl("/loginSuccess", true);
		security.exceptionHandling().accessDeniedPage("/accessDenied");
		security.logout().invalidateHttpSession(true).logoutSuccessUrl("/login");
		security.userDetailsService(boardUserDetailsService);

	}
	
//	@Autowired
//	public void authenticate(AuthenticationManagerBuilder auth) throws Exception{
//		
//		String query1 = "SELECT id username, concat('{noop}', password) password, 'T' ENABLED FROM member3 WHERE id=?";
//		String query2 = "SELECT id, role FROM member3 WHERE id=?";
//		
//		auth.jdbcAuthentication()
//		.dataSource(dataSource)
//		.usersByUsernameQuery(query1)
//		.authoritiesByUsernameQuery(query2);
//		
//		auth.inMemoryAuthentication().
//		withUser("manager").
//		password("{noop}manager123").
//		roles("MANAGER");
//		
//		auth.inMemoryAuthentication().
//		withUser("admin").
//		password("{noop}admin123").
//		roles("ADMIN");
//		
//	}
	@Bean 
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

}
