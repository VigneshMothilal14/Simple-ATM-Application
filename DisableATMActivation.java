package com.project.atmproject;
import java.util.Scanner;
public class DisableATMActivation {
    Scanner scanner = new Scanner(System.in);
    private int cid;
    private final String queryForRemoveAccount = "delete from user_details where customer_id = ?";
    private String decision;
    public void setCid (int cid) {
        this.cid = cid;
    }

    public int getCid () {
        return  cid;
    }

    public void setDecision (String decision) {
        this.decision = decision;
    }

    public String getDecision () {
        return decision;
    }

    public String getQueryForRemoveAccount () {
        return queryForRemoveAccount;
    }
    DBConnection dbConnection;
    public DisableATMActivation(DBConnection dbConnection, int cid) {
        this.dbConnection = dbConnection;
        setCid (cid);
    }

    void removeAccount() {
        System.out.println("Are you sure to remove account [ yes / No ]");
        setDecision (scanner.next());

        if (getDecision ().equals ("yes") || getDecision ().equals ("Yes")) {

            dbConnection.DisableATMService (getCid (), getQueryForRemoveAccount ());
            System.out.println ("Hope we prove the best service for you !!!");
            System.out.println("+----------------------------------------+\n");

        }

        else {
            System.out.println ("--------------------------------------------------------------------------------");
            System.out.println ("|                                                                              |");
            System.out.println ("| Thanks for not - removing your account in our service !!! continue service...|");
            System.out.println ("|                                                                              |");
            System.out.println ("--------------------------------------------------------------------------------\n");
        }
    }
}
