﻿--CREATE DATABASE PRJ301_TakeAttendanceSystem
USE PRJ301_TakeAttendanceSystem

CREATE TABLE Course
(
	courseId varchar(10) NOT NULL,
	courseName nvarchar(100),
	CONSTRAINT PK_Course PRIMARY KEY (courseId)
 )

 CREATE TABLE Instructor
 (
	instructorId varchar(20) NOT NULL,
	instructorName nvarchar(100),
	instructorImage varchar(max),
	CONSTRAINT PK_Instructor PRIMARY KEY (instructorId)

)

CREATE TABLE Student
(
	studentId varchar(8) NOT NULL,
	studentName nvarchar(100),
	studentImage varchar(max),
	CONSTRAINT PK_Student PRIMARY KEY (studentId)
 )



 CREATE TABLE TimeSlot
 (
	slotId int NOT NULL,
	slotNumber int,
	startTime time(0),
	endTime time(0),
	CONSTRAINT PK_TimeSlot PRIMARY KEY (slotId)
)

CREATE TABLE Room
(
	roomId varchar(10) NOT NULL,
	CONSTRAINT PK_Room PRIMARY KEY (roomId)
)

CREATE TABLE [Group]
(
	groupId int NOT NULL,
	groupName varchar(20) NULL,
	courseId varchar(10) NULL,
	instructorId varchar(20) NULL,
	CONSTRAINT PK_Group PRIMARY KEY (groupId)
)
ALTER TABLE [Group] ADD CONSTRAINT FK_Group_Course FOREIGN KEY(courseId)
REFERENCES Course (courseId)
ALTER TABLE [Group] ADD CONSTRAINT FK_Group_Instructor FOREIGN KEY(instructorId)
REFERENCES Instructor (instructorId)

 CREATE TABLE Participate
 (
	groupId int NOT NULL,
	studentId varchar(8) NOT NULL,
	CONSTRAINT PK_Participate PRIMARY KEY (groupId, studentId)
)

ALTER TABLE Participate ADD CONSTRAINT FK_Participate_Group FOREIGN KEY(groupId)
REFERENCES [Group] (groupId)
ALTER TABLE Participate ADD CONSTRAINT FK_Participate_Student FOREIGN KEY(studentId)
REFERENCES Student (studentId)

 CREATE TABLE [Session]
 (
	sessionId int NOT NULL,
	sessionName nvarchar(200) NULL,
	[date] date NULL, 
	slotId int NULL,
	lecturerId varchar(20) NULL,
	groupId int NULL,
	roomId varchar(10) NULL,
	CONSTRAINT PK_Session PRIMARY KEY (sessionId)
)
ALTER TABLE [Session] ADD CONSTRAINT FK_Session_Group FOREIGN KEY(groupId)
REFERENCES [Group] (groupId)
ALTER TABLE [Session] ADD CONSTRAINT FK_Session_Room FOREIGN KEY(roomId)
REFERENCES Room (roomId)
ALTER TABLE [Session] ADD CONSTRAINT FK_Session_TimeSlot FOREIGN KEY(slotId)
REFERENCES TimeSlot (slotId)
ALTER TABLE [Session] ADD CONSTRAINT FK_Session_Instructor FOREIGN KEY(lecturerId)
REFERENCES Instructor(instructorId)

ALTER TABLE [Session] ADD status bit
UPDATE [Session] SET [status] = 1
UPDATE [Session] SET [status] = 0 WHERE date > '2023-03-13'

ALTER TABLE Attend ADD firstTaken int
-- ALTER TABLE Attend DROP COLUMN firstTaken
UPDATE Attend SET firstTaken = 1
UPDATE Attend SET firstTaken = 1 where recordTime < '2023-03-14 00:00:00'
UPDATE Attend SET firstTaken = 1 where sessionId in (122, 123, 124, 125)
UPDATE Attend SET firstTaken = 1 where sessionId in (113, 114, 115)


CREATE TABLE Attend
(
	studentId varchar(8) NOT NULL,
	sessionId int NOT NULL,
	[status] bit NULL,
	recordTime datetime NULL,
	comment nvarchar(max),
	CONSTRAINT PK_Attend PRIMARY KEY (studentId, sessionId)
)
ALTER TABLE Attend ADD CONSTRAINT FK_Attend_Session FOREIGN KEY(sessionId)
REFERENCES [Session] (sessionId)
ALTER TABLE Attend ADD CONSTRAINT FK_Attend_Student FOREIGN KEY(studentId)
REFERENCES Student (studentId)

CREATE TABLE [User]
(
	username varchar(50) NOT NULL,
	password varchar(50),
	displayname nvarchar(50),
	CONSTRAINT PK_User PRIMARY KEY (username)
)

CREATE TABLE Role
(
	roleId int NOT NULL,
	roleName varchar(50),
	CONSTRAINT PK_Role PRIMARY KEY (roleId)
)


CREATE TABLE Feature
(
	featureId int NOT NULL,
	featureUrl varchar(50),
	featureName varchar(50),
	CONSTRAINT PK_Feature PRIMARY KEY (featureId)
)


CREATE TABLE HasRole
(
	userId varchar(50) NOT NULL,
	roleId int NOT NULL,
	CONSTRAINT PK_HasRole PRIMARY KEY (userId, roleId)
)
ALTER TABLE HasRole ADD CONSTRAINT FK_HasRole_User FOREIGN KEY (userId)
REFERENCES [User] (username)
ALTER TABLE HasRole ADD CONSTRAINT FK_HasRole_Role FOREIGN KEY (roleId)
REFERENCES Role (roleId)


CREATE TABLE MapRoleFeature
(
	roleId int NOT NULL,
	featureId int NOT NULL,
	CONSTRAINT PK_MapRoleFeature PRIMARY KEY (roleId, featureId)
)
ALTER TABLE MapRoleFeature ADD CONSTRAINT FK_MapRoleFeature_Role FOREIGN KEY (roleId)
REFERENCES Role (roleId)
ALTER TABLE MapRoleFeature ADD CONSTRAINT FK_MapRoleFeature_Feature FOREIGN KEY (featureId)
REFERENCES Feature (featureId)




/*
DROP TABLE Attend
DROP TABLE Participate
DROP TABLE [Session]
DROP TABLE [Group]
DROP TABLE Student
DROP TABLE Room
DROP TABLE Instructor
DROP TABLE TimeSlot
DROP TABLE Course

DROP TABLE MapRoleFeature
DROP TABLE HasRole
DROP TABLE Feature
DROP TABLE Role
DROP TABLE [User]
*/

/*
SELECT * FROM Student
SELECT * FROM Participate
SELECT * FROM [Session]
SELECT * FROM [Group]
SELECT * FROM Room
SELECT * FROM Instructor
SELECT * FROM TimeSlot
SELECT * FROM Attend
SELECT * FROM Course

SELECT * FROM [User]
SELECT * FROM Role
SELECT * FROM Feature
SELECT * FROM HasRole
role

--	Thứ tự xóa bảng 
DELETE FROM Attend
DELETE FROM Participate
DELETE FROM [Session]
DELETE FROM [Group]
DELETE FROM Student
DELETE FROM Room
DELETE FROM Instructor
DELETE FROM TimeSlot
DELETE FROM Course

DELETE FROM MapRoleFeature
DELETE FROM HasRole
DELETE FROM [Feature]
DELETE FROM [Role]
DELETE FROM [User]
*/











/*



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
--exec getSessionReport 1
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




*/