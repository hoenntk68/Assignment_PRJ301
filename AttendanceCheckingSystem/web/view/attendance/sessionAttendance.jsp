<%-- 
    Document   : sessionAttendance
    Created on : Mar 12, 2023, 4:54:43 PM
    Author     : Hp
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Session Attendance Report</title>
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
        </style>

<!--        <script>
            const targetDate = new Date('2023-03-03T23:59:59'); // Set the target date and time
            const currentTime = new Date(); // Get current system time

            if (currentTime > targetDate) {
                // If current time exceeds target date and time, disable all input tags
                const inputs = document.querySelectorAll('input');
                inputs.forEach(input => {
                    input.disabled = true;
                });
            }

        </script>-->
    </head>
    <body>
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
        <c:if test="${requestScope.records ne null}">
            <h1>There are ${requestScope.records.size()} records</h1>
        </c:if>
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
                    <td>no image</td>
                    <td
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
                    </td>
<!--                    <td>
                        <input type="radio" value="present" class="attend"
                               <c:if test="${record.status}">
                                   checked="checked"
                               </c:if>
                               />
                        <label class="attend">Present</label>
                        <br/>
                        <input type="radio" value="absent" class="absent"
                               <c:if test="${!record.status}">
                                   checked="checked"
                               </c:if>
                               />
                        <label class="absent">Absent</label>
                    </td>-->
                    <td>no comment</td>
                    <td>${session.instructor.id}</td>
                    <td>${record.recordTime}</td>
                </tr>
            </c:forEach>
        </table>

    </body>
</html>
