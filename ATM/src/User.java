
public class User {

	private int cardNumber;
	private int password;
	private double balance;

	public User(int cardNumber, int password, double balance) {

		this.cardNumber = cardNumber;
		this.password = password;
		this.balance = balance;
	}

	public int getCardNumber() {
		return cardNumber;
	}

	public int getPassword() {
		return password;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

}
