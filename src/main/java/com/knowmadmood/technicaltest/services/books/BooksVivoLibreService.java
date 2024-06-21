package com.knowmadmood.technicaltest.services.books;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knowmadmood.technicaltest.businessdtos.books.BooksFilterDto;
import com.knowmadmood.technicaltest.controllers.ports.in.books.BooksFilterByTextUseCase;
import com.knowmadmood.technicaltest.controllers.ports.in.books.BooksSortedByPublicationDateAndBioUseCase;
import com.knowmadmood.technicaltest.controllers.ports.in.books.BooksWithoutPublicationDateUseCase;
import com.knowmadmood.technicaltest.controllers.ports.out.books.BooksRepositoryPort;
import com.knowmadmood.technicaltest.repositories.models.books.Book;
import com.knowmadmood.technicaltest.repositories.models.books.BookDate;
import com.knowmadmood.technicaltest.services.books.comparators.BooksAuthorBioComparator;
import com.knowmadmood.technicaltest.services.books.comparators.BooksPublicationDateComparator;
import com.knowmadmood.technicaltest.services.books.comparators.MultipleComparator;
import com.knowmadmood.technicaltest.utils.DateUtils;

@Service
public class BooksVivoLibreService implements BooksWithoutPublicationDateUseCase, BooksFilterByTextUseCase,
		BooksSortedByPublicationDateAndBioUseCase {

	private final BooksRepositoryPort booksRepositoryPort;

	public BooksVivoLibreService(@Autowired BooksRepositoryPort loginRepositoryPort) {

		super();
		this.booksRepositoryPort = loginRepositoryPort;
	}

	@Override
	public List<Book> listBooksWithoutPublicationDate() {

		return booksRepositoryPort.getAllBooks().stream().filter(buildPublicationDatePredicate())
				.collect(Collectors.toList());
	}

	private Predicate<? super Book> buildPublicationDatePredicate() {
		return b -> Objects.isNull(b.getPublicationTimestamp());
	}

	@Override
	public Optional<BookDate> searchBookByText(BooksFilterDto filter) {

		List<Book> books = booksRepositoryPort.getAllBooks().stream().filter(buildContainsTextPredicate(filter))
				.collect(Collectors.toList());
		books.sort(Comparator.nullsLast(new BooksPublicationDateComparator()));
		return books.isEmpty() ? Optional.empty()
				: Optional.of(BookDate.builder().book(books.get(0))
						.date(DateUtils.fromTimestampToString(books.get(0).getPublicationTimestamp())).build());
	}

	private Predicate<? super Book> buildContainsTextPredicate(BooksFilterDto filter) {
		return b -> b.getTitle().contains(filter.getTextToFilter())
				|| b.getAuthor().getBio().contains(filter.getTextToFilter())
				|| b.getSummary().contains(filter.getTextToFilter());
	}

	@Override
	public List<Book> listBookSorted() {

		List<Book> books = booksRepositoryPort.getAllBooks();
		books.sort(Comparator.nullsLast(new BooksPublicationDateComparator()));
		books.sort((first, second) -> Integer.compare(first.getAuthor().getBio().length(),
				second.getAuthor().getBio().length()));

		Comparator<Book> comparator = new MultipleComparator<>(new BooksPublicationDateComparator(),
				new BooksAuthorBioComparator());

		Collections.sort(books, comparator);
		return books;
	}
}
