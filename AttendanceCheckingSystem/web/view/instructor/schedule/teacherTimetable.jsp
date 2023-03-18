<%-- 
    Document   : schedule
    Created on : Mar 3, 2023, 5:20:13 PM
    Author     : Hp
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

            .not-yet{
                color: red;
            }

            .take-attend{
                text-decoration: none;
                background-color: #FFA500;
                margin: 1px 0;
            }

        </style>
    </head>
    <body>
        <h1 style="text-align: center">
            Weekly Timetable
        </h1>
        <h1 style="text-align: center">
        </h1>
        <br/>
        <div style="text-align: center; margin-bottom: 10px">
            <c:set var="user" value="${requestScope.user}"></c:set>
            Lecturer: <input type="text" value="${user.username}" disabled="disabled"/>
        </div>


        <c:set var="currentDate" value="${requestScope.date}"></c:set>
            <table>
                <tr class="table-head">
                    <td>
                        <form action="weeklyTimetable">
                            <input type="date" name="date" value="${requestScope.date}"/>
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
                            <c:forEach items="${requestScope.sessions}" var="session">
                                <c:if test="${session.getDayOfWeek() % 8 eq dayStatus.index && session.timeslot.number eq slotStatus.index}">
                                    <p style="font-weight: bold; display: inline-block">${session.group.course.id} at ${session.room.id}</p>
                                    <p
                                        <c:if test="${session.status}">
                                            class="attended"
                                        </c:if>
                                        <c:if test="${!session.status}">
                                            class="not-yet"
                                        </c:if>
                                        >
                                        <c:if test="${session.status && requestScope.today != session.date}">
                                            (Attended)
                                            <a class="slot take-attend" href="sessionAttendance?sessionId=${session.id}">View attendance</a>
                                        </c:if>
                                        <c:if test="${!session.status}">
                                            (Not yet)
                                        </c:if>
                                        <c:if test="${requestScope.today == session.date}">
                                            <br/>
                                            <a class="slot take-attend" href="attendanceTaking?sessionId=${session.id}">Take attendance</a>
                                        </c:if>
                                    </p>
                                    <p class="slot group">${session.group.name}</p>
                                    <p class="slot"><fmt:formatDate type="time" pattern="HH:mm" value="${session.timeslot.startTime}"/> - <fmt:formatDate type="time" pattern="HH:mm" value="${session.timeslot.endTime}"/></p>
                                </c:if> 
                            </c:forEach>

                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
