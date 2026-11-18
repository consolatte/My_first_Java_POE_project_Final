import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        // Variables from Part 1
        String username;
        String storedUsername;
        String storedPassword;
        String password;
        String cellphone;
        String firstname;
        String lastname;
        Scanner sc = new Scanner(System.in);
        Login userLogin = new Login();

        System.out.println("Enter your first name:");
        firstname = sc.nextLine();
        System.out.println("Enter your last name:");
        lastname = sc.nextLine();
        // Prompting the username
        System.out.println("Enter your User name:");
        username = sc.nextLine();
        // Conditions for the username

        userLogin.checkUserName(username);

        // Prompting the user for the cellphone number
        System.out.println("Please enter cell phone number:");
        cellphone = sc.nextLine();
        // Prompting the user for the password
        System.out.println("Please enter your password:");
        password = sc.nextLine();

        // Checking the conditions for the password
        userLogin.checkPasswordComplexity(password);
        System.out.println("Registration successful");

        // Login part
        System.out.println("Please enter your login details");
        System.out.println("Username:");
        storedUsername = sc.nextLine();
        System.out.println("Password:");
        storedPassword = sc.nextLine();

        // Checking the login condition
        String loginResult = userLogin.loginUser(storedUsername, storedPassword, username, password, firstname, lastname);
        System.out.println(loginResult);

        // PART 2 - QuickChat (Added)
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to QuickChat.");

        // Simplified login for Part 2 demonstration (Assuming registered from Part 1)
        System.out.println("Please log in.");
        System.out.print("Username: ");
        String user = scanner.nextLine();
        System.out.print("Password: ");
        String pass = scanner.nextLine();

        // Dummy validation for Part 2 demonstration
        if (!user.equals("kyl_1") || !pass.equals("Ch&sec@ke99!")) {
            System.out.println("Login failed. Exiting.");
            return;
        }

        System.out.print("How many messages do you wish to send ? ");
        int numMessages = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        int totalSent = 0;

        for (int i = 1; i <= numMessages; i++) {
            System.out.println("\n--- Message " + i + " ---");
            System.out.print("Enter recipient cell number: ");
            String recipient = scanner.nextLine();

            System.out.print("Enter message (max 250 characters): ");
            String text = scanner.nextLine();

            if (text.length() > 250) {
                System.out.println("Please enter a message of less than 250 characters.");
                i--; // Retry this message
                continue;
            }

            Message msg = new Message(i, recipient, text);
            String check = msg.checkRecipientCell();
            System.out.println(check);
            if (check.contains("incorrectly formatted")) {
                i--;
                continue;
            }


            // Display details
            System.out.println("Message ID: " + msg.getMessageID());
            System.out.println("Message Hash: " + msg.getMessageHash());
            System.out.println("Recipient: " + msg.getRecipient());
            System.out.println("Message: " + msg.getMessageText());

            System.out.println("\nChoose an option:");
            System.out.println("1) Send Message");
            System.out.println("2) Disregard Message");
            System.out.println("3) Store Message to send later");
            System.out.print("Choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            String status = msg.sentMessage(choice);
            System.out.println(status);

            if (choice == 1) {
                totalSent++;
            }
        }

        System.out.println("\nTotal messages sent: " + totalSent);

        // Part 3 - Stored Messages Menu
        int storedChoice = 0;
        while (storedChoice != 7) {
            System.out.println("\n=== Part 3 - Stored Messages Menu ===");
            System.out.println("1) Display sender and recipient of all stored messages");
            System.out.println("2) Display the longest stored message");
            System.out.println("3) Search for a message by Message ID");
            System.out.println("4) Search all messages sent or stored for a recipient");
            System.out.println("5) Delete a message using a message hash");
            System.out.println("6) Display Report - all sent messages");
            System.out.println("7) Quit / Exit QuickChat");
            System.out.print("Choice: ");
            storedChoice = sc.nextInt();
            sc.nextLine();

            if (storedChoice == 1) {
                System.out.println(Stored.displaySenderAndRecipient());
            } else if (storedChoice == 2) {
                System.out.println("Longest message: " + Message.getLongestMessage());
            } else if (storedChoice == 3) {
                System.out.print("Enter Message ID: ");
                String id = scanner.nextLine();
                System.out.println(Message.searchByMessageID(id));
            } else if (storedChoice == 4) {
                System.out.print("Enter Recipient: ");
                String rec = scanner.nextLine();
                System.out.println(Message.searchByRecipient(rec));
            } else if (storedChoice == 5) {
                System.out.print("Enter Message Hash: ");
                String hash = scanner.nextLine();
                System.out.println(Message.deleteByHash(hash));
            } else if (storedChoice == 6) {
                System.out.println(Message.displayReport());
            } else if (storedChoice == 7) {
                System.out.println("Exiting QuickChat.");
            }
        }

        System.out.println("Exiting QuickChat.");
        scanner.close();


    }
}


