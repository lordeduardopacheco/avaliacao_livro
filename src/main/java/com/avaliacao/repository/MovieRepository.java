package com.avaliacao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avaliacao.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByWinnerTrueOrderByYearAsc();
}
