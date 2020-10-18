package com.salallegra.library.ui;

import java.util.List;

import java.util.Scanner;

import com.salallegra.library.Entity.Copies;

import com.salallegra.library.service.AdministratorService;
import com.salallegra.library.service.LibrarianService;
import com.salallegra.library.Entity.Book;
import com.salallegra.library.Entity.Branch;

/**
 * 
 * @author salal These Menus will display all system choices and perform all
 *         actions by calling Service class methods.
 */
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
		sc.nextLine();

		switch (selection) {
		case 1:
			lib1();
			break; // optional

		case 2:
			// Statements
			break; // optional

		case 3:
			// Statements
			break; // optional

		// You can have any number of case statements.
		default:
			System.out.println("Invalid entry, please enter 1,2 or 3");
		}

	}

	// This is the top level menu of
	public void lib1() {

		System.out.println("1) Enter the branch number you manage ");
		System.out.println("2) Quit to return to main menu ");
		int selection = sc.nextInt();
		sc.nextLine();

		switch (selection) {
		case 1:
			lib2();
			break; // optional

		case 2:
			displayMainMenu();
			break; // optional

		default:
			System.out.println("Invalid entry, please enter 1,2 or 3");
			lib1();
		}
	}

	public void lib2() {
		LibrarianService service = new LibrarianService();
		System.out.println("Displaying Branches....");
		System.out.println();
		List<Branch> branches = service.getAllBranches();
		for (Branch b : branches) {
			System.out.println("Branches: " + b.getBranchID() + " " + b.getBranchName());
		}
		int quitIndex = branches.size() + 1;
		System.out.println(quitIndex + ") Quit to previous...");

		int selection = sc.nextInt() - 1;
		sc.nextLine();
		String branchName = branches.get(selection).getBranchName();
		System.out.println(branchName);
		if (selection == quitIndex) {
			lib1();
		} else {
			lib3(selection, branchName);
		}

	}

	public void lib3(int branchID, String branchName) {
		LibrarianService libService = new LibrarianService();
		System.out.println("Currently selected library branch is " + branchName);
		System.out.println("1) Update branch details ");
		System.out.println("2) Add Copies of a book to the branch");
		System.out.println("3) Quit to return to previous menu ");

		int selection = sc.nextInt();
		sc.nextLine();
		String newBranchName = null;
		String newBranchAddress = null;

		// This is the display id on the menu, the true value in the arraylist is
		// branchId
		int displayBranchId = branchID + 1;

		switch (selection) {
		case 1:
			System.out.println("You've chosen to update branch " + branchName + " With ID " + displayBranchId);

			System.out.println("Please Enter a new branch name or N/A for no change");
			newBranchName = sc.nextLine();

			System.out.println("Please Enter a new branch address or N/A for no change");
			newBranchAddress = sc.nextLine();

			Branch branch = new Branch(displayBranchId, newBranchName, newBranchAddress);
			System.out.println(branch.getBranchID() + branch.getBranchAddress() + branch.getBranchName());
			libService.updateBranch(branch);

			break; // optional

		case 2:
			List<Book> books = libService.getAllBooks();
			System.out.println("Pick the book you want to add copies to...");
			for (Book b : books) {
				System.out.println("Book# " + b.getBookId() + " " + b.getTitle());
			}
			int bookId = sc.nextInt();
			sc.nextLine();
			System.out.println("Existing # of copies");
			List<Copies> copies = libService.getBookCopies(bookId);
			for (Copies c : copies) {
				System.out.println("Number of Copies... " + c.getNoCopies());
			}
			
			break; // optional
		case 3:
			lib2();
			break;

		default:
			System.out.println("Invalid entry, please enter 1,2 or 3");
			lib3(branchID, branchName);
		}

	}
}
