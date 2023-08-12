package com.poly.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AccountConfiguration {

	@Bean
	public UserDetailsService userDetailsService() {
		return new AccountServiceConfig();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// Tắt thuật tấn công giả mạo
		http.csrf(csrf -> csrf.disable());
		// Quyền yêu cầu truy cập
		// http.authorizeHttpRequests(requests -> requests.requestMatchers("/order/**",
		// "/account/changepassword")
		// .authenticated().requestMatchers("/admin/**").hasAnyRole("ADMIN").anyRequest().permitAll());
		// Đăng nhập
		// http.formLogin(login ->
		// login.loginPage("/account/login").loginProcessingUrl("/account/login")
		// .defaultSuccessUrl("/account/login/success",
		// false)
		// .failureUrl("/account/login/error"));
		// http.rememberMe(me -> me.tokenValiditySeconds(86400));
		// Điều khiển lỗi truy cập không đúng quyền
		http.exceptionHandling(handling -> handling.accessDeniedPage("/account/unauthoried"));
		// Đăng xuất
		http.logout(logout -> logout.logoutUrl("/account/logout").logoutSuccessUrl("/account/logout/success"));

		AuthenticationManagerBuilder authentication = http.getSharedObject(AuthenticationManagerBuilder.class);
		authentication.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
		AuthenticationManager auth = authentication.build();
		http.authenticationManager(auth)
				.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.ALWAYS));
		return http.build();
	}

}