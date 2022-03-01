package com.devsuperior.movieflix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.repositories.UserRepository;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private AuthService authService;

	
	public ReviewDTO insert(ReviewDTO reviewDTO) {

		Review review = new Review();
		
		User user = authService.authenticated();
		user = userRepository.findByEmail(user.getEmail());

		Movie movie = movieRepository.getOne(reviewDTO.getMovieId());

		review.setText(reviewDTO.getText());
		review.setUser(user);
		review.setMovie(movie);

		review = reviewRepository.save(review);

		return new ReviewDTO(review);
	}

}
