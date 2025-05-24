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

    @GetMapping("before")
    public String listMovies(Model model) { // 조회 페이지

        // 1. 모든 데이터 가져오기
        List<Movie> movieEntityList = movieRepository.findAll();

        // 2. 모델에 데이터 등록하기
        model.addAttribute("movieList", movieEntityList);

        // 3. 뷰 페이지 설정하기
        return "movies/list";
    }

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
    public String editMovie(@PathVariable Long id, Model model) { // url에 id 인식, 뷰에 정보 전달
        Movie movie = movieRepository.findById(id).orElse(null); // id에 해당하는 정보 get
        model.addAttribute("movie", movie); // get한 변수 값을 뷰페이지에 보냄
        return "movies/edit"; // 수정 폼 뷰 페이지 반환
    }

    @PostMapping("/update") // 수정 처리
    public String updateMovie(MovieForm form) { // 액션으로 보낸 값 form으로 받음
        Movie movie = form.toEntity(); // 엔티티 형태로 변환
        Movie target = movieRepository.findById(movie.getId()).orElse(null); // id에 해당하는 값
        if (target != null) { // 수정 값이면, 즉, 이미 있는 데이터라면 저장
            movieRepository.save(movie);
        }
        return "redirect:/movies/" + target.getId(); // 상세 페이지로 리다이렉트
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
