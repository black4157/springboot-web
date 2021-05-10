package com.black.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)        //JUnit에 내장된 실행자 외에 SpringRunner를 실행, 스프링부트 테스트와 JUnit 사이에 연결자 역할
@WebMvcTest(controllers = HelloController.class)    //Web(Spring MVC)에 집중할 수 있는 어노테이션, @Controller, @ControllerAdvice 사용 가능
public class HelloControllerTest {

    @Autowired              //스프링이 관리하는 빈을 주입 받음
    private MockMvc mvc;    //웹 API를 테스트할 때 사용, 스프링 MVC 테스트의 시작점, http get post 등 테스트 가능

    @Test
    public void hello가_리턴() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))          //MockMvc를 통해 /hello 주소로 http get 요청, 체이닝이 지원되어 이어서 선언 가능
                .andExpect(status().isOk())           //mvc.perform 결과 검증, 200, 404, 500 등의 상태 검증, 여기서는 200인지 ok인지 검증 
                .andExpect(content().string(hello));  //mvc.perform 결과 검증, 응답 본문의 내용을 검증, Controller에서 hello를 리턴하기 때문에 이 값이 맞는지를 검증
    }

    @Test
    public void helloDto가_리턴() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                .param("name", name)                        //API 테스트할 때 사용될 요청 파라미터 설정, 단 String만 허용, 숫자/날짜는 문자열로 변경해서 사용 
                .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))    //JSON 응답값을 필드별로 검증할 수 있는 메소드, $를 기준으로 필드명을 명시
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}
