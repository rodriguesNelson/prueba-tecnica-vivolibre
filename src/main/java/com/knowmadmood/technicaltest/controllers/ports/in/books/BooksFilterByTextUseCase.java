package com.knowmadmood.technicaltest.controllers.ports.in.books;

import java.util.Optional;

import com.knowmadmood.technicaltest.businessdtos.books.BooksFilterDto;
import com.knowmadmood.technicaltest.repositories.models.books.BookDate;

public interface BooksFilterByTextUseCase {

	Optional<BookDate> searchBookByText(BooksFilterDto filter);
}
