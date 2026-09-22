import java.util.*;

public class atmTransactionSimulator {

    static final int PIN = 1234;

    public static boolean authenticateUser(int enteredPin) {
        return enteredPin == PIN;
    }

    public static void checkBalance(double balance) {
        System.out.printf("Current Balance: Rs. %.2f%n", balance);
    }

    public static double depositMoney(double balance, double amount) {
        if (amount > 0) {
            balance = balance + amount;
            System.out.printf("Deposit successful. New Balance: Rs. %.2f%n", balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
        return balance;
    }

    public static double withdrawMoney(double balance, double amount) {
        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount.");
        } else if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            balance = balance - amount;
            System.out.printf("Withdrawal successful. New Balance: Rs. %.2f%n", balance);
        }
        return balance;
    }

    public static void viewTransactions(String[] transactions, int count) {
        System.out.println();
        System.out.println("===== TRANSACTIONS =====");

        if (count == 0) {
            System.out.println("No transactions available.");
        } else {
            for (int i = 0; i < count; i++) {
                System.out.println(transactions[i]);
            }
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        double balance = 10000.00;
        String[] transactions = new String[100];
        int transactionCount = 0;

        System.out.println();
        System.out.println("===== ATM TRANSACTION SIMULATOR =====");

        System.out.print("Enter PIN: ");
        int enteredPin = sc.nextInt();

        if (authenticateUser(enteredPin) == false) {
            System.out.println("Incorrect PIN. Access denied.");
            sc.close();
            return;
        }

        System.out.println("PIN verified successfully!");

        int choice = 0;

        while (choice != 5) {

            System.out.println();
            System.out.println("===== ATM MENU =====");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. View Transactions");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    checkBalance(balance);
                    break;

                case 2:
                    System.out.print("Enter deposit amount: Rs. ");
                    double deposit = sc.nextDouble();

                    double oldBalance = balance;
                    balance = depositMoney(balance, deposit);

                    if (balance != oldBalance && transactionCount < 100) {
                        transactions[transactionCount] = "Deposited: Rs. " + deposit;
                        transactionCount++;
                    }
                    break;

                case 3:
                    System.out.print("Enter withdrawal amount: Rs. ");
                    double withdrawal = sc.nextDouble();

                    oldBalance = balance;
                    balance = withdrawMoney(balance, withdrawal);

                    if (balance != oldBalance && transactionCount < 100) {
                        transactions[transactionCount] = "Withdrawn: Rs. " + withdrawal;
                        transactionCount++;
                    }
                    break;

                case 4:
                    viewTransactions(transactions, transactionCount);
                    break;

                case 5:
                    System.out.println(
                        "Thank you for using the ATM Transaction Simulator!"
                    );
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        sc.close();
    }
}
