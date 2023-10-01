package vn.funix.fx20894.java.asm04;

import vn.funix.fx20894.java.asm04.models.CustomerIdNotValidException;
import vn.funix.fx20894.java.asm04.models.DigitalBank;
import vn.funix.fx20894.java.asm04.models.models_asm2.Account;
import vn.funix.fx20894.java.asm04.models.models_asm2.Customer;

import java.util.Scanner;

public class Asm04 {
    private static final int EXIT_COMMAND_CODE = 0;
    private static final int EXIT_ERROR_CODE = -1;
    private static final Scanner sc = new Scanner(System.in);
    private static final DigitalBank activeBank = new DigitalBank();
    public static void Menu() {
        System.out.println("+----------+-------------------------+----------+" +
                "\n| NGÂN HÀNG SỐ | FX20894@v4.0.0                 |" +
                "\n+----------+-------------------------+----------+" +
                "\n| 1. Xem danh sách khách hàng                   |" +
                "\n| 2. Nhập danh sách khách hàng                  |" +
                "\n| 3. Thêm tài khoản ATM                         |" +
                "\n| 4. Chuyển tiền                                |" +
                "\n| 5. Rút tiền                                   |" +
                "\n| 6. Tra cứu lịch sử giao dịch                  |" +
                "\n| 0. Thoát                                      |" +
                "\n+----------+-------------------------+----------+");
    }
    public static void main(String[] args) {
        //          Chạy menu chính
        while (true) {
            Menu();
            System.out.print("Chức năng: ");
            int numFunction;
            while (true) {
                try {
                    numFunction = Integer.parseInt(sc.nextLine());
                    break;
                }catch (NumberFormatException e) {
                    System.out.print("Vui lòng nhập lại một số để chọn chức năng: ");
                }
            }
            checkMenu(numFunction);
        }
    }
    public static void checkMenu(int numFunction) {
        switch (numFunction) {
            case EXIT_COMMAND_CODE :
                System.exit(0);
            case 1:
                activeBank.showCustomer();
                break;
            case 2:
                addCustomer();
                break;
            case 3:
                addSavingsAccount();
                break;
            case 4:
                transfer();
                break;
            case 5:
                withdraw();
                break;
            case 6:
                activeBank.showTransactions(sc);
                break;
            default:
                System.out.println("Không có chức năng phù hợp. Vui lòng chọn lại.");
        }
    }

    public static void addCustomer() {
        System.out.println("Nhập đường dẫn đến tệp:");
        String fileName = sc.nextLine();
        activeBank.addCustomer(fileName);
    }

    public static void addSavingsAccount() {
        System.out.print("Nhập mã số khách hàng: ");
        String customerID = sc.nextLine();
        activeBank.addSavingsAccount(sc, customerID);
    }

    public static void transfer() {
        System.out.print("Nhập mã số khách hàng: ");
        String customerID = sc.nextLine();
        activeBank.transfer(sc, customerID);
    }

    public static void withdraw() {
        System.out.print("Nhập mã số khách hàng: ");
        String customerID = sc.nextLine();
        activeBank.withdraw(sc, customerID);
    }

}
