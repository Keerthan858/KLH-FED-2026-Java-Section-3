# ATM Transaction Simulator

A simple **ATM Transaction Simulator** developed using Java for the course **Problem Solving using Programming (Java)**.

The program simulates basic ATM operations such as PIN verification, balance checking, depositing money, withdrawing money, and viewing transaction history. Every transaction is saved to a text file, so the history is available even after the program is closed and run again.

---

## 📚 Course Details

- **Subject:** Problem Solving using Programming (Java)
- **Course Code:** 26SC1101E
- **Section:** 3
- **Batch:** 11

---

## 👥 Team Members

| Name | Roll Number |
|---|---|
| **ANDE KEERTHAN** | 2620030661 |
| **MAMIDI YUVARAJ** | 2620030680 |

---

## 📁 Repository & Submission Contents

* **Java File (`.java`):**  
  * `atmTransactionSimulator.java` — Contains the code for the project.
* **Project Presentation in PPT (`.pptx`):**  
  * `ATM_Transaction_Simulator_Presentation.pptx` — Presentation of the project.

---

## 💻 Project Features

- PIN verification
- Check account balance
- Deposit money
- Withdraw money
- Check for insufficient balance
- Reject invalid (zero or negative) amounts
- Save every transaction to a text file
- View transaction history from the file
- Exit the ATM system

---

## 🛠️ Technologies Used

- **Programming Language:** Java
- **Input:** Scanner
- **Data Storage:** Text file (`transactions.txt`) using `java.nio.file`
- **Program Type:** Console-based application

---

## 📖 Java Concepts Used

### Module 1 — Java Foundations
- Variables, data types (`int`, `double`) and constants (`static final`)
- Arithmetic, relational and compound assignment operators (`+=`, `-=`)
- `Scanner` for console input
- Formatted output using `printf()`

### Module 2 — Control Flow
- `if` / `if-else` statements
- `switch` statement with `case`, `break` and `default`
- `while` loop for the menu

### Module 3 — Methods and Arrays
- Methods with parameters and return values
- Passing an object to a method (the `Account` object is shared, so its changes persist)
- 1D arrays (`String[]` produced by `split(",")` for each transaction record)

### Module 4 — Object-Oriented Design and Exception Handling
- Classes and objects (`Account` class, created with `new`)
- Constructor and the `this` keyword
- Encapsulation — `private` balance with an accessor (`getBalance()`)
- Instance and `static` members
- Custom **checked** exception (`InsufficientFundsException`) with `throw` and `throws`
- **Unchecked** exception (`IllegalArgumentException`) for invalid amounts
- `try-catch` and **multi-catch**

### Module 5 — Strings and File I/O
- `String.format()` to build records and `split()` to parse them
- Validation of malformed records (skipping lines that do not have 3 fields)
- `java.nio.file` (`Path`, `Paths`, `Files`)
- Appending to a file using `StandardOpenOption.APPEND`
- Handling a missing file
- `try-with-resources` for automatic closing of the file

### Module 6 — Streams and Lambdas
- Generics (`Stream<String>`)
- Lambda expressions
- Stream pipeline over file lines: `Files.lines()` → `map()` → `filter()` → `forEach()`

---

## ⚙️ How the Program Works

1. The user enters the ATM PIN.
2. The program verifies the entered PIN.
3. If the PIN is incorrect, access is denied.
4. If the PIN is correct, the ATM menu is displayed.
5. The user can select:
   - Check Balance
   - Deposit Money
   - Withdraw Money
   - Transaction History
   - Exit
6. Each deposit or withdrawal is validated by the `Account` class. Invalid amounts and insufficient balance are reported using exceptions.
7. Every successful transaction is appended to `transactions.txt`.
8. Transaction History reads the file as a stream and prints each valid record.
9. The user can continue performing operations until Exit is selected.

---

## ▶️ How to Run

```bash
javac atmTransactionSimulator.java
java atmTransactionSimulator
```

Requires JDK 11 or later.

---

## 🧾 Sample Output

```text
===== ATM TRANSACTION SIMULATOR =====
Enter PIN: 1234
PIN verified successfully!

===== ATM MENU =====
1. Check Balance
2. Deposit Money
3. Withdraw Money
4. Transaction History
5. Exit
Enter your choice: 2
Enter deposit amount: Rs. 2500
Deposit successful. New Balance: Rs. 12500.00
```

---

## 🔐 Default PIN

```text
1234
```

The starting balance is **Rs. 10000.00**.

---

## 📝 Notes

- The transaction history includes transactions from earlier runs, because it is read from `transactions.txt`. The account balance itself resets to Rs. 10000.00 every time the program starts.
- Only the concepts needed for this program are used. Topics such as inheritance, recursion, 2D arrays, and collections such as `HashMap` are not used.
