<%-- 
    Document   : reportForStudent.jsp
    Created on : Mar 18, 2023, 4:32:49 PM
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
        <title>JSP Page</title>
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
                background-color: #6b90da;
                color: #ffffff;
                text-shadow: .5px .5px #333333;
                font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
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

            .slot, .date{
                padding: 2px 5px;
                border-radius: 5px;
                color: white;
                font-weight: bold;
                font-size: 12px;
                margin: 0;
            }
            .slot{
                background-color: #d9534f;
            }
            
            .date{
                background-color: #337ab7;
                
            }
        </style>
    </head>
    <body>
        <form></form>
        <!--<h1>${requestScope.records.size()} sessions found.</h1>-->
        <table>
            <tr class="table-head">
                <td>No</td>
                <td>Date</td>
                <td>Slot</td>
                <td>Room</td>
                <td>Lecturer</td>
                <td>Group Name</td>
                <td>Attendance Status</td>
                <td>Lecturer's Comment</td>
            </tr>
            <c:forEach items="${requestScope.records}" var="record" varStatus="iRecord">
                <tr>
                    <td>${iRecord.index + 1}</td>
                    <td>
                        <p class="date">
                        <fmt:formatDate value="${record.session.date}" pattern="EEEE dd/MM/yyyy"/>
                        </p>
                            
                    </td>
                    <td>
                        <p class="slot">
                        ${record.session.timeslot.number} _
                        (<fmt:formatDate value="${record.session.timeslot.startTime}" pattern="hh:mm"/>
                        -
                        <fmt:formatDate value="${record.session.timeslot.endTime}" pattern="hh:mm"/>)
                        </p>
                            
                    </td>
                    <td>${record.session.room.id}</td>
                    <td>${record.session.instructor.id}</td>
                    <td>${record.session.group.name}</td>
                    <td>
                        <c:if test="${record.status}">
                            <p class="attend">Present</p>
                        </c:if>
                        <c:if test="${!record.status && !record.session.status}">
                            <p>Future</p>
                        </c:if>
                        <c:if test="${!record.status && record.session.status}">
                            <p class="absent">Absent</p>
                        </c:if>
                    </td>
                    <td>${record.comment}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
