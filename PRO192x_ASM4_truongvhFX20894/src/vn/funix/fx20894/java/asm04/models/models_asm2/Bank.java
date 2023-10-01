package vn.funix.fx20894.java.asm04.models.models_asm2;

import vn.funix.fx20894.java.asm04.DAO.CustomerDao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Bank implements Serializable {
    private final String id;
    private final String name = "Digital Bank";
    private static final long serialVersionUID = 0L;

    public Bank() {
        this.id=String.valueOf(UUID.randomUUID());
    }

    public String getId() {
        return id;
    }
}
