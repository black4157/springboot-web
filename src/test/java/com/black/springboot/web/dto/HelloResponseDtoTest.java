package com.black.springboot.web.dto;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

    @Test
    public void 롬복_가능_테스트() {
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        //then
        assertThat(dto.getName()).isEqualTo(name);      //assertj라는 테스트 검증 라이브러리의 검증 메소드, 검증하고싶은 대상을 인자로 받음. 체이닝 지원
        assertThat(dto.getAmount()).isEqualTo(amount);  //isEqualTo는 assertj의 동등 비교 메소드, assertThat 값과 isEqualTo의 값이 같을 때만 성공
    }
}
