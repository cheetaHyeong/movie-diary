package com.example.moviediary.Repository;

import com.example.moviediary.Entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findAllByOrderByIsFavoriteDescIdAsc();
    List<Movie> findAllByOrderByIdAsc();
}
