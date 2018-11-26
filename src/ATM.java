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

import java.util.Scanner;

public class ATM {
	private BankAccount acc;
	private Scanner in = new Scanner(System.in);
	public ATM(BankAccount acc) {
		this.acc = acc;
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
		System.out.println("Welcome to the bank of UCVTS. Please enter your account number.");
		long i = in.nextLong();
		in.nextLine();
		if (i == acc.getAccountNumber()) {
			System.out.println("Please enter your pin.");
			int n = in.nextInt();
			in.nextLine();
			if (n == Integer.parseInt(acc.getAccountHolder().getPIN())) {
				int select = 0;
				while (select != 4) {
					System.out.println("Please select 1 to withdraw; 2 to deposit; 3 to see your account balance; 4 to exit.");
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
							System.out.println("Your current balance is " + acc.getBalance());
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
						System.out.println("Thank you for visiting the bank of UCVTS! Goodbye!");
					}
				}
			}
		}
		else {
			System.out.println("Please reenter your account information.");
		}
	}

public static void main(String[] args) {	
	
	ATM atm = new ATM(new BankAccount(0, new AccountHolder("1234","Ryan", "Wilson","January 1, 1970", 1012023003, "123 Main St", "Scotch Plains", "NJ", 105007076)));
	atm.menu();
	
}
}