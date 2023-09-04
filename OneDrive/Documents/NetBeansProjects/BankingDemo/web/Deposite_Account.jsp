<%-- 
    Document   : Deposite_Account
    Created on : 01-Sept-2023, 11:32:46 pm
    Author     : ersal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body style="background-color:lightsalmon;">
                <h1> Deposite Form </h1>

        
        <form method="post" action="Depostie_Account_Servlet" style="text-align:center;">
             <table border="1px " style="text-align:center;">
                <tr>
                    <td>
            
            Account_no<input type ="number" name="account_number" placeholder="Account Number"  required><br><br></tr>
                    </td>
            <tr>
                    <td>  Amount <input type ="text" name="amount" placeholder="Amount" required ><br><br></tr>
                    </td>

            
             <tr>
                    <td> <input type="submit" value="confirm"></tr>
                    </td>
             
             </table>
        </form>

    </body>
</html>
