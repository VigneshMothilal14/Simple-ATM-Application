package com.project.atmproject;
import java.util.Scanner;

public class AccountExistenceCheck {

    Scanner scanner = new Scanner(System.in);
    private String checking = null;
    private int customerIdCheckingExistence, customerId;
    private final String queryForFetchCustomerId = "select customer_id from user_details where customer_id = ?";

    // getters and setters
    public void setCustomerId (int customerId) {
        this.customerId = customerId;
    }
    public int getCustomerId () {
        return customerId;
    }
    public void setChecking (String checking) {
        this.checking = checking;
    }
    public String getChecking () {
        return checking;
    }
    public int getCustomerIdCheckingExistence () {
        return customerIdCheckingExistence;
    }
    public void setCustomerIdCheckingExistence (int id) {
        this.customerIdCheckingExistence = id;
    }
    public String getQueryForFetchCustomerId () {
        return queryForFetchCustomerId;
    }

    DBConnection dbConnection;
    public AccountExistenceCheck (DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    boolean alreadyHaveAnAccount() {
            System.out.println ("\t\t\t\t\t\t\t\t+---------------------------------------------------+");
            System.out.println ("\t\t\t\t\t\t\t\t|        Did you already have an account ?          |");
            System.out.println ("\t\t\t\t\t\t\t\t|                   |yes | No|                      |");
            System.out.println ("\t\t\t\t\t\t\t\t+---------------------------------------------------+");

            setChecking(scanner.next ());
            return getChecking().equals ("Yes") || getChecking().equals ("yes");
    }
    boolean validateCustomerId () {
        try {
            System.out.println ("Enter your customerId");
            setCustomerId (scanner.nextInt ());

            setCustomerIdCheckingExistence (dbConnection.fetchCustomerId (getCustomerId (), getQueryForFetchCustomerId ()));
            return getCustomerIdCheckingExistence () == getCustomerId ();
        }
        catch (Exception e) {
            System.out.println ("Enter the valid customer id. . . ");
        }
        return false;
    }
    int customerIdReturn () {
        return getCustomerIdCheckingExistence();
    }
}
