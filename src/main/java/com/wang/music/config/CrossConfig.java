package com.wang.music.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CrossConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration configuration = new CorsConfiguration();
        // 允许所有域名进行跨域调用
        configuration.addAllowedOriginPattern("*");
        // 允许跨域发送 cookie
        configuration.setAllowCredentials(true);
        // 放行全部请求信息
        configuration.addAllowedHeader("*");
        // 允许全部返回头信息
        configuration.addExposedHeader("*");
        // 允许所有请求方法跨域调用
        configuration.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return new CorsFilter(source);
    }

}
