package com.chattbank;

/*****************************************************************************
 * Java III, Lab 6: More Servlets
 * Author: NATE900
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
//New imports
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;

@WebServlet(urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            //Get a session object
            HttpSession sesh = request.getSession();
            
            //Get a dispatcher object
            RequestDispatcher dispatcher;
            //Get the username and password
            String username = request.getParameter("custIdField");
            String password = request.getParameter("passwordField");
            //Create a new Customer object
            Customer customer = new Customer();
            //Select customer from the database
            customer.selectDB(username);
            //Create a String to hold the password from the database
            String DBusername = customer.getCustId();
            String DBpassword = customer.getCustPassword();
            
            //Boolean value to determine login was a success
            boolean isLogin = (username.equals(DBusername) && password.equals(DBpassword));
            //Give the current customer object to the session
            sesh.setAttribute("customer", customer);
            
            //Determine which page to send the user to
            if(isLogin){
                dispatcher = request.getRequestDispatcher("accountLookUp.html");
                dispatcher.forward(request, response);
            }else{
                dispatcher = request.getRequestDispatcher("LoginError.jsp");
                dispatcher.forward(request, response);
            }
            
        }
    }
    //doGet method
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// doGet() calls processRequest()	
        processRequest(request, response);
    }
    
    //doPost
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		// doPost() calls processRequest()
        processRequest(request, response);
    }

}
