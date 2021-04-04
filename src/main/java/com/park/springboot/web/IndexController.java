package com.park.springboot.web;

import com.park.springboot.web.dto.PostsResponseDto;
import com.park.springboot.web.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    /**
     * index 페이지
     * @param model
     * @return
     */
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("posts", postsService.findAllDesc());
        return "index";
    }

    /**
     * 게시물 저장 페이지 이동
     * @return
     */
    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    /**
     * 게시물 업데이트
     * @param id 게시물 id
     * @param model model
     * @return
     */
    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post",dto);

        return "posts-update";
    }

}
