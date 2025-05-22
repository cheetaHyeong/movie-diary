package com.example.moviediary.DTO;

import com.example.moviediary.Entity.Movie;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Slf4j
public class MovieForm {

    private Long id;
    private String title;
    private String posterPhoto;
    private LocalDate watchedDate;
    private LocalDate releaseDate;
    private Double rating;
    private String review;
    private Boolean isFavorite;

    public Movie toEntity() {
        return new Movie(
                id,
                title,
                posterPhoto,
                watchedDate,
                releaseDate,
                rating,
                review,
                isFavorite
        );
    }
}
