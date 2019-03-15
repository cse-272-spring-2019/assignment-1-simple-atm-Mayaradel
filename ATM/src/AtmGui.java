import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AtmGui extends Application {

	Scene login, menu, Deposit, getBalance, withdrawMoney, history;
	int transCounter = -1;
	int transMove;
	boolean Catch;

	AtmLogic obj = new AtmLogic();

	public static void main(String[] args) {
		launch(args);

	}

	public void start(Stage primaryStage) throws Exception {

		// login scene

		Label cardNumber = new Label("Card Number ");
		Label password = new Label("Password ");
		TextField cardNumberField = new TextField();
		PasswordField passwordField = new PasswordField();
		Button login = new Button("Login");
		GridPane loginGrid = new GridPane();
		loginGrid.add(cardNumber, 1, 0);
		loginGrid.add(password, 1, 1);
		loginGrid.add(cardNumberField, 2, 0);
		loginGrid.add(passwordField, 2, 1);
		loginGrid.add(login, 2, 2);
		this.login = new Scene(loginGrid, 300, 200);

		// login event

		login.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				try {
					Integer.parseInt(cardNumberField.getText());
					Integer.parseInt(passwordField.getText());
					Catch = true;
				} catch (Exception e) {
					Alert alertLogin = new Alert(AlertType.ERROR);
					alertLogin.setTitle("Login");
					alertLogin.setHeaderText(null);
					alertLogin.setContentText("Invalid cardnumber or password !");
					alertLogin.showAndWait();
					Catch = false;
				}

				if (Catch) {
					int cardNumber = Integer.parseInt(cardNumberField.getText());
					int password = Integer.parseInt(passwordField.getText());
					boolean check = obj.validate(cardNumber, password);
					if (check) {
						primaryStage.setScene(menu);
					} else {
						Alert alertLogin = new Alert(AlertType.ERROR);
						alertLogin.setTitle("Login");
						alertLogin.setHeaderText(null);
						alertLogin.setContentText("Invalid cardnumber or password !");
						alertLogin.showAndWait();
					}
				}
			}
		});

		// Menu scene

		Label view = new Label("Welcome !");
		Button deposit = new Button("Deposit");
		Button balanceInquiry = new Button("Check Balance");
		Button withdraw = new Button("Withdraw");
		Button history = new Button("History");
		Button logout = new Button("Logout");
		GridPane menuGrid = new GridPane();
		menuGrid.add(view, 2, 0);
		menuGrid.add(deposit, 0, 1);
		menuGrid.add(withdraw, 0, 2);
		menuGrid.add(balanceInquiry, 3, 1);
		menuGrid.add(history, 3, 2);
		menuGrid.add(logout, 2, 3);
		menu = new Scene(menuGrid, 300, 200);

		// menu events

		logout.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				primaryStage.setScene(AtmGui.this.login);
			}
		});

		// get balance event

		balanceInquiry.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				view.setText(String.valueOf(obj.balanceInquiry()));
				transCounter++;
			}
		});

		// Withdraw scene

		Label withDraw = new Label("Withdraw");
		TextField withdrawField = new TextField();
		Button Done = new Button("DONE");
		Button back = new Button("BACK");
		GridPane withdrawGrid = new GridPane();
		withdrawGrid.add(withDraw, 0, 0);
		withdrawGrid.add(withdrawField, 0, 1);
		withdrawGrid.add(back, 0, 7);
		withdrawGrid.add(Done, 0, 5);
		withdrawMoney = new Scene(withdrawGrid, 300, 200);

		// withdraw event

		withdraw.setOnAction(new EventHandler<ActionEvent>() {

		@Override
			public void handle(ActionEvent event) {

				primaryStage.setScene(withdrawMoney);

			}
		});

		Done.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				try {
					Double.parseDouble(withdrawField.getText());
					Catch = true;
				} catch (Exception e) {
					Alert alertLogin = new Alert(AlertType.ERROR);
					alertLogin.setTitle("Error");
					alertLogin.setHeaderText(null);
					alertLogin.setContentText("Unable to withdraw a string !");
					alertLogin.showAndWait();
					Catch = false;
				}
				if (Catch) {
					double amount = Double.parseDouble(withdrawField.getText());
					boolean check = obj.withdraw(amount);

					if (check == true) {
						withdrawField.setText("Done");
						transCounter++;

					}

					else {

						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error");
						alert.setHeaderText("Unable to withdraw this amount");
						alert.setContentText("Please check the amount to withdraw ");
						alert.showAndWait();

					}
				}

			}
		});

		back.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				primaryStage.setScene(menu);
			}
		});

		// Deposit scene

		Label Depositt = new Label("Deposit");
		Button Save = new Button("SAVE");
		Button Back = new Button("BACK");
		TextField DepositField = new TextField();
		GridPane depositGrid = new GridPane();
		depositGrid.add(Save, 0, 1);
		depositGrid.add(Back, 0, 2);
		depositGrid.add(Depositt, 0, 0);
		depositGrid.add(DepositField, 1, 0);
		Deposit = new Scene(depositGrid, 300, 200);

		// event

		deposit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				primaryStage.setScene(Deposit);

			}
		});

		Save.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					Double.parseDouble(DepositField.getText());
					Catch = true;
				} catch (Exception e) {
					Alert alertLogin = new Alert(AlertType.ERROR);
					alertLogin.setTitle("Error");
					alertLogin.setHeaderText(null);
					alertLogin.setContentText("Unable to deposit a string");
					alertLogin.showAndWait();
					Catch = false;
				}
				if (Catch) {
					double dep = Double.parseDouble(DepositField.getText());
					boolean check = obj.deposit(dep);

					if (check) {
						DepositField.setText("DONE");
						transCounter++;
					}

					else {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error");
						alert.setHeaderText("Unable to deposit this amount");
						alert.setContentText("Please check the amount to deposit ");
						alert.showAndWait();

					}
				}
			}
		});

		Back.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				primaryStage.setScene(menu);
			}
		});

		// history scene

		Label viewTrans = new Label();
		Button Back_history = new Button("Back");
		Button prev = new Button("Prev");
		Button next = new Button("Next");
		GridPane historyGrid = new GridPane();
		historyGrid.add(viewTrans, 1, 0);
		historyGrid.add(Back_history, 0, 2);
		historyGrid.add(prev, 1, 2);
		historyGrid.add(next, 2, 2);

		this.history = new Scene(historyGrid, 300, 200);

		// history events

		history.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				if (transCounter > 5) {
					transCounter = 4;
				}
				if (transCounter > -1) {

					primaryStage.setScene(AtmGui.this.history);
					viewTrans.setText(obj.transaction[transCounter]);
					transMove = transCounter;
				} else {
					Alert alertHistory = new Alert(AlertType.WARNING);
					alertHistory.setTitle("Error");
					alertHistory.setHeaderText(" Nothing to display ");
					alertHistory.setContentText(null);
					alertHistory.showAndWait();
				}

			}
		});

		Back_history.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				primaryStage.setScene(menu);

			}
		});

		prev.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (transMove > 0) {
					viewTrans.setText(obj.transaction[--transMove]);
				} else {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Warning ");
					alert.setHeaderText(null);
					alert.setContentText("Unable to reach a previous transaction");
					alert.showAndWait();

				}

			}
		});

		next.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				if (transMove < transCounter) {
					viewTrans.setText(obj.transaction[++transMove]);
				} else {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Warning ");
					alert.setHeaderText(null);
					alert.setContentText("Unable to reach a next transaction");
					alert.showAndWait();
				}
			}
		});

		primaryStage.setScene(this.login);
		primaryStage.setTitle("ATM");
		primaryStage.show();

	}

}
