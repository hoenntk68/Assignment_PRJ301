<%-- 
    Document   : groupReport
    Created on : Mar 11, 2023, 9:50:29 PM
    Author     : Hp
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Attendance By Group</title>
        <link rel="icon" href="https://play-lh.googleusercontent.com/BFYTO8vhN2ZveSWA7XGoQVwei9cCvpi2je5eyDI2a1WoKxTjJJw5Sv8ULoQEGqAYo0g" type="image/x-icon">
        <style>

            .attend{
                color: rgb(0, 128, 0);
            }

            .absent{
                color: red;
            }

            table {
                /* width: 100vw; */
                table-layout: auto;
                margin: 0 auto;
            }

            table td {
                border: 1px solid black;
                border-collapse: collapse;
                padding: 20px 10px;
                padding: 5px;
            }

            table tr td:first-child{
                padding-left: 1%;
                text-align: left;
                /*max-width: 20px;*/
            }

            table .table-head td{
                text-align: center;
                padding: 10px;
                background-color: cadetblue;
                color: white;
            }

            table tr:nth-child(1) {
                text-align: center;
                position: sticky;
                top: 0px;
                z-index: 1;
            }

            table tr td.name {
                /*width: 100px;*/
            }

            /*            table tr td:nth-child(1){
                            max-width: 50px;
                        }*/

            /*            table tr td:nth-child(n+2):nth-child(-n+3) {
                            width: 10%;
                        }
            
                        table td:nth-child(n+5):nth-child(-n+24){
                            max-width: 10px;
                        }*/

            td img {
                margin-left: 50%;
                transform: translateX(-50%);
                /*width: 90%;*/
                border-radius: 5px;
                border: 1px solid grey;
            }

            button.clickable {
                display: block;
                padding: 1px 5px;
                margin: 0 auto;
                /* margin-bottom: 5px; */
            }

            button.clickable:hover {
                cursor: pointer;
                color: beige;
                background-color: black;
            }

            input.isPresent{
                margin-left: 50%;
                transform: translateX(-50%);
            }

            .absentPercentage{
                text-align: center;
            }

            /*            input[type=checkbox]:disabled {
                            background-color: green !important;
                            color: green !important;
                        }*/

            td img {
                margin-left: 50%;
                transform: translateX(-50%);
                max-width: 100px;
                border-radius: 5px;
                border: 1px solid grey;
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
        </script>
    </head>
    <body>
        <button class="scroll-to-top-btn" onclick="topFunction()" id="myBtn" title="Go to top"><i class="fa-solid fa-arrow-up"></i></button>


        <nav class="navbar">

            <a href="/AttendanceCheckingSystem" class="navbar-logo"><i class="fa-solid fa-house-chimney"></i></a>
            <div class="navbar-links">
                <a href="../logout">Logout</a>
            </div>
        </nav> 

        <h1 style="text-align: center">Attendance report for group ${requestScope.group.name}</h1>
        <c:set var="noOfSession" value="${requestScope.sessions.size()}"></c:set>
        <c:set var="noOfStudent" value="${requestScope.students.size()}"></c:set>  
            <table>
                <tr class="table-head">
                    <td style="padding: 15px">No</td>
                    <td class="name" style="min-width: 150px">Name</td>
                    <td>Code</td>
                    <td>Image</td>
                    <td>Absent</td>
                <c:forEach items="${requestScope.sessions}" var="session" varStatus="iSession">
                    <td>
                        <a 
                            href="sessionAttendance?sessionId=${session.id}"
                            style="color: white; text-shadow: 1px 1px 1px #eee"
                            >S${iSession.index + 1}</a>

                        <br/>
                        <fmt:formatDate value="${session.date}" pattern="dd/MM"></fmt:formatDate>
                        </td>
                </c:forEach>
            </tr>

            <c:forEach items="${requestScope.students}" var="student" varStatus="iStudent">
                <tr>
                    <td>${iStudent.index + 1}</td>
                    <td style="min-width: 150px" class="name">${student.name}</td>
                    <td>${student.id}</td>
                    <td>
                        <img src="https://i0.wp.com/www.mnleadership.org/wp-content/uploads/2017/02/Anonymous-Avatar.png?ssl=1" alt="${record.student.id}"/>
                    </td>
                    <td>
                        <c:forEach items="${requestScope.absent.entrySet()}" var="entry">
                            <c:if test="${entry.key eq student.id}">
                                ${entry.value}%
                            </c:if>
                        </c:forEach>
                    </td>
                    <c:forEach items="${requestScope.sessions}" var="session" varStatus="iSession">
                        <!--                        <td>
                                                    <input type="checkbox" disabled="disabled"
                        <%--<c:forEach items="${requestScope.records}" var="record" varStatus="iRecord">
                                                               <c:if test="${record.session.id eq session.id && record.student.id eq student.id && record.status}">
                                                                   checked="checked"
                                                               </c:if>
                                                           </c:forEach>--%>
                        />
             </td>-->
                        <td>
                            <c:forEach items="${requestScope.records}" var="record" varStatus="iRecord">
                                <c:if test="${record.session.id eq session.id && record.student.id eq student.id}">
                                    <c:if test="${record.status}">
                                        <p class="attend">P</p>
                                    </c:if>
                                    <c:if test="${!record.status && record.session.status}">
                                        <p class="absent">A</p>
                                    </c:if>
                                    <c:if test="${!record.status && !record.session.status}">
                                        <p class="not-yet">-</p>
                                    </c:if>
                                </c:if>
                            </c:forEach>
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>
        </table>

        <div>
            <p style="font-weight: bold">Note</p>
            <ul>
                <li>
                    <p><span class="attend">attend</span>: the student had attended this session</p>
                </li>
                <li>
                    <p><span class="absent">absent</span>: the student had NOT attended this session</p>
                </li>
                <li>
                    <p><span>-</span>: no data was given</p>
                </li>
            </ul>
        </div>
    </body>
</html>
