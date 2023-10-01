package vn.funix.fx20894.java.asm04.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextFileService {
    private static final String COMMA_DELIMITER = ",";
    public static List<List<String>> readFile(String fileName) throws IOException {
        List<List<String>> listOfLists = new ArrayList<>();
        try{
            List<String> lines = Files.readAllLines(Paths.get(fileName));
            lines.forEach(line ->{
                if(!line.isEmpty()){
                        List<String> innerList= new ArrayList<>(Arrays.asList(line.split(COMMA_DELIMITER)));
                        listOfLists.add(innerList);
                    }
            });
        }catch (NoSuchFileException noSuchFile){
            System.out.println("Khong tim thay tep "+fileName);
        }catch (InvalidPathException invalidPath){
            System.out.println("Day khong phai dinh dang cua mot duong dan!");
        }
        return listOfLists;
    }


}


