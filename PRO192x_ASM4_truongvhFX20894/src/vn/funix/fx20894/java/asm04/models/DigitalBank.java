package vn.funix.fx20894.java.asm04.models;


import vn.funix.fx20894.java.asm04.DAO.CustomerDao;
import vn.funix.fx20894.java.asm04.Utils;
import vn.funix.fx20894.java.asm04.models.models_asm2.Account;
import vn.funix.fx20894.java.asm04.models.models_asm2.Bank;
import vn.funix.fx20894.java.asm04.models.models_asm2.Customer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Serializable;
import java.util.*;

import static vn.funix.fx20894.java.asm04.models.CustomerIdValidator.isNumberic;


public class DigitalBank extends Bank {
    private List<Customer> customers = CustomerDao.list();
    public DigitalBank() {
        super();
    }

    public Customer getCustomerByID(String customerID, List<Customer> customerList) {
        for (Customer customer : customerList){
            if(customer.getCustomerID().equals(customerID)){
                return customer;
            }
        }
        return null;
    }

    public void showCustomer() {
        customers = CustomerDao.list();
        if (!customers.isEmpty()) {
            for (Customer customer: customers) {
                customer.displayInformation();
            }
        } else {
            System.out.println("Không có khách hàng nào trong danh sách.");
        }
    }

    public void addCustomer(String fileName) {
        List<Customer> customerList = new ArrayList<>();
        Scanner scanner = null;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(fileName)));
            scanner.useDelimiter(",");
            while(scanner.hasNextLine()) {
                String name = scanner.next();
                scanner.skip(scanner.delimiter());
                String cccd = scanner.nextLine();
                if (getCustomerByID(cccd, customerList)==null && CustomerIdValidator.validateCustomerID(cccd)) {
                    customerList.add(new Customer(name, cccd));
                    System.out.println("Thêm khách hàng "+cccd+" thành công.");
                } else if (getCustomerByID(cccd, customerList)!=null) {
                    System.out.println("Khách hàng "+cccd+" đã tồn tại, thêm khách hàng không thành công");
                } else if (!CustomerIdValidator.validateCustomerID(cccd)) {
                    System.out.println("Mã khách hàng "+cccd+" không hợp lệ, thêm khách hàng không thành công.");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Tệp không tồn tại.");
        } catch (NullPointerException | NoSuchElementException e) {
            System.out.println("Tệp không có nội dung hoặc sai định dạng.");;
        } finally {
            if (scanner!=null) {
                scanner.close();
            }
        }
        CustomerDao.save(customerList);
    }

    public void addSavingsAccount(Scanner sc, String customerID) {
        //Kiểm tra id nhập vào
        while (true) {
            if (!customerID.equals("0")) {
                if (getCustomerByID(customerID, customers) != null) {
                    break;
                } else {
                    try {
                        throw new CustomerIdNotValidException("Mã số khách hàng không đúng hoặc chưa tồn tại. Vui lòng nhập lại.");
                    } catch (CustomerIdNotValidException e) {
                        System.out.println(e.getMessage());
                        customerID = sc.nextLine();
                    }
                }
            } else {
                System.exit(0);
            }
        }
        //Nhập stk
        System.out.print("Nhập mã số tài khoản gồm 6 chữ số: ");
        String accountNumber = checkSTK(sc.nextLine(), customerID, sc);

        //Nhập số dư
        System.out.print("Nhập số dư: ");
        String balanceString = sc.nextLine();
        double balance;
        while (!isNumberic(balanceString)) {
            System.out.print("Số dư phải là một số lớn hơn 0. Vui lòng nhập lại: ");
            balanceString = sc.nextLine();
        }
        balance = Double.parseDouble(balanceString);

        //Tạo account từ những thông tin được nhập và gọi hàm thêm tài khoản và thêm lịch sử giao dịch
        SavingsAccount account = new SavingsAccount(accountNumber, balance, customerID);
        getCustomerByID(customerID, customers).addAccount(account);
        getCustomerByID(customerID, customers).getAccountByAccountNumber(getCustomerByID(customerID, customers)
                .getAccounts(), accountNumber)
                .createTransaction(balance, Utils.getDateTime(), true, TransactionType.DEPOSIT);
        CustomerDao.save(customers);

    }

    public String checkSTK(String accountNumber, String customerID, Scanner sc) {
        Customer customer = getCustomerByID(customerID, CustomerDao.list());
        //Kiểm tra tính hợp lệ của stk
        while (accountNumber.length() != 6 || customer.checkAccountExisted(accountNumber) || !isNumberic(accountNumber)) {
            if (accountNumber.length() != 6 || !isNumberic(accountNumber)) {
                System.out.print("STK không hợp lệ. Vui lòng nhập lại hoặc nhấn phím 0 để thoát: ");
            }
            else if (customer.checkAccountExisted(accountNumber)) {
                System.out.print("STK đã tồn tại. Vui lòng nhập lại hoặc nhấn phím 0 để thoát: ");
            }
            accountNumber = sc.nextLine();
            if (accountNumber.equals(String.valueOf(0))) {
                System.exit(0);
            }
        }
        return accountNumber;
    }

    public void transfer(Scanner sc, String customerID) {
        //Kiểm tra id nhập vào
        while (true) {
            if (!customerID.equals("0")) {
                if (getCustomerByID(customerID, customers)!=null) {
                    break;
                } else {
                    System.out.println("Mã số khách hàng không đúng hoặc chưa tồn tại. Vui lòng nhập lại:");
                    customerID = sc.nextLine();
                }
            } else {
                System.exit(0);
            }
        }

        //Gọi hàm transfer của customer
        for (Customer customer: customers) {
            if (customer.getCustomerID().equals(customerID)) {
                customer.transfer(sc);
            }
        }
    }

    public void withdraw(Scanner sc, String customerID) {
        //Kiểm tra id nhập vào
        while (true) {
            if (!customerID.equals("0")) {
                if (getCustomerByID(customerID, customers)!=null) {
                    break;
                } else {
                    System.out.println("Mã số khách hàng không đúng hoặc chưa tồn tại. Vui lòng nhập lại:");
                    customerID = sc.nextLine();
                }
            } else {
                System.exit(0);
            }
        }

        //Gọi hàm withraw của customer
        for (Customer customer: customers) {
            if (customer.getCustomerID().equals(customerID)) {
                 customer.withdraw(sc);
            }
        }
    }

    public void showTransactions(Scanner sc) {
        //Kiểm tra id nhập vào
        System.out.print("Nhập mã số khách hàng: ");
        String customerID;
        while (true) {
            customerID = sc.nextLine();
            if (!customerID.equals("0")) {
                if (getCustomerByID(customerID, customers)!=null) {
                    break;
                } else {
                    System.out.println("Mã số khách hàng không đúng hoặc chưa tồn tại. Vui lòng nhập lại:");
                }
            } else {
                System.exit(0);
            }
        }

        //Hiển thị thông tin khách hàng
        Customer customer = getCustomerByID(customerID, customers);
        if (customer != null) {
            customer.displayInformation();
        }
        //Gọi hàm hiển thị lịch sử giao dịch của các account
        assert customer != null;
        for (Account account: customer.getAccounts()) {
            account.displayTransactionsList();
        }
    }

    public boolean isAccountExisted(List<Account> accounts, Account newAccount) {
        return accounts.contains(newAccount);
    }

    public boolean isCustomerExisted(List<Customer> customers, Customer newCustomer) {
        return customers.contains(newCustomer);
    }
}
