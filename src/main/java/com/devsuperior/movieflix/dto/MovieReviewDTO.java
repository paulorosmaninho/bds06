package com.devsuperior.movieflix.dto;

import java.io.Serializable;

public class MovieReviewDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String text;
	private Long movieId;
	private UserDTO userDTO;
	
	public MovieReviewDTO() {
	}

	public MovieReviewDTO(Long id, String text, Long movieId, UserDTO userDTO) {
		this.id = id;
		this.text = text;
		this.movieId = movieId;
		this.userDTO = userDTO;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}
	
}
