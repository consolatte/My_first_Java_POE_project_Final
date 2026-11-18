import java.util.ArrayList;
import java.util.Random;
public class Message {
public static ArrayList<String> allMessages = new ArrayList<>();
public static ArrayList<String> messageIDs = new ArrayList<>();
public static ArrayList<String> recipients = new ArrayList<>();
public static ArrayList<String> messageHashes = new ArrayList<>();

    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;

    public Message(int messageNumber, String recipient, String messageText) {
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageID = generateMessageID();
        this.messageHash = createMessageHash();
    }

    // Generates a random 10-digit ID
    private String generateMessageID() {
        Random rand = new Random();
        long id = (long) (rand.nextDouble() * 9_000_000_000L) + 1_000_000_000L;
        return String.valueOf(id);
    }

    // Checks message ID is not more than 10 characters
    public boolean checkMessageID() {
        return this.messageID.length() <= 10;
    }

    // Checks recipient cell number (max 10 char)
    public String checkRecipientCell() {
        if (recipient.length() <= 10 && (recipient.startsWith("+") || recipient.startsWith("0"))) {
            return "Cell phone number successfully captured.";
        } else {

            return "Cell phone number is incorrectly formatted or" +
                    "does not contain an international code. " +
                    "Please correct the number and try again.";

        }
    }

    // Creates the Message Hash
    public String createMessageHash() {
        if (messageText == null || messageText.isEmpty()) return "";
        String[] words = messageText.trim().split("\\s+");
        String firstWord = words[0].replaceAll("[^a-zA-Z]", "");
        String lastWord = words[words.length - 1].replaceAll("[^a-zA-Z]", "");

        String firstTwoID = messageID.substring(0, 2);

        return (firstTwoID + ":" + messageNumber + ":" + firstWord + lastWord).toUpperCase();
    }

    // Allows user to choose send, store, or disregard
    public String sentMessage(int choice) {
        switch (choice) {
            case 1:
                return "Message successfully sent.";
            case 2:
                return "Press 0 to delete the message.";
            case 3: {
                Stored.add(messageID, messageHash, recipient, messageText);
                Stored.saveToJSON(messageID, messageHash, recipient, messageText);
                return "Message successfully stored.";
            }
            default:
                return "Invalid option.";
        }
    }

    // Getters
    public String getMessageID() {
        return messageID;
    }

    public String getMessageHash() {
        return messageHash;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessageText() {
        return messageText;
    }

    public int getMessageNumber() {
        return messageNumber;
    }
// ===== Part 3 Methods =====

    public static String getLongestMessage() {
        if (allMessages.isEmpty()) {
            return "No messages sent or stored yet.";
        }
        String longest = "";
        for (String m : allMessages) {
            if (m.length() > longest.length()) {
                longest = m;
            }
        }
        return longest;
    }

    public static String searchByMessageID(String id) {
        for (int i = 0; i < messageIDs.size(); i++) {
            if (messageIDs.get(i).equals(id)) {
                return "Message ID: " + messageIDs.get(i) +
                        "\nRecipient: " + recipients.get(i) +
                        "\nMessage: " + allMessages.get(i);
            }
        }
        return "No message found with ID: " + id;
    }

    public static String searchByRecipient(String rec) {
        String result = "";
        for (int i = 0; i < recipients.size(); i++) {
            if (recipients.get(i).equalsIgnoreCase(rec)) {
                result += "Message: " + allMessages.get(i) + "\n";
            }
        }
        if (result.isEmpty()) {
            return "No messages found for recipient: " + rec;
        }
        return result;
    }

    public static String deleteByHash(String hash) {
        for (int i = 0; i < messageHashes.size(); i++) {
            if (messageHashes.get(i).equals(hash)) {
                String deleted = allMessages.get(i);
                allMessages.remove(i);
                messageHashes.remove(i);
                messageIDs.remove(i);
                recipients.remove(i);
                Stored.storedMessages.remove(deleted);
                return "Message \"" + deleted + "\" successfully deleted.";
            }
        }
        return "No message found with hash: " + hash;
    }

    public static String displayReport() {
        if (allMessages.isEmpty()) {
            return "No messages sent yet.";
        }
        String report = "=== Message Report ===\n";
        for (int i = 0; i < allMessages.size(); i++) {
            report += "Hash: " + messageHashes.get(i) +
                    " | Recipient: " + recipients.get(i) +
                    " | Message: " + allMessages.get(i) + "\n";
        }
        return report;
    }


}




