package com.study.myapp.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**","/font/**","/image/**","/js/**","/lib/**","/vendor/**","/error");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        //http.rememberMe().tokenValiditySeconds(60 * 60 * 24 * 3).userDetailsService(userService); //3days
        http.authorizeRequests()
                //.antMatchers("/", "/signIn", "/signInProc", "/signError").permitAll()
                .antMatchers("/**").permitAll()
                .antMatchers("/member/**").access("hasRole('MEMBER') or hasRole('ADMIN')")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()

                .and()
                .formLogin()
                .loginPage("/signIn")//로그인폼URL
                .loginProcessingUrl("/signInProc")//로그인폼액션URL
                .failureUrl("/signError")//실패시URL
                .defaultSuccessUrl("/signAccess")//성공시이동될페이지URL
                .usernameParameter("UserId") //파라메터 설정
                .passwordParameter("UserPswd") //파라메터 설정

                .and()
                .logout()
                .logoutUrl("/logout")//로그아웃처리할URL
                .logoutSuccessUrl("/");//로그아웃성공시이동할URL;
    }
}
