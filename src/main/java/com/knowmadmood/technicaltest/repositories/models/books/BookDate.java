package com.knowmadmood.technicaltest.repositories.models.books;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BookDate {

	public String date;

	public Book book;
}