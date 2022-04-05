<%-- 
    Document   : LoginError
    Created on : Feb 28, 2022, 11:54:01 AM
    Author     : Josiah Martin
    Lab        : 7
    Due        : 28 February 2022
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "com.chattbank.Customer"%>
<!DOCTYPE html>
<!--
Author: Josiah Martin
Date: 16 February 2022
-->
<html>
    <head>
        <title>Error Page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/web-fonts.css">
        <style>
            body{
                background-color: black;
            }
            .container{
                width: 50%;
                margin: auto;
                padding-top: 10vh;
                font-family: sans-serif;
            }
            .container > h1{
                text-align: center;
            }
            .container > p{
                text-align: justified;
                font-size: .88rem;
            }
            .container > a{
                text-align: center;
                font-size: .88rem;
            }
        </style>
    </head>
    <body>
        <%
           //Get the customer's information from the LoginServlet
          Customer customer = (Customer)session.getAttribute("customer");
          String custId = customer.getCustId();
        %>
        <div class="container">
            <article>
                <h1>
                    Error logging in for user with ID <%=custId%>.
                </h1>
                <p>
                    You could have mistyped your username or password. Please try again with more caution. Thank you.
                </p>
                <a href="login.html">Click here to login again</a>
            </article>
        </div>
    </body>
</html>
