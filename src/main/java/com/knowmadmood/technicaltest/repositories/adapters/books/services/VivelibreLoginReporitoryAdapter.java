package com.knowmadmood.technicaltest.repositories.adapters.books.services;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.knowmadmood.technicaltest.controllers.ports.out.books.BooksRepositoryPort;
import com.knowmadmood.technicaltest.exceptions.VivolibreRuntimeException;
import com.knowmadmood.technicaltest.repositories.models.books.Book;

@Service
@Component("booksRepositoryAdapter")
class VivelibreBooksReporitoryAdapter implements BooksRepositoryPort {

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	public VivelibreBooksReporitoryAdapter() {
		super();
	}

	@Override
	public List<Book> getAllBooks() {

		URL resource = getClass().getClassLoader().getResource("data/books.json");
		String jsonInput = null;
		try {
			jsonInput = Files.readString(Paths.get(resource.toURI()), StandardCharsets.UTF_8);

			return OBJECT_MAPPER.readValue(jsonInput, new TypeReference<List<Book>>() {
			});
		} catch (IOException | URISyntaxException e) {
			throw new VivolibreRuntimeException(
					String.format("Some error occurred when it was loading books collection.", e.getMessage()),
					String.valueOf(HttpStatus.UNPROCESSABLE_ENTITY.value()));
		}
	}
}
