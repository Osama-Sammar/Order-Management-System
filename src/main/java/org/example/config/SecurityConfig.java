//package org.example.config;
//
//
//import org.example.security.CustomUserDetailsService;
//import org.example.security.JwtAuthenticationEntryPoint;
//import org.example.security.JwtAuthenticationFilter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
////Spring configuration class, Annotating a class with the @Configuration indicates that the class can be used by the Spring IoC container as a source of bean definitions.
///**
// * The WebSecurityConfig class is annotated with @EnableWebSecurity
// * to enable Spring Security’s web security support and provide the
// * Spring MVC integration.
// * It also extends WebSecurityConfigurerAdapter and overrides a couple
// * of its methods to set some specifics of the web security configuration.
// *
// * The configure(HttpSecurity) method defines which URL paths should be
// * secured and which should not.
// * Specifically, the / and /home paths are configured to not require
// * any authentication. All other paths must be authenticated.
// */
//@EnableWebSecurity
///**
// * to allow method level Spring security annotation for our application,
// * so in controller can use The @PreAuthorize annotation to check
// * before method execution.
// */
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    private final CustomUserDetailsService userDetailsService;
//
//    private final JwtAuthenticationEntryPoint authenticationEntryPoint;
//
//    public SecurityConfig(CustomUserDetailsService userDetailsService, JwtAuthenticationEntryPoint authenticationEntryPoint) {
//        this.userDetailsService = userDetailsService;
//        this.authenticationEntryPoint = authenticationEntryPoint;
//    }
//
//    @Bean
//    public JwtAuthenticationFilter jwtAuthenticationFilter(){
//        return  new JwtAuthenticationFilter();
//    }
//
//    @Bean
//    PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .exceptionHandling()
//                .authenticationEntryPoint(authenticationEntryPoint)//This class to handle the error of un-authorized request
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/api/v1/**").permitAll()
//                .antMatchers("/api/v1/auth/**").permitAll()
//                .antMatchers("/v3/api-docs/**").permitAll()
//                .antMatchers("/v2/api-docs/**").permitAll()
//                .antMatchers("/swagger-ui/**").permitAll()
//                .antMatchers("/swagger-resources/**").permitAll()
//                .antMatchers("/swagger-ui.html").permitAll()
//                .antMatchers("/api/v1/orders").permitAll()
//                .antMatchers("/webjars/**").permitAll()
//                .anyRequest().authenticated()
//;
//        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService)
//                .passwordEncoder(passwordEncoder());
//    }
//
//    @Override
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//
//}

package org.example.config;

        import org.example.security.CustomUserDetailsService;
        import org.example.security.JwtAuthenticationEntryPoint;
        import org.example.security.JwtAuthenticationFilter;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.http.HttpMethod;
        import org.springframework.security.authentication.AuthenticationManager;
        import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
        import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
        import org.springframework.security.config.annotation.web.builders.HttpSecurity;
        import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
        import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
        import org.springframework.security.config.http.SessionCreationPolicy;
        import org.springframework.security.core.userdetails.UserDetailsService;
        import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
        import org.springframework.security.crypto.password.PasswordEncoder;
        import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtAuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(){
        return new JwtAuthenticationFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/v1/**").permitAll()
                .antMatchers("/api/v1/auth/**").permitAll()
                .antMatchers("/v3/api-docs/**", "/v2/api-docs/**", "/swagger-ui/**",
                        "/swagger-resources/**", "/swagger-ui.html", "/webjars/**").permitAll()
                .antMatchers("/api/v1/orders").permitAll()
                .anyRequest().authenticated();

        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
