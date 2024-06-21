package com.knowmadmood.technicaltest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.knowmadmood.technicaltest.businessdtos.books.BooksFilterDto;
import com.knowmadmood.technicaltest.services.books.BooksVivoLibreService;

@SpringBootApplication
public class BootApplication {

	public static void main(String[] args) {
		ApplicationContext actx = SpringApplication.run(BootApplication.class, args);
		
		BooksVivoLibreService booksVivoLibreService =  actx.getBean(BooksVivoLibreService.class);
		
		System.out.println("===================================================================================");
		System.out.println("Books without publication date");
		booksVivoLibreService.listBooksWithoutPublicationDate().forEach(System.out::println);
		System.out.println("===================================================================================");
		
		String charToSearch = "1saaas";
		System.out.println("===================================================================================");
		System.out.println("Searching Book  that contains this text -> " + charToSearch);
		booksVivoLibreService.searchBookByText(BooksFilterDto.builder().textToFilter(charToSearch).build()).ifPresentOrElse(book -> System.out.println(book.toString()),
                () -> System.out.println("No book found with the provided text"));
		System.out.println("===================================================================================");
		
		System.out.println("===================================================================================");
		System.out.println("List books sorted by publication date and bio length");
		booksVivoLibreService.listBookSorted().forEach(System.out::println);
		System.out.println("===================================================================================");
	}
}
