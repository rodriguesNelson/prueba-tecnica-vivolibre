package com.knowmadmood.technicaltest.controllers.ports.out.books;

import java.util.List;

import com.knowmadmood.technicaltest.repositories.models.books.Book;

public interface BooksRepositoryPort {

	List<Book> getAllBooks();
}
