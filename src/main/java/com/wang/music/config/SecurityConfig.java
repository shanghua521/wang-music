package com.wang.music.config;

import com.wang.music.exception.RestAuthenticationEntryPoint;
import com.wang.music.filter.JwtAuthorizationFilter;
import com.wang.music.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String SECRET = "ShangHuaMusic";
    public static final long EXPIRATION_TIME = 864000000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String CREATE_TOKEN_URL = "/tokens";

    private UserService userService;
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 开启跨域，关闭 csrf
        http.cors().and().csrf().disable()
                .authorizeRequests()
                // 允许 POST SIGN_UP_URL
                .antMatchers(HttpMethod.POST, CREATE_TOKEN_URL).permitAll()
                // 其他 Request 鉴权
                .anyRequest().authenticated()
                .and()
                // token 鉴权
                .addFilter(new JwtAuthorizationFilter(authenticationManager()))
                .exceptionHandling()
                .authenticationEntryPoint(restAuthenticationEntryPoint)
                .and()
                // session 改变为无状态的
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/swagger**/**")
                .antMatchers("/webjars/**")
                .antMatchers("/v3/**")
                .antMatchers("/doc.html");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRestAuthenticationEntryPoint(RestAuthenticationEntryPoint restAuthenticationEntryPoint) {
        this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
    }
}
