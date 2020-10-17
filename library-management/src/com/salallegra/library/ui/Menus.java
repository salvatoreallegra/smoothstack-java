package com.salallegra.library.ui;

import java.util.List;

import com.salallegra.library.service.AdministratorService;
import com.salallegra.library.Entity.Book;

public class Menus {

	public void showBooks() {
		AdministratorService adminService = new AdministratorService();
		List<Book> books = adminService.getBooks(null);
		for (Book b : books) {
			System.out.println("Book Title: " + b.getTitle());
		}
	}

}
