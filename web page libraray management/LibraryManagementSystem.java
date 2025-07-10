import java.util.*;

public class LibraryManagementSystem {
    private static List<String> bookTitles = new ArrayList<>();
    private static List<String> authors = new ArrayList<>();
    private static Set<String> issuedBooks = new HashSet<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            printMenu();
            choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    searchBook();
                    break;
                case 3:
                    displayAllBooks();
                    break;
                case 4:
                    generateReport();
                    break;
                case 5:
                    removeBook();
                    break;
                case 6:
                    issueBook();
                    break;
                case 7:
                    returnBook();
                    break;
                case 8:
                    displayIssuedBooks();
                    break;
                case 0:
                    System.out.println("👋 Exiting Library Management System...");
                    break;
                default:
                    System.out.println("❌ Invalid choice! Try again.");
            }

            System.out.println(); // line break between operations
        } while (choice != 0);
    }

    private static void printMenu() {
        System.out.println("======= 📚 Library Menu =======");
        System.out.println("1. Add Book");
        System.out.println("2. Search Book");
        System.out.println("3. Display All Books");
        System.out.println("4. Generate Report");
        System.out.println("5. Remove Book");
        System.out.println("6. Issue Book");
        System.out.println("7. Return Book");
        System.out.println("8. Display Issued Books");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addBook() {
        System.out.print("Enter book title: ");
        String title = sc.nextLine();
        System.out.print("Enter author name: ");
        String author = sc.nextLine();

        bookTitles.add(title);
        authors.add(author);
        System.out.println("✅ Book added successfully.");
    }

    private static void searchBook() {
        System.out.print("Enter keyword to search in titles: ");
        String keyword = sc.nextLine().toLowerCase();
        boolean found = false;

        System.out.println("\n🔍 Search Results:");
        for (int i = 0; i < bookTitles.size(); i++) {
            if (bookTitles.get(i).toLowerCase().contains(keyword)) {
                System.out.printf("%d. \"%s\" by %s\n", i + 1, bookTitles.get(i), authors.get(i));
                found = true;
            }
        }
        if (!found) System.out.println("No matching books found.");
    }

    private static void displayAllBooks() {
        if (bookTitles.isEmpty()) {
            System.out.println("📂 No books in the library.");
            return;
        }
        System.out.println("\n📖 All Books:");
        for (int i = 0; i < bookTitles.size(); i++) {
            System.out.printf("%d. \"%s\" by %s\n", i + 1, bookTitles.get(i), authors.get(i));
        }
    }

    private static void generateReport() {
        System.out.println("\n📝 Library Report:");
        String[] titlesArray = bookTitles.toArray(new String[0]);
        String[] authorsArray = authors.toArray(new String[0]);

        for (int i = 0; i < titlesArray.length; i++) {
            System.out.printf("• \"%s\" by %s\n", titlesArray[i], authorsArray[i]);
        }
    }

    private static void removeBook() {
        System.out.print("Enter book title to remove: ");
        String title = sc.nextLine();
        int index = bookTitles.indexOf(title);

        if (index != -1) {
            bookTitles.remove(index);
            authors.remove(index);
            issuedBooks.remove(title);
            System.out.println("✅ Book removed successfully.");
        } else {
            System.out.println("❌ Book not found.");
        }
    }
    

    private static void issueBook() {
        System.out.print("Enter book title to issue: ");
        String title = sc.nextLine();

        if (bookTitles.contains(title)) {
            if (issuedBooks.contains(title)) {
                System.out.println("⚠ Book already issued.");
            } else {
                issuedBooks.add(title);
                System.out.println("📕 Book issued successfully.");
            }
        } else {
            System.out.println("❌ Book not found.");
        }
    }

    private static void returnBook() {
        System.out.print("Enter book title to return: ");
        String title = sc.nextLine();

        if (issuedBooks.contains(title)) {
            issuedBooks.remove(title);
            System.out.println("📗 Book returned successfully.");
        } else {
            System.out.println("⚠ Book was not issued.");
        }
    }

    private static void displayIssuedBooks() {
        if (issuedBooks.isEmpty()) {
            System.out.println("📂 No books are currently issued.");
        } else {
            System.out.println("\n📂 Issued Books:");
            for (String title : issuedBooks) {
                System.out.println("• " + title);
            }
        }
    }
}