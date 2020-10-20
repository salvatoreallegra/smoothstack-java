package com.salallegra.library.ui;

import java.util.List;
import java.util.Scanner;

import com.salallegra.library.Entity.Author;
import com.salallegra.library.Entity.Book;
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
			System.out.println("Book Title: " + b.getTitle());
			for(Author a: b.getAuthors()) {
				System.out.println("Author Name: " + a.getAuthorName()); 
				System.out.println();
			}
			
		}		

	}

	public void genreMenu() {

	}

	public void publisherMenu() {

	}

	public void branchMenu() {

	}

	public void borrowerMenu() {

	}

}
