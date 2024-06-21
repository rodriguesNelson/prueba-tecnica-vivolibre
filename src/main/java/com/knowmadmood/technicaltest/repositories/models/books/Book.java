package com.knowmadmood.technicaltest.repositories.models.books;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Book {

	private Integer id;

	private Timestamp publicationTimestamp;

	private String title;

	private Integer pages;

	private String summary;

	private Author author;
}