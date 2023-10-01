package vn.funix.fx20894.java.asm04.models.models_asm2;

import vn.funix.fx20894.java.asm04.models.CustomerIdValidator;

import java.io.Serializable;

public abstract class User implements Serializable {
    private String name;
    private String customerID;
    private static final long serialVersionUID = 0L;

    public User(String name, String customerID) {
        this.name = name;
        this.customerID = customerID;
    }

    public String getName() {
        return name;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCustomerID(String customerID) {
        if (CustomerIdValidator.validateCustomerID(customerID)) {
            this.customerID = customerID;
        } else {
            System.out.println("Mã khách hàng không hợp lệ.");
        }
    }
}
