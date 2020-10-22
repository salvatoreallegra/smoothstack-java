package com.salallegra.library.ui;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.salallegra.library.Entity.Book;
import com.salallegra.library.Entity.Borrower;
import com.salallegra.library.Entity.Branch;
import com.salallegra.library.Entity.Copies;
import com.salallegra.library.Entity.Loan;
import com.salallegra.library.service.BorrowerService;
import com.salallegra.library.service.LibrarianService;

public class BorrowerMenus {

	Menus mainMenu = new Menus();
	Scanner sc = new Scanner(System.in);
	BorrowerService bs = new BorrowerService();

	public void Borr1() {

		System.out.println("Hi Borrower, please enter a Card Number...");
		int cardNo = sc.nextInt();
		boolean cardExists = false;

		sc.nextLine();
		List<Borrower> borrowerCards;
		try {
			borrowerCards = bs.getAllCards();
			for (Borrower b : borrowerCards) {
				if (b.getCardNo() == cardNo) {
					cardExists = true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (cardExists == false) {
			System.out.println("Card doesn't exist.");
			Borr1();
		}
		System.out.println("1) Check out a book");
		System.out.println("2) Return a book");
		System.out.println("3) Back to Main Menu");
		int choice = sc.nextInt();
		sc.nextLine();
		switch (choice) {
		case 1:
			checkOutBook(cardNo);
			break; // optional

		case 2:
			returnBook(cardNo);
			break; // optional
		case 3:
			mainMenu.displayMainMenu();
			break; // optional

		default:
			System.out.println("Invalid entry, please enter 1,2 or 3");
			// lib1();
		}
	}

	public void checkOutBook(int cardNo) {
		System.out.println("Pick the branch you want to check out from");
		LibrarianService libService = new LibrarianService();
		List<Branch> branches = libService.getAllBranches();
		for (Branch b : branches) {
			System.out.println(b.getBranchID() + ") " + b.getBranchName());
		}
		int menuIndex = branches.size() + 1;
		System.out.println(menuIndex + ")" + "Back to main menu");
		int branchChoice = sc.nextInt();
		if (branchChoice == menuIndex) {
			Borr1();
		}
		System.out.println("Pick the book you want to check out....");
		List<Book> books;
		try {
			books = bs.getAllBooksForBranch(branchChoice);
			for (Book b : books) {
				System.out.println(b.getBookId() + " " + b.getTitle());

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int checkOutBookId = sc.nextInt();
		sc.nextLine();
		System.out.println("stack trace " + branchChoice + " " + checkOutBookId);
		List<Copies> copies;
		try {
			copies = bs.getNumberOfCopies(branchChoice, checkOutBookId);
			for (Copies c : copies) {
				System.out.println("This book has " + c.getNoCopies());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Move into the service
		LocalDate todaysDate = LocalDate.now();
		LocalDate dueDate = todaysDate.plusDays(7);
		Loan loan = new Loan(checkOutBookId, branchChoice, cardNo, todaysDate, dueDate, null);
		boolean bookCheckOut;
		try {
			bookCheckOut = bs.checkOutBook(loan);
			if (!bookCheckOut) {
				System.out.println("Book has been successfully checked out...");

			} else {
				System.out.println("You've already checked out this book at this branch");
				Borr1();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Borr1();

//		

	}

	public void returnBook(int cardNo) {
		System.out.println("Pick the branch you want to check into");
		LibrarianService libService = new LibrarianService();
		List<Branch> branches = libService.getAllBranches();
		for (Branch b : branches) {
			System.out.println(b.getBranchID() + ") " + b.getBranchName());
		}
		int menuIndex = branches.size() + 1;
		System.out.println(menuIndex + ")" + "Back to main menu");
		int branchChoice = sc.nextInt();
		if (branchChoice == menuIndex) {
			Borr1();
		}

		System.out.println("Testing " + cardNo + " Testing " + branchChoice);
		List<Book> checkedBooks;
		try {
			checkedBooks = bs.getCheckedOutBooks(cardNo, branchChoice);
			System.out.println("These are your Checked out Books for branch id " + branchChoice);
			for (Book b : checkedBooks) {
				System.out.println(b.getBookId() + ")" + b.getTitle());
			}
			int checkInIndex = checkedBooks.size() + 1;
			System.out.println(checkInIndex + ")" + "Back to main menu");
			System.out.println("Enter the book id you'd like to check in");
			int checkInId = sc.nextInt();
			sc.nextLine();
			if (checkInId == checkInIndex) {
				Borr1();
			}
			Loan loan = new Loan(checkInId, branchChoice, null, null, null, LocalDate.now());
			bs.checkInBook(loan);
			System.out.println("Your book is now checked in");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
