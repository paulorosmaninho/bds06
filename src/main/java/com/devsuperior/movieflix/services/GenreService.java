package com.devsuperior.movieflix.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.GenreDTO;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

@Service
public class GenreService {

	@Autowired
	private GenreRepository genreRepository;

	@Transactional(readOnly = true)
	public List<GenreDTO> findAll() {

		List<Genre> listEntity = genreRepository.findAll();

		List<GenreDTO> listGenreDTO = new ArrayList<>();

		listEntity.forEach(listElement -> listGenreDTO.add(new GenreDTO(listElement)));

		return listGenreDTO;

	}

	@Transactional(readOnly = true)
	public GenreDTO findById(Long id) {

		Optional<Genre> objOptional = genreRepository.findById(id);

		Genre entity = objOptional.orElseThrow(() -> new ResourceNotFoundException("Genero " + id + " não encontrado"));

		return new GenreDTO(entity);

	}

}
