use quanlydiemthi ;
select *  from quanlydiemthi.student;
select subjectId  'mã môn học ' , subjectName 'tên môn học' from quanlydiemthi.subject where 
subject.priority = 1 ;
select studentName `tên sinh viên`
,studentId `mã sinh viên` 
,year(curdate()) - year(birthday) `tuổi` 
,if(gender=1,'Nam','Nữ') `Giới tính` 
,phoneNumber `Số điện thoai`
from student ;
select studentName 'tên sinh viên' , subjectName 'tên môn học ', point ' diểm thi ' from mark 
join `subject` on subject.subjectId = mark.subjectId join  student On student.studentId =mark.studentId
where subject.subjectName = 'toán'
  order by mark.point desc;
  select if(gender = 1,'Nam','nữ ') 'giới tính ', count(studentId) 'số lượng ' from student group by gender ;
  SELECT student.studentId  'Mã học sinh',studentName  'Tên học sinh',SUM(mark.point)  'Tổng điểm',AVG(mark.point)  'Điểm trung bình'
FROM mark
JOIN student ON student.studentId = mark.studentId
GROUP BY student.studentId, student.studentName
order by avg(mark.point),sum(mark.point), studentName desc;

