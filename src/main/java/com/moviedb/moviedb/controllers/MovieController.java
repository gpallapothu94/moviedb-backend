package com.moviedb.moviedb.controllers;

import com.moviedb.moviedb.dto.PaginatedResponse;
import com.moviedb.moviedb.services.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@Slf4j
@CrossOrigin("*")
public class MovieController {

    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping(value = "/movies", name = "Fetch Movies from MovieDB")
    public ResponseEntity<PaginatedResponse> getMovies(@RequestParam(value = "page", required = false) Integer page) {
        page = Objects.isNull(page) ? 1: page;
        PaginatedResponse paginatedResponse = movieService.getMovieData(page).block();
        return ResponseEntity.ok(paginatedResponse);
    }

    @GetMapping(value = "/movies/search", name = "Search Movies from MovieDB")
    public ResponseEntity<PaginatedResponse> searchMovies(@RequestParam(value = "query", required = true) String query,@RequestParam(value = "includeAdult", required = false) Boolean includeAdult, @RequestParam(value = "page", required = false) Integer page) {
        page = Objects.isNull(page) ? 1: page;
        PaginatedResponse paginatedResponse = movieService.searchMovies(query, includeAdult, page).block();
        return ResponseEntity.ok(paginatedResponse);
    }
}
