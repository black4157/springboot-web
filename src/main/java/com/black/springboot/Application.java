package com.black.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication      //스프링부트의 자동 설정, 스프링 Bean 읽기와 생성 모두 자동으로 설정, 항상 프로젝트 최상단
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);     //WAS(Web Application Server)를 실행, 언제 어디서나 같은 환경에서 스프링 부트를 배포할 수 있기 때문에 권장
    }
}
