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
	private long zipcode;
	private String firstName;
	private String lastName;
	private String dob;
	private long telephone;
	private String stAddress;
	private String state;
	private String city;

	/*public BankAccount getBankAcc(String accountNumber) throws FileNotFoundException, IOException{
		String firstName;
		String lastName;
		String PIN;
		String dob;
		long telephone;
		String stAddress;
		String city;
		String state;
		long zipcode;
		int temp;
		BankAccount result;
	}
	public Database(String PIN, String firstName, String lastName, String dob, long telephone, String stAddress, String city, String state, long zipcode) {
		this.PIN = PIN;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.telephone = telephone;
		this.stAddress = stAddress;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
	}
	
public Database autoCreate() throws FileNotFoundException, IOException {
	try(BufferedReader br = new BufferedReader(new FileReader("accounts-db.txt"))){
		String line;
		while ((line = br.readLine()) != null) {
			temp = line.substring(0, 9);
			accountNumber = Long.valueOf(temp);
			
			result = new Database(accountNumber , PIN, balance, firstName, lastName, dob, telephone, stAddress, city, state, zipcode);
			
		}
	}
	return result;
}
*/
	}
