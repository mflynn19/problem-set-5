/**
 * Just like last time, the BankAccount class is primarily responsible
 * for depositing and withdrawing money. In the enhanced version, there
 * will be the added requirement of transfering funds between accounts.
 * 
 * Most of the functionality for this class should have already been
 * implemented last time. You can always reference my Github repository
 * for inspiration (https://github.com/rwilson-ucvts/java-sample-atm).
 */

public class BankAccount {
	private static long generatedAccountNumber = 100000001L;
	
	private long accountNumber;
	private double balance;
	private AccountHolder AccountHolder;
	
	public BankAccount(double balance, AccountHolder AccountHolder) {
		this.accountNumber = BankAccount.generatedAccountNumber++;
		this.balance = balance;
		this.AccountHolder = AccountHolder;
	}
	
	public long getAccountNumber() {
		return accountNumber;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public AccountHolder getAccountHolder() {
		return AccountHolder;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public void setAccountHolder(AccountHolder AccountHolder) {
		this.AccountHolder = AccountHolder;
	}
	
	public int deposit(double amount) {
		if (amount <= 0) {
			return 0;
		} else {
			balance = balance + amount;
			return 1;
		}
	}
	
	public int withdraw(double amount) {
		if (amount > balance) {
			return 0;
		} else if (amount <= 0) {
			return 1;
		} else {
			balance = balance - amount;	
			return 2;
		}
	}
		
	public int transfer(AccountHolder from, AccountHolder to, double amount) {
		if (amount > balance) {
			return 0;
		} else if (amount <= 0) {
			return 1;
		} else {
			// transfer to other using withdraw from one and deposit from other	
			return 2;
		}
	}
}