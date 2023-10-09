package com.moviedb.moviedb.services;

import com.moviedb.moviedb.config.Config;
import com.moviedb.moviedb.dto.PaginatedResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class MovieService {

    private final WebClient webClient;
    private final Config config;

    public MovieService(Config config) {
        this.webClient = WebClient.builder().baseUrl(config.getUrl())
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        this.config = config;
    }

    public Mono<PaginatedResponse> getMovieData(Integer page) {
        log.info("Fetching Movie Data");
        return webClient.get().uri("/trending/movie/day?page={page}&sort_by=popularity.desc", page)
                .header(HttpHeaders.AUTHORIZATION, config.getBearerToken())
                .retrieve()
                .bodyToMono(PaginatedResponse.class)
                .doOnError(WebClientException.class, ex -> log.error("Failed Feting MovieData {} - {}", ex.getMessage(), ex.getLocalizedMessage()));
    }

    public Mono<PaginatedResponse> searchMovies(String searchQuery, Boolean includeAdult, Integer page) {
        log.info("Fetching Movie Data");
        return webClient.get().uri("/search/movie?query={searchQuery}&include_adult={includeAdult}&language=en-US&page={page}&sort_by=popularity.desc",searchQuery, includeAdult, page)
                .header(HttpHeaders.AUTHORIZATION, config.getBearerToken())
                .retrieve()
                .bodyToMono(PaginatedResponse.class)
                .doOnError(WebClientException.class, ex -> log.error("Failed Searching MovieData {} - {}", ex.getMessage(), ex.getLocalizedMessage()));
    }

}
