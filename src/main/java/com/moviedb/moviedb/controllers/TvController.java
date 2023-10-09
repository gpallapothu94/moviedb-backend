package com.moviedb.moviedb.controllers;

import com.moviedb.moviedb.dto.PaginatedResponse;
import com.moviedb.moviedb.services.TvService;
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
public class TvController {
    private TvService tvService;

    public TvController(TvService tvService) {
        this.tvService = tvService;
    }

    @GetMapping(value = "/tv-shows", name = "Fetch Tv shows from MovieDB")
    public ResponseEntity<PaginatedResponse> getMovies(@RequestParam(value = "page", required = false) Integer page) {
        page = Objects.isNull(page) ? 1: page;
        PaginatedResponse paginatedResponse = tvService.getTvShowsData(page).block();
        return ResponseEntity.ok(paginatedResponse);
    }

    @GetMapping(value = "/tv-shows/search", name = "Search Tv shows from MovieDB")
    public ResponseEntity<PaginatedResponse> searchMovies(@RequestParam(value = "query", required = true) String query,@RequestParam(value = "includeAdult", required = false) Boolean includeAdult, @RequestParam(value = "page", required = false) Integer page) {
        page = Objects.isNull(page) ? 1: page;
        PaginatedResponse paginatedResponse = tvService.searchTvShows(query, includeAdult, page).block();
        return ResponseEntity.ok(paginatedResponse);
    }
}
