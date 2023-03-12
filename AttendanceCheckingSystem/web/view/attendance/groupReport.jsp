<%-- 
    Document   : groupReport
    Created on : Mar 11, 2023, 9:50:29 PM
    Author     : Hp
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Group Attendance Report</title>
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

            input[type=checkbox]:disabled {
                background-color: green !important;
                color: green !important;
            }

        </style>
    </head>
    <body>
        <h1 style="text-align: center">Attendance report for group ${requestScope.group.name}</h1>
        <c:set var="noOfSession" value="${requestScope.sessions.size()}"></c:set>
        <c:set var="noOfStudent" value="${requestScope.students.size()}"></c:set>
<!--        <h2>There are ${noOfSession} sessions</h2>
        <h2>There are ${noOfStudent} students</h2>-->
        <table>
            <tr class="table-head">
                <td>No</td>
                <td class="name">Name</td>
                <td>Code</td>
                <td>Image</td>
                <td>Absent</td>
                <c:forEach items="${requestScope.sessions}" var="session" varStatus="i">
                    <td>
                        S${i.index + 1}
                    </td>
                </c:forEach>
            </tr>

            <c:forEach items="${requestScope.students}" var="student" varStatus="iStudent">
                <tr>
                    <td>${iStudent.index + 1}</td>
                    <td class="name">${student.name}</td>
                    <td>${student.id}</td>
                    <td>no image</td>
                    <td>
                        <c:forEach items="${requestScope.absent.entrySet()}" var="entry">
                            <c:if test="${entry.key eq student.id}">
                                ${entry.value}%
                            </c:if>
                        </c:forEach>
                    </td>
                    <c:forEach items="${requestScope.sessions}" var="session" varStatus="iSession">
                        <td>
                            <input type="checkbox" disabled="disabled"
                                   <c:forEach items="${requestScope.records}" var="record" varStatus="iRecord">
                                       <c:if test="${record.session.id eq session.id && record.student.id eq student.id && record.status}">
                                           checked="checked"
                                       </c:if>
                                   </c:forEach>
                                   />
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
