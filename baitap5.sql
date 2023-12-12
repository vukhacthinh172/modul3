use quanlydiemthi ;
create view view_student as 
select s.studentId,s.studentName,s.gender,s.address from student s ;
create view AVERAGE_MARK_VIEW as 
select   s.studentId,s.studentName, avg(m.point) from student s join mark m on s.studentId = m.studentId
join subject sb on sb.subjectId = m.subjectId 
group by s.studentId;
--  drop view AVERAGE_MARK_VIEW;
create index index_phoneNumber on student(phoneNumber);
-- Đổi tên chỉ mục
ALTER TABLE quanlydiemthi.student RENAME INDEX index_phoneNumber TO new_index_phoneNumber;
-- Gọi stored procedure
DELIMITER //
CREATE PROCEDURE PROC_INSERTSTUDENT(
    IN p_studentId VARCHAR(4),
    IN p_studentName VARCHAR(100),
    IN p_birthday DATE,
    IN p_gender BIT,
    IN p_phoneNumber VARCHAR(45),
    IN p_address TEXT
)
BEGIN
    INSERT INTO student (studentId, studentName, birthday, gender, phoneNumber, address)
    VALUES (p_studentId, p_studentName, p_birthday, p_gender, p_phoneNumber, p_address);
END //

DELIMITER ;

-- Gọi stored procedure
CALL PROC_INSERTSTUDENT('D120', 'John Doe', '2000-01-01', '1', '123456789', '123 Main Street');

delimiter //
create procedure PROC_UPDATESUBJECT(
    IN p_subjectCode VARCHAR(10),
    IN p_newSubjectName VARCHAR(100)
)
begin update subject set subjectName = p_newsubjectName 
where subjectId = p_subjectCode ;
end //
create procedure PROC_DELETEMARK(
    IN p_studentId VARCHAR(4)
)
begin 
delete from mark 
where studentId = p_studentId ;
select row_count() deleteRecords ;
end //
delimiter ;
CALL PROC_UPDATESUBJECT('MATH101', 'New Math Subject');

-- Gọi stored procedure xoá toàn bộ điểm các môn học của học sinh
CALL PROC_DELETEMARK('D120');

-- DROP PROCEDURE IF EXISTS PROC_UPDATESUBJECT;
-- DROP PROCEDURE IF EXISTS PROC_DELETEMARK;
