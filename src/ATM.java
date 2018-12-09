import java.io.*;
import java.security.InvalidParameterException;
import java.util.*;
import java.util.Scanner;


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
	public BankAccount BankAccount;
	public AccountHolder AccountHolder;
	private Database database;
	private static Scanner in = new Scanner(System.in);
	
	public void menu() {
		while (!this.menu('a'));
	}
	
	private boolean menu(char a) {
		System.out.println("Welcome to the Bank of UCVTS! Please select 1 if you already have an account. Select 2 to create a new account. Select 3 to exit.");
		switch (in.nextLine().charAt(0)) {
			case '1':
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
					System.out.println("Please select 1 to deposit; 2 to withdraw; 3 to see your account balance; 4 to transfer funds ; 5 to view your personal information;" + 
							"					+ \"6 to edit your personal information; 7 to close your account; 8 to Save Changes and exit.");
					try {
						switch (in.nextLine().charAt(0)) {
							case '1':
								//deposit
								System.out.println("How much money would you like to deposit?");
								try {
									double amount = in.nextDouble();
									this.currentAccount.deposit(amount);
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
			case '2':
				this.createAccount();
				break;
            case '3':
                close();
                break;
		}
		return true;	
	}

    private void createAccount() {
    	System.out.print("Please enter your first name.");
		String firstName = in.nextLine();
		System.out.print("Please enter your last name");
		String lastName = in.nextLine();
		String dob = "";
		while (dob.length() != 8) {
			System.out.print("Please enter your date of birth in the following format: YYYYMMDD");
			dob = in.nextLine();
			if (String.valueOf(dob).length() != 8) {
				System.out.println("Please enter your date of birth in the correct format.");
			}
		}
		String telephone = "";
		while (telephone.length() != 10) {
			System.out.print("Please enter your telephone number; ten digits only.");
			telephone = in.nextLine();
			if (String.valueOf(telephone).length() != 10) {
				System.out.println("Invalid telephone number length.");
			}
		}
		System.out.print("What is your street address?");
		String stAddress = in.nextLine();
		System.out.print("What is the city/town called that you reside in?");
		String city = in.nextLine();
		String zipcode = "";
		while (zipcode.length() != 5) {
			System.out.println("What is your 5 digit zipcode?");
			zipcode = in.nextLine();
		}
		String state = "";
		while (state.length() != 2) {
			System.out.print("What is your resident state abbreviated as (ex. New Jersey should be typed as NJ)?");
			state = in.nextLine();
			if (String.valueOf(state).length() != 2) {
				System.out.println("Invalid state abbreviaion. Example: New Jersey should be typed as NJ");
			}
		}
		String PIN = "";
		while (String.valueOf(PIN).length() != 4) {
				System.out.print("Enter a 4 digit numerical PIN.");
				PIN = in.nextLine();
				if (String.valueOf(PIN).length() != 4 ){
					System.out.println("Invalid PIN");
				}
		}
		double balance = 0;
		//somehow set account number and open to open?
		AccountHolder = new AccountHolder(PIN, firstName, lastName, dob, telephone, stAddress, city, state, zipcode);
		//BankAccount = new BankAccount(accountNumber, balance, AccountHolder, open);
		System.out.println("Welcome to the Bank!");
		menu();
	}

	public void close() {
		in.close();
		if (currentAccount != null) {
			try {
				database.updateAccount(currentAccount, null);
			} catch (IOException e) {
				System.out.println("There's been an error. Please try again.");
				e.printStackTrace();
			}
		}
		System.out.println("Thank you for using this ATM");
	}
}