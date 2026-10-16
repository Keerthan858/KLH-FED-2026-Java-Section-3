import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Stream;

// Module 4: custom checked exception
class InsufficientFundsException extends Exception {
    InsufficientFundsException(String message) { super(message); }
}

// Module 4: encapsulation - private balance, changed only through methods
class Account {
    private double balance;
    Account(double balance) { this.balance = balance; }
    double getBalance() { 
        return balance; 
    }

    void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Invalid deposit amount.");
        balance += amount;
    }

    void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) throw new IllegalArgumentException("Invalid withdrawal amount.");
        if (amount > balance) throw new InsufficientFundsException("Insufficient balance.");
        balance -= amount;
    }
}

public class atmTransactionSimulator {
    static final int PIN = 1234;
    static final Path LOG = Paths.get("transactions.txt");

    public static boolean authenticateUser(int enteredPin) { 
        return enteredPin == PIN; 
    }

    public static void checkBalance(Account acc) {
        System.out.printf("Current Balance: Rs. %.2f%n", acc.getBalance());
    }

    public static void depositMoney(Account acc, double amount) {
        try {
            acc.deposit(amount);
            log("DEPOSIT", amount, acc.getBalance());
            System.out.printf("Deposit successful. New Balance: Rs. %.2f%n", acc.getBalance());
        } catch (IllegalArgumentException e) { System.out.println(e.getMessage()); }
    }

    public static void withdrawMoney(Account acc, double amount) {
        try {
            acc.withdraw(amount);
            log("WITHDRAWAL", amount, acc.getBalance());
            System.out.printf("Withdrawal successful. New Balance: Rs. %.2f%n", acc.getBalance());
        } catch (InsufficientFundsException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    // Module 5: append one record to a text file
    public static void log(String type, double amount, double balance) {
        try {
            Files.writeString(LOG, String.format("%s,%.2f,%.2f%n", type, amount, balance),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) { System.out.println("Could not save transaction."); }
    }

    // Module 5 + 6: read file as a stream, split records, skip malformed lines
    public static void showHistory() {
        try (Stream<String> lines = Files.lines(LOG)) {
            System.out.println("===== TRANSACTION HISTORY =====");
            lines.map(line -> line.split(","))
                 .filter(p -> p.length == 3)
                 .forEach(p -> System.out.printf("%-10s Rs. %s   Balance: Rs. %s%n",
                                                 p[0], p[1], p[2]));
        } catch (IOException e) { System.out.println("No transactions yet."); }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Account acc = new Account(10000.00);
        System.out.println("\n===== ATM TRANSACTION SIMULATOR =====");
        System.out.print("Enter PIN: ");
        if (authenticateUser(sc.nextInt()) == false) {
            System.out.println("Incorrect PIN. Access denied.");
            sc.close();
            return;
        }
        System.out.println("PIN verified successfully!");
        int choice = 0;
        while (choice != 5) {
            System.out.println("\n===== ATM MENU =====");
            System.out.println("1. Check Balance\n2. Deposit Money\n3. Withdraw Money");
            System.out.println("4. Transaction History\n5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1: 
                    checkBalance(acc); 
                    break;
                case 2:
                    System.out.print("Enter deposit amount: Rs. ");
                        depositMoney(acc, sc.nextDouble()); 
                        break;
                case 3: 
                    System.out.print("Enter withdrawal amount: Rs. ");
                    withdrawMoney(acc, sc.nextDouble()); 
                    break;
                case 4: 
                    showHistory(); 
                    break;
                case 5: 
                    System.out.println("Thank you for using the ATM Transaction Simulator!");
                    break;
                default:
                     System.out.println("Invalid choice. Please try again.");
            }
        }
        sc.close();
    }
}