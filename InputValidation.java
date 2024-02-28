package com.project.atmproject;
import java.util.Scanner;

public class InputValidation {
     Scanner scn = new Scanner(System.in);
     private int customerId, pinNumber;
     private String customerName, accountType;
     private int count = 0;

     //getters and setters
     public String getSavings () { return "savings"; }
     public String getCurrent () { return "current"; }
     public void setCustomerId (int customerId) { this.customerId = customerId; }
     public int getCustomerId () { return customerId; }
     public void setPinNumber (int pinNumber) { this.pinNumber = pinNumber; }
     public int getPinNumber () { return pinNumber; }
     public void setAccountType (String accountType) { this.accountType = accountType; }
     public String getAccountType () { return  accountType; }
     public void setCustomerName (String customerName) { this.customerName = customerName; }
     public String getCustomerName () { return customerName; }
     public void setCount (int count ) { this.count = count; }
     public int getCount () { return count; }


    boolean isValidId () {
        try {
           setCustomerId (scn.nextInt ());
            if (getCustomerId () < 0)
                throw new Exception ();
            else {
                System.out.println ("+---------------------+");
                System.out.println ("| CustomerId is saved |");
                System.out.println ("+---------------------+\n");
            }
        }
        catch (Exception ime) {
            System.out.println ("!!! Terminated Enter the valid id. . . ");
            System.out.println ("-------------------------------------------------------------------------------------------");
            scn.nextLine ();
            return false;
        }
        return true;
    }

    boolean isValidName () {
        try {
            setCustomerName (scn.next ());
            if (getCustomerName ().matches("[a-zA-Z]+")) {
                System.out.println ("+------------------------+");
                System.out.println ("| Customer name is saved |");
                System.out.println ("+------------------------+\n");
            }
            else
                throw new Exception ();
        }
        catch (Exception e) {
            System.out.println ("!!! Terminated Enter the valid name. . . ");
            System.out.println ("-------------------------------------------------------------------------------------------");
            scn.nextLine ();
            return false;
        }
        return true;
    }

    boolean isValidAccountType () {
        try {
            setAccountType (scn.next ());
            if (getSavings ().equalsIgnoreCase( accountType) || getCurrent ().equalsIgnoreCase (accountType)) {
                System.out.println ("+-----------------------+");
                System.out.println ("| Account type is saved |");
                System.out.println ("+-----------------------+\n");
            }
            else
                throw new Exception ();
        }
        catch (Exception e) {
            System.out.println ("!!! Terminated Enter the valid account type. . . ");
            System.out.println ("-------------------------------------------------------------------------------------------");
            scn.nextLine ();
            return false;
        }
        return true;
    }
    boolean isValidPinNumber () {
        try {
           setPinNumber (scn.nextInt ());
            int tempPin = getPinNumber ();
            while (tempPin > 0) {
                tempPin /= 10;
                setCount (getCount () + 1);
            }
            if (getCount () == 4 && getPinNumber () > 0) {
                System.out.println ("+-----------------------+");
                System.out.println ("| Your pin is activated |");
                System.out.println ("+-----------------------+\n");
            }
            else
                throw new Exception ();
        }
        catch (Exception e) {
            System.out.println ("!!! Terminated Enter the valid 4 - digit pin. . . ");
            System.out.println ("-------------------------------------------------------------------------------------------");
            scn.nextLine ();
            return false;
        }
        return true;
    }
}
