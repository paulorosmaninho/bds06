package com.devsuperior.movieflix.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MovieListReviewDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<MovieReviewDTO> movieReviews = new ArrayList<>();

	public MovieListReviewDTO() {
	}

	public MovieListReviewDTO(List<MovieReviewDTO> listMovieReviewDTO) {

		this.movieReviews = listMovieReviewDTO;
		
	}

	public List<MovieReviewDTO> getMovieReviews() {
		return movieReviews;
	}
	
}
