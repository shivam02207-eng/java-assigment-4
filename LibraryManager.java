import java.io.*;
import java.util.*;

public class LibraryManager {
    private Map<Integer, Book> books = new HashMap<>();
    private Map<Integer, Member> members = new HashMap<>();

    public void loadFromFile() {
        try {
            File f1 = new File("books.txt");
            f1.createNewFile();
            BufferedReader br = new BufferedReader(new FileReader(f1));
            String line;
            while ((line = br.readLine()) != null) {
                Book b = Book.fromString(line);
                books.put(b.getBookId(), b);
            }
            br.close();

            File f2 = new File("members.txt");
            f2.createNewFile();
            BufferedReader br2 = new BufferedReader(new FileReader(f2));
            while ((line = br2.readLine()) != null) {
                Member m = Member.fromString(line);
                members.put(m.getMemberId(), m);
            }
            br2.close();
        } catch (Exception e) {}
    }

    public void saveToFile() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("books.txt"));
            for (Book b : books.values()) {
                bw.write(b.toString());
                bw.newLine();
            }
            bw.close();

            BufferedWriter bw2 = new BufferedWriter(new FileWriter("members.txt"));
            for (Member m : members.values()) {
                bw2.write(m.toString());
                bw2.newLine();
            }
            bw2.close();
        } catch (Exception e) {}
    }

    public void addBook(Scanner sc) {
        System.out.print("Enter ID: ");
        int id = sc.nextInt(); sc.nextLine();
        System.out.print("Enter Title: ");
        String t = sc.nextLine();
        System.out.print("Enter Author: ");
        String a = sc.nextLine();
        System.out.print("Enter Category: ");
        String c = sc.nextLine();
        books.put(id, new Book(id, t, a, c, false));
        saveToFile();
    }

    public void addMember(Scanner sc) {
        System.out.print("Enter Member ID: ");
        int id = sc.nextInt(); sc.nextLine();
        System.out.print("Enter Name: ");
        String n = sc.nextLine();
        System.out.print("Enter Email: ");
        String e = sc.nextLine();
        members.put(id, new Member(id, n, e, new ArrayList<>()));
        saveToFile();
    }

    public void issueBook(Scanner sc) {
        System.out.print("Enter Book ID: ");
        int bid = sc.nextInt();
        System.out.print("Enter Member ID: ");
        int mid = sc.nextInt();

        if (!books.containsKey(bid) || !members.containsKey(mid)) return;
        Book b = books.get(bid);
        if (b.getIsIssued()) return;

        b.markAsIssued();
        members.get(mid).addIssuedBook(bid);
        saveToFile();
    }

    public void returnBook(Scanner sc) {
        System.out.print("Enter Book ID: ");
        int bid = sc.nextInt();
        System.out.print("Enter Member ID: ");
        int mid = sc.nextInt();

        if (!books.containsKey(bid) || !members.containsKey(mid)) return;
        Book b = books.get(bid);
        b.markAsReturned();
        members.get(mid).returnIssuedBook(bid);
        saveToFile();
    }

    public void searchBooks(Scanner sc) {
        System.out.print("Enter keyword: ");
        sc.nextLine();
        String k = sc.nextLine().toLowerCase();

        for (Book b : books.values()) {
            if (b.getTitle().toLowerCase().contains(k) ||
                b.getAuthor().toLowerCase().contains(k) ||
                b.getCategory().toLowerCase().contains(k)) {
                b.displayBookDetails();
            }
        }
    }

    public void sortBooks(Scanner sc) {
        System.out.println("1. Sort by Title");
        System.out.println("2. Sort by Author");
        int ch = sc.nextInt();

        List<Book> list = new ArrayList<>(books.values());
        if (ch == 1) Collections.sort(list);
        else list.sort(Comparator.comparing(Book::getAuthor));

        for (Book b : list) b.displayBookDetails();
    }
}
