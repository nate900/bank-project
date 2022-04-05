package com.chattbank;

/******************************************************************************
 * Lab 5: Java 3, More Business Objects
 * Author: Josiah Martin
 * Date: 10 February 2022
 * Due: 14 February 2022
 *******************************************************************************/
public class AccountList {
    //This class contains two properties and two methods
    
    //Properties
    private int count;
    private Account[] accounts;
    
    //Getter
    public int getSize(){return count;}
    
    //Constructor for AccountList
    AccountList(int elements){
        accounts = new Account[elements];
    }
    
    //Method adds a new Account object to the private array variable
    public void addAccount(Account account){
        //Make sure the accounts array can hold a new Account object
        accounts[count] = account;
        //Adds 1 to the count variable
        count++;
    }
    
    public Account getElement(int index){
        return accounts[index];
    }
    
    public void displayList(){
        //Uses traditional for-loop to iterate over the class's private array using the count variable
        for(int i = 0; i < count; i++){
            //On each iteration, call the display() method of each account object
            accounts[i].display();
        }
    }
    
    
    //Tester main method for the AccountList class
    public static void main(String[] args){
        //Creates a new AccountList object, which will hold only 3 Accounts
//        AccountList al = new AccountList(3);
//        
//        //Creates three Account objects and selects them from the database
//        Account a1 = new Account();
//        a1.selectDB("90000");
//        Account a2 = new Account();
//        a2.selectDB("90010");
//        Account a3 = new Account();
//        a3.selectDB("90007");
//        
//        //Adds all three accounts to the AccountList object
//        al.addAccount(a1);
//        al.addAccount(a2);
//        al.addAccount(a3);
//        
//        //Displays the AccountList object
//        al.displayList();
    }
}
