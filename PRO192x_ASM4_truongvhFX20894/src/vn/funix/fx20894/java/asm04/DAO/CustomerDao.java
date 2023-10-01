package vn.funix.fx20894.java.asm04.DAO;


import vn.funix.fx20894.java.asm04.models.models_asm2.Customer;
import vn.funix.fx20894.java.asm04.services.BinaryFileService;

import java.util.List;

public class CustomerDao {
    private final static String FILE_PATH = "store\\customers.dat";
    public static void save(List<Customer> customerList) {
        BinaryFileService.writeFile(FILE_PATH, customerList);
    }
    public static List<Customer> list() {
        return BinaryFileService.readFile(FILE_PATH);
    }
}
