use PRJ301_TakeAttendanceSystem


/*
This procedure returns information about an instructor's schedule in a given week.
It takes 2 parameters: the date on which the week start and the instructor's ID
*/
go
create proc getWeeklySchedule
@startWeek date,
@instructorId varchar(20)
as
begin
	select * from Session s join [Group] g on s.groupId = g.groupId
	where 
		s.date between @startWeek and DATEADD(day, 6, @startWeek)
		and g.instructorId = @instructorId
	order by s.date
end
--drop proc getWeeklySchedule
--exec getWeeklySchedule '3/1/2023', 'sonnt5'


/*
This procedure returns information about a session's attendance information.
It takes 1 parameters: the id of the session
*/ go
create proc getSessionReport
@sessionId int
as
begin
	select g.groupName, a.studentId, stu.studentName, stu.studentImage, a.status, a.comment, a.recordTime
	from Attend a join Session s on a.sessionId = s.sessionId
	join [Group] g on s.groupId = g.groupId 
	join Student stu on a.studentId = stu.studentId
	where s.sessionId = @sessionId
end
--exec getSessionReport 2
--drop proc getSessionReport


/*
This procedure returns information about a group's attendance information.
It takes 1 parameters: the id of the session
*/ go
create proc getGroupReport
@groupId int
as
begin
	declare @absent int
	--set @absent = select count
	select a.studentId, stu.studentName, stu.studentImage, a.status
	from Attend a join Session s on a.sessionId = s.sessionId
	--join [Group] g on s.groupId = g.groupId 
	join Student stu on a.studentId = stu.studentId
	where s.groupId = @groupId
end

--drop proc getGroupReport
--exec getGroupReport 13

