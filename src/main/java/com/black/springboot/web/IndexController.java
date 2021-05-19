package com.black.springboot.web;

import com.black.springboot.config.auth.LoginUser;
import com.black.springboot.config.auth.dto.SessionUser;
import com.black.springboot.service.posts.PostsService;
import com.black.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){  //기존에 (User) httpSession.getAttribute("user")로 가져오던 세션 정보 값 개선
        model.addAttribute("posts", postsService.findAllDesc());    //서버 템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있음

        //CustomOauth2UserService에서 로그인 성공 시 세션 SessionUser를 저장하도록 구성, 로그인 성공 시 값을 가져올 수 있음
        //SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if(user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
