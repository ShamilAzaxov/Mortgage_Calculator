package main;

import main.connector.Connector;
import main.sql.Implementation;

public class Main {
    public static void main(String[] args) {
        int principal = (int) Console.readNumber("Principal", 1000, 1000000);
        float annualInterest = (float) Console.readNumber("Annual Interest Rate", 1, 30);
        byte years = (byte) Console.readNumber("Period (Years)", 1, 30);
        MortgageCalculator calculator = new MortgageCalculator(principal, annualInterest, years);
        MortgageReport report = new MortgageReport(calculator);
        report.printMortgage();
        report.printPaymentSchedule();
        String user = Console.readNumber("Enter mysql user");
        String passWord = Console.readNumber("Enter mysql password");
        Connector.setUser(user);
        Connector.setPassWord(passWord);
        Implementation i = new Implementation(calculator);
        i.insertIntoTable();

    }
}