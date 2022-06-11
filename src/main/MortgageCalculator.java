package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MortgageCalculator {
    private final static byte MONTH_IN_YEAR = 12;
    private final static byte PERCENT = 100;

    int principal;
    float annualInterest;
    byte years;

    public MortgageCalculator() {
    }

    public MortgageCalculator(int principal, float annualInterest, byte years) {
        this.principal = principal;
        this.annualInterest = annualInterest;
        this.years = years;
    }

    public double[] getRemainingBalance() {
        double[] balances = new double[getNumberOfPayments()];
        for (short month = 1; month <= balances.length; month++) {
            double balance = calculateBalance(month);
            balances[month - 1] = balance;
        }
        return balances;
    }

        public double calculateBalance(short numberOfPaymentsMade){
            float monthlyInterest = getMonthlyInterest();
            short numberOfPayments = getNumberOfPayments();

            double balance = principal
                    * (Math.pow(1 + monthlyInterest, numberOfPayments) - Math.pow(1 + monthlyInterest, numberOfPaymentsMade))
                    / (Math.pow(1 + monthlyInterest, numberOfPayments) - 1);
            return balance;
        }

        public double calculateMortgage () {
            float monthlyInterest = getMonthlyInterest();
            short numberOfPayments = getNumberOfPayments();
            double mortgage = principal
                    * (monthlyInterest * Math.pow(1 + monthlyInterest, numberOfPayments))
                    / (Math.pow(1 + monthlyInterest, numberOfPayments) - 1);
            return mortgage;
        }
        private float getMonthlyInterest () {
            return annualInterest / PERCENT / MONTH_IN_YEAR;
        }
        public short getNumberOfPayments () {
            return (short) (years * MONTH_IN_YEAR);
        }
    }

