<%-- 
    Document   : login
    Created on : Feb 27, 2023, 2:24:44 AM
    Author     : Hp
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="icon" href="https://play-lh.googleusercontent.com/BFYTO8vhN2ZveSWA7XGoQVwei9cCvpi2je5eyDI2a1WoKxTjJJw5Sv8ULoQEGqAYo0g" type="image/x-icon">
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f5f5f5;
                margin: 0;
                padding: 0;
                background-image: url('https://i1-vnexpress.vnecdn.net/2021/02/20/DH-FPT-4359-1612093890-5647-1613785573.jpg?w=0&h=0&q=100&dpr=2&fit=crop&s=gQK2V-7CxACvJ6jG6kmv1Q');
                background-repeat: no-repeat;
                background-size: cover;
            }

            .container {
                margin: 50px auto;
                width: 400px;
                background-color: #fff;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.3);
            }

            h1 {
                text-align: center;
                margin-bottom: 20px;
            }

            label {
                display: block;
                font-size: 16px;
                margin-bottom: 5px;
                font-weight: bold;
            }

            input[type="text"],
            input[type="password"] {
                padding: 10px;
                font-size: 16px;
                border-radius: 5px;
                border: 2px solid black;
                margin-bottom: 20px;
                width: 100%;
                box-sizing: border-box;
            }

            button {
                background-color: #4CAF50;
                color: #fff;
                padding: 10px 20px;
                border: none;
                border-radius: 5px;
                font-size: 16px;
                cursor: pointer;
            }

            button:hover {
                background-color: #3e8e41;
            }

            .error {
                color: #ff0000;
                margin-bottom: 10px;
            }

            .error-message {
                background-color: #f8d7da;
                border: 1px solid #f5c6cb;
                border-radius: 5px;
                color: #721c24;
                padding: 5px 10px;
                margin-bottom: 10px;
                box-shadow: 5px 5px #f5c6cb;
            }

        </style>
    </head>
    <body>
        <h1 style="margin-top: 50px;">FPT Attendance System</h1>

        <div class="container">
            <h1>Login</h1>
            <form action="login" method="post">
                <label for="username">Username</label>
                <input type="text" id="username" name="username" required>

                <label for="password">Password</label>
                <input type="password" id="password" name="password" required>

                <c:if test="${requestScope.loginFailed}">
                    <div class="error-message">
                        <p>Incorrect username or password. Please try again.</p>
                    </div>
                </c:if>

                <button type="submit">Login</button>
            </form>
        </div>
    </body>
</html>
