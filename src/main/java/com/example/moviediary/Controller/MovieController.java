package com.example.moviediary.Controller;

import com.example.moviediary.DTO.MovieForm;
import com.example.moviediary.Entity.Movie;
import com.example.moviediary.Repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieRepository movieRepository;

    @GetMapping
    public String listMovies(@RequestParam(required = false) String sort, Model model) {
        List<Movie> movieList;
        if ("favorite".equals(sort)) {
            movieList = movieRepository.findAllByOrderByIsFavoriteDescIdAsc();
        } else {
            movieList = movieRepository.findAllByOrderByIdAsc(); // 기본 정렬
        }
        model.addAttribute("movieList", movieList);
        return "movies/newlist"; // list.mustache 등
    }


    @GetMapping("/{id}") // 상세 페이지
    public String showMovie(@PathVariable("id") Long id, Model model) {
        // 1. id를 조회하여 데이터 가져오기
        Movie movieEntity = movieRepository.findById(id).orElse(null);

        // 2. 모델에 데이터 등록하기
        model.addAttribute("movie", movieEntity);

        // 3. 뷰 페이지 반환하기
        return "movies/detail";
    }

    @GetMapping("/new") // 등록 페이지
    public String createMovie() {
        return "movies/form";
    }

    @PostMapping("/create") // 등록 처리
    public String addMovie(MovieForm form) {

        // 1. DTO를 엔티티로 변환
        Movie movie = form.toEntity();

        // 2. 리포지토리를 이용해 DB에 저장
        Movie saved = movieRepository.save(movie);

        // 3. 저장된 결과의 id로 리다이렉트
        return "redirect:/movies/" + saved.getId();
    }

    @GetMapping("/{id}/edit") // 수정 페이지
    public String editMovie(@PathVariable Long id, Model model) {
        Movie movie = movieRepository.findById(id).orElse(null);
        model.addAttribute("movie", movie);
        return "movies/edit";
    }

    @PostMapping("/update") // 수정 처리
    public String updateMovie(MovieForm form) {
        Movie movie = form.toEntity();
        Movie target = movieRepository.findById(movie.getId()).orElse(null);
        if (target != null) {
            movieRepository.save(movie);
        }
        return "redirect:/movies/" + target.getId();
    }

    @GetMapping("/{id}/delete") // 삭제 처리
    public String deleteMovie(@PathVariable("id") Long id, RedirectAttributes rttr) {

        Movie target = movieRepository.findById(id).orElse(null);

        if (target != null) {
            movieRepository.deleteById(id);
            rttr.addFlashAttribute("msg", "삭제되었습니다.");
        }
        return "redirect:/movies";
    }
}
