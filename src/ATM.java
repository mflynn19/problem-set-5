import java.io.*;
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
	private BankAccount currentAccount;
	private BankAccount BankAccount;
	public AccountHolder AccountHolder;
	private Database database;
	private static Scanner in = new Scanner(System.in);
	private BankAccount destination;
	
	public ATM() throws FileNotFoundException, IOException{
		this.destination = null;
		this.database = new Database("accounts-db.txt");
	}
	
	public static String rPAD(String s, int n) {
	     return String.format("%1$-" + n + "s", s);  
	}
	
	public void menu() throws IOException{
		System.out.println("Welcome to the Bank of UCVTS! Please select 1 if you already have an account. Select 2 to create a new account. Select 3 to exit.");
		switch (in.nextLine().charAt(0)) {
			case '1':
				Scanner in = new Scanner(System.in);
				Long testNum = (long) 0;
				String testPin = "";
				double returnval = 0;
				System.out.println("Please enter your account number.");
				testNum = in.nextLong();
				BankAccount = database.getAccount(testNum);
				
				Scanner textscan= new Scanner(System.in);
				System.out.println("Please enter your PIN.");
				testPin = textscan.nextLine();

				while(!(testPin.equals(BankAccount.getAccountHolder().getPIN()))){
					System.out.println("Please enter your correct account number.");
					testNum = in.nextLong();
					BankAccount = database.getAccount(testNum);
					System.out.print("Please enter your correct PIN.");
					testPin = textscan.nextLine();
				}
					System.out.println("Please select 1 to deposit; 2 to withdraw; 3 to see your account balance; 4 to transfer funds ; 5 to view your personal information;" + 
							"					+ \"6 to edit your personal information; 7 to close your account; 8 to Save Changes and exit.");
					char select = in.next().charAt(0);
					switch (select) {
						case '1':
							//deposit
							System.out.println("How much money would you like to deposit?");
							Double amount = in.nextDouble();
							this.BankAccount.deposit(amount);
							returnval = BankAccount.deposit(amount);
							if(returnval == 0){
								System.out.println("Invalid deposit amount.");
							}
							else if (returnval == 1) {
								System.out.println("Unable to complete transaction. Please see a bank teller.");
							}
							else {
								System.out.println(BankAccount.getBalance());
							}
							break;
						case '2':
							//withdraw
							System.out.println("How much money would you like to withdraw?");
							if(returnval == 0){
								System.out.println("Insufficient funds.");
							}
							else if (returnval == 1) {
								System.out.println("Invalid withdraw amount.");
							}
							else if (returnval == 2){
								System.out.println(BankAccount.getBalance());
							}
							break;
						case '3':
							//transfer
							System.out.println("What is the account number you would like to transfer funds to?");
							long receiver = in.nextLong();
							destination = database.getAccount(receiver);
							System.out.println("What is the amount you would like to transfer?");
							double much = in.nextDouble();
							if(destination != null) {
								returnval = BankAccount.transfer(destination, much);
								if(returnval == 1) {
									System.out.println("Your balance: " + BankAccount.getBalance());
								}
							}else {
								System.out.println("Invalid account");
								}
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
							this.currentAccount.getAccountHolder().updatePersonalInfo(in);
							break;
						case '7':
							//close account with bank
							System.out.println("To close your account with the Bank of UCVTS please press 1; to cancel press 2.");
							int p = in.nextInt();
							if (p == 2) {
								System.out.println("Thanks for that!");
							}
							else if (p == 1) {
								AccountHolder.setOpen("N");
							}
							
						case '8':
							//log out and save
							System.out.println("Logging out");
							this.database.updateAccount(this.currentAccount, destination);
							this.currentAccount = null;
						}
					
			case '2':
				Scanner nuevo = new Scanner(System.in);
				String firstname;
				String lastname;
				String telephone;
				String address;
				String city;
				String state;
				String postalcode;
				String dob;
				String pinnum;
				String balance = "0";
				
				System.out.println("Welcome to the Bank of UCVTS! Enter the information below to open an account.");
				do {
					System.out.print("Please enter your first name. 15 characters max");
					firstname = nuevo.nextLine();
				}
				while (firstname.length() > 15);
				firstname = rPAD(firstname, 15);

				do {
					System.out.print("Please enter your last name");
					String lastName = nuevo.nextLine();
					lastname = lastName;
				}
				while (lastname.length() > 20);
				lastname = rPAD(lastname, 20);
				
				do {
					System.out.print("Please enter your date of birth in the following format: YYYYMMDD");
					dob = nuevo.nextLine();
				}
				while (dob.length() != 8);
				dob = rPAD(dob, 8);
				
				do {
					System.out.print("Please enter your telephone number; ten digits only.");
					telephone = nuevo.nextLine();	
				}
				while (telephone.length() != 10);
				telephone = rPAD(telephone, 10);
				
				do {
					System.out.print("What is your street address? Maximum 30 characters.");
					address = nuevo.nextLine();
				}
				while (address.length() > 30);
				address = rPAD(address, 30);
				
				do {
					System.out.print("What is the city/town called that you reside in?");
					city = nuevo.nextLine();
				}
				while (city.length() > 30);
				city = rPAD(city, 30);
				
				do {
					System.out.println("What is your 5 digit zipcode?");
					postalcode = nuevo.nextLine();
				}
				while (postalcode.length() != 5);
				postalcode = rPAD(postalcode, 5);
				
				do {
					System.out.print("What is your resident state abbreviated as (ex. New Jersey should be typed as NJ)?");
					state = nuevo.nextLine();
				}
				while (state.length() != 2);
				state = rPAD(state, 2);
				
				do {
					System.out.print("Enter a 4 digit numerical PIN.");
					pinnum = nuevo.nextLine();
				}
				while (pinnum.length() != 4);
				pinnum = rPAD(pinnum, 4);
				
				balance = rPAD(balance, 15);
				
				long accountnumber = database.getMaxAccountNumber() + 1;
				
				BankAccount newacc = new BankAccount(accountnumber + pinnum + balance + lastname + firstname + dob + telephone + address + city + state + postalcode + "Y");
				database.updateAccount(newacc, destination);
				System.out.println("Welcome to the Bank! Your account number is" + accountnumber);
				menu();
				break;
            case '3':
    			BankAccount.getAccountHolder().setOpen("N");
            	database.updateAccount(BankAccount, destination);
            	System.out.println("Your account has been closed! thank you for doing business with us!");
                break;
            default:
            	System.out.println("Please choose a viable option!");
            	menu();
		}
		System.out.println("Thank you for using this ATM");
		in.close();
	}
}