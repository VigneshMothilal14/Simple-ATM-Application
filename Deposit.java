package com.project.atmproject;
import java.util.Scanner;

public class Deposit {
    Scanner scanner = new Scanner(System.in);
    Deposit() { }
    private int cid, verifyDepositAmount;
    private float initialDepAmount;
    private float remainingBalance = 0;
    private final String queryForUpdateRemainingBalance = "update user_details set remaining_balance = ? where customer_id = ?";
    private final String getQueryForFetchRemainingBalance = "select remaining_balance from user_details where customer_id = ?";

    // getters and setters
    public float getMinBalance() { return 100.0f; }
    public void setCid(int cid) { this.cid = cid; }
    public int getCid() { return cid; }
    public void setVerifyDepositAmount(int verifyDepositAmount) { this.verifyDepositAmount = verifyDepositAmount; }
    public int getVerifyDepositAmount() { return verifyDepositAmount; }
    public void setInitialDepAmount (float initialDepAmount) { this.initialDepAmount = initialDepAmount; }
    public float getInitialDepAmount() { return initialDepAmount; }
    public float getRemainingBalance () { return remainingBalance; }
    public void setRemainingBalance (float remainingBalance) { this.remainingBalance = remainingBalance; }
    public String getQueryForUpdateRemainingBalance() { return queryForUpdateRemainingBalance; }
    public String getGetQueryForFetchRemainingBalance() { return getQueryForFetchRemainingBalance; }

    DBConnection dbConnection;
    public Deposit(DBConnection dbConnection, int cid) {
        this.dbConnection = dbConnection;
        setCid(cid);
    }


    void depositAmount () {
        try {
            System.out.println("Enter the Amount you want to be deposit :");
            setInitialDepAmount (scanner.nextFloat ());
        } catch (Exception e) {
            System.out.println("Enter the Valid amount. . .");
            setVerifyDepositAmount (1);
        }


        if (getVerifyDepositAmount () != 1) {
            while (true) {
                if (getRemainingBalance () > getMinBalance ())
                    break;

                else if (getInitialDepAmount () < getMinBalance ()) {

                    System.out.println((int) getInitialDepAmount () + " - is less than the minimum balance enter amount more than or equal to 100\n");

                    System.out.println("Enter the Amount you want to be deposit :");
                    setInitialDepAmount (scanner.nextFloat ());

                    if (getInitialDepAmount () > getMinBalance ())
                        break;
                } else
                    break;
            }

            setRemainingBalance (dbConnection.fetchRemainingBalanceFromDb(getCid (), getGetQueryForFetchRemainingBalance ()) + getInitialDepAmount ());

            dbConnection.updateRemainingBalanceToDb(getRemainingBalance (), getCid (), getQueryForUpdateRemainingBalance ());

            System.out.println ("+--------------------------------------------------+");
            System.out.println("Your amount is deposited successfully : " + getInitialDepAmount ());
            System.out.println("balance is                            : " + getRemainingBalance ());
            System.out.println ("+--------------------------------------------------+\n");

        }
    }
}
