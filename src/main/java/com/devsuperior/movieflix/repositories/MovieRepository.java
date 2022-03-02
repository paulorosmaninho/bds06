package com.devsuperior.movieflix.repositories;

import java.util.List;

import javax.persistence.FetchType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

	//Aplicado FetchType.LAZY na entidade Movie para evitar
	//a consulta desnecessária dos generos
	@Query(value = "SELECT objMovie FROM Movie objMovie " 
			+ " INNER JOIN objMovie.genre objGenre "
			+ " WHERE (:genre IS NULL OR :genre IN objGenre) " 
			+ " ORDER BY objMovie.title ASC")
	Page<Movie> find(Pageable pageable, Genre genre);

	
	//Aplicado o JOIN FETCH para resolver o problema das N+1 Consultas
	@Query(value = "SELECT objReview " 
			+ " FROM Review objReview "
			+ " JOIN FETCH objReview.user objUser" 
			+ " JOIN FETCH objUser.roles objRoles" 
			+ " JOIN FETCH objReview.movie objMovie" 
			+ " WHERE (objReview.movie = :movie) "
			+ " ORDER BY objReview.id ASC")
	List<Review> findReviewsByMovieId(Movie movie);

}
