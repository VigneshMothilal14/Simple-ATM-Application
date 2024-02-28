package com.project.atmproject;
import java.util.Scanner;

public class AccountCreation extends InputValidation {
    Scanner sc = new Scanner(System.in);
    private int flag = 0;
    private int choice;
    private final float initialRemainingBalance = 0;
    private final String queryForFetchCustomerId = "select customer_id from user_details where customer_id = ?";
    private final String queryForInsertAllDataToDb = "insert into user_details values(?,?,?,?,?)";

    //getters and setters
    public void setFlag (int flag) { this.flag = flag; }
    public int getFlag () { return flag; }
    public void setChoice (int choice) {this.choice = choice; }
    public int getChoice () { return choice; }
    public float getInitialRemainingBalance () { return initialRemainingBalance; }
    public String getQueryForFetchCustomerId () { return queryForFetchCustomerId; }
    public String getQueryForInsertAllDataToDb () {return queryForInsertAllDataToDb; }

    DBConnection dbConnection;
    public AccountCreation (DBConnection dbConnection) { this.dbConnection = dbConnection; }

    void welcomeMessage () {
        System.out.println ("|------------------------------------------------------------------------------------------------------------------------------------|\n");
        System.out.println ("\t\t\t\t\t\t\t\t\t+--------------------------------------------+");
        System.out.println ("\t\t\t\t\t\t\t\t\t|-------- Welcome to our ATM service --------|");
        System.out.println ("\t\t\t\t\t\t\t\t\t+--------------------------------------------+");

        System.out.println ("\t+---------------------------------------------------------------------------------------------------------------+");
        System.out.println ("\t| To activate your ATM service, please insert the ATM card into the designated slot Enter the necessary details |");
        System.out.println ("\t+---------------------------------------------------------------------------------------------------------------+");
    }

    boolean activateATMService () {
        do {
            System.out.println ("Enter the CustomerId :");
            if (!isValidId ())
                break;

            System.out.println ("Enter the CustomerName :");
            if (!isValidName ())
                break;

            System.out.println ("Account Type Savings / Current ? :");
            if (!isValidAccountType ())
                break;

            System.out.println ("Set Your 4 - digit ATM PinNumber :");
            if (!isValidPinNumber ())
                break;

            dbConnection.insertAllDataToDb (getCustomerId (), getCustomerName (), getAccountType (), getPinNumber (), getInitialRemainingBalance (), getQueryForInsertAllDataToDb ());
            System.out.println ("-> All details are verified successfully and your ATM service is activated!!!");

            setFlag (1);
            break;
        } while (true);
        return getFlag () == 1;
    }

    int customerIdReturn () {
        return dbConnection.fetchCustomerId(getCustomerId (), getQueryForFetchCustomerId ());
    }

    void minimumBalanceDisplay () {
        System.out.println ("-> you should have the minimum balance at-least 100 Rupees\n");
    }

    int menuDisplayForNewUser () {
        System.out.println("+-----------------------------+");
        System.out.println("| 1. Deposit                  |");
        System.out.println("| 2. Withdraw                 |");
        System.out.println("| 3. CheckBalance             |");
        System.out.println("| 4. Update PIN Number        |");
        System.out.println("| 5. GetAccountCurrentDetails |");
        System.out.println("| 6. Remove Account           |");
        System.out.println("| 7. Exit                     |");
        System.out.println("+-----------------------------+");
        try {
            System.out.println ("Which service did you want: ?");
            setChoice (sc.nextInt ());
        }
        catch (Exception e) {
            System.out.println ("Enter the valid input for choice . . .");
            return 8;
        }
        return getChoice ();
    }

    public void welcomeMessageForExistingCustomer () {
        System.out.println ("|------------------------------------------------------------------------------------------------------------------------------------|\n\n");
        System.out.println("\t\t\t\t\t\t\t\t\t+--------------------------------------------+");
        System.out.println("\t\t\t\t\t\t\t\t\t|-------- Welcome to our ATM service --------|");
        System.out.println("\t\t\t\t\t\t\t\t\t+--------------------------------------------+");
    }

    int menuDisplayForExistingUser () {
        System.out.println("+-----------------------------+");
        System.out.println("| 1. Deposit                  |");
        System.out.println("| 2. Withdraw                 |");
        System.out.println("| 3. CheckBalance             |");
        System.out.println("| 4. Update PIN Number        |");
        System.out.println("| 5. GetAccountCurrentDetails |");
        System.out.println("| 6. Remove Account           |");
        System.out.println("| 7. Exit                     |");
        System.out.println("+-----------------------------+");
        try {
            System.out.println ("Which service did you want: ?");
            setChoice (sc.nextInt ());
        }
        catch (Exception e) {
            System.out.println ("Enter the valid input for choice . . .");
            return 8;
        }
        return getChoice ();
    }
}
