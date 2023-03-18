<%-- 
    Document   : accessDenied
    Created on : Mar 18, 2023, 1:18:15 AM
    Author     : Hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Access Denied</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f2f2f2;
                padding: 50px;
                text-align: center;
            }
            h1 {
                font-size: 36px;
                margin-bottom: 20px;
                color: #333;
            }
            p {
                font-size: 18px;
                line-height: 1.5;
                margin-bottom: 40px;
                color: #666;
            }
            .button {
                display: inline-block;
                padding: 12px 24px;
                font-size: 18px;
                font-weight: bold;
                background-color: #007bff;
                color: #fff;
                border-radius: 4px;
                text-decoration: none;
                transition: all 0.5s ease;
            }
            .button:hover {
                background-color: #0056b3;
            }

            @keyframes fade-in {
                from {
                    opacity: 0;
                }
                to {
                    opacity: 1;
                }
            }

            @keyframes zoom-in {
                from {
                    transform: scale(0.8);
                }
                to {
                    transform: scale(1);
                }
            }

            h1 {
                animation: fade-in 3s;
            }

            .button {
                animation: zoom-in 1.5s;
            }
            
            div{
                margin-top: 25vh;
                transform: translateY(0);
            }

        </style>
    </head>
    <body>
        <div>
            <h1>Access Denied</h1>
            <p>We're sorry, but you do not have permission to access this page.</p>
            <a href="/AttendanceCheckingSystem" class="button">Return to Homepage</a>
        </div>
    </body>
</html>
