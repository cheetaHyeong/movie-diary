// 깃허브 테스트
package com.example.moviediary.Controller;

import com.example.moviediary.Repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/movies")
public class MovieController {
    
    @Autowired
    MovieRepository movieRepository;
    
    @GetMapping
    public String listMovies() { // 조회 페이지
        return "";
    }

    @GetMapping("/{id}") // 상세 페이지
    public String showMovie() {
        return "";
    }

    @GetMapping("/new") // 등록 페이지
    public String createMovie() {
        return "";
    }

    @PostMapping // 등록 처리
    public String addMovie() {
        return "";
    }

    @GetMapping("/{id}/edit") // 수정 페이지
    public String editMovie() {
        return "";
    }

    @PostMapping("/{id}/edit") // 수정 처리
    public String updateMovie() {
        return "";
    }
    
    @GetMapping("/{id}/delete") // 삭제
    public String deleteMovie() {
        return "";
    }
}
