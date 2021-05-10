package com.black.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter             //클래스 내 모든 필드의 Getter 메소드를 자동 생성
@NoArgsConstructor  //기본생성자 자동 추가, public Posts() {}와 같음
@Entity             //테이블과 링크될 클래스임을 나타냄, Entity클래스에선 절대로 Setter를 선언하지 않음
public class Posts {

    @Id             //해당 테이블의 PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK의 생성 규칙, GenerationType.IDENTITY = auto_increment, 웬만하면 Long타입에 GenerationType.IDENTITY 추천
    private Long id;

    @Column(length = 500, nullable=false)       //테이블의 칼럼, 굳이 선언하지 않더라고 해당 클래스의 필드는 모두 칼럼이 됨, 기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder        //해당 클래스의 빌더 패턴 클래스를 생성, 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
