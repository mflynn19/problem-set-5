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

	public ATM(BankAccount acc, AccountHolder ach) {
		this.acc = acc;
<<<<<<< HEAD
		
=======
		//this.ach = ach;
>>>>>>> 3e2ea194c070ebf9c47145e7e8a41da354e95f48
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
<<<<<<< HEAD
		System.out.println("Welcome to the Bank of UCVTS. If you have an account please select 1. Otherwise select 2 to create an account. Select 3 to quit.");
		int i = in.nextInt();
=======
		System.out.println("Welcome to the bank of UCVTS. If you have an account please select 1. Otherwise select 2 to create an account. Select 3 to quit.");
		long i = in.nextLong();
>>>>>>> 3e2ea194c070ebf9c47145e7e8a41da354e95f48
		in.nextLine();
		if (i == 1) {
			if (i == acc.getAccountNumber()) {
				System.out.println("Please enter your pin.");
				int n = in.nextInt();
				in.nextLine();
				if (n == Integer.parseInt(acc.getAccountHolder().getPIN())) {
					String select = "";
					while (select != "9") {
						System.out.println("Please select 1 to withdraw; 2 to deposit; 3 to see your account balance; 4 to transfer funds ; 5 to view your personal information; "
								+ "6 to edit your personal information; 7 to close your account; 8 to Save Changes and Log Out; 9 to exit.");
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
<<<<<<< HEAD
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
=======
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
>>>>>>> 3e2ea194c070ebf9c47145e7e8a41da354e95f48
							//view info
							this.currentAccount.getAccountHolder().printpersonalInfo();
							in.nextLine();
							break;
						case "6":
							//edit info
<<<<<<< HEAD
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
							int k = in.nextInt();
							if (k == 2) {
								return;
							}
							else if (k == 1) {
							currentAccount.setOpen("N");
							}
						case "8":
=======
							
					
						case 7:
							//close account?
							
							System.out.println("Account has been closed.");
						case 8:
>>>>>>> 3e2ea194c070ebf9c47145e7e8a41da354e95f48
							//save edits
							System.out.println("Logging out");
							try {
								this.database.updateAccount(this.currentAccount, null);
							}
							catch(IOException e) {
								System.out.println("Could not update account!!!");
							}
								this.currentAccount = null;
<<<<<<< HEAD
						case "9":
							in.close();
							if (currentAccount != null) {
								try {
									this.database.updateAccount(this.currentAccount, null);
								} catch (IOException e) {
									System.out.println("Please try again.");
									e.printStackTrace();
								}							}
=======
							
						case 9:
>>>>>>> 3e2ea194c070ebf9c47145e7e8a41da354e95f48
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
			/*try {
				this.createAccount();
			}
			catch (IOException e) {
				System.out.println("Error. Try again.");
			}
			break;*/	
		}
		else {
			System.out.println("Goodbye!");
		}
		}
		else {
			System.out.println("Goodbye!");
		}
	}
}