import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class will serve as the intermediary between our ATM program and
 * the database of BankAccounts. It'll be responsible for fetching accounts
 * when users try to login, as well as updating those accounts after any
 * changes are made.
 */

public class Database {
	private String PIN;
	private String zipcode;
	private String firstName;
	private String lastName;
	private String dob;
	private String telephone;
	private String stAddress;
	private String state;
	private String city;
	private String temp;
	private String accountNumber;
	private Database result;

	/*public BankAccount getBankAcc (String accountNumber) throws FileNotFoundException, IOException{
		String firstName;
		String lastName;
		String PIN;
		String dob;
		String telephone;
		String stAddress;
		String city;
		String state;
		String zipcode;
		String temp;
		BankAccount result;
		String accountStatus;
	}
	public Database(String PIN, String firstName, String lastName, String dob, String telephone, String stAddress, String city, String state, String zipcode, String temp, BankAccount result) {
		this.PIN = PIN;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.telephone = telephone;
		this.stAddress = stAddress;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.temp = temp;
		this.result = result;
	}
	
public Database autoCreate() throws FileNotFoundException, IOException {
	try(BufferedReader br = new BufferedReader(new FileReader("accounts-db.txt"))){
		String line;
		while ((line = br.readLine()) != null) {
			temp = line.substring(0, 9);
			accountNumber = String.valueOf(temp);
			PIN = line.substring(9,13);
			firstName = line.substring(28, 48);
			lastName = line.substring(48,  63);
			dob = line.substring(63,  71);
			telephone = line.substring(71, 81);
			stAddress = line.substring(81, 111);
			city = line.substring(111, 141);
			state = line.substring(141, 143);
			zipcode = line.substring(143, 148);
			result = new Database(accountNumber , PIN, firstName, lastName, dob, telephone, stAddress, city, state, zipcode);
		}
	return result;

	}*/
}
