package com.global.produits.security;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	
	@Autowired
	@Lazy
	AuthenticationManager authMgr;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.csrf(csrf -> csrf.disable())
			.cors(cors -> cors.configurationSource(new CorsConfigurationSource()
			{
			 @Override
			 public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
			 CorsConfiguration cors = new CorsConfiguration();
			 
			//cors.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
			 cors.setAllowedOrigins(Collections.singletonList("*"));
			cors.setAllowedMethods(Collections.singletonList("*"));
			cors.setAllowedHeaders(Collections.singletonList("*"));
			cors.setExposedHeaders(Collections.singletonList("Authorization"));
			 return cors;
			 }
			 }))
			.authorizeHttpRequests(req -> 
				req/*requestMatchers("/api/all/**").hasAnyAuthority("ADMIN", "USER")
					.requestMatchers(HttpMethod.GET,"/api/getbyid/**").hasAnyAuthority("ADMIN", "USER")
					//.requestMatchers(HttpMethod.POST,"/api/addproduct/**").hasAnyAuthority("ADMIN")
					.requestMatchers(HttpMethod.PUT,"/api/updateproduct/**").hasAnyAuthority("ADMIN")
					.requestMatchers(HttpMethod.DELETE,"/api/delproduct/**").hasAnyAuthority("ADMIN")*/
					.anyRequest().permitAll())
			.addFilterBefore(new JWTAuthorizationFilter(), BasicAuthenticationFilter.class);
		return http.build();
		
	}

}
