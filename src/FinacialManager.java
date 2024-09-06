/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
import javax.swing.*;
import java.util.ArrayList;

public class FinacialManager {
    private double balance = 0.0;
    private ArrayList<transaction> transactions;

    public FinacialManager() {
        transactions = new ArrayList<>();
        run();
    }

    private void run() {
        while (true) {
            String[] options = {"Add Income", "Add Expense", "View Transactions", "View Balance", "Exit"};
            int choice = JOptionPane.showOptionDialog(null, "Select an option", "Financial Management",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            switch (choice) {
                case 0:
                    addTransaction("Income");
                    break;
                case 1:
                    addTransaction("Expense");
                    break;
                case 2:
                    viewTransactions();
                    break;
                case 3:
                    viewBalance();
                    break;
                case 4:
                    JOptionPane.showMessageDialog(null, "Exiting the application.");
                    System.exit(0);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option selected.");
                    break;
            }
        }
    }

    private void addTransaction(String type) {
        try {
            String amountText = JOptionPane.showInputDialog("Enter the amount:");
            if (amountText == null) return; // User pressed cancel
            double amount = Double.parseDouble(amountText);

            String description = JOptionPane.showInputDialog("Enter a description:");
            if (description == null) return; // User pressed cancel

            if (type.equals("Income")) {
                balance += amount;
            } else {
                balance -= amount;
            }

            transaction transaction = new transaction(type, amount, description);
            transactions.add(transaction);
            JOptionPane.showMessageDialog(null, "Transaction added successfully.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Please enter a valid amount.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void viewTransactions() {
        if (transactions.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No transactions recorded.");
        } else {
            StringBuilder historyText = new StringBuilder();
            for (transaction transaction : transactions) {
                historyText.append(transaction.toString()).append("\n");
            }
            JOptionPane.showMessageDialog(null, historyText.toString(), "Transaction History", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void viewBalance() {
        JOptionPane.showMessageDialog(null, "Current Balance: R" + balance, "Balance", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        new FinacialManager();
    }
}
