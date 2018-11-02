package com.revature.main;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.revature.pojos.Book;
import com.revature.service.BookService;

public class App {

	static BookService bService = new BookService();
	
	public static void main(String[] args) {
		
		run();
		
/*		DAO<Genre,Integer> gDao = new GenreDao();
//		List<Genre> genres = gDao.findAll();
//		for (Genre g : genres) {
//			System.out.println(g);
//		}
		
//		Genre g = gDao.findByID(22);
//		System.out.println(g.getName());
		
		DAO<Author,Integer> aDao = new AuthorDao();
		List<Author> authors = aDao.findAll();
		for (Author a : authors) {
			System.out.println(a);
		}
		
		DAO<Book,Integer> bDao = new BookDao();
		List<Book> books = bDao.findAll();
		for (Book b : books) {
			System.out.println(b);
		}
*/
	}
	
	static void run() {
		System.out.println("Hello, choose an option"
				+ "\n1. View all books"
				+ "\n2. View books by genre"
				+ "\n3. View books by author"
				+ "\n4. Add a book to the library");
		Scanner scan = new Scanner(System.in);
		int option = 0;
		try {
			option = scan.nextInt();
			switch(option) {
			case 1: viewAllBooks(); break;
			case 2:
			case 3:
			case 4:
			default: 
				System.out.println("Please enter a number on our menu");//didn't enter correct number
				run(); break;
			}
			
		} catch (InputMismatchException e) {
			System.out.println("Enter a number next time dummy");
			run();
		}
		scan.close();
		
	}
	
	static void viewAllBooks() {
		List<Book> books = bService.getAllBooks();
		if(books == null) {
			System.out.println("Our book supply is currently empty");
		}
		else {
			for(Book b : books) {
				System.out.println(b.getTitle());
			}
		}
	}

}
