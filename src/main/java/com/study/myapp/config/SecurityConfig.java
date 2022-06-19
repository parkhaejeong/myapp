package com.study.myapp.config;

import com.study.myapp.service.sign.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

//@Bean
//개발자가 작성한 메서드의 return 되는 객체를 Bean으로 만드는 것이다.
//Spring IoC 컨테이너가 관리하는 자바 객체를 빈(Bean)이라고 한다.
@Configuration
//환경 세팅(스프링의 기본 설정 정보들)을 돕는 어노테이션
//클래스의 어노테이션을 @Configuration라고 선언하면 스프링에게 이 클래스는 환경 구성 파일이고 @Bean 어노테이션을 통하여 Bean임을 알려주게 된다.
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

//custom
//    @Autowired
//    private UserService userService;
//
//    @Bean // 로그인 성공 시 실행되는 메소드
//    public AuthenticationSuccessHandler successHandlerHandler() {
//        return new LoginSuccessHandler();
//    }
//
//    @Bean // 로그인 실패 시 실행되는 메소드
//    public AuthenticationFailureHandler failureHandlerHandler() {
//        return new LoginFailureHandler();
//    }
//
//    @Bean // 패스워드 암호화 관련 메소드
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean // 로그인 시 실행되는 메소드
//    public AuthenticationProvider authenticationProvider(){
//        //return new LoginAuthenticationProvider();
//        return new LoginAuthenticationProvider(userService, bCryptPasswordEncoder());
//    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**","/font/**","/image/**","/js/**","/lib/**","/vendor/**","/error");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //.rememberMe().tokenValiditySeconds(60 * 60 * 24 * 3).userDetailsService(userDetailsService)//3days
                .csrf().disable()// 세션을 사용하지 않고 JWT 토큰을 활용하여 진행, csrf토큰검사를 비활성화
                .authorizeRequests() // 인증절차에 대한 설정을 진행
                .antMatchers("/","/ctgr/**", "/signIn", "/signInProc", "/signFailure").permitAll()
                //.antMatchers("/**", "/signIn", "/signInProc", "/signFailure").permitAll() // 설정된 url은 인증되지 않더라도 누구든 접근 가능
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/scrt/**").hasAnyRole("MEMBER")
                .anyRequest().authenticated()// 위 페이지 외 인증이 되어야 접근가능(ROLE에 상관없이)

                .and()
                .formLogin()
                .loginPage("/signIn")//로그인폼URL // 접근이 차단된 페이지 클릭시 이동할 url
                .loginProcessingUrl("/signInProc")//로그인폼액션URL // 로그인시 맵핑되는 url
                .failureUrl("/signFailure")//실패시URL
                .defaultSuccessUrl("/signAccess")//성공시이동될페이지URL, //custom 사용 시 주석
                .usernameParameter("UserId")  // view form 태그 내에 로그인 할 id 에 맵핑되는 name ( form 의 name )
                .passwordParameter("UserPswd")  // view form 태그 내에 로그인 할 password 에 맵핑되는 name ( form 의 name )
//                .successHandler(successHandlerHandler()) // 로그인 성공시 실행되는 메소드//custom
//                .failureHandler(failureHandlerHandler()) // 로그인 실패시 실행되는 메소드//custom

                .and()
                .rememberMe()
                .key("uniqueAndSecret")
                .rememberMeParameter("remember-Me")
                .tokenValiditySeconds(86400 * 30)
//                .userDetailsService(userService) //custom
//                .authenticationSuccessHandler(loginSuccessHandler())

                .and()
                .logout() // 로그아웃 설정
                .logoutUrl("/logout") // 로그아웃시 맵핑되는 url
                .logoutSuccessUrl("/") // 로그아웃 성공시 리다이렉트 주소
                .invalidateHttpSession(true) // 세션 clear
                //.and()
                //.exceptionHandling().accessDeniedPage("error403");
                ;
    }

//custom
//    // 로그인 시 실행되는 메소드
//    private class LoginAuthenticationProvider implements AuthenticationProvider {
//        //@Autowired
//        //private PasswordEncoder passwordEncoder;
//        //private BCryptPasswordEncoder passwordEncoder;
//
//        private final UserService userService;
//        private final BCryptPasswordEncoder passwordEncoder;
//        public LoginAuthenticationProvider(UserService userService, BCryptPasswordEncoder passwordEncoder) {
//            this.userService = userService;
//            this.passwordEncoder = passwordEncoder;
//        }
//
//        @Override
//        public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//            String userid = authentication.getName(); //(String)authentication.getPrincipal();
//            String password = (String)authentication.getCredentials();
//
//            UserDetails user = userService.loadUserByUsername(userid);
//            if (user == null) {
//                throw new BadCredentialsException("username is not found. username=" + userid);
//            }
//
//            if(!passwordEncoder.matches(password,user.getPassword())){
//                throw new BadCredentialsException("password is not matched");
//            }
//
//            UsernamePasswordAuthenticationToken authenticationToken
////                    = new UsernamePasswordAuthenticationToken(userid,password,user.getAuthorities());
//                    = new UsernamePasswordAuthenticationToken(user,authentication.getCredentials(),user.getAuthorities());
//
//            return authenticationToken;
//
//            //return new CustomAuthenticationToken(userid, password, user.getAuthorities());
//
//        }
//        // 토큰 타입과 일치할 때 인증
//        @Override
//        public boolean supports(Class<?> authentication) {
//            return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
//        }
//    }
//
//    // 로그인 성공 시 실행되는 메소드
//    private class LoginSuccessHandler implements AuthenticationSuccessHandler {
//        @Override
//        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//            clearAuthenticationAttributes(request);
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            response.sendRedirect("/");
//        }
//        private void clearAuthenticationAttributes(HttpServletRequest re) {
//            HttpSession session = re.getSession(false);
//            if (session != null){
//                session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
//            }
//        }
//    }
//    // 로그인 실패 시 실행되는 메소드
//    private class LoginFailureHandler implements AuthenticationFailureHandler {
//        @Override
//        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
//            exception.printStackTrace();
//
//            //String s = request.getRequestURI();
//            //String s1 = request.getAuthType();
//            //String s2 = request.getUserPrincipal().getName();
//
//            response.sendRedirect("/signFailure");
//            //writePrintErrorResponse(response, exception);
//        }
//    }
}
