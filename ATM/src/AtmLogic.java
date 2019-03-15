
public class AtmLogic {

	int historyCounter = 0;
	String[] transaction = new String[5];

	User user = new User(1234, 123, 0);

	public boolean validate(int cardNumber, int password) {

		if (cardNumber == user.getCardNumber()) {
			if (password == user.getPassword()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public void history(String type, double transactionAmount) {

		if (historyCounter < 5) {
			transaction[historyCounter] = type + "\n" + transactionAmount;
			historyCounter++;
		} else {
			for (int i = 0; i < 4; i++) {

				transaction[i] = transaction[i + 1];
			}
			transaction[4] = type + "\n" + transactionAmount;
		}
	}

	public boolean withdraw(double amount) {

		if (amount > user.getBalance() || amount <= 0) {

			System.out.println("error");
			return false;
		}

		else {
			user.setBalance(user.getBalance() - amount);
			history("Withdraw", amount);
			return true;

		}
	}

	public boolean deposit(double deposit) {
		if (deposit > 0) {

			user.setBalance(user.getBalance() + deposit);
			history("Deposite", deposit);
			return true;

		} else
			return false;
	}

	public double balanceInquiry() {
		history("Balance Inquiry", user.getBalance());
		return user.getBalance();

	}

}
