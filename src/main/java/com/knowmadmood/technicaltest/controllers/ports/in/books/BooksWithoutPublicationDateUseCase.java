package com.knowmadmood.technicaltest.controllers.ports.in.books;

import java.util.List;

import com.knowmadmood.technicaltest.repositories.models.books.Book;

public interface BooksWithoutPublicationDateUseCase {

	List<Book> listBooksWithoutPublicationDate();
}
