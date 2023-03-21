<%-- 
    Document   : home
    Created on : Mar 21, 2023, 11:53:17 AM
    Author     : Hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>FPT Attendance Management System</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="icon" href="https://play-lh.googleusercontent.com/BFYTO8vhN2ZveSWA7XGoQVwei9cCvpi2je5eyDI2a1WoKxTjJJw5Sv8ULoQEGqAYo0g" type="image/x-icon">
        <style>
            body {
                background-color: #f5f5f5;
                font-family: Arial, sans-serif;
                /*                display: flex;
                                justify-content: center;
                                align-items: center;*/
                height: 100vh;
                background-image: url('https://scontent.fhan14-4.fna.fbcdn.net/v/t39.30808-6/310744903_6250747208288161_8821814777399000400_n.jpg?stp=cp6_dst-jpg&_nc_cat=102&ccb=1-7&_nc_sid=e3f864&_nc_ohc=JBv9KXBNAjQAX9WwPwC&_nc_ht=scontent.fhan14-4.fna&oh=00_AfC4KP6vEkyDSuKU62NhDbUzhJ4pOSuDzOU4Z5p0SbbU0A&oe=641B459B');
                background-position-y: center;
                background-size: cover;
                
            }

            div.menu {
                margin: 0 auto;
                background-color: #fff;
                display: flex;
                flex-direction: row;
                justify-content: space-between;
                width: 80%;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0px 2px 5px rgba(0,0,0,0.1);
            }

            .left {
                width: 45%;
            }

            .left a {
                display: block;
                margin-bottom: 10px;
                font-size: 18px;
                color: #333;
                text-decoration: none;
                border-bottom: 2px solid transparent;
                transition: border-bottom 0.2s ease-in-out;
            }

            .left a:hover {
                border-bottom: 2px solid #555;
                color: #555;
            }

            .right {
                width: 45%;
            }

            .right a {
                display: block;
                margin-bottom: 10px;
                font-size: 18px;
                color: #333;
                text-decoration: none;
                border-bottom: 2px solid transparent;
                transition: border-bottom 0.2s ease-in-out;
            }

            .right a:hover {
                border-bottom: 2px solid #555;
                color: #555;
            }

            /* Additional styles */
            .left a:first-of-type {
                color: #ff5a5f;
                font-weight: bold;
            }

            .right a:first-of-type {
                color: #00b894;
                font-weight: bold;
            }


            /* Navbar styles */
            .navbar {
                background-color: #f05123;
                color: #fff;
                display: flex;
                justify-content: space-between;
                align-items: center;
                padding: 10px 20px;
            }

            .navbar-logo {
                font-size: 24px;
                font-weight: bold;
                text-decoration: none;
                color: #fff;
            }

            .navbar-links {
                display: flex;
                justify-content: center;
                align-items: center;
            }

            .navbar-links a {
                margin-left: 20px;
                text-decoration: none;
                color: #fff;
                font-weight: bold;
                font-size: 18px;
            }

            .navbar-links a:hover {
                color: #eee;
                text-shadow: 1px 1px 1px #eee;
            }
        </style>


    </head>
    <body>
        <nav class="navbar">
            <a href="#" class="navbar-logo">FPT Attendance Tracking System</a>
            <div class="navbar-links">
                <a href="logout">Logout</a>
            </div>
        </nav>
        <br/>
        <div class="menu">
            <section class="left">
                <a href="#">Lecturer</a>
                <br/>
                <a href="instructor/weeklyTimetable?date=">View weekly schedule</a>
                <br/>
                <a href="instructor/groupList">View group report</a>
                <br/>
<!--                <a href="instructor/sessionAttendance?sessionId=1">View session report</a>
                <br/>
                <a href="instructor/attendanceTaking?sessionId=12">Take attendance</a>-->
            </section>
            <section class="left">
                <a href="#">Student</a>
                <a href="student/weeklyTimetable?date=">View student timetable</a>
                <br/>
                <a href="student/groupList">View group report</a>

            </section>
        </div>
    </body>
</html>
