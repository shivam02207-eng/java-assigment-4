import java.util.*;

public class Member {
    private int memberId;
    private String name;
    private String email;
    private List<Integer> issuedBooks;

    public Member(int memberId, String name, String email, List<Integer> issuedBooks) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.issuedBooks = issuedBooks;
    }

    public int getMemberId() { return memberId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public List<Integer> getIssuedBooks() { return issuedBooks; }

    public void addIssuedBook(int id) { issuedBooks.add(id); }
    public void returnIssuedBook(int id) { issuedBooks.remove(Integer.valueOf(id)); }

    public String toString() {
        return memberId + "," + name + "," + email + "," + issuedBooks.toString();
    }

    public static Member fromString(String s) {
        String[] p = s.split(",");
        int id = Integer.parseInt(p[0]);
        String n = p[1];
        String e = p[2];
        String list = s.substring(s.indexOf("[") + 1, s.indexOf("]"));
        List<Integer> issued = new ArrayList<>();
        if (!list.isEmpty()) {
            for (String x : list.split(" ")) issued.add(Integer.parseInt(x.replace(",", "")));
        }
        return new Member(id, n, e, issued);
    }

    public void displayMemberDetails() {
        System.out.println("ID: " + memberId + " | " + name + " | " + email + " | Books: " + issuedBooks);
    }
}
