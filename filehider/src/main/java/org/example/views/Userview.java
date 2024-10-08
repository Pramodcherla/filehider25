package org.example.views;
import org.example.db.MyConnection;
import org.example.dao.DataDAO;
import org.example.model.Data;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Userview {
    private String email;
    Userview(String email){
        this.email = email;
    }
    public void home(){
    do{
        System.out.println("welcome"+ this.email);
        System.out.println("press 1 to show hidden files");
        System.out.println("press 2 to hide a new file");
        System.out.println("press 3 to unhide a files");
        System.out.println("press 0 to exit");
        Scanner sc = new Scanner(System.in);
        int ch = Integer.parseInt(sc.nextLine());
        switch (ch){
            case 1-> {
                try {
                    List<Data> files = DataDAO.getAllFiles(this.email);
                    System.out.println("ID - File Name");
                    for(Data file : files){
                       System.out.println(file.getId() + "-" + file.getFileName());
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            case 2-> {
                System.out.println("Enter the file path");
                String path = sc.nextLine();
                File f = new File(path);
                Data file = new Data(0,f.getName(),path,email);
                try {
                    DataDAO.hideFile(file);
                } catch (SQLException | IOException e) {
                    throw new RuntimeException(e);
                }
            }
            case 3->{
                List<Data> files = null;
                try {
                    files = DataDAO.getAllFiles(this.email);
                }catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("ID - File Name");
                for(Data file : files){
                    System.out.println(file.getId() + "-" + file.getFileName());
                }
                System.out.println("Enter the id of the file to unhide");
                int id = Integer.parseInt(sc.nextLine());
                boolean isValidId = false;
                for(Data file : files){
                    if(file.getId()==id){
                        isValidId = true;
                        break;
                    }
                }
                if(isValidId){
                    try {
                        DataDAO.unhide(id);
                    } catch (SQLException | IOException e) {
                        throw new RuntimeException(e);
                    }
                }else {
                    System.out.println("wrong Id");
                }
            }
            case 0->{
                System.exit(0);
            }


        }
    }while(true);
    }
}
