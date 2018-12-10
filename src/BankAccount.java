/**
 * Just like last time, the BankAccount class is primarily responsible
 * for depositing and withdrawing money. In the enhanced version, there
 * will be the added requirement of transferring funds between accounts.
 * 
 * Most of the functionality for this class should have already been
 * implemented last time. You can always reference my Github repository
 * for inspiration (https://github.com/rwilson-ucvts/java-sample-atm).
 */

public class BankAccount {	
	private double balance;
	private long accountNumber;
	private AccountHolder AccountHolder;
	
	public BankAccount(long accountNumber, double balance, AccountHolder AccountHolder) {
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.AccountHolder = AccountHolder;
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
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public void setAccountHolder(AccountHolder AccountHolder) {
		this.AccountHolder = AccountHolder;
	}
	
	public double deposit(double amount) {
		if (amount <= 0) {
			return 0;
		}
		if (balance + amount > 999999999999.99) {
			return 1;
		}
		else {
			setBalance(balance += amount);
			return 2;
		}
	}
	
	public double withdraw(double amount) {
		if (balance >= amount && amount > 0) {
			balance -= amount;
			return 2;
		}
		else if (0 > amount) {
			return 1;
		}
		else {
			return 0;
		}
	}
		
	public double transfer(BankAccount receiever, double much) {
		if (much > balance) {
			return 0;
		} 
		else if (much <= 0) {
			return 1;
		} 
		else {
			if(receiever == null) {
				System.out.println("Transfer account does not exist");
			}
			this.withdraw(much);
			receiever.deposit(much);
			return 2;
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
		return String.format("%09s%04s%-15.2s%-20s%-15s%-8s%10s%-30s%-30s%-2s%-5s%s", accountNumber, AccountHolder.getPIN(), 
				balance, AccountHolder.getlastName(), AccountHolder.getfirstName(), AccountHolder.getDOB(), AccountHolder.gettelephone(),
				AccountHolder.getstAddress(),AccountHolder.getcity(), AccountHolder.getstate(), AccountHolder.getzipcode(), AccountHolder.getOpen() );
	}
	}
