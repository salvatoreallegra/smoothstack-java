package com.salallegra.library.ui;

import java.util.List;
import java.util.Scanner;

import com.salallegra.library.Entity.Book;
import com.salallegra.library.Entity.Borrower;
import com.salallegra.library.service.BorrowerService;

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
		System.out.println(cardExists);
		
	}

}
