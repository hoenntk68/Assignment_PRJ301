<%-- 
    Document   : studentTimetable.jsp
    Created on : Mar 17, 2023, 11:35:45 PM
    Author     : Hp
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<fmt:setLocale value="en_US" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lecturer Timetable</title>
        <style>


            * {
                box-sizing: border-box;
                /* padding: 0; */
            }

            table td {
                border: 1px solid black;
                border-collapse: collapse;
                width: 200px;
                height: 100px;
            }

            .slot{
                box-sizing: border-box;
                background-color: #5cb85c;
                display: inline-block;
                color: #fff;
                padding: 2px 5px;
                border-radius: 5px;
                font-weight: bold;
            }

            .group{
                background-color: #337ab7;
            }

            .table-head{
                text-align: center;
                background-color: #6b90da;
                color: #ffffff;
                text-shadow: .5px .5px #333333;
                font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
            }

            input[type="submit"] {
                background-color: #FFA500;
                border: none;
                color: white;
                padding: 6px 12px;
                display: inline-block;
                font-size: 14px;
                margin: 4px 2px;
                cursor: pointer;
                border-radius: 4px;
                /*transition: background-color 0.3s ease-in-out;*/
                position: relative;
                overflow: hidden;
            }

            input[type="submit"]:hover{
                transform: scaleX(1.05);
            }

            p{
                margin: 3px;
            }

            .attended{
                color: green;
                margin: 1px
            }

            .not-yet, .absent{
                color: red;
            }

            .take-attend{
                text-decoration: none;
                background-color: #FFA500;
                margin: 1px 0;
            }

            /*NAVBAR*/

            .navbar {
                background-color: #f05123;
                color: #fff;
                display: flex;
                justify-content: space-between;
                align-items: center;
                padding: 10px 20px;
                margin-bottom: 50px;
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
            /* Scroll to top button */
            .scroll-to-top-btn {
                /* display: none; */
                /* Hide button by default */
                position: fixed;
                bottom: 20px;
                right: 30px;
                z-index: 99;
                font-size: 18px;
                border: none;
                outline: none;
                background-color: #4CAF50;
                color: white;
                cursor: pointer;
                padding: 5px;
                border-radius: 50%;
                width: 50px;
                height: 50px;
                text-align: center;
            }

            /* Show button when user starts scrolling */
            .scroll-to-top-btn.show {
                display: block;
            }

            /* On hover, change background color and add box-shadow */
            .scroll-to-top-btn:hover {
                background-color: #555;
                box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.3);
            }

        </style>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css" integrity="sha512-SzlrxWUlpfuzQ+pcUCosxcglQRNAq/DZjVsC0lE40xsADsfeQoEypE+enwcOiGjk/bSuGGKHEyjSoQ1zVisanQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <script>
            // Get the button
            let mybutton = document.getElementById("myBtn");

            // When the user scrolls down 20px from the top of the document, show the button
            window.onscroll = function () {
                scrollFunction()
            };

            function scrollFunction() {
                if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
                    mybutton.style.display = "block";
                } else {
                    mybutton.style.display = "none";
                }
            }

            // When the user clicks on the button, scroll to the top of the document
            function topFunction() {
                document.body.scrollTop = 0;
                document.documentElement.scrollTop = 0;
            }
        </script>    </head>
    <body>
        <button class="scroll-to-top-btn" onclick="topFunction()" id="myBtn" title="Go to top"><i class="fa-solid fa-arrow-up"></i></button>

        <nav class="navbar">

            <a href="/AttendanceCheckingSystem" class="navbar-logo"><i class="fa-solid fa-house-chimney"></i></a>
            <div class="navbar-links">
                <a href="../logout">Logout</a>
            </div>
        </nav>  

        <c:set var="user" value="${sessionScope.user1}"></c:set>
        <c:set var="currentDate" value="${requestScope.date}"></c:set>
            <h1 style="text-align: center">
                Weekly Timetable
            </h1>
            <h1 style="text-align: center">
            </h1>
            <br/>
            <div style="text-align: center; margin-bottom: 10px">
                Student: <input type="text" value="${user1.username}" disabled="disabled"/>
        </div>


        <table>
            <tr class="table-head">
                <td>
                    <form action="weeklyTimetable">
                        <input type="date" name="date" value="${currentDate}"/>
                        <br/>
                        <input type="submit" value="View schedule"/>
                    </form>
                </td>
                <c:forEach items="${requestScope.dates}" var="d">
                    <td style="font-weight: bold;">
                        <fmt:formatDate value="${d}" pattern="EEEE"/>
                        <br/>
                        <fmt:formatDate value="${d}" pattern="dd/MM"/>
                    </td>
                </c:forEach>
            </tr>
            <c:forEach begin="1" end="5" var="slot" varStatus="slotStatus">
                <tr>
                    <td style="padding-left: 10px">Slot ${slot}</td>
                    <c:forEach var="day" begin="2" end="8" varStatus="dayStatus">
                        <td style="padding: 0 10px">
                            <c:forEach items="${requestScope.attendances}" var="attendance">
                                <c:if test="${attendance.session.getDayOfWeek() % 8 eq dayStatus.index && attendance.session.timeslot.number eq slotStatus.index}">
                                    <p style="font-weight: bold; display: inline-block">${attendance.session.group.course.id} at ${attendance.session.room.id}</p>
                                    <p
                                        <c:if test="${attendance.status}">
                                            class="attended"
                                        </c:if>
                                        <c:if test="${!attendance.status && attendance.firstTaken == 0}">
                                            class="not-yet"
                                        </c:if>
                                        <c:if test="${!attendance.status && attendance.firstTaken != 0}">
                                            class="absent"
                                        </c:if>
                                        >
                                        <c:if test="${attendance.status}">
                                            (Attended)
                                        </c:if>
                                        <c:if test="${!attendance.status && attendance.firstTaken == 0}">
                                            (Not yet)
                                        </c:if>
                                        <c:if test="${!attendance.status && attendance.firstTaken != 0}">
                                            (Absent)
                                        </c:if>
                                    </p>
                                    <!--<p class="slot group">${attendance.session.group.name}</p>-->
                                    <p class="slot"><fmt:formatDate type="time" pattern="HH:mm" value="${attendance.session.timeslot.startTime}"/> - <fmt:formatDate type="time" pattern="HH:mm" value="${attendance.session.timeslot.endTime}"/></p>
                                </c:if> 
                            </c:forEach>

                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
