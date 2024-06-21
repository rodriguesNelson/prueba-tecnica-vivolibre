package com.knowmadmood.technicaltest.services.books.comparators;

import java.util.Comparator;

import com.knowmadmood.technicaltest.repositories.models.books.Book;

public class BooksPublicationDateComparator implements Comparator<Book> {

	@Override
	public int compare(final Book b1, final Book b2) {

		if (b1.getPublicationTimestamp() == null && b2.getPublicationTimestamp() == null) {
			return 0;
		} else if (b1.getPublicationTimestamp() == null) {
			return 1;
		} else if (b2.getPublicationTimestamp() == null) {
			return -1;
		} else {
			return b2.getPublicationTimestamp().compareTo(b1.getPublicationTimestamp());
		}
	}
}