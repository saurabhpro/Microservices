package com.saurabh.eventmanagement.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
				.withUser("admin").password(encoder().encode("admin")).roles("ADMIN")
				.and()
				.withUser("user").password(encoder().encode("user")).roles("USER");
	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	/*	http
				.csrf().disable()
				.exceptionHandling()
				.authenticationEntryPoint(new RestAuthenticationEntryPoint())
				.and()
				.authorizeRequests()
				.antMatchers("/events-api/**").authenticated()
				.antMatchers(HttpMethod.POST, "/events-api/events").hasRole("ADMIN")
				.antMatchers(HttpMethod.PUT, "/events-api/events/**").hasRole("ADMIN")
				.antMatchers(HttpMethod.PATCH, "/events-api/events/**").hasRole("ADMIN")
				.and()
				.formLogin()
				.successHandler(new MySavedRequestAwareAuthenticationSuccessHandler())
				.failureHandler(new SimpleUrlAuthenticationFailureHandler())
				.and()
				.logout();

		// tutorial
		http.httpBasic().and().authorizeRequests().antMatchers(HttpMethod.POST, "/events").hasRole("ADMIN")
				.antMatchers(HttpMethod.PUT, "/events/**").hasRole("ADMIN").antMatchers(HttpMethod.PATCH, "/events/**")
				.hasRole("ADMIN").and().csrf().disable();
*/
		http
				.csrf().disable()
				.authorizeRequests()
				//.antMatchers("/api/foos").authenticated()
				//.antMatchers("/events-api/events/**").hasRole("ADMIN")
				.antMatchers(HttpMethod.POST, "/events-api/events").hasRole("ADMIN")
				.antMatchers(HttpMethod.PUT, "/events-api/events/**").hasRole("ADMIN")
				.antMatchers(HttpMethod.PATCH, "/events-api/events/**").hasRole("ADMIN")
				.and()
				.httpBasic();
	}
}

