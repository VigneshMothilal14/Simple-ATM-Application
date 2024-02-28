package com.project.atmproject;
import java.sql.*;

public class DBConnection {
    Connection con = null;
    PreparedStatement preparedStatement;
    ResultSet resultSet = null;
    private int customerIdForExistingCustomer;
    private float remainingBalance = 0.0f;
    private int pinNumber;

    // getters and setters
    private String getUrl () {
        return "jdbc:mysql://localhost:3306/atm_application";
    }
    private String getUserName () {
        return "root";
    }
    private String getPassWord () {
        return "14112001";
    }
    public int getCustomerIdForExistingCustomer() {
        return customerIdForExistingCustomer;
    }

    public void setCustomerIdForExistingCustomer(int customerIdForExistingCustomer) {
        this.customerIdForExistingCustomer = customerIdForExistingCustomer;
    }

    public void setRemainingBalanceInDb(float remainingBalance) {
        this.remainingBalance = remainingBalance;
    }

    public float getRemainingBalanceInDb() {
        return remainingBalance;
    }

    public int getPinNumberInDb() {
        return pinNumber;
    }

    public void setPinNumberInDb(int pinNumber) {
        this.pinNumber = pinNumber;
    }

    void makingConnection () {
        try {
            con = DriverManager.getConnection(getUrl (), getUserName (), getPassWord ());
        } catch (SQLException e) {
            System.out.println ("Something is wrong when making the jdbc connection check again . . . \n");
        }
    }

    void insertAllDataToDb (int customerId, String customerName, String accType, int pinNUmber, float remainingBalance, String queryForInsertAllDataToDb ) {
        try {
            makingConnection();
            preparedStatement = con.prepareStatement(queryForInsertAllDataToDb);
            preparedStatement.setInt(1,customerId);
            preparedStatement.setString(2,customerName);
            preparedStatement.setString(3,accType);
            preparedStatement.setInt(4,pinNUmber);
            preparedStatement.setFloat(5,remainingBalance);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println("Something is wrong to insert all data to database try again. . . \n");
        }
    }

    void fetchAllDataFromDb(int cid, String queryForFetchAllDataFromDb) {
        try {
            makingConnection();
            preparedStatement = con.prepareStatement(queryForFetchAllDataFromDb);
            preparedStatement.setInt(1,cid);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int customerId = resultSet.getInt(1);
            String customerName = resultSet.getString(2);
            String accType = resultSet.getString(3);
            int pinNumber = resultSet.getInt(4);
            float remainingBalance = resultSet.getFloat(5);

            System.out.println("+-------------------------------+");
            System.out.println("| customer id is     : " + customerId);
            System.out.println("| customer name is   : " + customerName);
            System.out.println("| account type is    : " + accType);
            System.out.println("| pin number is      : " + pinNumber);
            System.out.println("| current balance is : " + remainingBalance);
            System.out.println("+-------------------------------+\n");
        }
        catch(SQLException e) {
            System.out.println("Something is wrong when fetch all data from the database try again . . . \n");
        }
    }

    int fetchCustomerId(int customerId, String queryForFetchCustomerId) {
        try {
            makingConnection();
            preparedStatement = con.prepareStatement(queryForFetchCustomerId);
            preparedStatement.setInt(1,customerId);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            setCustomerIdForExistingCustomer (resultSet.getInt(1));
        }
        catch(SQLException e) {
            System.out.println("May be your id is not exist check again. . . \n");
        }
        return getCustomerIdForExistingCustomer ();
    }
    void updateRemainingBalanceToDb(float remainingBalance , int cid, String queryForUpdateRemainingBalance) {
        try {
            makingConnection();
            preparedStatement = con.prepareStatement(queryForUpdateRemainingBalance);
            preparedStatement.setFloat(1,remainingBalance);
            preparedStatement.setInt(2,cid);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println("Something is wrong when update the remaining balance to the database check again . . . \n");
        }
    }

    float fetchRemainingBalanceFromDb(int cid, String queryForFetchRemainingBalance) {
        try {
            makingConnection();
            preparedStatement = con.prepareStatement(queryForFetchRemainingBalance);
            preparedStatement.setInt(1,cid);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            setRemainingBalanceInDb (resultSet.getFloat("remaining_balance"));
        }
        catch (SQLException e) {
            System.out.println("Something is wrong when fetch the remaining balance from the database try again. . . \n");
        }
        return getRemainingBalanceInDb ();
    }

    int fetchPinNumberFromDb(int cid, String queryForFetchPinNumber) {
        try {
            makingConnection();
            preparedStatement = con.prepareStatement(queryForFetchPinNumber);
            preparedStatement.setInt(1,cid);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            setPinNumberInDb (resultSet.getInt(1));
        }
        catch(SQLException e) {
            System.out.println("Something is wrong when fetch the pin-number from the database check again. . . \n");
        }
        return getPinNumberInDb ();
    }

    void updatePinFromDb(int cid, int pinNumber, String queryForUpdatePinNumber) {
        try {
            makingConnection();
            preparedStatement = con.prepareStatement(queryForUpdatePinNumber);
            preparedStatement.setFloat(1, pinNumber);
            preparedStatement.setInt(2,cid);
            preparedStatement.executeUpdate();
        }
        catch(SQLException e) {
            System.out.println("Something is wrong when update the pin-number to the database check again. . . \n");
        }
    }

    void DisableATMService(int cid, String queryForRemoveUserAccount) {
        try {
            makingConnection();
            preparedStatement = con.prepareStatement(queryForRemoveUserAccount);
            preparedStatement.setInt(1,cid);
            preparedStatement.executeUpdate();
            System.out.println("+----------------------------------------+");
            System.out.println( "Your account removed successfully");

        }
        catch(SQLException e) {
            System.out.println("Something is wrong while remove the account make sure you have an account input. . . \n");
        }
    }
}
