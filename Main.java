package com.project.atmproject;

public class Main {
    public static void main (String[] args) {

        DBConnection dbConnection = new DBConnection ();
        AccountCreation accountCreation = new AccountCreation (dbConnection);

        AccountExistenceCheck accountExistenceCheck = new AccountExistenceCheck (dbConnection);
        ExitClass exitClass = new ExitClass ();


        if (accountExistenceCheck.alreadyHaveAnAccount ()) {

            if (accountExistenceCheck.validateCustomerId ()) {

                int cid = accountExistenceCheck.customerIdReturn ();

                Deposit deposit = new Deposit (dbConnection, cid);
                Withdraw withdraw = new Withdraw (dbConnection, cid);
                BalanceChecking balanceChecking = new BalanceChecking (dbConnection, cid);
                PinNumberUpdate pinNumberUpdate = new PinNumberUpdate (dbConnection, cid);
                AccountCurrentDetails accountCurrentDetails = new AccountCurrentDetails (dbConnection, cid);
                DisableATMActivation disableATMActivation = new DisableATMActivation (dbConnection, cid);

                System.out.println ("----------------------------------------");
                System.out.println ("| yes we verified you have the account |");
                System.out.println ("----------------------------------------\n");
                accountCreation.welcomeMessageForExistingCustomer ();
                outerLoop:
                while (true) {
                    switch (accountCreation.menuDisplayForExistingUser ()) {
                        case 1:
                            deposit.depositAmount ();
                            break;
                        case 2:
                            withdraw.withdrawAmount ();
                            break;
                        case 3:
                            balanceChecking.checkBalance ();
                            break;
                        case 4:
                            pinNumberUpdate.updatePinNumber ();
                            break;
                        case 5:
                            accountCurrentDetails.miniStatement ();
                            break;
                        case 6:
                            disableATMActivation.removeAccount ();
                            break;
                        case 7:
                            exitClass.exitTheLoop ();
                            break outerLoop;
                        case 8:
                            break outerLoop;
                        default:
                            System.out.println ("Enter the choice between 1 to 6. . .\n");
                            break;
                    }
                }
            } else {
                System.out.println ("we didn't found any account to the check again. . .");
            }
        }

        else {
            accountCreation.welcomeMessage ();
            if (accountCreation.activateATMService ()) {
                accountCreation.minimumBalanceDisplay ();

                int cid = accountCreation.customerIdReturn ();

                Deposit deposit = new Deposit (dbConnection, cid);
                Withdraw withdraw = new Withdraw (dbConnection, cid);
                BalanceChecking balanceChecking = new BalanceChecking (dbConnection, cid);
                PinNumberUpdate pinNumberUpdate = new PinNumberUpdate (dbConnection, cid);
                AccountCurrentDetails accountCurrentDetails = new AccountCurrentDetails (dbConnection, cid);
                DisableATMActivation disableATMActivation = new DisableATMActivation (dbConnection, cid);

                outerLoop:
                while (true) {
                    switch (accountCreation.menuDisplayForNewUser ()) {
                        case 1:
                            deposit.depositAmount ();
                            break;
                        case 2:
                            withdraw.withdrawAmount ();
                            break;
                        case 3:
                            balanceChecking.checkBalance ();
                            break;
                        case 4:
                            pinNumberUpdate.updatePinNumber ();
                            break;
                        case 5:
                            accountCurrentDetails.miniStatement ();
                            break;
                        case 6:
                            disableATMActivation.removeAccount ();
                            break;
                        case 7:
                            exitClass.exitTheLoop ();
                            break outerLoop;
                        case 8:
                            break outerLoop;
                        default:
                            System.out.println ("Enter the choice between 1 to 6. . . ");
                            break;
                    }
                }
            }
            else
                System.out.println ("You entered invalid input try again. . . ");
        }
    }
}
