package com.knowmadmood.technicaltest.businessdtos.books;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The business dto class books filter.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BooksFilterDto implements Serializable {

  private static final long serialVersionUID = 1L;

  private String textToFilter;
}