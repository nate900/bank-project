package com.chattbank;

/******************************************************************************
 * Lab 5: Java 3, More Business Objects
 * Author: Josiah Martin
 * Date: 10 February 2022
 * Due: 14 February 2022
 *******************************************************************************/
import java.sql.*;

public class Customer {
    private String custId;
    private String custPassword;
    private String custFirstName;
    private String custLastName;
    private String custAddress;
    private String custEmail;
    
    //New AccountList property for the customer
    private AccountList accountList;
    
    //Private connection object to establish a connection to the database
    Connection conn;
    
    //Path referencing the database
    private final String PATH = "jdbc:ucanaccess://YOUR_FILE_PATH_TO_DATABASE";
    private final String DRIVER = "net.ucanaccess.jdbc.UcanaccessDriver";
    
    //no-arg constructor
    Customer(){
        custId = "";
        custPassword = "";
        custFirstName = "";
        custLastName = "";
        custAddress = "";
        custEmail = "";
        
        //If no elements were specified, then the AccountList property will hold up to 10 Account objects by default
        accountList = new AccountList(10);
    }
    
    //Construcstor which takes 7 arguments
    Customer(String ID, String pass, String firstName, String lastName, String address, String email, int elements){
        //Set properties
        custId = ID;
        custPassword = pass;
        custFirstName = firstName;
        custLastName = lastName;
        custAddress = address;
        custEmail = email;
        accountList = new AccountList(elements);
    }
    
    //Method to select the current customer from the database
    public void selectDB(String ID){
        //Make a connection to the database and load the driver
        establishConnection();
        
        String select = "SELECT * FROM Customers WHERE CustID = '" + ID + "'";
        try{
            //Create a statement object
            Statement stmt = conn.createStatement();
            System.out.println(select);
            ResultSet rs = stmt.executeQuery(select);
            
            //Process the result set and set the cusotmer's properties
            processSelect(rs);
            
        } catch(SQLException e){
            System.out.println("The select did not work... " + e);
        }
        
        //Close the database connection
        closeConnection();
        
        //Get the number of accounts this customer has
        getAccounts();
    }
    
    //Method to process the query return in the selectDB() method
    private void processSelect(ResultSet rs) throws SQLException{
        rs.next();
        custId = rs.getString(1);
        custPassword = rs.getString(2);
        custFirstName = rs.getString(3);
        custLastName = rs.getString(4);
        custAddress = rs.getString(5);
        custEmail = rs.getString(6);
        
    }
    
    //Method to insert a new customer into the database
    public void insertDB(String custId, String custPassword, String custFirstName, String custLastName, String custAddress, String custEmail){
        String insert = "INSERT INTO Customers VALUES('" + custId + "', '" + custPassword + "', '" + custFirstName + "', '" + custLastName + "', '" + custAddress + "', '" + custEmail + "')";
        
        //Set object properties
        this.custId = custId;
        this.custPassword = custPassword;
        this.custFirstName = custFirstName;
        this.custLastName = custLastName;
        this.custAddress = custAddress;
        this.custEmail = custEmail;
        
        //Make a connection to the database and load the driver
        establishConnection();
        
        try{
            //Create a statement object
            Statement stmt = conn.createStatement();
            System.out.println(insert);
            //Execute the sql statement
            int rowsEffected = stmt.executeUpdate(insert);
            
            if(rowsEffected == 1){System.out.println("Customer " + custFirstName + " is in the database");}
            else{System.out.println("Customer " + custFirstName + " could not be inserted into the database");}
            
        } catch(SQLException e){
            System.out.println("Could not insert customer... " + e);
        }
        
        //Close the connection
        closeConnection();
    }
    
    //Method that will delete the current customer object from the database
    public void deleteDB(){
        String delete = "DELETE FROM Customers WHERE CustID = '" + custId + "'";
        
        //Make a connection to the database and load the driver
        establishConnection();
        
        try{
            //Create new statement object
            Statement stmt = conn.createStatement();
            System.out.println(delete);
            //Execute the delete
            int rowsEffected = stmt.executeUpdate(delete);
            
            if(rowsEffected == 1){System.out.println("Customer " + custFirstName + " was deleted from the database");}
            else{System.out.println("Customer " + custFirstName + " was not deleted from the database");}
            
        } catch(SQLException e){
            System.out.println("Could not delete " + custFirstName + " from the database... " + e);
        }
        
        //Close the connection from the database
        closeConnection();
    }
    
    private void getAccounts(){
        String query = "SELECT * FROM Accounts WHERE Cid = '" + custId + "'";
        //Make a connection to the database
        establishConnection();
        try(Statement stmt = conn.createStatement();){
            //Execute the query
            ResultSet rs = stmt.executeQuery(query);
            //Process the query
            while(rs.next()){
                //Adds a new account to the AccountList property
                accountList.addAccount(new Account(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDouble(4)));
            }
        }catch(SQLException e){
            System.out.println("Counld not execute statement against the Accounts table... " + e);
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
    
    //Setters
    public void setCustId(String ID){custId = ID;}
    public void setCustFirstName(String firstName){custFirstName = firstName;}
    public void setCustLastName(String lastName){custLastName = lastName;}
    public void setCustAddress(String address){custAddress = address;}
    public void setCustEmail(String email){custEmail = email;}
    
    //Getters
    public String getCustId(){return custId;}
    public String getCustPassword(){return custPassword;}
    public String getCustFirstName(){return custFirstName;}
    public String getCustLastName(){return custLastName;}
    public String getCustAddress(){return custAddress;}
    public String getCustEmail(){return custEmail;}
    
    //Method that calls the toString() method
    public void display(){
        System.out.println(toString());
        accountList.displayList();
    }
    
    public AccountList getCustomerAccounts(){
        return (accountList != null)? accountList : null;
    }
    
    //Method to return a string representation of this object
    @Override
    public String toString(){
        return "Name: " + custFirstName + " " + custLastName + "\nCustomer ID: " + custId + "\nCustomer Password: " + custPassword + "\nCustomer Address: " + custAddress + "\nCustomer Email: " + custEmail;
    }
    
    public static void main(String[] args){
        //Testing the selectDB() method
        
        String guiPass = "1234";
        String ID = "3001";
        Customer c1 = new Customer();
        c1.selectDB(ID);
        //Compare the gui password to the customer password found in the database
        if(guiPass.equals(c1.getCustPassword())){
            System.out.println("The customer from the gui matches the customer from the database");
            c1.display();
        }else{
            System.out.println("This customer is a fruad");
        }
        
        //Testing the insertDB() method
        /*
        String[] custInfo = {
            "1010",
            "password",
            "Joe",
            "Mart",
            "An Address",
            "newEmail@email.com"
        };
        Customer c2 = new Customer();
        c2.insertDB(custInfo[0], custInfo[1], custInfo[2], custInfo[3], custInfo[4], custInfo[5]);
        */
        
        //Testing the delete method
        /*
        String custId = "1010";
        
 
        Customer c3 = new Customer();
        c3.selectDB(custId);
        c3.deleteDB();
        */
    }
}
