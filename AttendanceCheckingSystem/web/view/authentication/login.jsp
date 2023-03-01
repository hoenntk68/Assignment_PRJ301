<%-- 
    Document   : login
    Created on : Feb 27, 2023, 2:24:44 AM
    Author     : Hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <form action="login" method="post">
            <label for="username">Username: </label>
            <input type="text" id="username" name="username" required/>
            <br/>
            <label for="password">Password: </label>
            <input type="text" id="password" name="password" required/>
            
            <br/>  
            <input type="submit" value="Login"></button>
            
        </form>
    </body>
</html>
