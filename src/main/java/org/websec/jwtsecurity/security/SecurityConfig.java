package org.websec.jwtsecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.websec.jwtsecurity.config.ConfigPathHelper;
import org.websec.jwtsecurity.config.JwtConfigProperties;
import org.websec.jwtsecurity.filter.CustomAuthenticationFilter;
import org.websec.jwtsecurity.filter.CustomAuthorizationFilter;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebSecurity
@SuppressWarnings("deprecation")
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private JwtConfigProperties jwtConfigProperties;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		CustomAuthenticationFilter customAuthFilter = new CustomAuthenticationFilter(authenticationManagerBean(),
				jwtConfigProperties);
		customAuthFilter.setFilterProcessesUrl("/token");

		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		ConfigPathHelper.getAuthDisabledPaths().forEach(path -> {
			try {
				http.authorizeHttpRequests().antMatchers(path).permitAll();
			} catch (Exception e) {
				log.error("Severe Error has occured: {}", e.getMessage());
				e.printStackTrace();
			}
		});
		http.authorizeHttpRequests().anyRequest().authenticated();
		http.addFilter(customAuthFilter);
		http.addFilterBefore(new CustomAuthorizationFilter(jwtConfigProperties),
				UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
