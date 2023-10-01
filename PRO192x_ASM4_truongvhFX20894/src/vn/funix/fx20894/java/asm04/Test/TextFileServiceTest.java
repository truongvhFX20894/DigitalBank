package vn.funix.fx20894.java.asm04.Test;

import org.junit.Test;
import vn.funix.fx20894.java.asm04.services.BinaryFileService;

import static org.junit.Assert.*;

public class TextFileServiceTest {

    @Test
    public void readFile() {
        BinaryFileService.readFile("store\\customers.txt");
        BinaryFileService.readFile("storecustomers.txt");
    }
}