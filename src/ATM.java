/**
 * Just like last time, the ATM class is responsible for managing all
 * of the user interaction. This means login procedures, displaying the
 * menu, and responding to menu selections. In the enhanced version, the
 * ATM class will have the added responsibility of interfacing with the
 * Database class to write and read information to and from the database.
 * 
 * Most of the functionality for this class should have already been
 * implemented last time. You can always reference my Github repository
 * for inspiration (https://github.com/rwilson-ucvts/java-sample-atm).
 */

import java.io.IOException;
import java.util.Scanner;

public class ATM {
	private BankAccount acc;
	private Database database;
	//private AccountHolder ach;
	private Scanner in = new Scanner(System.in);
	private BankAccount currentAccount = null;

	public ATM(BankAccount acc) {
		this.acc = acc;
		//this.ach = ach;
	}
	
	public void deposit() {
		int k = in.nextInt();
		in.nextLine();
		acc.setBalance(acc.getBalance() + k); 
	}
	public void withdraw() {
		int k = in.nextInt();
		in.nextLine();
		if (k < acc.getBalance()) {
			acc.setBalance(acc.getBalance() - k);
		}
	}
	
	public void menu() {
		System.out.println("Welcome to the bank of UCVTS. If you have an account please select 1. Otherwise select 2 to create an account. Select 3 to quit.");
		long i = in.nextLong();
		in.nextLine();
		if (i == 1) {
			if (i == acc.getAccountNumber()) {
				System.out.println("Please enter your pin.");
				int n = in.nextInt();
				in.nextLine();
				if (n == Integer.parseInt(acc.getAccountHolder().getPIN())) {
					int select = 0;
					while (select != 4) {
						System.out.println("Please select 1 to withdraw; 2 to deposit; 3 to see your account balance; 4 to transfer funds ; 5 to view your personal information; "
								+ "6 to edit your personal information; 7 to close your account; 8 to Save Changes and Log Out; 9 to exit.");
						select = in.nextInt();
						switch(select) {	
						case 1:
							System.out.println("How much money would you like to withdraw?");
							double want = in.nextDouble();
							in.nextLine();
							double who = acc.withdraw(want);
							if (who == 0) {
								System.out.println("Insufficient funds. Go to work.");
							}
							else if (who == 1) {
								System.out.println("Invalid amount!");
							}
							else {
								System.out.println("Your current balance is $" + acc.getBalance());
							}
							break;
	
						case 2:
							System.out.println("How much money would you like to deposit?");
							double addition = in.nextDouble();
							in.nextLine();
							double what = acc.deposit(addition);
							if (what == 0) {
								System.out.println("Cannot deposit value.");
							}
							else {
								System.out.println("Your current balance is " + acc.getBalance() + ".");
							}
							break;
	
						case 3:
							System.out.println("Your current balance is " + acc.getBalance() + " .");
							break;
						case 4:
							//transfer
							/*System.out.println("What account would you like to transfer to?");
							Double reciever = in.nextDouble();
							if (reciever == acc.MaxAccountNumber()){
								//use database to return BankAccount associated with receiver account ID
							}
							in.nextLine();
							System.out.println("How much money would you like to transfer?");
							double amount = in.nextDouble();
							in.nextLine();
							double que = acc.transfer(acc.receiever, amount);
							if (what == 0) {
								System.out.println("Cannot deposit value.");
							}
							acc.transfer();*/
						case 5:
							//view info
							this.currentAccount.getAccountHolder().printpersonalInfo();
							in.nextLine();
							break;
						case 6:
							//edit info
							
					
						case 7:
							//close account?
							
							System.out.println("Account has been closed.");
						case 8:
							//save edits
							System.out.println("Logging out");
							try {
								this.database.updateAccount(this.currentAccount, null);
							}
							catch(IOException e) {
								System.out.println("Could not update account!!!");
							}
								this.currentAccount = null;
							
						case 9:
							System.out.println("Thank you for visiting the bank of UCVTS! Goodbye!");
						}
					}
				}
			}
			else {
				System.out.println("Please reenter your account information.");
			}
	}
		else if (i == 2) {
			//somehow summon hell to create a new account
		}
		else {
			System.out.println("Goodbye!");
		}
	}

public static void main(String[] args) {	
	
	ATM atm = new ATM(new BankAccount(0, 0, new AccountHolder("1234","Ryan", "Wilson","January 1, 1970", "1012023003", "123 Main St", "Scotch Plains", "NJ", "105007076")));
	atm.menu();
	
}
}