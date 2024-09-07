package org.example.views;

import org.example.dao.UserDAO;
import org.example.model.User;
import org.example.service.GenerateOTP;
import org.example.service.SendOTPService;
import org.example.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.Scanner;

import static org.example.service.SendOTPService.sendOTP;

public class Welcome {

    public void welcomeScreen(){
     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
     System.out.println("Welcome....!");
     System.out.println(" press 1 to Login");
     System.out.println("press 2 to signUp");
     System.out.println("press 0 to Exit");
    int choice =0;
    try{
        choice = Integer.parseInt(br.readLine());
    }catch (IOException ex){
        ex.printStackTrace();
    }

    switch(choice) {
        case 1 -> login();
        case 2 -> signUp();
        case 0 -> System.exit(0);

    }



 }

    private void login() {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter email");
        String email= sc.nextLine();
        try{
            if(UserDAO.isExists(email)){
                String genOTP = GenerateOTP.getOTP();
                sendOTP(email,genOTP);
                System.out.println("enter the OTP: ");
                String otp = sc.nextLine();
                if(otp.equals(genOTP)){
                     new Userview(email).home();
                }else{
                    System.out.println("wrong otp entered");
                }

            }
            else{
                System.out.println("User do not exist");
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }

    }
    private void signUp(){
     Scanner sc = new Scanner(System.in);
     System.out.println("enter name");
     String name = sc.nextLine();
     System.out.println("enter email");
     String email = sc.nextLine();
        String genOTP = GenerateOTP.getOTP();
        SendOTPService.sendOTP(email,genOTP);
        System.out.println("enter the OTP: ");
        String otp = sc.nextLine();
        if(otp.equals(genOTP)){
            User user = new User(name,email);
            int response = UserService.saveUser(user);
            switch (response){
                case 0 -> System.out.println("user registered");
                case 1 -> System.out.println("user already exist");
            }
        }else{
            System.out.println("wrong otp entered");
        }

    }
}

