package com.chattbank;

/********************************************************************************
 * Java III, Lab#4: Business Objects
 * Author: NATE900
 * Date: 5 February 2022
 * Due: 7 February 2022
 *********************************************************************************/
import java.sql.*;

public class Account extends AssertionError {
    private String acctNo;
    private String custId;
    private String type;
    private double balance;
    
    //Private connection object to establish a connection to the database
    Connection conn;
    
    
    
    //Path referencing the database
    private final String PATH = "jdbc:ucanaccess://YOUR_FILE_PATH_TO_DATABASE";
    private final String DRIVER = "net.ucanaccess.jdbc.UcanaccessDriver";
    
    //No-arg constructor
    Account(){
        acctNo = "";
        custId = "";
        type = "";
        balance = 0.0;
    }
    
    //Constructor with arguments
    Account(String acctNo, String custId, String type, double balance){
        this.acctNo = acctNo;
        this.custId = custId;
        this.type = type;
        this.balance = balance;
    }
    
    //Method to select an account from the database
    public void selectDB(String acctNo){
        //Create a string to hold sql statement
        String select = "SELECT * FROM Accounts WHERE AcctNo = '" + acctNo + "'";
        
        //Create a connection to the database
        establishConnection();
        
        try{
            //Create statement
            Statement stmt = conn.createStatement();
            System.out.println(select);
            //Execute statement
            ResultSet rs = stmt.executeQuery(select);
            
            //Process the select statement
            processSelect(rs);
            
        }catch(SQLException e){
            System.out.println("Could not select the account from the database... " + e);
        }
        
        //Close the connection
        closeConnection();
    }
    
    //Method to insert a new account into the database
    public void insertDB(String acctNo, String custId, String type, double balance){
        //Create a string object to hold sql statement
        String insert = "INSERT INTO Accounts VALUES('" + acctNo + "', '" + custId + "', '" + type + "', " + balance + ")";
        
        //Set object properties
        this.acctNo = acctNo;
        this.custId = custId;
        this.type = type;
        this.balance = balance;
        
        //Create a connection
        establishConnection();
        
        try{
            //Create a statement object
            Statement stmt = conn.createStatement();
            System.out.println(insert);
            //Execute the statement
            int rowsEffected = stmt.executeUpdate(insert);
            if(rowsEffected == 1){System.out.println("Insert was a success");}
            else{System.out.println("Insert failed");}
            
        } catch(SQLException e){
            System.out.println("Could not delete account " + acctNo + " from the database... " + e);
        }
        
        //Close the connection
        closeConnection();
    }
    
    //Method to delete account from the database
    public void deleteDB(){
        //Create a string to hold sql statement
        String delete = "DELETE FROM Accounts WHERE AcctNo = '" + acctNo + "'";
        
        //Create connection to the database
        establishConnection();
        
        try{
            //Create statement
            Statement stmt = conn.createStatement();
            System.out.println(delete);
            //Execute statement
            int rowsEffected = stmt.executeUpdate(delete);
            if(rowsEffected == 1){System.out.println("Delete was a success");}
            else{System.out.println("Delete failed");}
        } catch(SQLException e){
            System.out.println("Could not delete the account " + acctNo + " from the database... " + e);
        }
        
        //Close the connection
        closeConnection();
    }
    
     //Method that establishes a connection to the database
    private void establishConnection(){
        try{
            //Load the driver
            Class.forName(DRIVER);
            //Get the connection
            conn = DriverManager.getConnection(PATH);
        } catch(ClassNotFoundException | SQLException e){
            System.out.println("Connection failed... " + e);
        }
    }
    
    //Method to close the connection
    //Returns a boolean value
    private boolean closeConnection(){
        boolean isClosed = false;
        if(conn != null)
            try{
                conn.close();
                isClosed = true;
            } catch(SQLException e){
                System.out.println("Cannot close the connection... " + e);
            }
        return isClosed;
    }
    
    //Private method to be used within the selectDB() method
    private void processSelect(ResultSet rs) throws SQLException{
        rs.next();
        this.acctNo = rs.getString(1);
        this.custId = rs.getString(2);
        this.type = rs.getString(3);
        this.balance = rs.getDouble(4);
    }
    
    //Setters
    public void setAcctNo(String acctNo){this.acctNo = acctNo;}
    public void setCustId(String custId){this.custId = custId;}
    public void setType(String type){this.type = type;}
    public void setBalance(double balance){
        if(balance < 0){
            System.out.println("Balance cannot be less than 0");
        }else{
            this.balance = balance;
        }
    }
    
    //Getters
    public String getAcctNo(){return acctNo;}
    public String getCustId(){return custId;}
    public String getType(){return type;}
    public double getBalance(){return balance;}
    
    //Display method
    public void display(){
        System.out.println(toString());
    }
    
    @Override
    public String toString(){
        return "Account Number: " + acctNo + "\nCustomer ID: " + custId + "\nAccount Type: " + type + "\nAccount Balance: " + balance;
    }
    
    public static void main(String[] args){
        String acctNo = "90003";
        Account a1 = new Account();
        a1.selectDB(acctNo);
        a1.display();

        
        //Testing select statement
        /*
        String acctNo = "90007";
        Account a1 = new Account();
        a1.selectDB(acctNo);
        a1.display();
        */
        
        //Testing the insertDB() method
        /*
        String[] accountInfo = {
            "90011",
            "1111",
            "SAV",
        };
        double balance = 10000.0;
        
        Account a2 = new Account();
        a2.insertDB(accountInfo[0], accountInfo[1], accountInfo[2], balance);
        */
        
        //Testing the deleteDB() method
        /*
        String acctNo = "90011";
        Account a3 = new Account();
        a3.selectDB(acctNo);
        a3.deleteDB();
        */
       
    }
    
}
