/**
 * 
 */
package com.salallegra.library.Entity;

import java.util.List;

/**
 * @author ppradhan
 *
 */
public class Book {
	private Integer bookId;
	private String title;
	private List<Author> authors;
	private List<Genre> genres;
	private List<Branch> branches;
	private Publisher publisher;
	int publisherId;
	public Integer getBookId() {
		return bookId;
	}
	public Book() {
		
	}
	
	public int getPublisherId() {
		return publisherId;
	}
	public void setPublisherId(int publisherId) {
		this.publisherId = publisherId;
	}
	public Book(String title, int publisherId) {
		this.title = title;
		this.publisherId = publisherId;
	}
	public Book(Integer bookId, String title) {
		super();
		this.bookId = bookId;
		this.title = title;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<Author> getAuthors() {
		return authors;
	}
	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
}

