package com.moviedb.moviedb.dto;

import lombok.Data;

import java.util.ArrayList;

@Data
public class PaginatedResponse {
    public int page;
    public ArrayList<Result> results;
    public int total_pages;
    public int total_results;
}
