package com.project.atmproject;

public class Withdraw extends Deposit {
    Withdraw() { }
    private float initialWithdrawAmount, remainingBalanceInWithdraw = 0;
    private int pinForWithdraw, existingPinNumber;
    private final String queryForFetchPinNumber = "select pin_number from user_details where customer_id = ?";
    private int cidInWithdraw;

    // getters and setters
    public String getQueryForFetchPinNumber () {
        return queryForFetchPinNumber;
    }
    public void setPinForWithdraw (int pinForWithdraw) {
        this.pinForWithdraw = pinForWithdraw;
    }
    public int getPinForWithdraw () {
        return pinForWithdraw;
    }
    public void setExistingPinNumber (int existingPinNumber) {
        this.existingPinNumber = existingPinNumber;
    }
    public int getExistingPinNumber () {
        return existingPinNumber;
    }
    public void setInitialWithdrawAmount (float initialWithdrawAmount) {
        this.initialWithdrawAmount = initialWithdrawAmount;
    }
    public float getInitialWithdrawAmount () {
        return initialWithdrawAmount;
    }
    public void setRemainingBalanceInWithdraw (float remainingBalanceInWithdraw) {
        this.remainingBalanceInWithdraw = remainingBalanceInWithdraw;
    }
    public float getRemainingBalanceInWithdraw () {
        return remainingBalanceInWithdraw;
    }
    public void setCidInWithdraw (int cid) {
        this.cidInWithdraw = cid;
    }
    public int getCidInWithdraw () {
        return cidInWithdraw;
    }
    DBConnection dbConnection;
    public Withdraw(DBConnection dbConnection, int cid) {
        this.dbConnection = dbConnection;
        setCidInWithdraw (cid);
    }

    void withdrawAmount () {

        try {
            System.out.println ("Enter the 4 - digit Pin-Number");

            setPinForWithdraw (scanner.nextInt ());

            setExistingPinNumber (dbConnection.fetchPinNumberFromDb(getCidInWithdraw (), getQueryForFetchPinNumber()));

            setRemainingBalanceInWithdraw (dbConnection.fetchRemainingBalanceFromDb(getCidInWithdraw (), getGetQueryForFetchRemainingBalance ()));
        }
        catch (Exception e) {
            System.out.println ("Enter the valid pin-number. . .");
            scanner.nextLine ();
            return;
        }


        if (getExistingPinNumber () == getPinForWithdraw ()) {
            try {
                System.out.println ("Enter the amount to withdraw");
                setInitialWithdrawAmount ( scanner.nextFloat ());
            } catch (Exception e) {
                System.out.println ("Enter the valid withdraw Amount. . . \n");
                scanner.nextLine ();
                return;
            }
            if (getInitialWithdrawAmount () >= getMinBalance ()) {

                if (getInitialWithdrawAmount () <= getRemainingBalanceInWithdraw ()) {

                    System.out.println ("+------------------------------+");
                    System.out.println ("Collect your Cash: " + getInitialWithdrawAmount ());
                    System.out.println ("+------------------------------+\n");

                    setRemainingBalanceInWithdraw (getRemainingBalanceInWithdraw () - getInitialWithdrawAmount ());

                    if (getRemainingBalanceInWithdraw () < getMinBalance ()) {

                        System.out.println ("+------------------------------------------------------------------------------------------+");
                        System.out.println (getRemainingBalanceInWithdraw () + " - Lesser than minimum balance deposit immediately or it leads to lock your account\n");
                        System.out.println ("+------------------------------------------------------------------------------------------+");
                    }

                    dbConnection.updateRemainingBalanceToDb (getRemainingBalanceInWithdraw (), getCidInWithdraw (), getQueryForUpdateRemainingBalance ());
                }
                else {
                    System.out.println ("Insufficient Balance");
                    System.out.println ("Suggestion : for more information check the balance. . .\n ");
                }
            }
            else {
                System.out.println ("Can't able to withdraw already you are less than the minimum balance");
                System.out.println ("Suggestion : for more information check the balance. . . \n");
            }
        }
        else
            System.out.println ("Invalid PIN Number - try again . . .\n");
    }
}