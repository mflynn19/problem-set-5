/**
 * Just like last time, the BankAccount class is primarily responsible
 * for depositing and withdrawing money. In the enhanced version, there
 * will be the added requirement of transferring funds between accounts.
 * 
 * Most of the functionality for this class should have already been
 * implemented last time. You can always reference my Github repository
 * for inspiration (https://github.com/rwilson-ucvts/java-sample-atm).
 */
import java.security.InvalidParameterException;

public class BankAccount {	
	private double balance;
	private long accountNumber;
	private AccountHolder AccountHolder;
	private String open;
	
	public BankAccount(long accountNumber, double balance, AccountHolder AccountHolder, String open) {
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.AccountHolder = AccountHolder;
		this.open = open;
	}
	
	public BankAccount(AccountHolder AccountHolder) {
		this.balance = 0;
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
	
	public String getOpen() {
		return open;
	}
	
	public void setOpen(String open) {
		this.open = open;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public void setAccountHolder(AccountHolder AccountHolder) {
		this.AccountHolder = AccountHolder;
	}
	
	public double deposit(double amount) {
		if (amount <= 0) {
			throw new InvalidParameterException("Deposit amount is too low");
		}
		if (balance + amount > 999999999999.99) {
			throw new InvalidParameterException("Deposit amount is too high");
		}
		else {
			return balance += amount;
		}
	}
	
	void withdraw(double amount) {
		if (balance >= amount && amount > 0)
			balance -= amount;
		else if (0 > amount)
			throw new InvalidParameterException("Cannot withdraw that amount.");
		else
			throw new InvalidParameterException("Insufficient funds.");
	}
		
	public BankAccount transfer(BankAccount receiever, double amount) {
		if (amount > balance) {
			throw new InvalidParameterException("You cannot transfer more money than you have.");
		} else if (amount <= 0) {
			throw new InvalidParameterException("Invalid tarnsfer amount.");
		} 
		else {
			if(receiever == null) {
				System.out.println("Transfer account does not exist");
			}
			this.withdraw(amount);
			receiever.deposit(amount);
			return receiever;
		}
	}
	
	public BankAccount(String account) {
		this.accountNumber = Long.parseLong(account.substring(0, 9));
		this.AccountHolder = new AccountHolder(
				account.substring(9, 13), 
						account.substring(28, 48),
						account.substring(48, 63),
						account.substring(63, 71),
						account.substring(71, 81),
						account.substring(81, 111),
						account.substring(111, 141),
						account.substring(141, 143),
						account.substring(143, 148)
		);
			
		}
	public String toString() {
		return String.format("%09%04d%-15.2f%-20s%-15s%-8s%10d%-30s%-30s%-2s%-5s%s", accountNumber, AccountHolder.getPIN(), 
				balance, AccountHolder.getfirstName(), AccountHolder.getlastName(), AccountHolder.getDOB(), AccountHolder.gettelephone(),
				AccountHolder.getstAddress(),AccountHolder.getcity(), AccountHolder.getstate(), AccountHolder.getzipcode() );
	}
	}
