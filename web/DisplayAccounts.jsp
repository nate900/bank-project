<%-- 
    Document   : DisplayAccounts
    Created on : Mar 5, 2022, 6:56:08 PM
    Author     : Josiah Martin
    Lab        : 8
    Due        : 7 March 2022
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--Add necessary import-->
<%@page import="com.chattbank.Customer, com.chattbank.AccountList, com.chattbank.Account"%>
<%
  //Get the customer out of the session
    Customer customer = (Customer)session.getAttribute("customer");
    AccountList accounts = customer.getCustomerAccounts();
    //Use this object later in the loop that will display all the customer accounts
    Account currentAccount;
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Display Accounts</title>
        <link rel="stylesheet" href="css/web-fonts.css">
        <link rel="stylesheet" href="css/display-accounts.css">
    </head>
    <body>
        <div class="container">
            <h2 id="account-custi-id" style="text-align: center;">Customer ID: <%=customer.getCustId()%><br>Welcome <%=customer.getCustFirstName() + " " + customer.getCustLastName()%></h2>
            <table clas="grid">
                <h2 style="text-align: center;">Your Accounts</h2>
                <%for(int i = 0; i < accounts.getSize(); i++){currentAccount = accounts.getElement(i);%>
                <td class="grid-item">
                    <h3 id="account-number">Account Number: <%=currentAccount.getAcctNo()%></h3>
                    <h3 id="account-type">Account Type: <%=currentAccount.getType()%></h3>
                    <h3 id="account-balance">Account Balance: <%=currentAccount.getBalance()%></h3>
                </td>
                <%}%>
            </table>
        </div>
    </body>
</html>
