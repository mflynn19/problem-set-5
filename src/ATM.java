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

import java.io.*;
import java.security.InvalidParameterException;
import java.util.*;

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

public class ATM {
	private BankAccount currentAccount = null;
	private Database database;
	private static Scanner in = new Scanner(System.in);


	public boolean loginScreen() {
		System.out.println("Welcome to the Bank of UCVTS! Please select 1 if you already have an account. Select 2 to create a new account. Select 3 to exit.");
		switch (in.nextLine().charAt(0)) {
			case '1':
				this.login();
				break;
			/*case '2':
				try {
					this.createAccount();
				}
				catch (IOException e) {
					System.out.println("Please try again.");
				}
				break;*/
            case '3':
                return true;
			default:
				return false;
		}
		return true;
	}

	private void login() {
	    Long testNum;
	    Integer testPin;
		while (this.currentAccount == null) {
			System.out.println("Please enter your account number.");
			testNum = null;
			try {
			    testNum = in.nextLong();
            }
			catch (InputMismatchException e) {
			    System.out.println("Invalid account number");
				in.nextLine();
			    continue;
            }
			System.out.println("Please enter your PIN.");
			testPin = null;
			try {
			    testPin = in.nextInt();
            }
			catch (InputMismatchException e){
                System.out.println("Invalid pin");
				in.nextLine();
                continue;
            }
			in.nextLine();
			if (testNum != null && testPin != null)
			    this.currentAccount = this.database.getAccount(testNum);
			if (this.currentAccount == null)
				System.out.println("Incorrect information, please try again");
		}
	}

	public void menu() {
		while (!this.menu('O'));
	}
	
	private boolean menu(char O) {
		System.out.println("Please select 1 to deposit; 2 to withdraw; 3 to see your account balance; 4 to transfer funds ; 5 to view your personal information;" + 
				"					+ \"6 to edit your personal information; 7 to close your account; 8 to Save Changes and exit.");
		try {
			switch (in.nextLine().charAt(0)) {
				case '1':
					//deposit
					System.out.println("How much money would you like to deposit?");
					try {
						this.currentAccount.deposit(in.nextDouble());
					} catch (InvalidParameterException e) {
						System.out.println(e.getMessage());
					} catch (InputMismatchException e) {
						System.out.println("Invalid amount");
					}
					in.nextLine();
					break;
				case '2':
					//withdraw
					System.out.println("How much money would you like to withdraw?");
					try {
						this.currentAccount.withdraw(in.nextDouble());
					} catch (InvalidParameterException e) {
						System.out.println(e.getMessage());
					} catch (InputMismatchException e) {
						System.out.println("Invalid amount");
					}
					in.nextLine();
					break;
				case '3':
					//transfer
					try {
						System.out.println("How much money would you like to transfer?");
						double amount = in.nextDouble();
						try {
							this.database.updateAccount(this.currentAccount.transfer(database.getAccount(database.getMaxAccountNumber()), amount), currentAccount);
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
					} catch (InputMismatchException e) {
						System.out.println("Not a valid amount");
					}
					in.nextLine();
					break;
				case '4':
					//current balance
					System.out.println("Your current balance is: $" + String.format("%,10.2f", currentAccount.getBalance()));
					break;
				case '5':
					//see personal info
					this.currentAccount.getAccountHolder().printpersonalInfo();
					in.nextLine();
					break;
				case '6':
					//update personal info
					while (!this.currentAccount.getAccountHolder().updatePersonalInfo(in)) ;
					try {
						this.database.updateAccount(this.currentAccount, null);
					} catch (IOException e1) {
						System.out.println("Please try again.");
						e1.printStackTrace();
					}
					break;
				case '7':
					//close account with bank
					System.out.println("To close your account with the Bank of UCVTS please press 1; to cancel press 2.");
					int p = in.nextInt();
					if (p == 2) {
						System.out.println("Thanks for that!");
					}
					else if (p == 1) {
						currentAccount.setOpen("N");
					}
				case '8':
					//log out and save
					System.out.println("Logging out");
					try {
						this.database.updateAccount(this.currentAccount, null);
					}
					catch(IOException e) {
						System.out.println("Could not update account!!!");
					}
						this.currentAccount = null;
				}
			}
		catch (StringIndexOutOfBoundsException e) {
			System.out.println("Invalid option");
		}
		return false;
	}

    public boolean hasAccount() {
	    return (this.currentAccount != null);
    }
}
   /*import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ATM {
	private BankAccount acc;
	private Database database;
	//private AccountHolder ach;
	private Scanner in = new Scanner(System.in);
	private BankAccount currentAccount = null;
	private BankAccount destination;

	public ATM() {
		
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
		
	public boolean simpleLogin() {
		System.out.println("Welcome to the Bank of UCVTS! Please select 1 if you already have an account. Select 2 to create a new account. Select 3 to exit.");
		switch (in.nextLine().charAt(0)) {
			case '1':
				this.menu();
				break;
			case '2':
				try {
					this.makeAccount();
				}
				catch (IOException e) {
					System.out.println("Error writing account to file");
				}
				break;
            case '3':
                return true;
			default:
				return false;
		}
		return true;
	}
	
	
	private void loggingIn() {
	    Long Anum;
	    Integer Apin;
		while (this.currentAccount == null) {
			System.out.println("Please enter your account number.");
			Anum = null;
			try {
			    Anum = in.nextLong();
            }
			catch (InputMismatchException e) {
			    System.out.println("Invalid account number!");
				in.nextLine();
			    continue;
            }
			System.out.println("Please enter your PIN.");
			Apin = null;
			try {
			    Apin = in.nextInt();
            }
			catch (InputMismatchException e){
                System.out.println("Invalid pin");
				in.nextLine();
                continue;
            }
			in.nextLine();
			if (Anum != null && Apin != null)
			    this.currentAccount = this.database.getAccount(Anum);
			
			if (this.currentAccount == null)
				System.out.println("Try again.");
		}
	}

	public void menu() {
		while (!this.menu('a'));
	}
	
	private boolean menu(char k) {
		System.out.println("Please select 1 to withdraw; 2 to deposit; 3 to see your account balance; 4 to transfer funds ; 5 to view your personal information; "
					+ "6 to edit your personal information; 7 to close your account; 8 to Save Changes and exit.");
		try {
			select = in.nextLine();
			switch(select) {
			case "1":
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

			case "2":
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

			case "3":
				System.out.println("Your current balance is " + String.format("%,10.2f", currentAccount.getBalance()) + " .");
				break;
			case "4":
				//transfer
				System.out.println("What account would you like to transfer to?");
				Long comparison = database.getMaxAccountNumber();
				Long reciever = in.nextLong();
				in.nextLine();
				if (reciever == comparison){
					System.out.println("How much money would you like to transfer?");
					double amount = in.nextDouble();
					in.nextLine();
					//runs through checks in AccountHolder hopefully
					acc.transfer(database.getAccount(comparison), amount);
					//fix below
					//this.database.updateAccount(this.currentAccount.transfer(database.getAccount(), amount));
				}
				else {
					System.out.println("Account does not exist.");
				}
			case "5":
				//view info
				this.currentAccount.getAccountHolder().printpersonalInfo();
				in.nextLine();
				break;
			case "6":
				//edit info
				while (!this.currentAccount.getAccountHolder().updatePersonalInfo(in)) ;
				try {
					this.database.updateAccount(this.currentAccount, null);
				} catch (IOException e1) {
					System.out.println("Please try again.");
					e1.printStackTrace();
				}
				break;
			case "7":
				//close account?
				System.out.println("To close your account with the Bank of UCVTS please press 1; to cancel press 2.");
				int p = in.nextInt();
				if (p == 2) {
					return;
				}
				else if (p == 1) {
				currentAccount.setOpen("N");
				}
			case "8":
				//save edits
				System.out.println("Logging out");
				try {
					this.database.updateAccount(this.currentAccount, null);
				}
				catch(IOException e) {
					System.out.println("Could not update account!!!");
				}
					this.currentAccount = null;
			case "9":
				in.close();
				if (currentAccount != null) {
					try {
						this.database.updateAccount(this.currentAccount, null);
					} catch (IOException e) {
						System.out.println("Please try again.");
						e.printStackTrace();
					}							}
				System.out.println("Thank you for visiting the bank of UCVTS! Goodbye!");
			
			default:
				System.out.println("Please enter a valid request.");
			}
					}
			catch (StringIndexOutOfBoundsException e) {
				System.out.println("Please reenter your account information.");
			}
				}
			}
	}
		else if (i == 2) {
			try {
				this.destination = null;
				this.database = new Database("accounts-db.txt");
			}
			catch (IOException e) {
				System.out.println("Error. Try again.");
			}
				
		}
		else {
			System.out.println("Goodbye!");
		}
		}
	}*/
