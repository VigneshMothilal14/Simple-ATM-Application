
# Simple ATM Application
Welcome to the ATM project repository! This is a simple and efficient Automated Teller Machine (ATM) application designed to perform basic banking tasks such as account creation, depositing, withdrawing, balance checking, updating PIN numbers, and managing account details.

## Prerequisites
1. Before running this project, ensure you have set up a MySQL database with a table named user_details, containing columns for customer_id, customer_name, account_type, pin_number, and remaining_balance.
2. Whether you can use your own database name and table name. If you choose to do so, ensure to update the database and table names in the queries written in each class. Alternatively, you can use the default names we have used: "atm_application" as the database name and "user_details" as the table name.## Technology Used

## Technology Used
+ Language: Java
+ Database: MySQL
+ IDE: IntelliJ IDEA

## Topics Covered
This project covers the following Java and Object-Oriented Programming concepts:

+ Basic Java concepts: Conditions, loops, switch cases, operators, and methods.
+ Object-Oriented Programming concepts: Classes, objects, methods, inheritance, encapsulation, constructors, and composition.
+ Exception handling.
+ Java Database Connectivity (JDBC) for database connectivity.
Account Verification:

## Working flow

### Account Verification:

1. Upon starting the application, users are prompted to indicate whether they already have an account.
2. If the response is "no," the application directs them to create a new account.
3. If the response is "yes," users are prompted to enter their customer ID.

### Account Management:

1. After entering the customer ID, the system verifies it and enables users to execute various operations.
2. if a new customer then prompted to account creation part
3. once existing customer enter customer id or new customer creates the account then the application prompted to below tasks.

### Task Execution:

#### Users are empowered to perform multiple banking operations, for multiple users including:

+ Depositing funds
+ Withdrawing funds
+ Checking account balance
+ Updating PIN number
+ Removing an account (optional)
+ Exit
 
## Database Interaction:

1. All transactional data is stored and retrieved from the database.
2. When a user performs an action (e.g., withdrawing funds), the corresponding data is fetched from the database, the operation is executed, and the updated information is saved back to the database.

### Efficiency and Scalability:

1. The application architecture leverages object-oriented programming principles for easy maintenance and scalability.
2. Each user's data is encapsulated, ensuring privacy and facilitating seamless operation for multiple customers.
3. Through proper design and encapsulation, the system enables efficient management and updates to accommodate future enhancements.
4. This user-centric approach ensures a seamless and secure banking experience, where users can effortlessly manage their accounts and perform transactions with confidence and efficiency.
   
## Future Improvements
1. Continuous improvement and refinement to ensure 100% functionality.
2. Enhanced error handling and exception management.
  
**Thank you for exploring my ATM application project!**
