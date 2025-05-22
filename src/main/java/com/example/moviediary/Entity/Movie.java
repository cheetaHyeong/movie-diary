package com.example.moviediary.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String title;

    private String posterPhoto;

    private LocalDate watchedDate;
    private LocalDate releaseDate;

    private Double rating;

    private String review;

    private Boolean isFavorite;
}
