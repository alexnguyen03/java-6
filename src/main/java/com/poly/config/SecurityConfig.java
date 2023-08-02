package com.poly.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//	@Autowired
//	AccountService accountService;
//
//	/* Cơ chế mã hóa mật khẩu */
//	@Bean
//	public BCryptPasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//
//	/* Cho phép truy xuất REST API từ domain khác */
//	@Bean
//	public WebSecurityCustomizer webSecurityCustomizer() {
//		return (web) -> web.ignoring().requestMatchers(HttpMethod.OPTIONS, "/**");
//	}
//	
//	@Bean
//    public UserDetailsService userDetailsService() {
//        return new MyUserDetailsService();
//    }
//	
//	@Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userDetailsService());
//        authProvider.setPasswordEncoder(passwordEncoder());
//        return authProvider;
//    }
//
////	@Bean
////	public InMemoryUserDetailsManager userDetailsService() {
////		String username = (String) session.getAttribute("username");
////		Account user = accountService.findById(username);
////		String password = pe.encode(user.getPassword()); // Mã hóa mật khấu
////		String[] roles = user.getAuthorities().stream().map(er -> er.getRole().getId_role())
////				.collect(Collectors.toList()).toArray(new String[0]);
////		UserDetails admin = User.withUsername(username).password(pe.encode(password)).roles(roles).build();
////		return new InMemoryUserDetailsManager(admin);
////	}
//
//	/* Quản lý dữ liệu người sử dụng */
////	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////		auth.userDetailsService(username -> {
////			try {
////				Account user = accountService.findById(username);
////				String password = pe.encode(user.getPassword()); // Mã hóa mật khấu
////				String[] roles = user.getAuthorities().stream().map(er -> er.getRole().getId_role())
////						.collect(Collectors.toList()).toArray(new String[0]);
////				Map<String, Object> authentication = new HashMap<>();
////				authentication.put("user", user);
////				byte[] token = (username + ":" + user.getPassword()).getBytes();
////				authentication.put("token", "Basic " + Base64.getEncoder().encodeToString(token));
////				session.setAttribute("authentication", authentication);
////				return User.withUsername(username).password(password).roles(roles).build();
////			} catch (NoSuchElementException e) {
////				throw new UsernameNotFoundException(username + " not found!");
////			}
////		});
////	}
//
//	/* Phân quyền sử dụng */
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		// Tắt thuật tấn công giả mạo
//		http.csrf(csrf -> csrf.disable());
//		// Quyền yêu cầu truy cập
//		http.authorizeHttpRequests(requests -> requests.requestMatchers("/order/**", "/account/change-password")
//				.authenticated().requestMatchers("/admin/**").hasAnyRole("STAF", "DIRE")
//				.requestMatchers("/rest/authorities").hasRole("DIRE").anyRequest().permitAll());
//		// Đăng nhập
//		http.formLogin(login -> login.loginPage("/account/login").loginProcessingUrl("/account/login")
//				.defaultSuccessUrl("/account/login/success", false).failureUrl("/account/login/error"));
//		http.rememberMe(me -> me.tokenValiditySeconds(86400));
//		// Điều khiển lỗi truy cập không đúng quyền
//		http.exceptionHandling(handling -> handling.accessDeniedPage("/account/unauthoried"));
//		// Đăng xuất
//		http.logout(logout -> logout.logoutUrl("/account/logout").logoutSuccessUrl("/account/logout/success"));
//		// OAuth2 - Đăng nhâp từ mang xã hôi
////		http.oauth2Login().loginPage("/account/login").defaultSuccessUrl("/oauth2/login/success", true)
////				.failureUrl("/account/login/error").authorizationEndpoint().baseUri("/oauth2/authorization");
//		http.authenticationProvider(authenticationProvider());
//		return http.build();
//	}
}