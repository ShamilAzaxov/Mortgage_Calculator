package main;

import main.connector.Connector;

import java.util.Scanner;

public class Console {
    private static Scanner scan = new Scanner(System.in);

    public static String readNumber(String prompt) {
        System.out.print(prompt + ": ");
        return scan.next();
    }
    public static double readNumber(String prompt, double min, double max) {
        double value;
        while (true) {
            System.out.print(prompt + ": ");
            value = scan.nextDouble();
            if (value >= min && value <= max)
                break;
            System.out.println("Enter a value between " + min + " and " + max + ": ");
        }
        return value;
    }
}