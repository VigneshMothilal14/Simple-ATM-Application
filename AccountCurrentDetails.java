package com.project.atmproject;

public class AccountCurrentDetails {
    private int cid;
    private final String queryForFetchAllDataFromDb = "select * from user_details where customer_id = ?" ;
    private final String getQueryForFetchRemainingBalance = "select remaining_balance from user_details where customer_id = ?";
    private float checkRemainingAmount;

    public void setCheckRemainingAmount (float checkRemainingAmount) {
        this.checkRemainingAmount = checkRemainingAmount;
    }

    public float getCheckRemainingAmount() {
        return checkRemainingAmount;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getCid() {
        return  cid;
    }

    public String getQueryForFetchAllDataFromDb() {
        return queryForFetchAllDataFromDb;
    }

    public String getGetQueryForFetchRemainingBalance() {
        return getQueryForFetchRemainingBalance;
    }
    DBConnection dbConnection;
    public AccountCurrentDetails(DBConnection dbConnection, int cid) {
        this.dbConnection = dbConnection;
        setCid (cid);
    }

    private float getMinBalance() {
        return 100;
    }
    void miniStatement() {
        dbConnection.fetchAllDataFromDb(getCid (), getQueryForFetchAllDataFromDb ());

        setCheckRemainingAmount (dbConnection.fetchRemainingBalanceFromDb (getCid (),getGetQueryForFetchRemainingBalance ()));

        if(getCheckRemainingAmount () < getMinBalance())
            System.out.println(getCheckRemainingAmount () + " - You are below the minimum balance!!!\n");
    }
}
