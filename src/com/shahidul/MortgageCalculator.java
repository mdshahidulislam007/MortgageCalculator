package com.shahidul;

import java.text.NumberFormat;
import java.util.Scanner;


public class MortgageCalculator {

    public static void displayMonthlyBalance(int loanAmount,
                                             int termInYears, double interestRate, double monthlyPayment) {

        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();

        interestRate /= 100.0;
        double monthlyRate = interestRate / 12.0;
        int termInMonths = termInYears * 12;

        // Loop through the term of the loan tracking the balance

        double balance = loanAmount;
        for (int i=0; i<termInMonths; i++) {

            // Add interest to the balance

            balance += (balance * monthlyRate);

            // Subtract the monthly payment

            balance -= monthlyPayment;

            // Display running balance

            System.out.println("Balance after payment "+(i+1)+": "+
                    currencyFormat.format(balance));
        }
    }

    public static double calculateMonthlyPayment(
            int loanAmount, int termInYears, double interestRate) {

        // Convert interest rate into a decimal

        interestRate /= 100.0;

        // Monthly interest rate

        double monthlyRate = interestRate / 12.0;

        // The length of the term in months

        int termInMonths = termInYears * 12;

        // Calculate the monthly payment


        double monthlyPayment =
                (loanAmount*monthlyRate) /
                        (1-Math.pow(1+monthlyRate, -termInMonths));

        return monthlyPayment;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Prompt user for details of loan

        System.out.print("Enter loan amount: ");
        int loanAmount = scanner.nextInt();
        System.out.print("Enter loan term (in years): ");
        int termInYears = scanner.nextInt();
        System.out.print("Enter interest rate: ");
        double interestRate = scanner.nextDouble();

        // Calculate the monthly payment

        double monthlyPayment = calculateMonthlyPayment(
                loanAmount, termInYears, interestRate);

        // formatting currency and percentage values

        NumberFormat currencyFormat =
                NumberFormat.getCurrencyInstance();
        NumberFormat interestFormat =
                NumberFormat.getPercentInstance();

        // Display details of the loan

        System.out.println("Loan Amount: "+
                currencyFormat.format(loanAmount));
        System.out.println("Loan Term: "+
                termInYears+" years");
        System.out.println("Interest Rate: "+
                interestFormat.format(interestRate));
        System.out.println("Monthly Payment: "+
                currencyFormat.format(monthlyPayment));

        // Now display the monthly balance for
        // the term of the loan

        displayMonthlyBalance(
                loanAmount, termInYears, interestRate, monthlyPayment);
    }

}
