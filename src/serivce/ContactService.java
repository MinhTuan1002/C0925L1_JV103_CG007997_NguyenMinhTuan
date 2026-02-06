package serivce;
import model.Contact;
import until.FileUtils;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class ContactService {
    private List<Contact> list = new ArrayList<>();
    private final String FILE = "contacts.csv";

    private boolean validPhone(String phone) {
        return phone.matches("0\\d{9}");
    }

    private boolean validEmail(String email) {
        return Pattern.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", email);
    }

    private Contact findByPhone(String phone) {
        for (Contact c : list) {
            if (c.getPhone().equals(phone)) {
                return c;
            }
        }
        return null;
    }



    public void display() {
        if (list.isEmpty()) {
            System.out.println("Danh sách trống!");
            return;
        }

        Scanner sc = new Scanner(System.in);
        int count = 0;

        for (Contact c : list) {
            System.out.println(c);
            count++;

            if (count % 5 == 0) {
                System.out.print("Nhấn Enter để xem tiếp...");
                sc.nextLine();
            }
        }
    }


    public ContactService() {
        list = FileUtils.read();

        if (list.isEmpty()) {
            list.add(new Contact(
                    "0971234567",
                    "CodeGym",
                    "Nguyễn Văn A",
                    "Nam",
                    "Mỹ Đình - Hà Nội",
                    "1989-01-01",
                    "vana@codegym.vn"
            ));

            list.add(new Contact(
                    "0971234568",
                    "Bạn bè",
                    "Nguyễn Thị B",
                    "Nữ",
                    "Thanh Xuân - Hà Nội",
                    "1990-01-01",
                    "nguyenthib@codegym.vn"
            ));

            FileUtils.save(list);
        }
    }


    public void add(Scanner sc) {
        System.out.print("Số điện thoại: ");
        String phone = sc.nextLine();
        if (!validPhone(phone)) {
            System.out.println("Số điện thoại không hợp lệ!");
            return;
        }
        if (findByPhone(phone) != null) {
            System.out.println("Số điện thoại đã tồn tại!");
            return;
        }

        System.out.print("Nhóm: ");
        String group = sc.nextLine();

        System.out.print("Họ tên: ");
        String name = sc.nextLine();

        System.out.print("Giới tính: ");
        String gender = sc.nextLine();

        System.out.print("Địa chỉ: ");
        String address = sc.nextLine();

        System.out.print("Ngày sinh: ");
        String dob = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();
        if (!validEmail(email)) {
            System.out.println("Email không hợp lệ!");
            return;
        }

        list.add(new Contact(phone, group, name, gender, address, dob, email));
        System.out.println("Thêm danh bạ thành công!");
    }




    public void update(Scanner sc) {
        System.out.print("Nhập số điện thoại cần sửa (Enter để thoát): ");
        String phone = sc.nextLine();
        if (phone.isEmpty()) return;

        Contact c = findByPhone(phone);
        if (c == null) {
            System.out.println("Không tìm thấy danh bạ!");
            return;
        }

        System.out.print("Nhóm mới: ");
        c.setGroup(sc.nextLine());

        System.out.print("Họ tên mới: ");
        c.setName(sc.nextLine());

        System.out.print("Giới tính mới: ");
        c.setGender(sc.nextLine());

        System.out.print("Địa chỉ mới: ");
        c.setAddress(sc.nextLine());

        System.out.print("Ngày sinh mới: ");
        c.setBirthday(sc.nextLine());

        System.out.print("Email mới: ");
        String email = sc.nextLine();
        if (!validEmail(email)) {
            System.out.println("Email không hợp lệ!");
            return;
        }
        c.setEmail(email);

        System.out.println("Cập nhật thành công!");
    }


    public void delete(Scanner sc) {
        System.out.print("Nhập số điện thoại cần xóa: ");
        String phone = sc.nextLine();

        System.out.print("Xác nhận xóa (Y/N): ");
        String confirm = sc.nextLine();
        if (confirm.equalsIgnoreCase("Y")) {
            list.remove(sc);
            System.out.println("Đã xóa!");
        }
    }


    public void search(Scanner sc) {
        System.out.print("Nhập từ khóa: ");
        String key = sc.nextLine().toLowerCase();

        for (Contact c : list) {
            if (c.getPhone().contains(key) ||
                    c.getName().toLowerCase().contains(key)) {
                System.out.println(c);
            }
        }
    }

    public void readCSV(Scanner sc) {
        System.out.print("Đọc file sẽ xóa toàn bộ danh bạ hiện tại. Y/N? ");
        if (!sc.nextLine().equalsIgnoreCase("Y")) return;

        list = FileUtils.read();
        System.out.println("Đọc file thành công!");
    }


    public void writeCSV() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE))) {
            for (Contact c : list) pw.println(c.toCSV());
            System.out.println("Ghi file thanh cong!");
        } catch (Exception e) {
            System.out.println("Loi ghi file!");
        }
    }


}