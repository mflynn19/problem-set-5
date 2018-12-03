import java.util.Scanner;

/**
 * Just like last time, the User class is responsible for retrieving
 * (i.e., getting), and updating (i.e., setting) user information.
 * This time, though, you'll need to add the ability to update user
 * information and display that information in a formatted manner.
 * 
 * Most of the functionality for this class should have already been
 * implemented last time. You can always reference my Github repository
 * for inspiration (https://github.com/rwilson-ucvts/java-sample-atm).
 */

public class AccountHolder {
		private String firstName;
		private String lastName;
		private String PIN;
		private String dob;
		private String telephone;
		private String stAddress;
		private String city;
		private String state;
		private String zipcode;
		
		public AccountHolder(String PIN, String firstName, String lastName, String dob, String telephone, String stAddress, String city, String state, String zipcode) {
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
				
		public String getPIN() {
			return PIN;
		}
		public void setPIN(String PIN) {
			this.PIN = PIN;
		}
		public String setnPIN(String opin, String nPIN) {
			if (opin == PIN) {
				if (nPIN.matches("\\d\\d\\d\\d")) {
					this.PIN = nPIN;
					return "0";
				}
				else {
					return "1";
				}
			}
			else {
				return "2";
			}
	
		}
		public String getfirstName() {
			return firstName;
		}
		
		public String getlastName() {
			return lastName;
		}
		
		public String getDOB() {
			return dob;
		}
		
		public void setstAddress(String stAddress) {
			this.stAddress = stAddress;
		}
		public String getstAddress() {
			return stAddress;
		}
		
		public void setcity(String city) {
			this.city = city;
		}
		public String getcity() {
			return city;
		}
		
		public void setstate(String state) {
			this.state = state;
		}
		public String getstate() {
			return state;
		}
		
		public void setzipcode(String zipcode) {
			this.zipcode = zipcode;
		}
		public String getzipcode() {
			return zipcode;
		}
		
		public void settelephone(String telephone) {
			this.telephone = telephone;
		}
		public String gettelephone() {
			return telephone;
		}
		public void printpersonalInfo() {
			System.out.println(firstName + " " + lastName);
			System.out.println(dob);
			System.out.println(stAddress + " " + city + ", " + state + zipcode);
			System.out.println(telephone);
		}
		public boolean updatePersonalInfo(Scanner in) {
			System.out.println("What would you like to update? 1 for PIN, 2 for telephone, 3 for address");
			switch (in.nextLine().toLowerCase().charAt(0)) {
			case '1': 
				//somehow call the update pin function? not working
			case '2':
				System.out.println("What is your updated telephone number?");
				this.settelephone(in.nextLine());
				return true;
			case 3:
				System.out.println("What is your new street address?");
				stAddress = in.nextLine();
				System.out.println("What is your new city named?");
				city = in.nextLine();
				System.out.println("What is your new state abbreviation?");
				state = in.nextLine();
				System.out.println("What is your new zipcode?");
				zipcode = in.nextLine();
				return true;
			default:
				System.out.println("Invalid input please try again.");
				return false;
		}
	}
}