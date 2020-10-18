package com.salallegra.library.Entity;

public class Copies {
	
	private Integer bookId;
	private Integer branchID;
	private Integer noCopies;
	
	public Copies(Integer bookId, Integer branchID, Integer noCopies) {
		this.bookId = bookId;
		this.branchID = branchID;
		this.noCopies = noCopies;
	}
	public Integer getNoCopies() {
		return noCopies;
	}

	public void setNoCopies(Integer noCopies) {
		this.noCopies = noCopies;
	}

	public Integer getBookId() {
		return bookId;
	}
	
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public Integer getBranchID() {
		return branchID;
	}
	public void setBranchID(Integer branchID) {
		this.branchID = branchID;
	}
	public Integer getNoOfCopies() {
		return noOfCopies;
	}
	public void setNoOfCopies(Integer noOfCopies) {
		this.noOfCopies = noOfCopies;
	}
	private Integer noOfCopies;
	
}
