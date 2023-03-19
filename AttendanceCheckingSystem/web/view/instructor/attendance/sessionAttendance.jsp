<%-- 
    Document   : sessionAttendance
    Created on : Mar 12, 2023, 4:54:43 PM
    Author     : Hp
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<fmt:setLocale value="en_US" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Session Attendance Report</title>
        <link rel="icon" href="https://play-lh.googleusercontent.com/BFYTO8vhN2ZveSWA7XGoQVwei9cCvpi2je5eyDI2a1WoKxTjJJw5Sv8ULoQEGqAYo0g" type="image/x-icon">
        <style>
            h1, h3{
                text-align: center;
            }
            h3{
                color: cadetblue;
            }
            table{
                margin: 0 auto;
                table-layout: auto;
            }
            table td{
                table-layout: auto;
                border: 1px solid black;
                border-collapse: collapse;
                padding: 10px 20px;
                /*max-width: 25px;*/
            }

            table td:hover{
                background-color: aliceblue;
            }
            table .table-head td{
                text-align: center;
                padding: 0; /*overwrite*/
                background-color: cadetblue;
                color: white;
            }

            table tr td:first-child{
                padding-left: 1%;
                text-align: left;
                /*max-width: 20px;*/
            }

            table tr:first-child{
                z-index: 1;
                position: sticky;
                top: 0;
            }

            button.clickable {
                display: block;
                padding: 1px 5px;
                margin: 0 auto;
            }

            button.clickable:hover {
                cursor: pointer;
                color: beige;
                background-color: black;
            }

            .attend{
                color:rgb(0, 184, 0);
            }

            .absent{
                color: red;
            }

            td img {
                margin-left: 50%;
                transform: translateX(-50%);
                max-width: 100px;
                border-radius: 5px;
                border: 1px solid grey;
            }

            #submitAttendance {
                display: block;
                margin: 20px auto;
                background-color: cadetblue;
                color: white;
                padding: 5px 20px;
                border-radius: 15px;
                border: none;
            }

            #submitAttendance:hover {
                cursor: pointer;
                background-color: white;
                color: cadetblue;
                border: 1px solid black;
            }

            input[name*="comment"]{
                height: 100px;
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

        </style>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css" integrity="sha512-SzlrxWUlpfuzQ+pcUCosxcglQRNAq/DZjVsC0lE40xsADsfeQoEypE+enwcOiGjk/bSuGGKHEyjSoQ1zVisanQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>
    <body>

        <nav class="navbar">

            <a href="/AttendanceCheckingSystem" class="navbar-logo"><i class="fa-solid fa-house-chimney"></i></a>
            <div class="navbar-links">
                <a href="../logout">Logout</a>
            </div>
        </nav> 

        <h1 style="text-align: center">View attendance for session</h1>
        <c:set var="session" value="${requestScope.session}"></c:set>
        <!--<h1>Attendance record of ${session.group.name}</h1>-->
        <h3>Lecturer: ${session.instructor.id}</h3>
        <h3>Subject: ${session.group.course.id}</h3>
        <h3>Student group: ${session.group.name}</h3>
        <h3>Room: ${session.room.id}</h3>
        <h3>
            On: 
            <fmt:formatDate value="${session.date}" pattern="EEEE dd/MM/yyyy"/>
        </h3>
        <!-- <c:if test="${requestScope.records ne null}">
             <h1>There are ${requestScope.records.size()} records</h1>
        </c:if> -->
        <c:choose>
            <c:when test="${requestScope.records.size() == 0}">
                <div style="text-align: center">
                    <h1>
                        No information for this session.
                    </h1>
                </div>
            </c:when>
            <c:otherwise>
                <form method="post">
                    <input type="hidden" value=""/>
                    <table>
                        <tr class="table-head">
                            <td>No</td>
                            <td>Group</td>
                            <td>Code</td>
                            <td>Name</td>
                            <td>Image</td>
                            <td>Status</td>
                            <td>Comment</td>
                            <td>Taker</td>
                            <td>Record time</td>
                        </tr>
                        <c:forEach items="${requestScope.records}" var="record" varStatus="iRecord">
                            <tr>
                                <td>${iRecord.index + 1}</td>
                                <td>${session.group.name}</td>
                                <td>${record.student.id}</td>
                                <td>${record.student.name}</td>
                                <td>
                                    <img src="https://i0.wp.com/www.mnleadership.org/wp-content/uploads/2017/02/Anonymous-Avatar.png?ssl=1" alt="${record.student.id}"/>
                                </td>
                                <!--                        <td
                                                            class="
                                <c:if test="${record.status}">
                                    attend
                                </c:if>
                                <c:if test="${!record.status}">
                                    absent
                                </c:if>
                                "
                                >
                                <c:if test="${record.status}">
                                    Present
                                </c:if>
                                <c:if test="${!record.status}">
                                    Absent
                                </c:if>
                            </td>-->
                                <td>
                                    <input 
                                        type="hidden" 
                                        value="${record.student.id}" 
                                        name="student${iRecord.index}"
                                        />
                                    <input 
                                        type="radio" 
                                        value="present" 
                                        class="attend" 
                                        name="status${iRecord.index}" 
                                        value="attended"
                                        disabled="disabled"
                                        <c:if test="${record.status}">
                                            checked="checked"
                                        </c:if>
                                        />
                                    <label class="attend">Present</label>
                                    <br/>
                                    <input 
                                        type="radio" 
                                        value="absent" 
                                        class="absent" 
                                        name="status${iRecord.index}" 
                                        value="absent"
                                        disabled="disabled"
                                        <c:if test="${!record.status}">
                                            checked="checked"
                                        </c:if>
                                        />
                                    <label class="absent">Absent</label>
                                </td>
                                <td>
                                    <input type="text" value="${record.comment}" disabled="disabled"/>
                                </td>
                                <td>${session.instructor.id}</td>
                                <td>${record.recordTime}</td>
                            </tr>
                        </c:forEach>
                    </table>
                    <!--<input id="submitAttendance" type="submit" value="Save Attendance">-->
                </form>
            </c:otherwise>
        </c:choose>
    </body>
</html>
