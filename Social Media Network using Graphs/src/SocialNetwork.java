import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

class User {
    private String name;
    private String uniqueID;
    private String password;
    private List<User> friends;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.uniqueID = generateUniqueID();
        this.friends = new ArrayList<>();
    }

    public String getUniqueID() {
        return uniqueID;
    }

    public void addFriend(User friend) {
        friends.add(friend);
    }

    public List<User> getFriends() {
        return friends;
    }

    private String generateUniqueID() {
        Random rand = new Random();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder uniqueID = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            uniqueID.append(characters.charAt(rand.nextInt(characters.length())));
        }
        return uniqueID.toString();
    }

    @Override
    public String toString() {
        return "User: " + name + " (ID: " + uniqueID + ")";
    }
}

public class SocialNetwork {
    private Map<String, User> users;

    public SocialNetwork() {
        users = new HashMap<>();
    }

    public void createUser(String name, String password) {
        User newUser = new User(name, password);
        users.put(newUser.getUniqueID(), newUser);
        System.out.println("User created: " + newUser);
    }

    public User findUserByID(String uniqueID) {
        return users.get(uniqueID);
    }

    public void connectFriends(String userID1, String userID2) {
        User user1 = findUserByID(userID1);
        User user2 = findUserByID(userID2);

        if (user1 != null && user2 != null) {
            user1.addFriend(user2);
            user2.addFriend(user1);
            System.out.println(user1 + " and " + user2 + " are now friends.");
        } else {
            System.out.println("User not found. Make sure the IDs are correct.");
        }
    }

    public void showFriendsList(String userID) {
        User user = findUserByID(userID);
        if (user != null) {
            List<User> friends = user.getFriends();
            System.out.println(user + "'s Friends List:");
            for (User friend : friends) {
                System.out.println(friend);
            }
        } else {
            System.out.println("User not found. Make sure the ID is correct.");
        }
    }

    public static void main(String[] args) {
        SocialNetwork socialNetwork = new SocialNetwork();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nSocial Network Menu:");
            System.out.println("1. Create Account");
            System.out.println("2. Connect Friends");
            System.out.println("3. Show Friends List");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter your name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter your password: ");
                    String password = scanner.nextLine();
                    socialNetwork.createUser(name, password);
                    break;
                case 2:
                    System.out.print("Enter your unique ID: ");
                    String userID1 = scanner.nextLine();
                    System.out.print("Enter your friend's unique ID: ");
                    String userID2 = scanner.nextLine();
                    socialNetwork.connectFriends(userID1, userID2);
                    break;
                case 3:
                    System.out.print("Enter your unique ID: ");
                    String userID = scanner.nextLine();
                    socialNetwork.showFriendsList(userID);
                    break;
                case 4:
                    System.out.println("Exiting the program.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
