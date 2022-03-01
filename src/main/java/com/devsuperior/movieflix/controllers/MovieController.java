package com.devsuperior.movieflix.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.MovieDetailDTO;
import com.devsuperior.movieflix.dto.MovieReviewDTO;
import com.devsuperior.movieflix.services.MovieService;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {

	@Autowired
	MovieService movieService;

	@GetMapping
	public ResponseEntity<Page<MovieDTO>> findAllPaged(Pageable pageable,
			@RequestParam(value = "genreId", defaultValue = "0") Long genreId) {

		Page<MovieDTO> pageMovieDTO = movieService.findAllPaged(pageable, genreId);

		return ResponseEntity.ok().body(pageMovieDTO);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<MovieDetailDTO> findById(@PathVariable Long id) {

		MovieDetailDTO movieDetailDTO = movieService.findById(id);

		return ResponseEntity.ok().body(movieDetailDTO);

	}

	@GetMapping(value = "/{movieId}/reviews")
	public ResponseEntity<List<MovieReviewDTO>> findReviewsByMovieId(@PathVariable Long movieId) {
		
		List<MovieReviewDTO> movieReviewDTO = movieService.findReviewsByMovieId(movieId);
		
		return ResponseEntity.ok().body(movieReviewDTO);
		
	}
	
}
