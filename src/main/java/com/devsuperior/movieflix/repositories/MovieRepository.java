package com.devsuperior.movieflix.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>{

	
	@Query(value = "SELECT objMov FROM Movie objMov "
			+ " INNER JOIN objMov.genre objGen "
			+ " WHERE (:genre IS NULL OR :genre IN objGen) "
			+ " ORDER BY objMov.title ASC")
	Page<Movie> find(Pageable pageable, Genre genre);

	@Query(value = "SELECT objReview.id, objReview.text, objReview.movie, objReview.user"
			+ " FROM Review objReview "
			+ " WHERE (objReview.movie.id = :movieId) "
			+ " ORDER BY objReview.id ASC")
	List<Review> findReviewsByMovieId(Long movieId);
	
}
