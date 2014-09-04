package com.acertainbank.business;

import java.util.Set;
public class Branch {
	private int id;
	private Set<Account> account;
	public Branch(Set<Account> account,int id)
	{
		this.account=account;
		this.id=id;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the account
	 */
	public Set<Account> getAccount() {
		return account;
	}
	/**
	 * @param account the account to set
	 */
	public void setAccount(Set<Account> account) {
		this.account = account;
	}
}
