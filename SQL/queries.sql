use PRJ301_TakeAttendanceSystem


/*
---------------------------------GET WEEKLY SCHEDULE---------------------------------
This procedure returns information about an instructor's schedule in a given week.
It takes 2 parameters: the date on which the week start and the instructor's ID
*/
go
create proc getWeeklySchedule
@startWeek date,
@instructorId varchar(20)
as
begin
	select sessionId, sessionName, date, slotNumber, startTime, endTime, instructorId,roomId, groupName, courseId, s.status
	from Session s 
	join [Group] g on s.groupId = g.groupId
	join TimeSlot ts on ts.slotId = s.slotId
	where 
		s.date between @startWeek and DATEADD(day, 6, @startWeek)
		and g.instructorId = @instructorId
	order by s.date
end
--drop proc getWeeklySchedule
--exec getWeeklySchedule '2023-3-20', 'sonnt5'


/*
---------------------------------GET SESSION REPORT---------------------------------
This procedure returns information about a session's attendance information.
It takes 1 parameters: the id of the session
*/ 
go
create proc getSessionReport
@sessionId int
as
begin
	select distinct stu.studentId, g.groupId, g.groupName, stu.studentName, stu.studentImage, a.status, a.comment, a.recordTime
	from Attend a join Session ses on a.sessionId = ses.sessionId
	join Student stu on a.studentId = stu.studentId
	join [Group] g on ses.groupId = g.groupId
	where ses.sessionId = @sessionId
end
--drop proc getSessionReport
--exec getSessionReport 2



/*
---------------------------------GET SESSION DETAIL---------------------------------
This procedure returns information about a session with given Id.
It takes 1 parameters: the id of the session
*/ 
go
create proc getSessionInfo
@sessionId int
as
begin
	select s.*, c.courseId, ts.slotNumber, g.groupName from Session s
	join TimeSlot ts on s.slotId = ts.slotId
	join [Group] g on s.groupId = g.groupId
	join Course c on g.courseId = c.courseId
	where s.sessionId = @sessionId
end
--exec getSessionInfo 2
--drop proc getSessionInfo


/*
---------------------------------GET GROUP REPORT---------------------------------
This procedure returns information about a group's attendance information.
It takes 1 parameters: the id of the session
*/ go
create proc getGroupReport
@groupId int
as
begin
	declare @absent int
	select a.sessionId, a.studentId, stu.studentName, stu.studentImage, a.status
	from Attend a join Session s on a.sessionId = s.sessionId
	join Student stu on a.studentId = stu.studentId
	group by a.sessionId, s.groupId, a.studentId, stu.studentName, stu.studentImage, a.status
	having s.groupId = @groupId
end

--drop proc getGroupReport
--exec getGroupReport 15

/*
---------------------------------GET STUDENTS FROM GROUPID---------------------------------
This procedure returns a list of students from given groupId
*/
go 
create proc getStudentsFromGroup
@groupId int
as
begin
	select s.studentId, s.studentImage, s.studentName
	from Student s
	join Participate p on s.studentId = p.studentId
	join [Group] g on g.groupId = p.groupId
	where p.groupId = @groupId
end

-- drop proc getStudentsFromGroup
-- exec getStudentsFromGroup 15

/*
---------------------------------GET STUDENTS FROM SESSIONID---------------------------------
This procedure returns a list of students from given sessionId
*/
go 
create proc getStudentsFromSession
@sessionId int
as
begin
	select s.studentId, s.studentImage, s.studentName
	from Student s
	join Participate p on s.studentId = p.studentId
	join [Group] g on g.groupId = p.groupId
	join Session ses on ses.groupId = g.groupId
	where ses.sessionId = @sessionId
end

-- drop proc getStudentsFromSession
-- exec getStudentsFromSession 12

/*
---------------------------------GET REPORT FROM SESSIONID---------------------------------
Show attendance record from a given sessionId
*/

/*go 
create proc getSessionReport
@sessionId int
as
begin
	select s.studentId, s.studentImage, s.studentName, a.status
	from Student s join Attend a on s.studentId = a.studentId
	join Session ses on a.sessionId = ses.sessionId
	where ses.sessionId = @sessionId
end*/

-- drop proc getSessionReport
-- exec getSessionReport 40

/*
---------------------------------GET GROUP FROM GROUPID---------------------------------
Return groupName from given groupId
*/
go
create proc getGroup
@groupId int
as 
begin
select * from [Group] g where g.groupId = @groupId
end

-- drop proc getGroup
-- exec getGroup 10


select count(s.sessionId) as noOfSessions, s.groupId, g.courseId
from Session s join [Group] g on s.groupId = g.groupId
where g.groupId in (10, 11, 12, 13, 14, 15)
group by s.groupId, g.courseId

/*
---------------------------------GET NUMBER OF SESSIONS FROM GROUPID---------------------------------
Return groupName from given groupId
*/
go
create proc getSessionCount
@groupId int
as 
begin
	select count(s.sessionId) as noOfSessions, s.groupId, g.courseId
	from Session s join [Group] g on s.groupId = g.groupId
	where g.groupId = @groupId
	group by s.groupId, g.courseId
end

/*
---------------------------------GET NUMBER OF STUDENTS FROM GROUPID---------------------------------
Return groupName from given groupId
*/
go
create proc getStudentCount
@groupId int
as 
begin
	select g.groupId, g.groupName, g.courseId, count(s.studentId) as noOfStudents
	from Student s join Participate p on s.studentId = p .studentId
	join [Group] g on g.groupId = p.groupId
	where g.groupId = @groupId
	group by g.groupId, g.groupName, g.courseId
end

-- drop proc getStudentCount
-- exec getStudentCount 15

/*
---------------------------------GET SESSIONS FROM GROUPID---------------------------------
Return groupName from given groupId
*/
go
create proc getSessionsFromGroup
@groupId int
as 
begin
	select s.sessionId, s.date, s.groupId
	from Session s
	where s.groupId = @groupId
end

-- drop proc getSessionsFromGroup
-- exec getSessionsFromGroup 11


/*
---------------------------------GET ABSENT PERCENTAGE FROM GROUPID & STUDENTID---------------------------------
Return groupName from given groupId
*/
go
create proc getAbsentCount
@groupId int
as 
begin
	select s.studentId, sum(1 - CAST(a.status as int)) as absentCount
	from Student s left join Attend a on s.studentId = a.studentId
	left join Session ses on a.sessionId = ses.sessionId
	where ses.groupId = @groupId  
	group by s.studentId
end

-- drop proc getAbsentCount
-- exec getAbsentCount 15

/*
---------------------------------GET POSSIBLE URLS FROM USERID---------------------------------
*/
go 
create proc getUserPossibleUrls
@userId varchar(50),
@url varchar(50)
as
begin
	select u.username, hr.roleId, r.roleName, f.featureId, f.featureName, f.featureUrl
	from [User] u join HasRole hr on u.username = hr.userId
	join Role r on hr.roleId = r.roleId
	join MapRoleFeature mrf on hr.roleId = mrf.roleId
	join Feature f on mrf.featureId = f.featureId
	--group by u.username, hr.roleId, r.roleName, f.featureId, f.featureName, f.featureUrl
	where u.username = @userId and f.featureUrl = @url
end

-- drop proc getUserPossibleUrls
-- exec getUserPossibleUrls 'sonnt5', '/instructor/weeklyTimetable'


/*
---------------------------------GET ATTENDANCE BY SESSION ID---------------------------------

---------------------------------SET STATUS TO 0 AND COMMENT TO EMPTY STRING---------------------------------
*/
go 
create proc getAttendanceBySessionId
@sessionId int
as
begin
	SELECT 
		stu.studentId, 
		stu.studentName, 
		ISNULL(a.status, 0) as status, 
		ISNULL(a.comment, '') as comment,
		ISNULL(a.firstTaken, 0) as firstTaken
	FROM Student stu 
		JOIN [Participate] p on stu.studentId = p.studentId
		JOIN [Group] g on g.groupId = p.groupId
		JOIN [Session] ses on ses.groupId = g.groupId
		LEFT JOIN Attend a on a.studentId = stu.studentId AND a.sessionId = ses.sessionId
	WHERE ses.sessionId = @sessionId
end

-- drop proc getAttendanceBySessionId
-- exec getAttendanceBySessionId 12


/*
---------------------------------GET WEEKLY TIMETABLE FOR GIVEN STUDENT ID---------------------------------
*/

go
create proc getStudentTimetable
@studentId varchar(8),
@startWeek date
as
begin
	select 
		stu.studentId, stu.studentName,
		g.groupId, g.groupName, g.courseId, g.instructorId, 
		ses.sessionId, ses.date, ses.slotId, ses.status as sessionStatus,
		ts.slotNumber, ts.startTime, ts.endTime,
		r.roomId,
		ISNULL(a.status, 0) as attendStatus, ISNULL(a.firstTaken, 0) as firstTaken
	from Student stu
		join Participate p on stu.studentId = p.studentId
		join [Group] g on p.groupId = g.groupId
		join Session ses on ses.groupId = g.groupId
		join Room r on ses.roomId = r.roomId
		join TimeSlot ts on ses.slotId = ts.slotId
		left join Attend a on a.studentId = stu.studentId and a.sessionId = ses.sessionId
	where
		stu.studentId = @studentId
		and ses.date between @startWeek and DATEADD(day, 6, @startWeek)
end

-- drop proc getStudentTimetable
-- exec getStudentTimetable 'HE170863', '2023-3-13'


select 
	stu.studentId, stu.studentName, stu.studentImage, 
	g.groupId, g.groupName, g.courseId, g.instructorId
	ses.sessionId, ses.date, 
from Student stu
	join Participate p on stu.studentId = p.studentId
	join [Group] g on p.groupId = g.groupId
	join Session ses on ses.groupId = g.groupId
	join TimeSlot ts on 
	left join Attend a on stu.studentId = a.studentId and a.sessionId = ses.sessionId
where  
	stu.studentId = 'HE170863'
	and ses.date between @startWeek and DATEADD(day, 6, @startWeek)


