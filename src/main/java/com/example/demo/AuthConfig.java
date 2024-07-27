package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.service.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AuthConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    UserService userService;

    // mã hóa mật khẩu
    @Bean
    public BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // quản lý người sử dụng
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	 auth.userDetailsService(userService).passwordEncoder(getPasswordEncoder());
    }

    // phân quyền sử dụng và hình thức đăng nhập
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // CSRF, CORS
        http.csrf().disable().cors().disable();

        // Phân quyền sử dụng
        http.authorizeRequests()
            .antMatchers("/home/admins").hasRole("ADMIN")
            .antMatchers("/home/users").hasAnyRole("ADMIN","USER")
            .antMatchers("/home/authenticated").authenticated() 
            .anyRequest().permitAll(); // Yêu cầu xác thực cho tất cả các yêu cầu khác

        
        //Điều khiển lỗi truy cập không đúng vai trò
        http.exceptionHandling()
           .accessDeniedPage("/auth/access/denied");
        
        // Giao diện đăng nhập
        http.formLogin()
        .loginPage("/auth/login/form")
        .loginProcessingUrl("/auth/login") // [/login]
        .defaultSuccessUrl("/auth/login/success", false)
        .failureUrl("/auth/login/error")
        .usernameParameter("username") // [username]
        .passwordParameter("password"); // [password]
       http.rememberMe()
        .rememberMeParameter("remember"); // [remember-me
       
       //Đăng xuất
       http.logout()
           .logoutUrl("/auth/logoff")
           .logoutSuccessUrl("/auth/logoff/success");
    }
}
