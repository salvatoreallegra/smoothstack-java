package com.salallegra.library.Entity;

public class Branch {
	
	Integer branchId;
	String branchName;
	String branchAddress;
	
	public Branch(int branchId,String branchName, String branchAddress) {
		this.branchId = branchId;
		this.branchName = branchName;
		this.branchAddress = branchAddress;
	}
	public Integer getBranchID() {
		return branchId;
	}
	public void setBranchID(Integer branchID) {
		this.branchId = branchID;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getBranchAddress() {
		return branchAddress;
	}
	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}
	
	

}
