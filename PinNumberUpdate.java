package com.project.atmproject;
import java.util.Scanner;
public class PinNumberUpdate {
    Scanner scanner = new Scanner(System.in);
    private int cidForPinNumberUpdate;
    private int existingPinNumber, updatedPinNumber, dbPinNumber;
    private final String queryForFetchPinNumber = "select pin_number from user_details where customer_id = ?";
    private final  String queryForUpdatePinNumber = "update user_details set pin_number = (?) where customer_id = ?";

    public void setCidForPinNumberUpdate(int cidForPinNumberUpdate) {
        this.cidForPinNumberUpdate = cidForPinNumberUpdate;
    }

    public int getCidForPinNumberUpdate() {
        return cidForPinNumberUpdate;
    }
    DBConnection dbConnection;
    public PinNumberUpdate(DBConnection dbConnection, int cid) {
      this.dbConnection = dbConnection;
     setCidForPinNumberUpdate (cid);
    }

    public void setExistingPinNumber(int existingPinNumber) {
        this.existingPinNumber = existingPinNumber;
    }

    public int getExistingPinNumber() {
        return existingPinNumber;
    }

    public void setDbPinNumber(int dbPinNumber) {
        this.dbPinNumber = dbPinNumber;
    }

    public int getDbPinNumber() {
        return dbPinNumber;
    }

    public void setUpdatedPinNumber (int updatedPinNumber) {
        this.updatedPinNumber = updatedPinNumber;
    }

    public int getUpdatedPinNumber () {
        return updatedPinNumber;
    }
    public String getQueryForFetchPinNumber() {
     return queryForFetchPinNumber;
    }

    public String getQueryForUpdatePinNumber() {
        return queryForUpdatePinNumber;
    }

    void updatePinNumber() {
        try {
            System.out.println ("Enter your Existing 4 - digit PIN Number :");
            setExistingPinNumber (scanner.nextInt ());
        }
        catch(Exception e) {
            System.out.println ("Enter the valid pin Number. . . \n");
            return;
        }
        setDbPinNumber(dbConnection.fetchPinNumberFromDb(getCidForPinNumberUpdate (), getQueryForFetchPinNumber ()));

        if(getExistingPinNumber () == getDbPinNumber ()) {

            System.out.println("Enter the new 4 - digit PIN Number :");
            setUpdatedPinNumber(scanner.nextInt());

            dbConnection.updatePinFromDb(getCidForPinNumberUpdate (), getUpdatedPinNumber (), getQueryForUpdatePinNumber ());

            System.out.println("+----------------------------------------------------------------+");
            System.out.println("| Your PIN Number is updated successfully your new PIN is : " + getUpdatedPinNumber () + " |");
            System.out.println("+----------------------------------------------------------------+\n");
        }
        else
            System.out.println("Enter the valid existing pin number . . .\n");
    }
}
