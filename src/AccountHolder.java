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
		private String address;
		private long telephone;
		private String stAddress;
		private String city;
		private String state;
		private long zipcode;
		
		/**
		 * Constructor for AccountHolder class.
		 * 
		 * @param name
		 * @param dob
		 * @param address
		 */
		
		public AccountHolder(String PIN, String firstName, String lastName, String dob, String address, long telephone, String stAddress, String city, String state, Long zipcode) {
			this.PIN = PIN;
			this.firstName = firstName;
			this.lastName = lastName;
			this.dob = dob;
			this.address = address;
			this.telephone = telephone;
			this.stAddress = stAddress;
			this.city = city;
			this.state = state;
			this.zipcode = zipcode;
		}
		
		/////////////////////////////////// GETTERS AND SETTERS ///////////////////////////////////
		
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
		public void setfirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getlastName() {
			return lastName;
		}
		public void setlastName(String lastName) {
			this.lastName = lastName;
		}
		public String getDOB() {
			return dob;
		}
		public void setDOB(String dob) {
			this.dob = dob;
		}
		public String getAddress() {
			return address;
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
		public void setzipcode(Long zipcode) {
			this.zipcode = zipcode;
		}
		
		public Long getzipcode() {
			return zipcode;
		}
		public void settelephone(Long telephone) {
			this.telephone = telephone;
		}
		
		public Long gettelephone() {
			return telephone;
		}
}