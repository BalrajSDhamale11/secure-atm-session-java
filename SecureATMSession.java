import java.util.Scanner;
import java.util.Random;

public class SecureAtmSession {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // ---------------- PART 1: LOGIN SYSTEM ----------------
        int attempts = 3;
        boolean loggedIn = false;

        while (attempts > 0) {

            System.out.print("Enter email: ");
            String email = scanner.nextLine();

            System.out.print("Enter 4-digit PIN: ");
            String pin = scanner.nextLine();

            boolean validLogin = true;

            int atIndex = email.indexOf("@");

            if (atIndex == -1) {
                System.out.println("Invalid email format.");
                validLogin = false;
            } else {
                String domain = email.substring(atIndex + 1);
                if (!domain.equals("bank.com")) {
                    System.out.println("Invalid email domain.");
                    validLogin = false;
                }
            }

            if (pin.length() != 4) {
                System.out.println("PIN must be exactly 4 digits.");
                validLogin = false;
            }

            if (validLogin) {
                System.out.println("Login successful!");
                loggedIn = true;
                break;
            } else {
                attempts--;
                System.out.println("Login failed. Attempts left: " + attempts);
            }
        }

        if (!loggedIn) {
            System.out.println("Account locked.");
            scanner.close();
            return;
        }

        // ---------------- PART 2: ATM MENU ----------------
        double balance = random.nextInt(1000,5001);
        boolean running = true;

        while (running) {

            System.out.println("\n1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();

            switch (choice) {

                case 1 -> {
                    System.out.println("Current Balance: " + balance);
                }

                case 2 -> {
                    System.out.print("Enter deposit amount: ");
                    double amount = scanner.nextDouble();
                    balance += amount;
                    balance = Math.min(balance, 10000);
                    System.out.println("Updated Balance: " + balance);
                }

                case 3 -> {
                    System.out.print("Enter withdrawal amount: ");
                    double amount = scanner.nextDouble();

                    if (amount <= balance) {
                        balance -= amount;
                        balance = Math.max(balance, 500);
                        System.out.println("Updated Balance: " + balance);
                    } else {
                        System.out.println("Insufficient funds.");
                    }
                }

                case 4 -> {
                    running = false;
                }

                default -> {
                    System.out.println("Invalid choice.");
                }
            }
        }

        // ---------------- PART 3: EXIT ----------------
        System.out.println("Session ended.");
        System.out.println("Final Balance: " + balance);
        scanner.close();
    }
}
