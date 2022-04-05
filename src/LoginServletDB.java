package com.chattbank;

/*****************************************************************************
 * Java III, Lab 3: Servlets
 * Author: Josiah Martin
 * Date: 26 January 2022
 * Due:  31 January 2022
 *****************************************************************************/
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/LoginServletDB"})
public class LoginServletDB extends HttpServlet {
    
    private DBConnection conn = null;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            System.out.println("Servlet runnin");
            //Establish a connection to the database using a connection manager class
            conn = new DBConnection();
            
            //Get the username and password
            String username = request.getParameter("custIdField");
            String password = request.getParameter("passwordField");
            System.out.println(username + " " + password);
            
            //Test username and password against database
            String query = "SELECT* FROM Customers WHERE CustID = " + username + " AND CustPassword = " + password + ";";
            if(conn.processQuery(conn.select(query), username, password)){
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet LoginServletDB</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Welcome " + conn.getName() + "</h1>");
                out.println("<p>Login was successful</p>");
                out.println("</body>");
                out.println("</html>");
            }else{
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet LoginServletDB</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Login was unsuccessful</h1>");
                out.println("</body>");
                out.println("</html>");
            }
            conn.closeConnection();
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
