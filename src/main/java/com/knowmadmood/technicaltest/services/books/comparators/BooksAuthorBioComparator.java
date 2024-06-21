package com.knowmadmood.technicaltest.services.books.comparators;

import java.util.Comparator;

import com.knowmadmood.technicaltest.repositories.models.books.Book;

public class BooksAuthorBioComparator implements Comparator<Book> {

	@Override
	public int compare(final Book b1, final Book b2) {

		if ((b1.getAuthor() == null && b2.getAuthor() == null)
				|| (b1.getAuthor().getBio() == null && b2.getAuthor().getBio() == null)) {
			return 0;
		} else if (b1.getAuthor() == null || b1.getAuthor().getBio() == null) {
			return -1;
		} else if (b2.getAuthor() == null || b2.getAuthor().getBio() == null) {
			return 1;
		} else {
			return b1.getAuthor().getBio().length() > b2.getAuthor().getBio().length() ? 1 : -1;
		}
	}
}