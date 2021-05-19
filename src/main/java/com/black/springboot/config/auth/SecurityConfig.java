package com.black.springboot.config.auth;

import com.black.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity      //spring security 설정들을 활성화시켜줌
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .headers().frameOptions().disable()     //h2-console 화면을 사용하기 위해 해당 옵션들을 disable
                .and()
                    .authorizeRequests()            //url별 권한 관리를 설정하는 옵션의 시작점, andMatchers를 사용할 수 있음
                    .antMatchers("/","/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())    //권한 관리 대상을 지정하는 옵션, url/http 메소드별로 관리가 가능, 지정된 url들은 permitAll()로 전체 열람 권한을 줌
                    .anyRequest().authenticated()   //anyRequest() : 설정된 값들 이외 나머지 url들을 나타냄, authenticated()를 추가해 나머지 url들은 모두 인증된 사용자들에게만 허용->로그인한 사용자들
                .and()
                    .logout()
                    .logoutSuccessUrl("/")          //로그아웃 기능에 대한 설정의 진입점, 로그아웃 성공 시 / 주소로 이동
                .and()
                    .oauth2Login()      //OAuth2 로그인 기능에 대한 설정의 진입점
                        .userInfoEndpoint() //로그인 성공 후 사용자 정보를 가져올 때의 설정들을 담당
                            .userService(customOAuth2UserService);  //로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록, 리소스 서버에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능을 명시할 수 있음
    }
}
