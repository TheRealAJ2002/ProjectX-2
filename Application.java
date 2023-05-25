import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {
    private boolean loggedIn;
    private boolean darkModeEnabled;
    private boolean incognitoModeEnabled; // New variable for incognito mode
    private List<SearchQuery> searchHistory;
    private Language language;
    private AccountManager accountManager;
    private AIInterface aiInterface;

    public Application() {
        loggedIn = false;
        darkModeEnabled = false;
        incognitoModeEnabled = false;
        searchHistory = new ArrayList<>();
        language = Language.ENGLISH;
        accountManager = new AccountManager();
        aiInterface = new AIInterface();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Login");
            System.out.println("2. Create Account");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    login(scanner);
                    break;
                case 2:
                    createAccount(scanner);
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void login(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();

        if (accountManager.login(username, password)) {
            loggedIn = true;
            System.out.println("Login successful!");

            while (loggedIn) {
                displayMenu();
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        askQuestion(scanner);
                        break;
                    case 2:
                        toggleDarkLightMode();
                        break;
                    case 3:
                        showSearchHistory();
                        break;
                    case 4:
                        resumeSearch(scanner);
                        break;
                    case 5:
                        setLanguage(scanner);
                        break;
                    case 6:
                        toggleIncognitoMode();
                        break;
                    case 7:
                        logout();
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private void createAccount(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();

    if (password.length() >= 12){
        if (accountManager.createAccount(username, password)) {
            System.out.println("Account created successfully!");
        } else {
            System.out.println("Username already exists.");
        }
    }
    else {
        System.out.println("Password requires atleast 12 charachters.");
    }
}

    private void displayMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Ask a question");
        System.out.println("2. Toggle dark/light mode");
        System.out.println("3. Show search history");
        System.out.println("4. Resume search");
        System.out.println("5. Change language");
        System.out.println("6. Toggle incognito mode");
        System.out.println("7. Logout");
        System.out.print("Enter your choice: ");
    }

    private void askQuestion(Scanner scanner) {
        if (!incognitoModeEnabled) {
            System.out.print("Enter your question: ");
            String question = scanner.nextLine();

            Response response = aiInterface.sendQuestion(question);
            if (!incognitoModeEnabled) {
                searchHistory.add(new SearchQuery(question, response));
            }
            System.out.println("AI Response: " + response);
        } else {
            System.out.println("Incognito mode is enabled. Unable to ask a question.");
        }
    }

    private void toggleDarkLightMode() {
        if (darkModeEnabled) {
            darkModeEnabled = false;
            System.out.println("Dark mode disabled.");
        } else {
            darkModeEnabled = true;
            System.out.println("Dark mode enabled.");
        }
    }



    private void showSearchHistory() {
        if (!incognitoModeEnabled) {
            if (searchHistory.isEmpty()) {
                System.out.println("Search history is empty.");
            } else {
                System.out.println("Search History:");
                for (SearchQuery query : searchHistory) {
                    System.out.println("- Question: " + query.getQuestion());
                    System.out.println("  Response: " + query.getResponse());
                }
            }
        } else {
            System.out.println("Incognito mode is enabled. Search history is not available.");
        }
    }

    private void resumeSearch(Scanner scanner) {
        if (searchHistory.isEmpty()) {
            System.out.println("Search history is empty.");
        } else {
            System.out.println("Select a search to resume:");
            for (int i = 0; i < searchHistory.size(); i++) {
                System.out.println((i + 1) + ". " + searchHistory.get(i).getQuestion());
            }

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            if (choice >= 1 && choice <= searchHistory.size()) {
                SearchQuery selectedQuery = searchHistory.get(choice - 1);
                System.out.println("Resuming search:");
                System.out.println("- Question: " + selectedQuery.getQuestion());
                System.out.println("- Response: " + selectedQuery.getResponse());
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }

    private void setLanguage(Scanner scanner) {
        System.out.println("Select language:");
        System.out.println("1. English");
        System.out.println("2. Dutch");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        switch (choice) {
            case 1:
                language = Language.ENGLISH;
                System.out.println("Language set to English.");
                break;
            case 2:
                language = Language.DUTCH;
                System.out.println("Language set to Dutch.");
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private void toggleIncognitoMode() {
        incognitoModeEnabled = !incognitoModeEnabled;
        System.out.println("Incognito mode " + (incognitoModeEnabled ? "enabled." : "disabled."));
    }

    private void logout() {
        loggedIn = false;
        System.out.println("Logged out successfully.");
    }

    public static void main(String[] args) {
        Application application = new Application();
        application.run();
    }
}

class Response {
    private String text;

    public Response(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return text;
    }
}

enum Language {
    ENGLISH,
    DUTCH
}

class Account {
    private String username;
    private String password;

    public Account
            (String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}



