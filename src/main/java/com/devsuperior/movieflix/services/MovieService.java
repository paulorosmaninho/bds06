package com.devsuperior.movieflix.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.MovieDetailDTO;
import com.devsuperior.movieflix.dto.MovieReviewDTO;
import com.devsuperior.movieflix.dto.UserDTO;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

@Service
public class MovieService {

	@Autowired
	MovieRepository movieRepository;

	@Autowired
	GenreRepository genreRepository;

	@Autowired
	ReviewRepository reviewRepository;

	public Page<MovieDTO> findAllPaged(Pageable pageable, Long genreId) {

		Genre genre;

		if (genreId == 0) {

			genre = null;

		} else {

			genre = genreRepository.getOne(genreId);
		}

		Page<Movie> pageMovie = movieRepository.find(pageable, genre);
		
		Page<MovieDTO> pageMovieDTO = pageMovie.map(elementMovie -> new MovieDTO(elementMovie));

		return pageMovieDTO;

	}

	public MovieDetailDTO findById(Long id) {

		Optional<Movie> objOptional = movieRepository.findById(id);

		Movie entity = objOptional.orElseThrow(() -> new ResourceNotFoundException("Filme " + id + " não encontrado"));

		return new MovieDetailDTO(entity);
	}

	public List<MovieReviewDTO> findReviewsByMovieId(Long movieId) {

		Movie movie = movieRepository.getOne(movieId);

		List<Review> listMovieReview = movieRepository.findReviewsByMovieId(movie);

		List<MovieReviewDTO> listMovieReviewDTO = new ArrayList<>();

		for (Review review : listMovieReview) {

			MovieReviewDTO dto = new MovieReviewDTO();

			dto.setId(review.getId());
			dto.setText(review.getText());
			dto.setMovieId(review.getMovie().getId());
			dto.setUserDTO(new UserDTO(review.getUser()));

			listMovieReviewDTO.add(dto);
		}

		return listMovieReviewDTO;
	}

}
