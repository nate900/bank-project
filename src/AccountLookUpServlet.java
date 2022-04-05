package com.chattbank;

/*****************************************************************************
 * Java III, Lab 6: More Servlets
 * Author: Josiah Martin
 * Date: 16 February 2022
 * Due: 21 February 2022
 *****************************************************************************/

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;

@WebServlet(urlPatterns = {"/AccountLookUpServlet"})
public class AccountLookUpServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            //Declare HttpSession object and RequestDispatcher object
            HttpSession sesh = request.getSession();
            
            //Get the customer from the session
            Customer customer = (Customer)sesh.getAttribute("customer");
            
            //Declare the RequestDispatcher object
            RequestDispatcher dispatcher;
            
            //Get data from previous HTML page
            String accountNumber = request.getParameter("acctNoField");
            String custId = request.getParameter("custIdField");
            String accountType = request.getParameter("acctTypeField");
            String balance = request.getParameter("balanceField");
            //String balance = request.getParameter("balanceField"); Variable was never used
            
            //Loop up the account from the database provided the account numer given by the customer
            Account customerAccount = new Account();
            customerAccount.selectDB(accountNumber);
            customerAccount.display(); //Display the "account" object to the console window
            //Put the account in the sesssion
            sesh.setAttribute("account", customerAccount);
            //Make sure the user is searching for his or her accounts and not other customer accounts
            if(customer.getCustId().equals(customerAccount.getCustId())){
                // The customer can see his or her account
                dispatcher = request.getRequestDispatcher("DisplayAccount.jsp");
                dispatcher.forward(request, response);
            }else{ 
                //Give the current session the "customerAccount" object
                sesh.setAttribute("customerAccount", customerAccount);
                //The customer is not entering the correct customer id
                dispatcher = request.getRequestDispatcher("AccountLookUpError.jsp");
                dispatcher.forward(request, response);
            }
            
            
            
        }
    }
    
    //doGet method
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// doGet() calls processRequest()	
        processRequest(request, response);
    }
    
    //doPost
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		// doPost() calls processRequest()
        processRequest(request, response);
    }
}

/*
    Someone enters data into the accountLookUp.html page.
    What are the data that the customer enterened into the page?
    Data are: Account Number, Customer Id, Account Type, Balance
    How do we get the account from the database?
    We use the Account.java object to retrieve the account based on the account number entered by the current customer.
*/
