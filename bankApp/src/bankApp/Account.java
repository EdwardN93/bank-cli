package bankApp;

public class Account {

	private String name;
	private String email;

	private int sum, pin, failedAttempts;
	private boolean mustChangePin;
	private boolean activeAccount;
	private boolean locked;
	
	
	public Account(String name, String email, int sum) {
		this.name = name;
		this.email = email;
		if(sum < 0) {
			this.sum = 0;
		} else {
			this.sum = sum;
		}
		
		this.pin = 1111;
		this.failedAttempts = 0;
		this.locked = false;
		this.mustChangePin = true;
		this.activeAccount = false;
	}
	
	public String getInfo() {
		return "\nAccount info: " + name  + "\nEmail: " + email  + "\nSum: " + sum;
	}
	
	public String getBalance() {
		return "Your balance is " + sum;
	}
	
	public String addBalance(int amount) {
		if(mustChangePin) {
			return warnUserAboutPinChange();
		}
		
		if(amount <= 0) {
			return "Your amount should be higher than 0";
		}
		
		sum += amount;
		return "Your balance is: " + sum;
	}
	
	public String withdraw(int amount) {
		if(mustChangePin) {
			return warnUserAboutPinChange();
		}
		
		if(amount <= 0) {
			return "Your amount should be higher than 0";
		}
		
		if(amount > sum) {
			return "Your balance is: " + sum + ".The amount to withdraw exceeds your balance";
		}
		
		sum -= amount;
		return "Withdrawal successful. Balance: " + sum;
	}
	
	public String changePin(int newPin) {
		if(newPin < 1000 || newPin > 9999) {
			return "Invalid pin, try again";
		} else {
			this.pin = newPin;
			activeAccount = true;
			if(mustChangePin) {
				mustChangePin = false;
				return "Pin changed succesfuly. Account is now active!";
			} else {				
				return "Pin changed succesfuly";
			}
		}
	}
	
	
	public String deactivateAccount() {
		if(mustChangePin) return warnUserAboutPinChange();
		if(!activeAccount) {
			return "Account is already inactive";
		} else {
			activeAccount = false;
			return "Account is deactivated, Gudbay * please note we'll keep your money THANK YOU BYE ! (jk)";
		}
	}

	public boolean checkPin(int enteredPin) {
		if (locked) return false;

		if (enteredPin == pin) {
			failedAttempts = 0;
			return true;
		}

		failedAttempts++;

		if (failedAttempts >= 3) {
			locked = true;
		}

		return false;
	}
	
	public String canOperateMessage() {
		if(mustChangePin) return warnUserAboutPinChange();
		if(!activeAccount) return "Account is deactivated";
		return null;
	}
	
	// warns
	public String warnUserAboutPinChange() {
		return "You must change your pin first";
	}
	
	public boolean isAccountActive() {
		return activeAccount;
	}

	public String getEmail() { return email; }
}

