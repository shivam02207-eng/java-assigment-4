import java.util.*;

public class Main {
    public static void main(String[] args) {
        LibraryManager lm = new LibraryManager();
        lm.loadFromFile();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Book");
            System.out.println("2. Add Member");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Search Books");
            System.out.println("6. Sort Books");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");

            int ch = sc.nextInt();
            switch (ch) {
                case 1: lm.addBook(sc); break;
                case 2: lm.addMember(sc); break;
                case 3: lm.issueBook(sc); break;
                case 4: lm.returnBook(sc); break;
                case 5: lm.searchBooks(sc); break;
                case 6: lm.sortBooks(sc); break;
                case 7: lm.saveToFile(); return;
            }
        }
    }
}
