package com.example.moviediary.Repository;

import com.example.moviediary.Entity.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Long> {
    @Override
    ArrayList<Movie> findAll();
}
