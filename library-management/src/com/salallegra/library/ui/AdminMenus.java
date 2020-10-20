package com.salallegra.library.ui;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.salallegra.library.Entity.Author;
import com.salallegra.library.Entity.Book;
import com.salallegra.library.Entity.Publisher;
import com.salallegra.library.service.AdministratorService;

public class AdminMenus {
	Scanner sc = new Scanner(System.in);
	AdministratorService as = new AdministratorService();

	public void adminMain() {
		System.out.println("1) Add/update/delete/read book and author");
		System.out.println("2) Add/update/delete/read genres");
		System.out.println("3) Add/update/delete/read publishers");
		System.out.println("4) Add/update/delete/read library branches");
		System.out.println("5) Add/update/delete/read borrowers");
		System.out.println("6) Back to main menu");

		int choice = sc.nextInt();
		sc.nextLine();

		switch (choice) {
		case 1:
			authorMenu();
			break;
		case 2:
			genreMenu();
			break;
		case 3:
			publisherMenu();
			break;
		case 4:
			branchMenu();
			break;
		case 5:
			borrowerMenu();
			break;
		case 6:
			Menus mainMenu = new Menus();
			mainMenu.displayMainMenu();
			break;

		default:
			System.out.println("Invalid entry, please enter 1,2 or 3");
		}
	}

	public void authorMenu() {
		List<Book> books = as.getBooks(null);
		for (Book b : books) {
			System.out.println("Book Title: " + b.getBookId() + ") " + b.getTitle());
			for (Author a : b.getAuthors()) {
				System.out.println("Author Name: " + a.getAuthorName() + " Author ID: " + a.getAuthorId());
				System.out.println();
			}
		}
		System.out.println("1) Add a book");
		System.out.println("2) Update a book");
		System.out.println("3) Deleta a book");
		System.out.println("Enter 1, 2 or 3");
		int choice = sc.nextInt();
		sc.nextLine();
		switch (choice) {
		case 1:
			addBook();
			break; // optional

		case 2:
			updateBook();
			break; // optional

		case 3:
			deleteBook();
			break; // optional

		default:
			System.out.println("Invalid entry, please enter 1,2 or 3");
			authorMenu();
		}
	}

	public void genreMenu() {

	}

	public void publisherMenu() {
		System.out.println("Enter your publisher operation...");
		System.out.println("1) Add a Publisher");
		System.out.println("2) Update a publisher");
		System.out.println("3) Deleta a publisher");
		
		int choice = sc.nextInt();
		sc.nextLine();
		switch (choice) {
		case 1:
			addPublisher();
			break; // optional

		case 2:
			updateBook();
			break; // optional

		case 3:
			deleteBook();
			break; // optional

		default:
			System.out.println("Invalid entry, please enter 1,2 or 3");
			publisherMenu();
		}
		
	}

	

	public void branchMenu() {

	}

	public void borrowerMenu() {

	}

	public void addBook() {
		System.out.println("Add Book");
		System.out.println("Enter Book Name: ");
		Book newBook = new Book();
		String bookName = sc.nextLine();
		System.out.println("Is your publisher in the list?...Y or N");
		List<Publisher> publishers = as.getAllPublishers();
		for (Publisher p : publishers) {
			System.out.println("Publishers: " + p.getPublisherId() + ") " + p.getPublisherName());
		}
		String inPublisherList = sc.nextLine();
		int publisherNumber = 0;
		String publisherName = null;
		if (inPublisherList.equalsIgnoreCase("Y")) {
			System.out.println("Enter publisher number");
			publisherNumber = sc.nextInt();
			sc.nextLine();
		}
		else {
			System.out.println("We need to create a publisher for the book you are adding...");
			System.out.println("Enter publisher Name");
			publisherName = sc.nextLine();
			Publisher publisher = new Publisher(publisherName);
			try {
				as.addPublisher(publisher);
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Publisher Added");
		}
		
		//Display List of Authors to add to table book Authors
		System.out.println("Is your auther in the list?...Y or N");
//		List<Author> authors = as.getAllPublishers();
//		for (Publisher p : publishers) {
//			System.out.println("Publishers: " + p.getPublisherId() + ") " + p.getPublisherName());
//		}
//		String inPublisherList = sc.nextLine();
//		int publisherNumber = 0;
//		String publisherName = null;
//		if (inPublisherList.equalsIgnoreCase("Y")) {
//			System.out.println("Enter publisher number");
//			publisherNumber = sc.nextInt();
//			sc.nextLine();
//		}
//		else {
//			System.out.println("We need to create a publisher for the book you are adding...");
//			System.out.println("Enter publisher Name");
//			publisherName = sc.nextLine();
//			Publisher publisher = new Publisher(publisherName);
//			try {
//				as.addPublisher(publisher);
//			
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			System.out.println("Author Added");
//		}
		
		newBook.setTitle(bookName);
		newBook.setPublisherId(publisherNumber);
		try {
			as.addBookSimple(newBook);
			System.out.println("Book Added!");
			adminMain();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void updateBook() {
		List<Book> books = as.getBooks(null);
		for (Book b : books) {
			System.out.println("Books: " + b.getBookId() + ") " + b.getTitle());
		}
		System.out.println("Select book id to Update");
		int bookId = sc.nextInt(); 
		sc.nextLine();
		
		System.out.println("Enter New Book Name to Update");
		String bookName = sc.nextLine(); 
		
//		System.out.println("Select PublisherID to Update");
//		int pubId = sc.nextInt(); 
//		sc.nextLine();
		
		
		Book book = new Book(bookId,bookName);
		
		try {
			as.updateBook(book);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		authorMenu();
		
		
	}

	public void deleteBook() {
		
		List<Book> books = as.getBooks(null);
		for (Book b : books) {
			System.out.println("Books: " + b.getBookId() + ") " + b.getTitle());
		}
		System.out.println("Select book id to delete");
		int bookId = sc.nextInt(); 
		sc.nextLine();
		Book book = new Book();
		book.setBookId(bookId);
		try {
			as.deleteBook(book);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		authorMenu();
		
	}
	
	public void addPublisher() {
		
	}
}
