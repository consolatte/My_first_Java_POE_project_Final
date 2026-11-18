import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Stored {
    public static ArrayList<String> storedMessages = new ArrayList<>();
    public static ArrayList<String> storedRecipients = new ArrayList<>();
    public static ArrayList<String> storedHashes = new ArrayList<>();
    public static ArrayList<String> storedIDs = new ArrayList<>();

    public static void add(String id, String hash, String recipient, String message) {
        storedIDs.add(id);
        storedHashes.add(hash);
        storedRecipients.add(recipient);
        storedMessages.add(message);
    }

    public static String displaySenderAndRecipient() {
        if (storedMessages.isEmpty()) {
            return "No stored messages.";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < storedMessages.size(); i++) {
            sb.append("Recipient: ").append(storedRecipients.get(i))
                    .append(" | Message: ").append(storedMessages.get(i)).append("\n");
        }
        return sb.toString();
    }

    public static void saveToJSON(String id, String hash, String recipient, String message) {
        try {
            FileWriter fw = new FileWriter("storedMessages.json", true);
            fw.write(id + "," + hash + "," + recipient + "," + message + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("Error storing JSON");
        }
    }

    public static void loadFromJSON() {
        try {
            File file = new File("storedMessages.json");
            if (!file.exists()) {
                return;
            }
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains(",")) {
                    String[] parts = line.split(",", 4);
                    if (parts.length == 4) {
                        if (!storedIDs.contains(parts[0])) {
                            storedIDs.add(parts[0]);
                            storedHashes.add(parts[1]);
                            storedRecipients.add(parts[2]);
                            storedMessages.add(parts[3]);
                        }
                    }
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error storing JSON");
        }
    }
}
