package com.chattbank;

/*****************************************************************************
 * Java III, Lab 3: Servlets
 * Author: Josiah Martin
 * Date: 26 January 2022
 * Due:  31 January 2022
 *****************************************************************************/
import java.sql.*;
public class DBConnection {
    //Path referencing the database
    private final String path = "jdbc:ucanaccess://C:YOUR_FILE_PATH_TO_DATABASE";
    private final String driver = "net.ucanaccess.jdbc.UcanaccessDriver";
    
    private Connection conn;
    
    //Fields to hold customer information
    private String name;
    private String custID;
    
    public DBConnection(){
        //Load the driver
        System.out.println((establishConnection()) ? "Connection open" : "Could not open a connection");
    }
    
    //Method to select tuples from the database
    //Returns the reference to the tuple
    public ResultSet select(String query){
        ResultSet rs = null;
        try{
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
        } catch(SQLException e){
            System.out.println("Could not execute query... " + e);
        }
        return rs;
    }
    
    //Used to get the connection
    //Called in the constructor
    private boolean establishConnection(){
        boolean isConnected = false;
        try{
            //Load the driver
            Class.forName(driver);
            //Get the connection
            conn = DriverManager.getConnection(path);
            isConnected = true;
        } catch(SQLException | ClassNotFoundException e){
            System.out.println("Connection failed... " + e);
        }
        return isConnected;
    }
    
    //Method to close connections
    //Returns true if successful
    public boolean closeConnection(){
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
    
    //Method to process a tuple matching a customer
    //Returns true if there is a match for a customer
    public boolean processQuery(ResultSet rs, String username, String password){
        try{
            //rs.next() moves the row cursor to the next row
            //There should only be one match
            rs.next();
            if(rs.getString(1).equals(username) && rs.getString(2).equals(password)){
                this.name = rs.getString(3) + " " + rs.getString(4);
                this.custID = rs.getString(1);
                //The first customer to match someone in the database will return true here
                return true;
            }
        } catch(SQLException e){
            System.out.println("Could not process the results..." + e);
        }
        //If the method executes the following line, then there was no match
        return false;
    }
    
    
    //Gets the customer's name
    public String getName(){
        return name;
    }
    
    //Gets the customer's ID
    public String getID(){
        return custID;
    }
    
    public static void main(String[] args){
        //Make a new ConnectionDB object
        DBConnection connection = new DBConnection();
        String query = "SELECT * FROM Customers";
        System.out.println(query);
        ResultSet rs = connection.select(query);
        try{
            int i = 0; //Counter variable to display the current row number
            while(rs.next()){
                System.out.println("Row " + (++i) + " : " + rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5) + " " + rs.getString(6));
            }
            System.out.println("Returned " + i + " rows");
        } catch(SQLException e){
            System.out.println(e);
        }
        System.out.println((connection.closeConnection()) ? "Connection closed" : "Counld not close the connection");
        double[] doubles = {1.0,2.0,3.0,4.0,5.0,6.0,7.0,8.0,9.0};
        double myDouble;
        for(int i = 0; i < doubles.length; ++i){
            myDouble = doubles[i];
            System.out.printf("%.2f", myDouble);
            System.out.println();
        }
    }
    
}
