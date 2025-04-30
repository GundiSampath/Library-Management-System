import java.util.*;
import java.util.regex.*;

class Color {
    public static final String RESET = "\033[0m";
    public static final String RED = "\033[0;31m";
    public static final String GREEN = "\033[0;32m";
    public static final String YELLOW = "\033[0;33m";
    public static final String BLUE = "\033[0;34m";
    public static final String PURPLE = "\033[0;35m";
    public static final String CYAN = "\033[0;36m";
    public static final String WHITE = "\033[0;37m";
    public static final String RED_BOLD = "\033[1;31m";
    public static final String GREEN_BOLD = "\033[1;32m";
    public static final String YELLOW_BOLD = "\033[1;33m";
    public static final String BLUE_BOLD = "\033[1;34m";
    public static final String PURPLE_BOLD = "\033[1;35m";
    public static final String CYAN_BOLD = "\033[1;36m";
    public static final String WHITE_BOLD = "\033[1;37m";
}

abstract class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public abstract void displayMenu();
}

class Admin extends User {
    public Admin(String username, String password) {
        super(username, password);
    }

    @Override
    public void displayMenu() {
        adminMenu();
    }

    private void adminMenu() {
        Scanner scanner = Library.getScanner();
        while (true) {
            System.out.println("\n" + Color.BLUE_BOLD + "Admin Menu" + Color.RESET);
            System.out.println(Color.YELLOW + "1. Search Book");
            System.out.println("2. Add Book");
            System.out.println("3. Update Book Quantity");
            System.out.println("4. Show Registered Students");
            System.out.println("5. Logout" + Color.RESET);
            System.out.print(Color.WHITE_BOLD + "Please enter an option: " + Color.RESET);

            int choice = Library.getIntInput(1, 5);

            switch (choice) {
                case 1:
                    Library.searchBook();
                    break;
                case 2:
                    Library.addBook();
                    break;
                case 3:
                    Library.updateBookQuantity();
                    break;
                case 4:
                    Library.showRegisteredStudents();
                    break;
                case 5:
                    System.out.println(Color.GREEN_BOLD + "Logout successful!" + Color.RESET);
                    return;
            }
        }
    }
}

class Student extends User {
    private String branch;
    private String id;
    private String email;
    private String mobile;

    public Student(String username, String branch, String id, String email, String mobile, String password) {
        super(username, password);
        this.branch = branch;
        this.id = id;
        this.email = email;
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }

    public void displayInfo() {
        System.out.println("\n" + Color.CYAN_BOLD + "Student Details:" + Color.RESET);
        System.out.println(Color.YELLOW + "Name: " + Color.RESET + getUsername());
        System.out.println(Color.YELLOW + "Branch: " + Color.RESET + branch);
        System.out.println(Color.YELLOW + "ID: " + Color.RESET + id);
        System.out.println(Color.YELLOW + "Email: " + Color.RESET + email);
        System.out.println(Color.YELLOW + "Mobile: " + Color.RESET + mobile);
    }

    @Override
    public void displayMenu() {
        studentMenu();
    }

    private void studentMenu() {
        Scanner scanner = Library.getScanner();
        while (true) {
            System.out.println("\n" + Color.BLUE_BOLD + "Student Menu" + Color.RESET);
            System.out.println(Color.YELLOW + "1. Search Book");
            System.out.println("2. Borrow Book");
            System.out.println("3. Return Book");
            System.out.println("4. Logout" + Color.RESET);
            System.out.print(Color.WHITE_BOLD + "Please enter an option: " + Color.RESET);

            int choice = Library.getIntInput(1, 4);

            switch (choice) {
                case 1:
                    Library.searchBook();
                    System.out.println("\nPress enter to return to menu...");
                    scanner.nextLine();
                    break;
                case 2:
                    Library.borrowBook(this);
                    break;
                case 3:
                    Library.returnBook(this);
                    break;
                case 4:
                    System.out.println(Color.GREEN_BOLD + "Logout successful!" + Color.RESET);
                    return;
            }
        }
    }
}

class Book {
    private String name;
    private String author;
    private int quantity;
    private double rentPerDay;

    public Book(String name, String author, int quantity, double rentPerDay) {
        this.name = name;
        this.author = author;
        this.quantity = quantity;
        this.rentPerDay = rentPerDay;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getRentPerDay() {
        return rentPerDay;
    }

    public void displayInfo() {
        System.out.println("\n" + Color.PURPLE_BOLD + "Book Details:" + Color.RESET);
        System.out.println(Color.YELLOW + "Name: " + Color.RESET + name);
        System.out.println(Color.YELLOW + "Author: " + Color.RESET + author);
        System.out.println(Color.YELLOW + "Quantity: " + Color.RESET + quantity);
        System.out.println(Color.YELLOW + "Rent per day: " + Color.RESET + rentPerDay);
    }
}

class Transaction {
    private String studentUsername;
    private String bookName;
    private Date borrowDate;
    private Date returnDate;
    private double totalAmount;
    private boolean returned;

    public Transaction(String studentUsername, String bookName, Date borrowDate, Date returnDate, double totalAmount) {
        this.studentUsername = studentUsername;
        this.bookName = bookName;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.totalAmount = totalAmount;
        this.returned = false;
    }

    public String getStudentUsername() {
        return studentUsername;
    }

    public String getBookName() {
        return bookName;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    public void displayInfo() {
        System.out.println("\n" + Color.GREEN_BOLD + "Transaction Details:" + Color.RESET);
        System.out.println(Color.YELLOW + "Student: " + Color.RESET + studentUsername);
        System.out.println(Color.YELLOW + "Book: " + Color.RESET + bookName);
        System.out.println(Color.YELLOW + "Borrow Date: " + Color.RESET + borrowDate);
        System.out.println(Color.YELLOW + "Return Date: " + Color.RESET + returnDate);
        System.out.println(Color.YELLOW + "Total Amount: " + Color.RESET + totalAmount);
        System.out.println(Color.YELLOW + "Status: " + Color.RESET
                + (returned ? Color.GREEN + "Returned" : Color.RED + "Not Returned") + Color.RESET);
    }
}

class Library {
    private static Admin[] admins = { new Admin("admin", "Admin@123") };
    private static Student[] students = new Student[100];
    private static int studentCount = 0;
    private static Book[] books = new Book[100];
    private static int bookCount = 0;
    private static Transaction[] transactions = new Transaction[100];
    private static int transactionCount = 0;
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();

    static {
        books[bookCount++] = new Book("Java Programming", "John Doe", 5, 2.5);
        books[bookCount++] = new Book("Data Structures", "Jane Smith", 3, 3.0);
        books[bookCount++] = new Book("Algorithms", "Robert Johnson", 4, 2.0);
    }

    public static Scanner getScanner() {
        return scanner;
    }

    public static int getStudentCount() {
        return studentCount;
    }

    public static Student getStudent(int index) {
        if (index >= 0 && index < studentCount) {
            return students[index];
        }
        return null;
    }

    public static void searchBook() {
        System.out.println("\n" + Color.BLUE_BOLD + "Search Book" + Color.RESET);
        System.out.print(Color.YELLOW + "Enter book name: " + Color.RESET);
        String bookName = scanner.nextLine();

        boolean found = false;
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getName().equalsIgnoreCase(bookName)) {
                books[i].displayInfo();
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println(Color.RED + "Book not found." + Color.RESET);
        }
    }

    public static void addBook() {
        System.out.println("\n" + Color.BLUE_BOLD + "Add Book" + Color.RESET);

        if (bookCount >= books.length) {
            System.out.println(Color.RED + "Cannot add more books. Library capacity reached." + Color.RESET);
            return;
        }

        System.out.print(Color.YELLOW + "Enter book name: " + Color.RESET);
        String name = scanner.nextLine();
        System.out.print(Color.YELLOW + "Enter author name: " + Color.RESET);
        String author = scanner.nextLine();

        System.out.print(Color.YELLOW + "Enter quantity: " + Color.RESET);
        int quantity = getIntInput(1, Integer.MAX_VALUE);

        System.out.print(Color.YELLOW + "Enter rent per day: " + Color.RESET);
        double rent = getDoubleInput(0.1, Double.MAX_VALUE);

        books[bookCount++] = new Book(name, author, quantity, rent);
        System.out.println(Color.GREEN_BOLD + "Book added successfully!" + Color.RESET);
    }

    public static void updateBookQuantity() {
        System.out.println("\n" + Color.BLUE_BOLD + "Update Book Quantity" + Color.RESET);
        System.out.print(Color.YELLOW + "Enter book name: " + Color.RESET);
        String bookName = scanner.nextLine();

        boolean found = false;
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getName().equalsIgnoreCase(bookName)) {
                System.out.print(Color.YELLOW + "Enter new quantity: " + Color.RESET);
                int newQuantity = getIntInput(0, Integer.MAX_VALUE);
                books[i].setQuantity(newQuantity);
                System.out.println(Color.GREEN_BOLD + "Book quantity updated successfully!" + Color.RESET);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println(Color.RED + "Book not found." + Color.RESET);
        }
    }

    public static void showRegisteredStudents() {
        System.out.println("\n" + Color.BLUE_BOLD + "Registered Students" + Color.RESET);

        if (studentCount == 0) {
            System.out.println(Color.YELLOW + "No students registered yet." + Color.RESET);
            return;
        }

        for (int i = 0; i < studentCount; i++) {
            students[i].displayInfo();
            System.out.println(Color.CYAN + "---------------------" + Color.RESET);
        }
    }

    public static void borrowBook(Student student) {
        System.out.println("\n" + Color.BLUE_BOLD + "Borrow Book" + Color.RESET);

        System.out.println(Color.PURPLE + "\nAvailable Books:" + Color.RESET);
        boolean booksAvailable = false;
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getQuantity() > 0) {
                System.out.println(Color.YELLOW + books[i].getName() + Color.RESET + " - Available: " +
                        Color.GREEN + books[i].getQuantity() + Color.RESET +
                        ", Rent per day: " + Color.CYAN + books[i].getRentPerDay() + Color.RESET);
                booksAvailable = true;
            }
        }

        if (!booksAvailable) {
            System.out.println(Color.RED + "No books are currently available." + Color.RESET);
            return;
        }

        System.out.print(Color.YELLOW + "\nEnter book name you want to borrow: " + Color.RESET);
        String bookName = scanner.nextLine();

        Book selectedBook = null;
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getName().equalsIgnoreCase(bookName)) {
                selectedBook = books[i];
                break;
            }
        }

        if (selectedBook == null) {
            System.out.println(Color.RED + "Book not found." + Color.RESET);
            return;
        }

        if (selectedBook.getQuantity() <= 0) {
            System.out.println(Color.RED + "This book is currently not available." + Color.RESET);
            return;
        }

        System.out.print(Color.YELLOW + "How many days do you want to borrow the book? " + Color.RESET);
        int days = getIntInput(1, 30);

        Date borrowDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(borrowDate);
        calendar.add(Calendar.DATE, days);
        Date returnDate = calendar.getTime();

        double totalAmount = selectedBook.getRentPerDay() * days;

        System.out.println(Color.PURPLE + "\nBorrowing Details:" + Color.RESET);
        System.out.println(Color.YELLOW + "Book: " + Color.RESET + selectedBook.getName());
        System.out.println(Color.YELLOW + "Author: " + Color.RESET + selectedBook.getAuthor());
        System.out.println(Color.YELLOW + "Borrow Date: " + Color.RESET + borrowDate);
        System.out.println(Color.YELLOW + "Return Date: " + Color.RESET + returnDate);
        System.out.println(Color.YELLOW + "Total Amount: " + Color.RESET + totalAmount);

        System.out.print(Color.YELLOW + "\nDo you want to proceed with payment? (yes/no): " + Color.RESET);
        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("yes")) {
            System.out.print(Color.YELLOW + "Enter your UPI ID: " + Color.RESET);
            String upiId = scanner.nextLine();

            if (upiId.contains("@") && upiId.length() > 5) {
                System.out.println(Color.GREEN_BOLD + "\nPayment Successful!" + Color.RESET);
                System.out.println(Color.PURPLE + "Transaction Details:" + Color.RESET);
                student.displayInfo();
                System.out.println(Color.YELLOW + "Book: " + Color.RESET + selectedBook.getName());
                System.out.println(Color.YELLOW + "Total Amount Paid: " + Color.RESET + totalAmount);
                System.out.println(Color.YELLOW + "Return Date: " + Color.RESET + returnDate);

                selectedBook.setQuantity(selectedBook.getQuantity() - 1);

                if (transactionCount < transactions.length) {
                    transactions[transactionCount++] = new Transaction(
                            student.getUsername(), selectedBook.getName(), borrowDate, returnDate, totalAmount);
                } else {
                    System.out.println(
                            Color.RED + "Cannot process transaction. Transaction limit reached." + Color.RESET);
                }
            } else {
                System.out.println(Color.RED + "Invalid UPI ID. Payment cancelled." + Color.RESET);
            }
        } else {
            System.out.println(Color.YELLOW + "Payment cancelled." + Color.RESET);
        }
    }

    public static void returnBook(Student student) {
        System.out.println("\n" + Color.BLUE_BOLD + "Return Book" + Color.RESET);

        List<Transaction> studentTransactions = new ArrayList<>();
        for (int i = 0; i < transactionCount; i++) {
            if (transactions[i].getStudentUsername().equals(student.getUsername()) && !transactions[i].isReturned()) {
                studentTransactions.add(transactions[i]);
            }
        }

        if (studentTransactions.isEmpty()) {
            System.out.println(Color.YELLOW + "You have no books to return." + Color.RESET);
            return;
        }

        System.out.println(Color.PURPLE + "\nBooks you have borrowed:" + Color.RESET);
        for (int i = 0; i < studentTransactions.size(); i++) {
            System.out.println(Color.YELLOW + (i + 1) + ". " + Color.RESET + studentTransactions.get(i).getBookName() +
                    Color.YELLOW + " (Due: " + Color.RESET + studentTransactions.get(i).getReturnDate() + Color.YELLOW
                    + ")" + Color.RESET);
        }

        System.out.print(Color.YELLOW + "\nEnter the number of the book you want to return: " + Color.RESET);
        int choice = getIntInput(1, studentTransactions.size());

        Transaction selectedTransaction = studentTransactions.get(choice - 1);
        selectedTransaction.setReturned(true);

        for (int i = 0; i < bookCount; i++) {
            if (books[i].getName().equalsIgnoreCase(selectedTransaction.getBookName())) {
                books[i].setQuantity(books[i].getQuantity() + 1);
                break;
            }
        }

        System.out.println(Color.GREEN_BOLD + "\nBook returned successfully!" + Color.RESET);
        System.out.println(Color.YELLOW + "Book: " + Color.RESET + selectedTransaction.getBookName());
        System.out.println(Color.GREEN + "Thank you for returning the book on time." + Color.RESET);
    }

    public static void studentSignup() {
        System.out.println("\n" + Color.BLUE_BOLD + "Student Signup" + Color.RESET);

        if (studentCount >= students.length) {
            System.out.println(Color.RED + "Cannot register more students. Limit reached." + Color.RESET);
            return;
        }

        System.out.print(Color.YELLOW + "Enter student username: " + Color.RESET);
        String username = scanner.nextLine();

        for (int i = 0; i < studentCount; i++) {
            if (students[i].getUsername().equals(username)) {
                System.out.println(Color.RED + "Username already exists. Please choose another." + Color.RESET);
                return;
            }
        }

        System.out.print(Color.YELLOW + "Enter student branch: " + Color.RESET);
        String branch = scanner.nextLine();

        System.out.print(Color.YELLOW + "Enter student ID: " + Color.RESET);
        String id = scanner.nextLine();

        String email = validateEmail();
        if (email == null)
            return;

        String mobile = validateMobile();
        if (mobile == null)
            return;

        String password = validatePassword("student");
        if (password == null)
            return;

        students[studentCount++] = new Student(username, branch, id, email, mobile, password);
        System.out.println(Color.GREEN_BOLD + "\nStudent signup successful!" + Color.RESET);
    }

    private static String validatePassword(String userType) {
        String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        Pattern pattern = Pattern.compile(passwordPattern);

        for (int attempts = 3; attempts > 0; attempts--) {
            System.out.print(Color.YELLOW
                    + "Enter password (must be 8+ chars with uppercase, lowercase, number & symbol): " + Color.RESET);
            String password = scanner.nextLine();

            if (pattern.matcher(password).matches()) {
                return password;
            }

            System.out.println(
                    Color.RED + "Invalid password. You have " + (attempts - 1) + " attempts left." + Color.RESET);
        }

        System.out.println(Color.RED_BOLD + "Too many failed attempts. Returning to main menu." + Color.RESET);
        return null;
    }

    private static String validateEmail() {
        String emailPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailPattern);

        for (int attempts = 3; attempts > 0; attempts--) {
            System.out.print(Color.YELLOW + "Enter student email (must end with @gmail.com): " + Color.RESET);
            String email = scanner.nextLine();

            if (email.endsWith("@gmail.com") && pattern.matcher(email).matches()) {
                int otp = random.nextInt(900000) + 100000;
                System.out.println(Color.CYAN + "OTP sent to your email: " + Color.YELLOW_BOLD + otp + Color.RESET);

                for (int otpAttempts = 3; otpAttempts > 0; otpAttempts--) {
                    System.out.print(Color.YELLOW + "Enter OTP: " + Color.RESET);
                    int enteredOtp = getIntInput(100000, 999999);

                    if (enteredOtp == otp) {
                        return email;
                    }

                    System.out.println(
                            Color.RED + "Invalid OTP. You have " + (otpAttempts - 1) + " attempts left." + Color.RESET);
                }

                System.out.println(
                        Color.RED_BOLD + "Too many failed OTP attempts. Returning to main menu." + Color.RESET);
                return null;
            }

            System.out
                    .println(Color.RED + "Invalid email. You have " + (attempts - 1) + " attempts left." + Color.RESET);
        }

        System.out.println(Color.RED_BOLD + "Too many failed attempts. Returning to main menu." + Color.RESET);
        return null;
    }

    private static String validateMobile() {
        for (int attempts = 3; attempts > 0; attempts--) {
            System.out
                    .print(Color.YELLOW + "Enter student mobile number (10 digits starting with 6-9): " + Color.RESET);
            String mobile = scanner.nextLine();

            if (mobile.matches("[6-9]\\d{9}")) {
                int otp = random.nextInt(900000) + 100000;
                System.out.println(Color.CYAN + "OTP sent to your mobile: " + Color.YELLOW_BOLD + otp + Color.RESET);

                for (int otpAttempts = 3; otpAttempts > 0; otpAttempts--) {
                    System.out.print(Color.YELLOW + "Enter OTP: " + Color.RESET);
                    int enteredOtp = getIntInput(100000, 999999);

                    if (enteredOtp == otp) {
                        return mobile;
                    }

                    System.out.println(
                            Color.RED + "Invalid OTP. You have " + (otpAttempts - 1) + " attempts left." + Color.RESET);
                }

                System.out.println(
                        Color.RED_BOLD + "Too many failed OTP attempts. Returning to main menu." + Color.RESET);
                return null;
            }

            System.out.println(
                    Color.RED + "Invalid mobile number. Must start with 6-9 and be 10 digits. You have "
                            + (attempts - 1) + " attempts left." + Color.RESET);
        }

        System.out.println(Color.RED_BOLD + "Too many failed attempts. Returning to main menu." + Color.RESET);
        return null;
    }

    public static void studentPasswordReset() {
        System.out.println("\n" + Color.BLUE_BOLD + "Student Password Reset" + Color.RESET);
        System.out.print(Color.YELLOW + "Enter student username: " + Color.RESET);
        String username = scanner.nextLine();

        Student student = null;
        for (int i = 0; i < studentCount; i++) {
            if (students[i].getUsername().equals(username)) {
                student = students[i];
                break;
            }
        }

        if (student == null) {
            System.out.println(Color.RED + "Student not found." + Color.RESET);
            return;
        }

        System.out.print(Color.YELLOW + "Enter your registered mobile number: " + Color.RESET);
        String mobile = scanner.nextLine();

        if (!mobile.equals(student.getMobile())) {
            System.out.println(Color.RED + "Mobile number doesn't match our records." + Color.RESET);
            return;
        }

        int otp = random.nextInt(900000) + 100000;
        System.out.println(Color.CYAN + "OTP sent to your mobile: " + Color.YELLOW_BOLD + otp + Color.RESET);

        for (int otpAttempts = 3; otpAttempts > 0; otpAttempts--) {
            System.out.print(Color.YELLOW + "Enter OTP: " + Color.RESET);
            int enteredOtp = getIntInput(100000, 999999);

            if (enteredOtp == otp) {
                String newPassword = validatePassword("student");
                if (newPassword != null) {
                    student.setPassword(newPassword);
                    System.out.println(Color.GREEN_BOLD + "Password reset successful!" + Color.RESET);
                }
                return;
            }

            System.out.println(
                    Color.RED + "Invalid OTP. You have " + (otpAttempts - 1) + " attempts left." + Color.RESET);
        }

        System.out.println(Color.RED_BOLD + "Too many failed OTP attempts. Returning to main menu." + Color.RESET);
    }

    public static int getIntInput(int min, int max) {
        while (true) {
            try {
                int input = Integer.parseInt(scanner.nextLine());
                if (input >= min && input <= max) {
                    return input;
                }
                System.out
                        .print(Color.RED + "Please enter a number between " + min + " and " + max + ": " + Color.RESET);
            } catch (NumberFormatException e) {
                System.out.print(Color.RED + "Invalid input. Please enter a number: " + Color.RESET);
            }
        }
    }

    public static double getDoubleInput(double min, double max) {
        while (true) {
            try {
                double input = Double.parseDouble(scanner.nextLine());
                if (input >= min && input <= max) {
                    return input;
                }
                System.out
                        .print(Color.RED + "Please enter a number between " + min + " and " + max + ": " + Color.RESET);
            } catch (NumberFormatException e) {
                System.out.print(Color.RED + "Invalid input. Please enter a number: " + Color.RESET);
            }
        }
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = Library.getScanner();
        while (true) {
            System.out.println("\n" + Color.BLUE_BOLD + "Welcome to Library Management System" + Color.RESET);
            System.out.println(Color.YELLOW + "1. Admin Login");
            System.out.println("2. Student Login");
            System.out.println("3. Student Signup");
            System.out.println("4. Forgot Password");
            System.out.println("5. Exit" + Color.RESET);
            System.out.print(Color.WHITE_BOLD + "Please enter an option: " + Color.RESET);

            int choice = Library.getIntInput(1, 5);

            switch (choice) {
                case 1:
                    adminLogin();
                    break;
                case 2:
                    studentLogin();
                    break;
                case 3:
                    Library.studentSignup();
                    break;
                case 4:
                    forgotPassword();
                    break;
                case 5:
                    System.out.println(Color.GREEN_BOLD + "Thank you for using the Library Management System. Goodbye!"
                            + Color.RESET);
                    System.exit(0);
            }
        }
    }

    private static void adminLogin() {
        Scanner scanner = Library.getScanner();
        System.out.println("\n" + Color.BLUE_BOLD + "Admin Login" + Color.RESET);

        for (int attempts = 3; attempts > 0; attempts--) {
            System.out.print(Color.YELLOW + "Enter username: " + Color.RESET);
            String username = scanner.nextLine();
            System.out.print(Color.YELLOW + "Enter password: " + Color.RESET);
            String password = scanner.nextLine();

            if (username.equals("Sathya") && password.equals("Sathay@123")) {
                System.out.println(Color.GREEN_BOLD + "\nLogin successful!" + Color.RESET);
                new Admin(username, password).displayMenu();
                return;
            }

            System.out.println(Color.RED + "Invalid username or password. You have " + (attempts - 1)
                    + " attempts left." + Color.RESET);
        }

        System.out.println(Color.RED_BOLD + "Too many failed attempts. Returning to main menu." + Color.RESET);
    }

    private static void studentLogin() {
        Scanner scanner = Library.getScanner();
        System.out.println("\n" + Color.BLUE_BOLD + "Student Login" + Color.RESET);

        for (int attempts = 3; attempts > 0; attempts--) {
            System.out.print(Color.YELLOW + "Enter username: " + Color.RESET);
            String username = scanner.nextLine();
            System.out.print(Color.YELLOW + "Enter password: " + Color.RESET);
            String password = scanner.nextLine();

            for (int i = 0; i < Library.getStudentCount(); i++) {
                Student student = Library.getStudent(i);
                if (student != null && student.getUsername().equals(username)
                        && student.getPassword().equals(password)) {
                    System.out.println(Color.GREEN_BOLD + "\nLogin successful!" + Color.RESET);
                    student.displayMenu();
                    return;
                }
            }

            System.out.println(Color.RED + "Invalid username or password. You have " + (attempts - 1)
                    + " attempts left." + Color.RESET);
        }

        System.out.println(Color.RED_BOLD + "Too many failed attempts. Returning to main menu." + Color.RESET);
    }

    private static void forgotPassword() {
        System.out.println("\n" + Color.BLUE_BOLD + "Forgot Password" + Color.RESET);
        Library.studentPasswordReset();
    }
}