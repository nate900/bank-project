
<%@page import="com.chattbank.Customer"%>
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
                    Error looking up account for user with ID <%=custId%>.
                </h1>
                <p>
                    You could have mistyped your account number and/or customer ID. Please try again with more caution. Thank you.
                </p>
                <a href="accountLookUp.html">Click here to look up your account again</a>
            </article>
        </div>
    </body>
</html>
