package vn.funix.fx20894.java.asm04.Test;

import org.junit.Before;
import org.junit.Test;
import vn.funix.fx20894.java.asm04.models.models_asm2.Customer;
import vn.funix.fx20894.java.asm04.services.BinaryFileService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BinaryFileServiceTest {
    private List<Customer> customerList = new ArrayList<>();
    @Before
    public void setup() {
        Customer customer1 = new Customer("Truong", "024098000125");
        Customer customer2 = new Customer("Vi", "024198000126");
        customerList.add(customer1);
        customerList.add(customer2);
    }

    @Test
    public void readFile() {
        BinaryFileService.readFile("store\\customers.txt");
        BinaryFileService.readFile("store\\customers.dat");
        BinaryFileService.readFile("storecustomers.dat");
    }

    @Test
    public void writeFile() {
        BinaryFileService.writeFile("store\\customers.txt", customerList);
        BinaryFileService.writeFile("storecustomers.txt", customerList);
    }
}