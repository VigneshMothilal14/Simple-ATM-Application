package com.project.atmproject;

public class BalanceChecking  extends Withdraw {

    private int cidInBalanceCheck;
    private float remainingBalanceForBalanceCheck;

    // getters and setters
    public void setCidInBalanceCheck (int cid) {
        this.cidInBalanceCheck = cid;
    }

    public int getCidInBalanceCheck() {
        return  cidInBalanceCheck;
    }

    public void setRemainingBalanceForBalanceCheck (float remainingBalanceForBalanceCheck) {
        this.remainingBalanceForBalanceCheck = remainingBalanceForBalanceCheck;
    }

    public float getRemainingBalanceForBalanceCheck () {
        return remainingBalanceForBalanceCheck;
    }

    DBConnection dbConnection;
    public BalanceChecking(DBConnection dbConnection, int cid) {
        this.dbConnection = dbConnection;
       setCidInBalanceCheck (cid);
    }


    void checkBalance() {

        setRemainingBalanceForBalanceCheck (dbConnection.fetchRemainingBalanceFromDb (getCidInBalanceCheck (), getGetQueryForFetchRemainingBalance ()));

        if (getRemainingBalanceForBalanceCheck () == getMinBalance ()) {

            System.out.println ("+----------------------------------------------------+");
            System.out.println ("Your Current Balance is : " + getRemainingBalanceForBalanceCheck () + " (Minimum Balance)");
            System.out.println ("+----------------------------------------------------+\n");

        }

        else if (getRemainingBalanceForBalanceCheck () < getMinBalance ()) {

            System.out.println ("+--------------------------------------------------------------------------------------------------------------------+");
            System.out.println ("Your Current Balance is : " + getRemainingBalanceForBalanceCheck () + " (Lesser than minimum balance deposit immediately or it leads to lock your account)");
            System.out.println ("+--------------------------------------------------------------------------------------------------------------------+\n");

        }

        else {

            System.out.println ("+-------------------------------------+");
            System.out.println ("Your Current Balance is : " + getRemainingBalanceForBalanceCheck ());
            System.out.println ("+-------------------------------------+\n");

        }
    }
}
