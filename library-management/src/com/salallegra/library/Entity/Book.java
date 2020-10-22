/**
 * 
 */
package com.salallegra.library.Entity;

import java.util.List;

/**
 * @author salvatore allegra
 *
 */
public class Book {
	private Integer bookId;
	private String title;
	private List<Author> authors;
	private List<Genre> genres;
	private List<Branch> branches;
	private List<Publisher> publishers;
	int publisherId;

	public Integer getBookId() {
		return bookId;
	}

	public Book() {

	}
	public Book(Integer bookId, String title) {
		super();
		this.bookId = bookId;
		this.title = title;
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

	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	public List<Branch> getBranches() {
		return branches;
	}

	public void setBranches(List<Branch> branches) {
		this.branches = branches;
	}

	public List<Publisher> getPublishers() {
		return publishers;
	}

	public void setPublishers(List<Publisher> publishers) {
		this.publishers = publishers;
	}
	
}
