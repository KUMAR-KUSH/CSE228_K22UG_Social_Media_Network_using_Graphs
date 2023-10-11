import java.util.*;

class Person {
    private String name;
    private String uniqueId;
    private String password;

    public Person(String name, String uniqueId, String password) {
        this.name = name;
        this.uniqueId = uniqueId;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public String getPassword() {
        return password;
    }
}

public class SocialNetwork {
    private Map<String, Person> users; // Store users by their unique ID
    private Map<String, List<String>> relationships; // Store relationships between users

    public SocialNetwork() {
        users = new HashMap<>();
        relationships = new HashMap<>();
    }

    public void createAccount(String name, String password) {
        String uniqueId = generateUniqueId();
        Person person = new Person(name, uniqueId, password);
        users.put(uniqueId, person);
        relationships.put(uniqueId, new LinkedList<>()); // Initialize empty friends list
        System.out.println("Account created successfully! Your unique ID is: " + uniqueId);
    }

    public Person findUserById(String uniqueId) {
        return users.get(uniqueId);
    }

    public void connectWithFriends(String userId, String friendId) {
        List<String> friends = relationships.get(userId);
        friends.add(friendId);
        relationships.put(userId, friends);
        System.out.println("Connected successfully!");
    }

    public void showFriendsList(String userId) {
        List<String> friends = relationships.get(userId);
        System.out.println("Friends list:");
        for (String friendId : friends) {
            Person friend = users.get(friendId);
            System.out.println(friend.getName() + " - " + friend.getUniqueId());
        }
    }

    private String generateUniqueId() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            builder.append(characters.charAt(random.nextInt(characters.length())));
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SocialNetwork socialNetwork = new SocialNetwork();

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Create account");
            System.out.println("2. Find friends and connect");
            System.out.println("3. Show friends list");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    scanner.nextLine(); // Consume newline character
                    System.out.print("Enter your name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter your password: ");
                    String password = scanner.nextLine();
                    socialNetwork.createAccount(name, password);
                    break;

                case 2:
                    scanner.nextLine(); // Consume newline character
                    System.out.print("Enter your unique ID: ");
                    String userId = scanner.nextLine();
                    System.out.print("Enter your friend's unique ID: ");
                    String friendId = scanner.nextLine();
                    socialNetwork.connectWithFriends(userId, friendId);
                    break;

                case 3:
                    scanner.nextLine(); // Consume newline character
                    System.out.print("Enter your unique ID: ");
                    String id = scanner.nextLine();
                    socialNetwork.showFriendsList(id);
                    break;

                case 4:
                    System.exit(0);

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}