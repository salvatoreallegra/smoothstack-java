package com.salallegra.library.ui;

import java.util.List;
import java.util.Scanner;

import com.salallegra.library.service.AdministratorService;
import com.salallegra.library.service.LibrarianService;
import com.salallegra.library.Entity.Book;
import com.salallegra.library.Entity.Branch;

public class Menus {
	
	Scanner sc = new Scanner(System.in);

	public void showBooks() {
		AdministratorService adminService = new AdministratorService();
		List<Book> books = adminService.getBooks(null);
		for (Book b : books) {
			System.out.println("Book Title: " + b.getTitle());
		}
	}
	
	public void displayMainMenu() {
		
		System.out.println("Welcome to the SS Library Management System ");
		System.out.println("1) Librarian ");
		System.out.println("2) Administrator ");
		System.out.println("3) Borrower ");
		System.out.println("Please Enter Your Selection ");
		int selection = sc.nextInt();
		
		
		switch(selection) {
		   case 1 :
		      displayMainLibraryMenu();
		      break; // optional
		   
		   case 2 :
		      // Statements
		      break; // optional
		      
		   case 3 :
			      // Statements
			      break; // optional
		   
		   // You can have any number of case statements.
		   default :
			   System.out.println("Invalid entry, please enter 1,2 or 3");
		}
		
	}
	
	//This is the top level menu of 
	public void displayMainLibraryMenu() {
		LibrarianService service = new LibrarianService();
		System.out.println("1) Enter the branch number you manage ");
		System.out.println("2) Quit to return to main menu ");
		int selection = sc.nextInt();
		
		 List<Branch> branches = service.getAllBranches();
		 for (Branch b : branches) {
				System.out.println("Branches: " + b.getBranchID() + " " + b.getBranchName());
			}
		
		switch(selection) {
		   case 1 :
		    
		      break; // optional
		   
		   case 2 :
		      displayMainMenu();
		      break; // optional
		      
		   case 3 :
			      // Statements
			      break; // optional
		   
		   // You can have any number of case statements.
		   default :
			   System.out.println("Invalid entry, please enter 1,2 or 3");
		}
		
	}

}
