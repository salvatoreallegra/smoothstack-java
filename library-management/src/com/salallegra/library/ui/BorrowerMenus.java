package com.salallegra.library.ui;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;



import com.salallegra.library.Entity.Book;
import com.salallegra.library.Entity.Borrower;
import com.salallegra.library.Entity.Branch;
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
		List<Borrower> borrowerCards = bs.getAllCards();
		for (Borrower b : borrowerCards) {
			if (b.getCardNo() == cardNo) {
				cardExists = true;
			}
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
			pickBranch(cardNo);
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

	public void pickBranch(int cardNo) {
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
		List<Book> books = bs.getAllBooksForBranch(branchChoice);
		for (Book b : books) {
			System.out.println(b.getBookId() + " " + b.getTitle());
		}
		int checkOutBookId = sc.nextInt();
		sc.nextLine();
		LocalDate todaysDate = LocalDate.now();
		LocalDate dueDate = todaysDate.plusDays(7);
		Loan loan = new Loan(checkOutBookId, branchChoice, cardNo, todaysDate, dueDate, null);
		boolean bookCheckOut = bs.checkOutBook(loan);
		if (!bookCheckOut) {
			System.out.println("Book has been successfully checked out...");
		} else {
			System.out.println("You've already checked out this book at this branch");
			Borr1();
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
		List<Book> checkedBooks = bs.getCheckedOutBooks(cardNo, branchChoice);
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

	}

}
