package org.example;

import org.example.views.Welcome;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Welcome w = new Welcome();
        do{
            w.welcomeScreen();
        }while (true);
    }
}