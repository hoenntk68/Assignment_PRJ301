<%-- 
    Document   : groupList
    Created on : Mar 19, 2023, 11:58:30 AM
    Author     : Hp
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
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
                background-color: #6b90da;
                color: #ffffff;
                text-shadow: .5px .5px #333333;
                font-weight: bold;
                font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
            }

            table tr:nth-child(1) {
                text-align: center;
                position: sticky;
                top: 0px;
                z-index: 1;
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

            a{
                text-decoration: none;
                font-weight: bold;
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
        <h1>${requestScope.groups.size()} group(s) found for ${sessionScope.user1.displayname} ${sessionScope.user1.username}</h1>
        <table>
            <tr class="table-head">
                <td>No</td>
                <td>Group name</td>
                <td>Course</td>
                <td>Instructor</td>
                <td>View report</td>
            </tr>
            <c:forEach items="${requestScope.groups}" var="group" varStatus="iGroup">
                <td>${iGroup.index + 1}</td>
                <td>${group.name}</td>
                <td>${group.course.id}</td>
                <td>${group.instructor.id}</td>
                <td>
                    <a href="viewAttendance?groupId=${group.id}">View attendance report</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
