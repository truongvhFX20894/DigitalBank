package vn.funix.fx20894.java.asm04.Test;

import org.junit.Before;
import org.junit.Test;
import vn.funix.fx20894.java.asm04.DAO.CustomerDao;
import vn.funix.fx20894.java.asm04.models.DigitalBank;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class DigitalBankTest {
    private DigitalBank activeBank;
    @Before
    public void setup() {
        activeBank = new DigitalBank();
        activeBank.addCustomer("store\\customers.txt");
    }

    @Test
    public void getCustomerByID() {
        assertNotNull(activeBank.getCustomerByID("024098000125", CustomerDao.list()));
        assertNotNull(activeBank.getCustomerByID("024098000126", CustomerDao.list()));
    }
}