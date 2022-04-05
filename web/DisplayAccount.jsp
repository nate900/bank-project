<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : DisplayAccount
    Created on : Feb 28, 2022, 12:10:51 PM
    Author     : Josiah Martin
    Lab        : 8
    Due        : 7 March 2022
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--<%@page import = "com.chattbank.Account"%>--%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Display Account</title>
    </head>
    <body>
        <html>
    <head>
        <title>Chattahoochee Bank Account Look Up</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/web-fonts.css">
        <style>
            body{
                background-color: #000;
            }
            .background-image{
                background-color: #ddd;
                background-image: url("https://media.istockphoto.com/photos/piggy-bank-dollar-bag-on-a-laptop-picture-id1275761597?b=1&k=20&m=1275761597&s=170667a&w=0&h=v8S7_c6h5xK8B034rBfgypNVgPgiHY2jovD4srzyQ4I=");
                background-repeat: no-repeat;
                background-position: center;
                background-size: cover;
                height: 900px;
            }
            .container{
                width:50%;
                margin: auto;
                background-color: darkgray;
                border: 3px #ddd solid;
                border-radius: 10px;
            }
            .container > form{
                display: box;
                width: 50%;
                margin: auto;
                padding: 1rem;
            }
            .container > form > label{
                margin: .5rem;
                padding: .3rem;
            }
            .container > form > input{
                padding: .3rem;
                margin: .5rem;
            }
            #submitButton{
                width: 100%;
                font-size: 1rem;
                margin: .5rem;
            }
            #resetButton{
                width: 100%;
                font-size: 1rem;
                margin: .5rem;
            }
        </style>
    </head>
    <body> 
       <jsp:useBean id="account" class="com.chattbank.Account" scope="session"></jsp:useBean>
        <div class="background-image">
            <h1 style="text-align: center; font-size: 2.5rem; padding: .5rem; opacity: .8; color: rgba(255, 255, 255, .78);">Account Look Up</h1>
            <div class="container">             
                <form name="accountLookupForm" action="http://localhost:8080/ChattBank/AccountLookUpServlet" method="post" onsubmit="return validateForm()">
<!--                 4 textboxes
                    account number
                    customer ID
                    type of account
                    account balance-->
                    
                    <label for="acctNoField">Account #:</label>
                    <input id="acctNoField" name="acctNoField" type="text" value="<jsp:getProperty name="account" property="acctNo"/>"><br>
                    <label for="custID-field">Customer ID:</label>
                    <input id="custIdField" name="custIdField" type="text" value="<jsp:getProperty name="account" property="custId"/>"><br>
                    <label for="acctType-field">Account Type:</label>
                    <input id="acctTypeField" name="acctTypeField" type="text" value="<jsp:getProperty name="account" property="type"/>"><br>
                    <label for="balanceField">Balance:</label>
                    <input id="balanceField" name="balanceField" type="number" value="<jsp:getProperty name="account" property="balance"/>"><br>
                    <input id="submitButton" name="submit" type="submit" value="Look Up"><br>
                    <input id="resetButton" name="reset" type="reset" value="Reset Form">
                </form>
                    <a style="display: block; text-align: center;"href="DisplayAccounts.jsp">View All Accounts</a>
            </div>
        </div>
        <script>
            function validateForm(){
                return (validateAccountNumber() && validateCustId());
            }
            
            function validateAccountNumber(){
                var accountNumber = document.getElementById("acctNoField").value;
                if(accountNumber === ""){
                    alert("Please enter a valid account number.");
                    return false;
                }
                if(isNaN(accountNumber)){
                    alert("The account number must be a number.");
                    return false;
                }
                return true;
            }
            
            function validateCustId(){
                var custId = document.getElementById("custIdField").value;
                
                if(custId === "") {
                    alert("Must enter a customer id");
                    return false;
                }
                if(isNaN(custId)){
                    alert("Customer ID must be a number");
                    return false;
                }
                if(custId < 3001 || custId > 3999){
                    alert("Customer ID must be a number between 3000 and 4000");
                    return false;
                }
                return true;
            }
            
        </script>
    </body>
</html>
