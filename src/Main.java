import serivce.ContactService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ContactService service = new ContactService();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- CHUONG TRINH QUAN LY DANH BA ---");
            System.out.println("1. Xem danh sach");
            System.out.println("2. Them moi");
            System.out.println("3. Cap nhat");
            System.out.println("4. Xoa");
            System.out.println("5. Tim kiem");
            System.out.println("6. Doc tu file CSV");
            System.out.println("7. Ghi vao file CSV");
            System.out.println("8. Thoat");
            System.out.print("Chon chuc nang: ");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> service.display();
                case 2 -> service.add(sc);
                case 3 -> service.update(sc);
                case 4 -> service.delete(sc);
                case 5 -> service.search(sc);
                case 6 -> service.readCSV(sc);
                case 7 -> service.writeCSV();
                case 8 -> { return; }
                default -> System.out.println("Lua chon khong hop le!");
            }
        }
    }
}
