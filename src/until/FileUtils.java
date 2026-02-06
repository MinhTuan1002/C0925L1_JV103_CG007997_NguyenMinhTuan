package until;

import model.Contact;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    private static final String FILE = "contacts.csv";

    // Ghi danh sách contact ra file CSV
    public static void save(List<Contact> list) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE))) {
            for (Contact c : list) {
                pw.println(c.toCSV());
            }
        } catch (Exception e) {
            System.out.println("Lỗi ghi file");
        }
    }

    // Đọc danh sách contact từ file CSV
    public static List<Contact> read() {
        List<Contact> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                list.add(Contact.fromFile(line));
            }
        } catch (Exception e) {
            // File chưa tồn tại thì bỏ qua
        }

        return list;
    }
}

