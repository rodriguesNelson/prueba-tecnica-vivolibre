package com.knowmadmood.technicaltest.services.books.comparators;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MultipleComparator<T> implements Comparator<T> {

	private List<Comparator<T>> listComparators;

	@SafeVarargs
	public MultipleComparator(Comparator<T>... comparators) {
		listComparators = Arrays.asList(comparators);
	}

	@Override
	public int compare(T o1, T o2) {
		for (Comparator<T> comparator : listComparators) {
			int result = comparator.compare(o1, o2);
			if (result != 0) {
				return result;
			}
		}
		return 0;
	}
}